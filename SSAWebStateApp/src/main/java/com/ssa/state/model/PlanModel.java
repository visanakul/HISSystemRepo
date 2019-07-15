package com.ssa.state.model;

import java.util.Date;

import lombok.Data;
/**
 * Model to store Plan data
 * @author VISHAL
 *
 */
@Data
public class PlanModel {
	/**
	 * store plan id
	 */
	private Integer id;
	/**
	 * store plan name
	 */
	private String name;
	/**
	 * store plan description
	 */
	private String description;
	/**
	 * store plan start date
	 */
	private Date startDate;
	/**
	 * store plan end date
	 */
	private Date endDate;
}
