package com.ssa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * Used to work with State Table Data
 * @author VISHAL
 *
 */
@Data
@Entity
@Table(name = "STATE_MASTER")
public class StateEntity {
	@Id
	/**
	 * Store state id
	 */
	private Integer stateId;
	/**
	 * Store state code
	 */
	private String stateCode;
	/**
	 * Store state name
	 */
	private String stateName;
}
