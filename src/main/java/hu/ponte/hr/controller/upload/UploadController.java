package hu.ponte.hr.controller.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hu.ponte.hr.controller.ImageMeta;
import hu.ponte.hr.services.ImageStore;

@Component
@RequestMapping("api/file")
public class UploadController
{	
	@Autowired
    private ImageStore imageStore;
	

	// original
	// http://localhost:8080/api/file/post  re fog jonni, post method, could be simpler...
//    @RequestMapping(value = "post", method = RequestMethod.POST)
//    @ResponseBody	// should just return "ok", but it somehow returns the imagemeta
//    public String handleFormUpload(@RequestParam("file") MultipartFile file) {  
//    	imageStore.saveImg(file);  
//        return "ok";
//    }
    
    
    // i would do it like this
    @PostMapping("/post")
    @ResponseBody	// without it:     org.thymeleaf.exceptions.TemplateInputException: Error resolving template [api/file/post], template might not 
    				// exist or might not be accessible by any of the configured Template Resolvers
    				// de miert a thymeleaf buziskodik ezzel ???
    public ImageMeta handleFormUpload(@RequestParam("file") MultipartFile file) {      	 
        return imageStore.saveImg(file);
    }
}
