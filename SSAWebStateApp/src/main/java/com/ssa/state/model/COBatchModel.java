package com.ssa.state.model;

import java.util.Date;

import lombok.Data;
@Data
public class COBatchModel {
	private Integer batchId;
	private String batchName;
	private Date startDate;
	private Date endDate;
	private String bacthStatus;
}
