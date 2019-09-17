package com.search.service.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.search.service.domain.User;
import com.search.service.login.LoginService;


@Controller
public class PlaceSearchController {
	
	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		return model;
	}
	@RequestMapping(value = "/login")
	public ModelAndView login(ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		return modelAndView;
	}
//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public ModelAndView login(
//		@RequestParam("userId") String userId,
//		@RequestParam("password") String password) throws Exception {
//		
//		ModelAndView model = new ModelAndView();
//		Account user = loginService.login(userId, password);
//		
//		if(user != null) {
//			model.addObject("message", "Welcome "+user.getUserId());
//			model.addObject("date", new SimpleDateFormat("yyyy년 MM월 dd일 E요일").format(new Date()));
//		} else {
//			model.addObject("invaild", true);
//		}
//		return model;
//	}
}
