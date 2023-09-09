package com.lan.website.controller;


import com.lan.website.mbg.model.*;
import com.lan.website.service.*;
import com.lan.website.util.DateUtil;
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

@Api(tags = "AdminController", description = "后端管理")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	LogService logService;
	@Autowired
	CourseService courseService;
	@Autowired
	IpsetService ipsetService;
	@Autowired
	MessageService messageService;
	public void setLog(User loginUser, String ip, String type, String adminname){ //设置日志
		Log log = new Log();
		log.setUserid(loginUser.getId());
		log.setUsername(loginUser.getUsername());
		log.setIp(ip);
		log.setType(type);
		log.setExecutor(adminname);
		logService.insert(log);
	}
	@ApiOperation(value = "管理员首页")
	@RequestMapping(value = "/adminIndex", method = RequestMethod.GET)
	public String adminIndex(HttpSession session){
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			//没登录
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//添加管理员的再次验证
		session.setAttribute("loginUser", loginUser);
		return "redirect:/user/course"; //UserController里的请求
		}

		return "admin/adminIndex";
	}

	@ApiOperation(value = "管理员登录")
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public String adminLogin(User user, HttpSession session,HttpServletRequest req) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", user.getUsername());
		paramMap.put("password", user.getPassword());
		User loginUser = userService.selectLoginUser(paramMap);
		if (loginUser == null) { 
			//未登录
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			// 不是admin也不是showAdmin
			//添加管理员登录的再次验证，防止直接跳过前端验证进行强制登录
		session.setAttribute("loginUser", loginUser);
		Log log = new Log();
		log.setUserid(loginUser.getId());
		log.setUsername(loginUser.getUsername());
		log.setIp(req.getRemoteAddr());
		log.setType("用户尝试强制登录管理员页面");
		logService.insert(log);
		return "redirect:/user/course";
		}else{
			//通过
		session.setAttribute("loginUser", loginUser);
		setLog(loginUser, req.getRemoteAddr(),"登录", loginUser.getUsername());
		return "admin/leftMenu";
		
		}
	}
	@ApiOperation(value = "展示所有用户")
	@RequestMapping(value = "/allUser", method = RequestMethod.GET)
	public ModelAndView allUser(ModelAndView mav, int page, HttpSession session) { //注意，参数有指定page
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			mav.setViewName("user/login");
			return mav;
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//身份不对
			//添加管理员的再次验证
			mav.setViewName("redirect:/user/course");
			return mav;
		}else{
			//身份对了，可以展示
			List<User> userList = userService.selectAllUser();
			int totalPage = 14;//一页的数量
			List<User> users = new ArrayList<User>();
			mav.addObject("maxPage", (userList.size()-1)/totalPage);
			for(int i = page*totalPage;i<page*totalPage+totalPage;i++){
				if(userList.size()==i){
					mav.addObject("users", users);
					mav.addObject("page", page);
					mav.setViewName("admin/allUser");
					return mav;
				}
				users.add(userList.get(i));
			}
			mav.addObject("page", page);
			mav.addObject("loginUser", loginUser);
			mav.addObject("users", users);
			mav.setViewName("admin/allUser");
		    return mav;
		
		}
	}
	@ApiOperation(value = "屏蔽和恢复用户")
	@RequestMapping(value = "/banUser", method = RequestMethod.POST)
	public String banUser(String userId,int type, HttpSession session,HttpServletRequest req) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())){
			//身份不匹配
			//添加管理员的再次验证
		session.setAttribute("loginUser", loginUser);
		return "redirect:/user/course";
		}else{
			//成功验证，开始任务
			if(type==0){//0为屏蔽用户
				User user = userService.selectByPrimaryKey(userId);
				user.setBuycase("1");
				userService.updateByPrimaryKeySelective(user);
				setLog(user,req.getRemoteAddr(),"屏蔽用户登录",loginUser.getUsername());
				return "redirect:/admin/allUser"+"?page=0";
			}
			if(type==1){//1为恢复用户
				User user = userService.selectByPrimaryKey(userId);
				user.setBuycase("0");
				userService.updateByPrimaryKeySelective(user);
				setLog(user,req.getRemoteAddr(),"恢复用户登录",loginUser.getUsername());
				return "redirect:/admin/allUser"+"?page=0";
			}
		
		}
		return "redirect:/admin/allUser"+"?page=0";
	}
	@ApiOperation(value = "充值余额界面")
	@RequestMapping(value = "/reChargeIndex", method = RequestMethod.GET)
	public String reChargeIndex( HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//身份不对
			//添加管理员的再次验证
		return "redirect:/user/course";
		}
		return "admin/reCharge";
	}


	/**
	 *
	 * @param userid 要充给的账号id
	 * @param collect 要充的钱
	 * @throws IOException
	 */
	@ApiOperation(value = "充值")
	@RequestMapping(value = "/reCharge", method = RequestMethod.POST)
	public void recharge(String userid,int collect, String RechargePassword,HttpServletRequest req,
			HttpSession session,HttpServletResponse resp) throws IOException {
		User loginUser = (User) session.getAttribute("loginUser"); //loginUser是自己已经登录的账号
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		User user = userService.selectByPrimaryKey(userid);  //user是要充值钱进去的账号
		if(user==null){
			pw.print("用户ID不存在！请核实后再充值");
		}else if(!RechargePassword.equals("114514")){ //官网指定支付密码,至于怎么充钱得密码，就是具体的问题了。可以当成卡密。这里随便写个吧
			pw.print("0");
		}else{
			//验证成，执行
			user.setCollect(user.getCollect()+collect);//余额增加
			userService.updateByPrimaryKeySelective(user); //up噗跌多
			setLog(user,req.getRemoteAddr(),"充值"+collect+"元",loginUser.getUsername());
			pw.print("账户"+userid+",充值"+collect+"元成功，余额："+user.getCollect());
		}
	}

	@ApiOperation(value = "新建用户界面，用户详情界面")
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public String newUser(String userid, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//添加管理员的再次验证
		return "redirect:/user/course";
		}
		if(userid!=null){
			User user = userService.selectByPrimaryKey(userid);
			session.setAttribute("user", user);
		}else{
			session.removeAttribute("user");
		}
		return "admin/newUser";
	}

	@ApiOperation(value = "新建账户")
	@RequestMapping(value = "/newAddUser", method = RequestMethod.POST)
	public String newAddUser(User newUser,HttpSession session,HttpServletRequest req){
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())){
			//添加管理员的再次验证
			return "redirect:/user/course";
		}
		newUser.setId(DateUtil.getId());
		userService.insertSelective(newUser);
		setLog(newUser,req.getRemoteAddr(),"创建用户",loginUser.getUsername());
		return "redirect:/admin/newUser";
	}

	@ApiOperation(value = "修改账户")
	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	public String setUser(User user,HttpSession session,HttpServletRequest req){
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())){
			//添加管理员的再次验证
			return "redirect:/user/course";
		}
		user.setCollect(userService.selectByPrimaryKey(user.getId()).getCollect());
		userService.updateByPrimaryKeySelective(user);
		setLog(user,req.getRemoteAddr(),"修改用户信息",loginUser.getUsername());
		return "redirect:/admin/allUser"+"?page=0";
	}

	@ApiOperation(value = "删除用户")
	@RequestMapping(value = "/removeUser", method = RequestMethod.POST)
	public void removeUser(String userid,String removePassword,HttpSession session,HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		User loginUser = (User) session.getAttribute("loginUser");
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		if(!removePassword.equals("114514191")){ //同上。//官网指定删除密码(也可以改成管理员均可删除)。这里先随便写个
			pw.print("0");
		}else{
			User user = userService.selectByPrimaryKey(userid);
			userService.deleteByPrimaryKey(userid);
			setLog(user,req.getRemoteAddr(),"删除用户",loginUser.getUsername());
			pw.print("账户："+userid+",删除成功");
			List<User> users = userService.selectAllUser();
			session.setAttribute("users", users);
		}
	}

	@ApiOperation(value = "日志查看")
	@RequestMapping(value = "/showLog", method = RequestMethod.GET)
	public String showLog(String searchUsername,String type, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//添加管理员的再次验证
		return "redirect:/user/course";
		}
		List<Log> logs;
		if(searchUsername!=null&&type==null){
			logs = logService.selectByUsername(searchUsername);
			session.setAttribute("logs", logs);
			session.removeAttribute("type");
			session.setAttribute("logs", initLogPage(logs));
			session.setAttribute("maxPage", (logs.size()-1)/15);//10为每页个数
			session.setAttribute("page", 0);
			return "admin/log";
		}
		if(searchUsername==null&&type!=null){
			logs = logService.selectAdminLog();
			session.setAttribute("type", "admin");
			session.setAttribute("logs", logs);
			session.setAttribute("logs", initLogPage(logs));
			session.setAttribute("maxPage", (logs.size()-1)/15);
			session.setAttribute("page", 0);
			return "admin/log";
		}
		if(type==null){
			logs = logService.select();
		} else { //type!=null 存在
		    logs = logService.selectAdminLogByUsername(searchUsername);
		}
		session.removeAttribute("type");
		session.setAttribute("logs", logs);
		session.setAttribute("logs", initLogPage(logs));
		session.setAttribute("maxPage", (logs.size()-1)/15);
		session.setAttribute("page", 0);
		return "admin/log";
	}



	@ApiOperation(value = "第一次传输日志")
	@RequestMapping(value = "/initLogPage", method = RequestMethod.GET)
	public List<Log> initLogPage(List<Log> logs){//第一次传输日志
		int totalPage = 15;//一页的数量
		List<Log> logList = new ArrayList<Log>();
		for(int i = 0;i<totalPage;i++){
			if(logs.size()==i)
				return logList;
			logList.add(logs.get(i));
		}
		return logList;
	}

	@ApiOperation(value = "日志翻页")
	@RequestMapping(value = "/logPage", method = RequestMethod.GET)
	public String  logPage(int page,HttpSession session){
		List<Log> logs = (List<Log>) session.getAttribute("logs");
		int totalPage = 15;//一页的数量
		List<Log> logList = new ArrayList<Log>();
		for(int i = page*totalPage;i<page*totalPage+totalPage;i++){
			if(logs.size()==i){
				session.setAttribute("logs", logList);
				session.setAttribute("page", page);
				return "admin/log";
			}
			logList.add(logs.get(i));
		}
		session.setAttribute("logs", logList);
		session.setAttribute("page", page);
		return "admin/log";
	}

	@ApiOperation(value = "全部课程界面")
	@RequestMapping(value = "/allCourse", method = RequestMethod.GET)
	public String allCourseIndex(int page, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "user/login";
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//添加管理员的再次验证
		return "redirect:/user/course";
		}
		List<Course> courses = courseService.selectAllCourse();
		int totalPage = 14;//一页的数量
		List<Course> courseList = new ArrayList<Course>();
		session.setAttribute("maxPage", (courses.size()-1)/totalPage);
		for(int i = page*totalPage;i<page*totalPage+totalPage;i++){
			if(courses.size()==i){
				session.setAttribute("courses", courseList);
				session.setAttribute("page", page);
				return "admin/allCourse";
			}
			courseList.add(courses.get(i));
		}
		session.setAttribute("page", page);
	    session.setAttribute("courses", courseList);
		return "admin/allCourse";
	}

	@ApiOperation(value = "上下架课程")
	@RequestMapping(value = "/banCourse", method = RequestMethod.POST)
	public String banCourse(int type,int courseId, HttpSession session,HttpServletRequest req) {
		User loginUser = (User) session.getAttribute("loginUser");
		int page = (int) session.getAttribute("page");
		if (loginUser == null) {
			return "login"; 
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//添加管理员的再次验证
		return "redirect:/user/course";
		}
		//验证结束，开始执行
		Course course = courseService.selectByPrimaryKey(courseId);
		Log log = new Log();
		log.setId(courseId);
		log.setExecutor(loginUser.getUsername());
		log.setIp(req.getRemoteAddr());
		if(type==1) {//下架课程
			course.setPrice("1");
			log.setType("下架课程："+course.getName());
		}
		if(type==0) {//上架课程
			course.setPrice("0");
			log.setType("上架课程："+course.getName());
		}
		logService.insert(log);
		courseService.updateByPrimaryKeySelective(course);
		return "redirect:/admin/allCourse"+"?page="+page;
	}

	@ApiOperation(value = "删除课程")
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
	public void deleteCourse(int courseId, String removePassword,HttpSession session,HttpServletRequest req,HttpServletResponse resp) throws IOException {
		User loginUser = (User) session.getAttribute("loginUser");
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		if(!removePassword.equals("114514191")){ //默认密码，见上方的说明
			pw.print("0");
		}else{
			Course course = courseService.selectByPrimaryKey(courseId);
			courseService.deleteByPrimaryKey(String.valueOf(courseId));
			Message message = new Message();
			message.setCourseid(courseId);
			messageService.delete(message);
			Log log = new Log();
			log.setId(courseId);
			log.setExecutor(loginUser.getUsername());
			log.setIp(req.getRemoteAddr());
			log.setType("删除课程："+course.getName());
			logService.insert(log);
			pw.print("课程："+course.getName()+",删除成功！请刷新页面后操作");
		}
	}
	@ApiOperation(value = "展示所有IP信息")
	@RequestMapping(value = "/allIp", method = RequestMethod.GET)
	public String allIp(int page, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "login"; 
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//添加管理员的再次验证
		session.setAttribute("loginUser", loginUser);
		return "redirect:/user/course";
		}else{
			List<Ipset> ipsetList = ipsetService.select();
			int totalPage = 14;//一页的数量
			List<Ipset> ips = new ArrayList<Ipset>();
			session.setAttribute("maxPage", (ipsetList.size()-1)/totalPage);
			for(int i = page*totalPage;i<page*totalPage+totalPage;i++){
				if(ipsetList.size()==i){
					session.setAttribute("ips", ips);
					session.setAttribute("page", page);
					return "admin/allIp";
				}
				ips.add(ipsetList.get(i));
			}
			session.setAttribute("page", page);
		    session.setAttribute("ips", ips);
		    return "admin/allIp";
		}
		}

	@ApiOperation(value = "ip管理")
	@RequestMapping(value = "/ipset", method = RequestMethod.POST)
	public String ipset(HttpSession session,String ip,String onBaned){ //onBand,在ban中
		if(onBaned!=null) {
			Ipset ip1 = ipsetService.selectip(ip);
			ip1.setType("0");
			ip1.setBantime(null);
			ipsetService.updateByPrimaryKeySelective(ip1);
			return "redirect:/admin/allIp"+"?page=0";
		}
		
		session.setAttribute("ip", ipsetService.selectip(ip));
		return "admin/ipset";
	}

	//time是封禁时间
	@ApiOperation(value = "封禁ip")
	@RequestMapping(value = "/banIp", method = RequestMethod.POST)
	public void banIp(HttpServletResponse resp,HttpSession session,String ip,String mark,String time) throws IOException{
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return ;
		}else if(!"admin".equals(loginUser.getMission())){
			//添加管理员的再次验证
			return ;
		}
		Date date = new Date();
		Ipset ip1 = ipsetService.selectip(ip);
		boolean isNull = false;
		if(ip1==null) {
			ip1=new Ipset();
			ip1.setIp(ip);
			isNull =true;
		}
		ip1.setIp(ip);
		ip1.setMark(mark);
		ip1.setType("1");
		switch (time) {
			case "5m":
				if (date.getMinutes() > 55) {
					date.setMinutes(date.getMinutes() - 55);
					date.setHours(date.getHours() + 1);
				} else {
					date.setMinutes(date.getMinutes() + 5);
				}
				ip1.setBantime(date);
				break;
			case "2h":
				date.setHours(date.getHours() + 2);
				ip1.setBantime(date);
				break;
			case "1d":
				date.setDate(date.getDate() + 1);
				ip1.setBantime(date);
				break;
			case "1m":
				date.setMonth(date.getMonth() + 1);
				ip1.setBantime(date);
				break;
			case "1y":
				date.setYear(date.getYear() + 1);
				ip1.setBantime(date);
				break;
			case "ever":
				date.setYear(date.getYear() + 99);
				ip1.setBantime(date);
				break;
		}
		if(isNull) {
			ipsetService.insert(ip1);
		}else {
		ipsetService.updateByPrimaryKeySelective(ip1);
		}
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write("封禁成功！封禁至："+date);
	}


	@ApiOperation(value = "管理员注销") //注销是退出账号登录，不是销号
	@RequestMapping(value = "/logoutAdmin", method = RequestMethod.GET)
	public String logoutAdmin(HttpSession session,HttpServletRequest req){
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null){
			return "user/loginAdmin";
		}
		session.invalidate(); session.invalidate(); //它实际上调用的是session对象中的destroy方法
		setLog(loginUser, req.getRemoteAddr(),"注销", loginUser.getUsername());
		return "user/loginAdmin";
	}

	@ApiOperation(value = "课程详情界面")
	@RequestMapping(value = "/courseMes", method = RequestMethod.GET)
	public String courseMes(String courseId, HttpSession session) {
		session.removeAttribute("msg");
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "login"; 
		}else if(!"admin".equals(loginUser.getMission())&&!"showAdmin".equals(loginUser.getMission())){
			//添加管理员的再次验证
		return "redirect:/user/course";
		}
		if(courseId!=null) {
			Course course = courseService.selectByPrimaryKey(Integer.parseInt(courseId));
			session.setAttribute("course", course);
			return "admin/course";
		}
		    session.removeAttribute("course");
			return "admin/course";
		
	}

	@ApiOperation(value = "课程上传/修改")
	@RequestMapping(value = "/courseSave", method = RequestMethod.POST)
	public String courseSave(HttpServletRequest req, HttpSession session) {
		session.removeAttribute("msg");
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "login"; 
		}else if(!"admin".equals(loginUser.getMission())){
			//添加管理员的再次验证
		return "redirect:/user/course";
		}
		//注意，CourseService.saveCourse用了mapper的复数方法
		courseService.saveCourse(req);
		session.setAttribute("msg", "操作成功");
			return "admin/course";
		
	}

}
