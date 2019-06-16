package com.ssa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "State Model",description = "It stores state details")
public class State {
	@ApiModelProperty(value = "State ID")
	private Integer stateId;
	@ApiModelProperty(value = "State Code")
	private String stateCode;
	@ApiModelProperty(value = "State Name")
	private String stateName;
	
}
