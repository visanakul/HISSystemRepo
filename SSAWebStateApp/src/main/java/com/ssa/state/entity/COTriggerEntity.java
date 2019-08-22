package com.ssa.state.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table (name="CO_TRIGGERS")
@Data
public class COTriggerEntity {
	@Id
	private Integer trgId;
	private String caseNum;
	private Date createDt;
	private String trgStatus;
	private Date updateDt;
}
