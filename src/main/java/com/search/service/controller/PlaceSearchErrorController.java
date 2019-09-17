package com.search.service.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlaceSearchErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		int status = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status == HttpStatus.NOT_FOUND.value()) {
			return "404";
		} else if(status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			return "500";
		} 
		return "index";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
