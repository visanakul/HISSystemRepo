package com.ssa.state.model;

import java.util.Date;

import lombok.Data;
@Data
public class COTriggerModel {
	private Integer trgId;
	private String caseNum;
	private Date createDt;
	private String trgStatus;
	private Date updateDt;
}
