package com.hackthon.webservice;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hackthon.domain.Greeting;

@Controller
public class UploadController {
	private static final String template = "Hello, %s!";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String FILE_ROOT = "d://uploadFile";
//	@Context
//    ServletContext sc;

	@RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody Greeting handleFileUpload(
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
        	String name = file.getOriginalFilename();
        	SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        	String rootPath = FILE_ROOT + "//" + dateformat.format(new Date());
        	File rootFile = new  File(rootPath);
        	if(!rootFile.exists()){
        		rootFile.mkdirs();
        	}
        	String location =  rootPath + "//" + name;
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(location)));
                stream.write(bytes);
                stream.close();
                String str =  "file:" + name + " uploaded to location: "+ location;
                return new Greeting(String.format(template, str));
            } catch (Exception e) {
                String str =  "You failed to upload " + name + " => " + e.getMessage();
                return new Greeting(String.format(template, str));
            }
        } else {
            String str = "You failed to upload " + " because the file was empty.";
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
	
//	private void writeToFile(OutputStream out,
//            String uploadedFileLocation) {
//        try {
////            OutputStream out = new FileOutputStream(new File(
////                    uploadedFileLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                System.out.println("write file.....");
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }
//
//    private String readFile(String path) {
//        StringBuffer content = new StringBuffer();
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            String sCurrentLine;
//            while ((sCurrentLine = br.readLine()) != null) {
//                System.out.println(sCurrentLine);
//                content.append(sCurrentLine);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            return content.toString();
//        }
//    }
//
}
