package com.ssa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PHOTO")
public class PhotoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHOTO_SEQ")
    @SequenceGenerator(sequenceName = "photo_seq", allocationSize = 1, name = "PHOTO_SEQ")
	private Integer photoId;
	private String fileName;
	private String fileType;
	private byte[] pic;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ssn")
	private SSNUserEntity user;
}
