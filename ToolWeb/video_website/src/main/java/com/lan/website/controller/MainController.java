package com.lan.website.controller;


import com.lan.website.mbg.model.Course;
import com.lan.website.mbg.model.Log;
import com.lan.website.mbg.model.Review;
import com.lan.website.mbg.model.User;
import com.lan.website.service.*;
import com.lan.website.util.DateUtil;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Api(tags = "MainController", description = "主管理")
@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	MessageService messageService;
	@Autowired
	LogService logService;

	//设置日志
	public void setLog(User loginUser, String ip, String type) {
		Log log = new Log();
		log.setUserid(loginUser.getId());
		log.setUsername(loginUser.getUsername());
		log.setIp(ip);
		log.setType(type);
		logService.insert(log);
	}


	/** 图形验证码工具类 CaptchaUtil
	 *
	 *
	public static boolean ver(String code, HttpServletRequest request) {
		if (code != null) {
			String captcha = (String) request.getSession().getAttribute(SESSION_KEY);
			return code.trim().toLowerCase().equals(captcha);
		}
		return false;
	}
	 */
	@ApiOperation(value = "验证码验证")
	@RequestMapping(value = "/varCodeCheck", method = RequestMethod.GET)
	public void varCodeCheck(String varCode,HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("utf-8");
		PrintWriter pw = res.getWriter();
		if(!CaptchaUtil.ver(varCode, req)){
			pw.write("0");
		}
	}

	/*
	Gif验证码类   GifCaptcha
	 */
	@ApiOperation(value = "更换验证码，验证码显示")
	@RequestMapping(value = "/changeVarCode", method = RequestMethod.GET)
	public void changeVarCode(HttpServletRequest req,HttpServletResponse res) throws IOException, FontFormatException {
        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(130,48,4);
        gifCaptcha.setFont(gifCaptcha.FONT_7); //设置字体（有内置字体）
		CaptchaUtil.out(gifCaptcha,req, res); //输出验证码
	}

	@ApiOperation(value = "管理员登录入口")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(HttpSession session) {
		return "user/loginAdmin";
	}


	@ApiOperation(value = "列出所有课程")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		List<Course> freeCourses = courseService.freeCourse(); //select免费的课堂
		List<Course> vipCourses = courseService.selectVipCourse(); //select vip的课程
		mav.addObject("freeCourses", freeCourses);
		mav.addObject("vipCourses", vipCourses);
		mav.setViewName("main/index");
		return mav;
	}


	//**  avgable 拼错了 是 avgabel
	@ApiOperation(value = "提交评论")
	@RequestMapping(value = "/subReview", method = RequestMethod.POST)
	public String subReview(HttpSession session, Review review, HttpServletRequest req) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) { //没登录的去登录
			return "main/login";
		}
		//看看是不是vip
		int vip ;
		if(loginUser.getVip()!=null){
			vip=1;
		}else{
			vip=0;
		}

		//先更新评论，再因为评论（评分的更新）而导致课程平均评分的更新而更新课程Course
		review.setVip(vip);
		review.setSex(loginUser.getSex());
		review.setReviewid(DateUtil.getId());
		review.setUsername(loginUser.getUsername());
		reviewService.insert(review);


		Course course = new Course();
		course.setId(review.getCourseid());
		course.setLabel(reviewService.avgLable(review.getCourseid())); //reviewService.avgLable() 返回String形式的“平均评价/分数“
		courseService.updateByPrimaryKeySelective(course); //更新课程


		setLog(loginUser, req.getRemoteAddr(), "发表评论，在'"+courseService.selectByPrimaryKey(review.getCourseid()).getName() +"'");
		return "redirect:/user/courseVideo"+"?courseId=" + review.getCourseid();

	}

	@ApiOperation(value = "查看评论")
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ModelAndView review(ModelAndView mav, int courseid) {
		List<Review> reviews = reviewService.select(courseid);
		mav.addObject("reviews", reviews);
		mav.setViewName("redirect:/user/courseVideo");
		return mav;
	}

	@ApiOperation(value = "查找课程")
	@RequestMapping(value = "/courseSearch", method = RequestMethod.GET)
	public String courseSearch(String search, Map map) {
		List<Course> courses = courseService.courseSearch(search);
		map.remove(courses);
		map.put("courses", courses);
		map.put("search", search);
		return "user/courseIndex";
	}

	@ApiOperation(value = "出错,返回错误码")
	@RequestMapping(value = "/error/{errorCode}", method = RequestMethod.GET)
	public String error(@PathVariable int errorCode) {
		String pager = "404";
		switch (errorCode) {
        case 404:
            pager = "404";
            break;
        case 500:
            pager = "500";
            break;
    }
		return pager;
	}
}
