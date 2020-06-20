package com.hello.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

	//TODO : error fix..
	@RequestMapping("/hello") 
	public ModelAndView content() {
		System.out.println("zzzzzzzzzzzzzzzzz");
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("/views/hello.jsp"); 
		mv.addObject("data", "12341234"); 
		System.out.println("zzzzzzzzzzzzzzzzz2");
		return mv;
	}

}
