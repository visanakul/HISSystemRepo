package com.ssa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "State Model",description = "It stores state details")
/**
 * State model
 * @author VISHAL
 *
 */
public class State {
	@ApiModelProperty("State ID")
	/**
	 * Store state id
	 */
	private Integer stateId;
	@ApiModelProperty( "State Code")
	/**
	 * Store state code
	 */
	private String stateCode;
	@ApiModelProperty("State Name")
	/**
	 * Store state name
	 */
	private String stateName;
	
}
