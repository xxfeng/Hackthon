package com.hackthon.web;

import com.hackthon.domain.Greeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/greeting")
public class GreetingController {
	private static final String template = "Hello, %s!";
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	public Greeting greeting(@RequestParam(value="name", defaultValue="nicole") String name){
		return new Greeting(String.format(template, name));
	}
	
	@RequestMapping(value="/hello2", method=RequestMethod.GET)
	@ResponseBody
	public Greeting greeting2(@RequestParam(value="name", defaultValue="nicole") String name){
		return new Greeting(String.format(template, name));
	}
}
