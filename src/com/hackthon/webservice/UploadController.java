package com.hackthon.webservice;

//import java.io.InputStream;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
    public String uploadFile(
            @RequestParam("files") MultipartFile file) {
//		,
//        @RequestParam("filename") String name		
//		if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(name)));
//                stream.write(bytes);
//                stream.close();
//                return "You successfully uploaded " + name + "!";
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
		return "ok";
    }
//	@Path("fileUpload")
//	 @POST
//	    @Consumes(MediaType.MULTIPART_FORM_DATA)
//	    @Produces(MediaType.APPLICATION_JSON)
//	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
//	@ResponseBody
//	    public String uploadFile2(
//	            @FormDataParam("targetFiles") InputStream uploadedInputStream,
//	            @FormDataParam("targetFiles") FormDataContentDisposition fileDetail,
//	            @FormDataParam("path") String path) {
//	        String uploadedFileFS = "D:\\workspace\\ShareProject\\upload\\"; 
//	        String fileLocation = uploadedFileFS + fileDetail.getFileName();
//	        writeToFile(uploadedInputStream, fileLocation);
//	        String content = readFile(fileLocation);
////	        String content2 = getStringFromInputStream(uploadedInputStream);
//	        String output = "File uploaded to : " + content;// + uploadedFileLocation;
//	        System.out.println(output);
//	        String res = "{\"msg\":\""+ output +"\"}";
//	        return Response.status(200).entity(res).build();
////	        return this.buildResponse(res);
//			return "ok";
//
//	    }
	
	@RequestMapping(value="/testPost", method=RequestMethod.POST)
	@ResponseBody
    public String testPost(
            @RequestParam("params")String params) {
//		,
//        @RequestParam("filename") String name
		
//		if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(name)));
//                stream.write(bytes);
//                stream.close();
//                return "You successfully uploaded " + name + "!";
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
		System.out.println("params = " + params);
		return "ok";
    }
	
	
}
