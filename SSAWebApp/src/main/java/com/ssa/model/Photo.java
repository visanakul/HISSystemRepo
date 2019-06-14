package com.ssa.model;

import lombok.Data;

@Data
public class Photo {
	private Integer ssn;
	private String fileName;
	private String fileType;
	private byte[] pic;
	
}
