package hu.ponte.hr.services;

import java.util.List;

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
	
	public ImageMeta saveImgMetaData(MultipartFile file) {
		
		ImageMeta img = new ImageMeta();
		
		String name = file.getOriginalFilename();
		img.setName(name.substring(0, name.indexOf(".")));
		img.setMimeType(file.getContentType());
		
		sign_service.sign(img);
		
		return img_meta_dao.save(img);		
	}
}
