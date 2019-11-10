package hu.ponte.hr.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Getter;

/**
 * @author zoltan
 */
@Getter
@Builder
@Entity
public class ImageMeta
{
	@Column(name="ID")
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="MIMETYPE")
	private String mimeType;
	
	@Column(name="DIGITALSIGN")
	private String digitalSign;
	
	@Transient
	private long size;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getDigitalSign() {
		return digitalSign;
	}
	public void setDigitalSign(String digitalSign) {
		this.digitalSign = digitalSign;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}		
	
}
