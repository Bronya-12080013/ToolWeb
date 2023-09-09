package com.lan.website.controller;


import com.lan.website.mbg.model.*;
import com.lan.website.service.*;
import com.lan.website.util.DateUtil;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * ModelAndView有必要吗
 * 之后我再改成thymeleaf形式试试
 * ===============================
 * 写完了
 * 注意一点！ 方法的GET或POST方法一定要写对，否则方法是无法实现的
 * 下面的方法我还没全部测试完
 */


@Api(tags = "UserController", description = "用户管理")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private MessageService messageService;

    @Autowired
    private ReviewService reviewService;

    /**
     *普通日志写入
     *
     * @param loginUser
     * @param ip
     * @param type
     */
    public void setLog(User loginUser,String ip, String type){
        Log log = new Log();
        log.setUserid(loginUser.getId());
        log.setUsername(loginUser.getUsername());
        log.setIp(ip);
        log.setType(type);
        logService.insert(log);
    }

    @ApiOperation(value = "起始页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String logout() {
        return "user/index";
    }
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session, HttpServletRequest req){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("username",user.getUsername());
        paramMap.put("password",user.getPassword());
        User loginUser = userService.selectLoginUser(paramMap);
        if(loginUser==null){
            return "user/login"; //返回视图 目前未写
        }
        //request.getRemoteAddr():获得IP地址
        setLog(loginUser,req.getRemoteAddr(),"登录");
        //如果有vip但过时了，给他更新一下
        if(loginUser.getVip()!=null&&loginUser.getVip().getTime()<new Date().getTime()){
            loginUser.setVip(null);
            userService.updateByPrimaryKeySelective(loginUser);
        }
        session.setAttribute("loginUser",loginUser);
        return "redirect:/user/course"; //重定向到下面的请求去
    }

    @ApiOperation(value = "用户注销")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(String type,User user,HttpSession session,HttpServletRequest req){
        User loginUser =(User) session.getAttribute("loginUser");
        if(loginUser==null){
            return "user/login"; //要用到前端再自行处理吧 springboot用templates的话，应该不用视图解析器，记得导入thymeleaf依赖
        }else {
            session.invalidate();//删除session  https://blog.csdn.net/weixin_37673550/article/details/70224410
            if(type=="admin"){
                setLog(loginUser,req.getRemoteAddr(),"管理员注销");
                return "user/loginAdmin";
            }else setLog(loginUser,req.getRemoteAddr(),"注销");//request.getRemoteAddr():获得IP地址
            return "redirect:/user/index"; //重定向! 不是像上面那样转到某个页面html，而是转到另一个请求(controller请求)
        }
    }


    /*
    ajax密码检查
     */
    @ApiOperation(value = "ajax密码检查")
    @RequestMapping(value = "/passwordCheck", method = RequestMethod.POST)
    public void selectUser(User user, HttpServletResponse response,HttpServletRequest req) throws IOException {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("username",user.getUsername());
        paramMap.put("password",user.getPassword());
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if(userService.selectUser(paramMap)==1){
            user=userService.selectLoginUser(paramMap);
            //mission 使命; 使团; 官方使命; 使团的使命; 代表团; 执行任务的地点; (尤指在海外的)传教; 布道; 布道团; 布道所; 军事行动; 太空飞行任务; 任务，旅行;
            if(!"admin".equals(user.getMission())&&!"showAdmin".equals(user.getMission())){ //mission是user表的属性
                if(user.getBuycase()!=null){
                    if("1".equals(user.getBuycase())){
                        out.println("3"); //屏蔽登录
                    }else out.println("1");//正常登录密码正确
                }else {
                    out.println("1");
                }
            }else {
                out.println("2");
            }
        }else {
            Log log = new Log();
            log.setIp(req.getRemoteAddr());//获取ip地址
            log.setType("尝试登录账号:"+user.getUsername()+",密码错误");
            logService.insert(log);
            out.println("0"); //密码错误返回值
        }
    }

    // 注册检查
    @ApiOperation(value = "注册检查")
    @RequestMapping(value = "/userCheck", method = RequestMethod.GET)
    public void UserCheck(String username,HttpSession session,HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        int i = userService.selectUser(username);
        out.println(i);
    }

    @ApiOperation(value = "快速注册")
    @RequestMapping(value = "/quickRegist", method = RequestMethod.POST)
    public ModelAndView insertUser(String varCode,User user,HttpSession session,HttpServletRequest req,ModelAndView mav){
        String id = DateUtil.getId();
        String username = user.getUsername();
        // ModelAndView   mav.setViewName()  https://blog.csdn.net/csucsgoat/article/details/120789171
        mav.setViewName("redirect:/user/course");
        if(varCode==null){
            return mav;
        }
        if(userService.selectUser(username)==1||!CaptchaUtil.ver(varCode,req)) {
            return mav;
        }
        user.setId(id);
        user.setMission(null);
        user.setBuycase(null);
        user.setMycase(null);
        user.setVip(null);
        userService.insertSelective(user);
        session.setAttribute("loginUser",user);
        setLog(user,req.getRemoteAddr(),"快速注册");
        return mav;
    }

    @ApiOperation(value = "普通注册") //与上面的快速注册相比，仅少了一句session.setAttribute("loginUser",user);
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ModelAndView regist(ModelAndView mav, String varCode, User user, HttpSession session, HttpServletRequest req) {
        String id = DateUtil.getId();
        String username = user.getUsername();
        // ModelAndView   mav.setViewName()  https://blog.csdn.net/csucsgoat/article/details/120789171
        mav.setViewName("redirect:/user/course");
        if(varCode==null){
            return mav;
        }
        if(userService.selectUser(username)==1||!CaptchaUtil.ver(varCode,req)) {
            return mav;
        }
        user.setId(id);
        user.setMission(null);
        user.setBuycase(null);
        user.setMycase(null);
        user.setVip(null);
        userService.insertSelective(user);
        setLog(user,req.getRemoteAddr(),"快速注册");
        return mav;
    }


    @ApiOperation(value = "登录页")
    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public ModelAndView registerPage(ModelAndView mav, String varCode, User user, HttpSession session, HttpServletRequest req) {
        User loginUser = (User) session.getAttribute("loginUser");
        //我觉得作者写得太别扭了 自己改改吧
        if(loginUser==null){ //如果目前没登录者 就让他登录注册去
            mav.setViewName("user/regist");
            return mav;
        }else {         //如果已经登录了 就自己上course
            mav.setViewName("redirect:/user/course");
            return mav;
        }
    }


    //暂时看不出来这玩意的用处
    @ApiOperation(value = "会员中心")
    @RequestMapping(value = "/showVip", method = RequestMethod.GET)
    public ModelAndView showVip(HttpSession session,ModelAndView mav){
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser!=null)
        {
            loginUser = userService.selectByPrimaryKey(loginUser.getId());
            session.setAttribute("loginUser",loginUser);
        }
        mav.setViewName("user/vip");
        return mav;
    }

    @ApiOperation(value = "我的课程查询")
    @RequestMapping(value = "/MyLearn", method = RequestMethod.GET)
    public ModelAndView myCourse(HttpSession session,ModelAndView mav){
            User loginUser = (User) session.getAttribute("loginUser");
            if(loginUser==null)
            {
                mav.setViewName("user/login");
                return mav;
            }else {
                List<Course> courseList= new ArrayList();
                List<Message> messageList = messageService.selectMsgById(loginUser.getId());
                for (int i=0;i<messageList.size();i++)
                {
                    int id = messageList.get(i).getKeyId();
                    Course course = courseService.selectByPrimaryKey(id);
                    courseList.add(course);
                }
                mav.addObject("myCourses",courseList);
                mav.setViewName("user/myLearn");
                return mav;
            }
    }

    @ApiOperation(value = "主页课程查询")
    @RequestMapping(value = "/course", method = RequestMethod.GET) //注意这里要用正确的请求方式，如果用POST的话可能会报405错误的
    public String Course(HttpSession session) {
        Map map = new HashMap();
        List<Course> courseList = courseService.selectAllCourse();
        map.put("courses",courseList);
        return "user/courseIndex";
    }

    @ApiOperation(value = "单课程主页")
    @RequestMapping(value = "/courseIndex", method = RequestMethod.GET)
    public ModelAndView CourseIndex(int id,HttpSession session,ModelAndView mav)
    {
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null){
            mav.setViewName("user/login");
            return mav;
        }
        Message message = new Message();
        message.setCourseid(id);
        message.setUserid(loginUser.getId());
        Message myMessage = messageService.select(message);
        if(myMessage==null){
            mav.addObject("isSelect",false);
        }else {
            mav.addObject("isSelect",true);
        }
        Course course = courseService.selectByPrimaryKey(id);
        mav.addObject("course",course);
        mav.setViewName("courseDetail");
        return mav;
    }

    @ApiOperation(value = "单课程视频")
    @RequestMapping(value = "/courseVideo", method = RequestMethod.GET)
    public String CourseVideo(int courseId,HttpSession session,Map map){
        User loginUser  = (User) session.getAttribute("loginUser");
        if(loginUser==null){
            return "user/login";
        }
        Course course = courseService.selectByPrimaryKey(courseId);
        //判断是不是vip课程
        if("1".equals(course.getType())){
            if(loginUser.getVip()==null){
                return "user/vip";
            }
        }
        map.put("course",course);
        List<Review> reviews =reviewService.select(courseId);
        map.put("reviews",reviews);
        return "user/courseVideo";
    }

    @ApiOperation(value = "加入课程") //在message做个记录
    @RequestMapping(value = "/insertCourse", method = RequestMethod.POST)
    public void insertCourse(int courseId,String userId,HttpSession session,HttpServletRequest req,HttpServletResponse response) throws IOException{
        String result = "订阅成功！";
        User user = (User) session.getAttribute("loginUser");
        Course course = courseService.selectByPrimaryKey(courseId);
        if(user.getVip()==null&&"1".equals(course.getType())){
            result="此课程是会员课程，请购买会员!";
        }else {
            Message message = new Message();
            message.setCourseid(courseId);
            message.setUserid(userId);
            messageService.insert(message);
            setLog(user,req.getRemoteAddr(),"订阅课程："+course.getName()); //req.getRemoteAddr()获得ip
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
    }

    @ApiOperation(value = "删除课程")
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
    public String deleteCourse(int courseId, String userId, HttpServletResponse response, HttpServletRequest req)
            throws IOException {
        Message message = new Message();
        message.setCourseid(courseId);
        message.setUserid(userId);
        int bool = messageService.delete(message); //顺着作者
        User loginUser = userService.selectByPrimaryKey(userId);
        Course course = courseService.selectByPrimaryKey(courseId);
        setLog(loginUser,req.getRemoteAddr(),"取消课程:"+course.getName()); //eq.getRemoteAddr()获取ip
        String result = bool>0?"true":"false";
        return result;
    }

    @ApiOperation(value = "个人信息页")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String Info(User user,HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null){
            return "user/login";
        }
        return "user/infoSet";
    }

    @ApiOperation(value = "个人信息设置")
    @RequestMapping(value = "/infoSet", method = RequestMethod.POST)
    public String InfoSet(User user,HttpSession session,HttpServletRequest req){
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null) return "user/login";
        user.setCollect(loginUser.getCollect());

        //个人信息修改的同时更新评论的用户信息
        List<Review> reviews = reviewService.selectByUserId(loginUser.getUsername()); //这什么起名鬼才
        for (int i=0;i<reviews.size();i++){
            //这是示例吧 可以把其他信息也一起改了 这里只写一个
            reviews.get(i).setSex(user.getSex());
        }
        reviewService.updateByPrimaryKeySelective(reviews);//更新

        userService.updateByPrimaryKeySelective(user);
        Map map = new HashMap<String,String>();
        map.put("username",loginUser.getUsername());
        map.put("password",loginUser.getPassword());
        session.setAttribute("loginUser",userService.selectLoginUser(map));
        setLog(loginUser,req.getRemoteAddr(),"个人信息更改");
        return "redirect:/user/course";



    }

    //collect 应该是表示余额的意思 vipType为要购买的vip类型
    @ApiOperation(value = " vip购买") //0为1个月，1为半年，2为一年
    @RequestMapping(value = "/vip",method = RequestMethod.POST)
    public String Vip(HttpSession session,int vipType,HttpServletResponse response,HttpServletRequest req) throws IOException
    {
        String data = "已经充值成功";
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null) return "user/login";
        List<Review> reviews = reviewService.selectByUserId(loginUser.getUsername()); //命名鬼才
        int collect = loginUser.getCollect();
        boolean isVip = false; //woc 作者会用boolean了(还是之前到底有什么地方必须用1和0呢 难度数据库mysql不能设类似boolean的数据类型吗)
        Date date = new Date();
        Date vipDate = loginUser.getVip(); //User的vip属性是datetime属性的(数据库类型)，表示到期时间
        if(vipDate==null||vipDate.getTime()<date.getTime()){
            loginUser.setVip(new Date()); //(这里是先给他更新好新时间，但还要根据下面的判断，才判定成不成功，成功就给他更新到表里，不成功就算了) 充vip后，时间是从现在开始算起，而不是从之前的截止时间算
        }
        switch (vipType){
            default:
                data = "请求错误";
                break;
            case 0:
                if(collect<500){
                    data = "钱钱不够惹";
                }else {
                    loginUser.setCollect(collect-500);
                    vipDate = loginUser.getVip();
                    vipDate.setMonth(vipDate.getMonth()+1);
                    loginUser.setVip(vipDate);
                    isVip=true;
                    setLog(loginUser,req.getRemoteAddr(),"购买会员:一个月");
                }
            case 1:
                if (collect < 2000) {
                    data = "余额不足，请联系管理员充值！";
                } else {
                    loginUser.setCollect(collect - 2000);
                    vipDate = loginUser.getVip();
                    vipDate.setMonth(vipDate.getMonth() + 6);
                    loginUser.setVip(vipDate);
                    isVip = true;
                    setLog(loginUser, req.getRemoteAddr(), "购买会员：半年");
                }
                break;
            case 2:
                if (collect < 3000) {
                    data = "余额不足，请联系管理员充值！";
                } else {
                    loginUser.setCollect(collect - 3000);
                    vipDate = loginUser.getVip();
                    vipDate.setYear(vipDate.getYear() + 1);
                    loginUser.setVip(vipDate);
                    isVip = true;
                    setLog(loginUser, req.getRemoteAddr(), "购买会员：一年");
                }
                break;
        }
        //评论的vip也要刷新
        if(isVip){
            for(int i=0;i<reviews.size();i++){
                reviews.get(i).setVip(1);
            }
        }
        reviewService.updateByPrimaryKeySelective(reviews);
        userService.updateByPrimaryKeySelective(loginUser);
        Map map = new HashMap<String,String>();
        map.put("username",loginUser.getUsername());
        map.put("password",loginUser.getPassword());
        session.setAttribute("loginUser",userService.selectLoginUser(map));

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(data);
        return null;
    }
}
