package com.ssa.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ResourceApiError Model",description = "Used to show error")
/**
 * Store Exception Data
 * @author VISHAL
 *
 */
public class ResourceApiError {
	@ApiModelProperty("Status code")
	/**
	 * Store status code
	 */
	private Integer statusCode;
	@ApiModelProperty("Error Message")
	/**
	 * Store error message
	 */
	private String errMsg;
	@ApiModelProperty( "Date for error")
	/**
	 * Store date
	 */
	private Date date;
}
