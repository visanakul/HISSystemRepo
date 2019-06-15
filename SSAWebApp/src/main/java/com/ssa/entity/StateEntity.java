package com.ssa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STATE_MASTER")
public class StateEntity {
	@Id
	private Integer stateId;
	private String stateCode;
	private String stateName;
}
