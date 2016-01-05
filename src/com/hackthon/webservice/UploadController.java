package com.hackthon.webservice;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hackthon.domain.Greeting;

@Controller
@RequestMapping("/source")
public class UploadController {
	private static final String template = "Hello, %s!";
	@RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody Greeting handleFileUpload(@RequestParam("name") String name, 
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                String str =  "You successfully uploaded " + name + "!";
                return new Greeting(String.format(template, str));
            } catch (Exception e) {
                String str =  "You failed to upload " + name + " => " + e.getMessage();
                return new Greeting(String.format(template, str));
            }
        } else {
            String str = "You failed to upload " + name + " because the file was empty.";
            return new Greeting(String.format(template, str));
        }
    }

	@RequestMapping(value="/testPost", method=RequestMethod.POST)
	@ResponseBody
    public String testPost(
            @RequestParam("params")String params) {
		System.out.println("params = " + params);
		return "ok";
    }
	
	
}
