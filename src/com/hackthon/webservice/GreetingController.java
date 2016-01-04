package com.hackthon.webservice;

import com.hackthon.domain.Greeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
	private static final String template = "Hello, %s!";
	@RequestMapping(value="/greeting", method=RequestMethod.GET)
	@ResponseBody
	public Greeting greeting(@RequestParam(value="name", defaultValue="nicole") String name){
		return new Greeting(String.format(template, name));
	}

}
