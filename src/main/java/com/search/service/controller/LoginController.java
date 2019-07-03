package com.search.service.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.search.service.domain.Account;
import com.search.service.login.LoginService;


@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value= {"/"}, method = RequestMethod.GET)
	public String login() throws Exception {
		return "login";
	}
	
	@RequestMapping(value="/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(
		@RequestParam("userId") String userId,
		@RequestParam("password") String password) throws Exception {
		
		ModelAndView model = new ModelAndView();
		Account user = loginService.login(userId, password);
		
		if(user != null) {
			model.addObject("message", "Welcome "+user.getUserId());
			model.addObject("date", new SimpleDateFormat("yyyy년 MM월 dd일 E요일").format(new Date()));
			model.setViewName("redirect:/home");
		} else {
			model.addObject("invaild", true);
			model.setViewName("redirect:/");
		}
		return model;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home() throws Exception {
		return "home";
	}
}
