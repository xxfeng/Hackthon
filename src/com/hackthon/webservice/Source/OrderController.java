package com.hackthon.webservice.Source;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackthon.domain.Order;


@Controller
public class OrderController {
	@RequestMapping(value="/order/add", method=RequestMethod.POST)
    public @ResponseBody String add(@RequestBody Order params) {
		System.out.println("order_id="+params.getOrder_id());
		System.out.println("user.userid="+params.getUser().getUserid());
		System.out.println("user.dishNamelist="+params.getDishNameList().get(0).getDish_id());
		System.out.println("user.dishNumlist="+params.getDishNumList().get(0));
        return "You can upload a file by posting to this same URL.";
    }
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody String delete(@RequestBody Order params) {
        return "You can upload a file by posting to this same URL.";
    }
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
    public @ResponseBody String modify(@RequestBody Order params) {
        return "You can upload a file by posting to this same URL.";
    }
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
    public @ResponseBody String search(@RequestBody Order params){
		return "get post params=";
	}
}
