package com.ssa.state.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


/**
 * To map with PLAN_MASTER
 * @author VISHAL
 *
 */
@Entity
@Table(name="PLAN_MASTER")
@Data
//@ScriptAssert(lang = "javascript", script = "_this.startDate < (_this.endDate)")
public class PlanEntity {
	/**
	 * Stores plan id
	 */
	@Id
	@GeneratedValue(strategy =  GenerationType.SEQUENCE,generator = "plan_id_gen")
	@SequenceGenerator(name = "plan_id_gen",initialValue = 1,allocationSize = 1,sequenceName = "plan_id_seq")
	
	private Integer id;
	
	/**
	 * Stores plan name
	 */
	@NotEmpty@Size(min = 3,max = 40,message = "Plan name min 3 char required")
	private String name;
	/**
	 * Stores plan description
	 */
	@NotEmpty@Size(min = 10,max = 100,message = "Plan description min 10 char required")
	private String description;
	/**
	 * Stores plan start data
	 */
	@Future
	@NotNull
	@DateTimeFormat(pattern = "DD-MM-YYYY")
	private Date startDate;
	/**
	 * Stores plan end data
	 */
	@NotNull
	@DateTimeFormat(pattern = "DD-MM-YYYY")
	private Date endDate;
}
