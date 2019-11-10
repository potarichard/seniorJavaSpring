package hu.ponte.hr.controller;

import lombok.Builder;
import lombok.Getter;

/**
 * @author zoltan
 */
@Getter
@Builder
public class ImageMeta
{
	private String id;
	private String name;
	private String mimeType;	
	private String digitalSign;
	private long size;
}
