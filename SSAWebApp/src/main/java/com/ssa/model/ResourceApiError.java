package com.ssa.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ResourceApiError Model",description = "Used to show error")
public class ResourceApiError {
	@ApiModelProperty(value = "Status code")
	private Integer statusCode;
	@ApiModelProperty(value = "Error Message")
	private String errMsg;
	@ApiModelProperty(value = "Date for error")
	private Date date;
}
