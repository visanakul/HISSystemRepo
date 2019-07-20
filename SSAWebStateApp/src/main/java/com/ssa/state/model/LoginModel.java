package com.ssa.state.model;

import lombok.Data;

/**
 * Stores login data
 * @author VISHAL
 *
 */
@Data
public class LoginModel {
	private String email;
	private String password;
	private String role;
}
