package hu.ponte.hr.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hu.ponte.hr.controller.ImageMeta;
import hu.ponte.hr.repositories.ImageMetaDAO;

@Service
public class ImageStore {

	private ImageMetaDAO img_meta_dao;
	
	private SignService sign_service;

	@Autowired
	public void setImg_meta_dao(ImageMetaDAO img_meta_dao) {
		this.img_meta_dao = img_meta_dao;
	}	
	
	@Autowired
	public void setSign_service(SignService sign_service) {
		this.sign_service = sign_service;
	}


	public List<ImageMeta> getAllMetaDatas(){
		return img_meta_dao.findAll();
	}
	
	public ImageMeta saveImg(MultipartFile file) {
		
		ImageMeta img = new ImageMeta();
		
		String name = file.getOriginalFilename();
		img.setName(name.substring(0, name.indexOf(".")));
		img.setMimeType(file.getContentType());
		
		try 
		{
			img.setData(file.getBytes());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sign_service.sign(img);
		
		return img_meta_dao.save(img);		
	}

	public void previewImg(String id, HttpServletResponse resp) {
		
		Optional<ImageMeta> img = img_meta_dao.findById(id);
		
		byte[] datas = img.get().getData();
		
		try 
		{
			resp.setContentType(img.get().getMimeType());
			resp.getOutputStream().write(datas);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}




