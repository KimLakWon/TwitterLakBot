package com.hello.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hello.model.UserVO;

@RestController
@RequestMapping("/body/*")
public class BodyController {

	@RequestMapping(value = "/body2", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public UserVO hello() {
		UserVO userVO = new UserVO();
		userVO.setUserId("lak");
		userVO.setUserPassword("1234");
		userVO.setUserEmail("lak.com");

		return userVO;
	}

}
