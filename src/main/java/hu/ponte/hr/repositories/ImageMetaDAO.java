package hu.ponte.hr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.ponte.hr.controller.ImageMeta;

@Repository
public interface ImageMetaDAO extends JpaRepository<ImageMeta, String>{

	
	
}
