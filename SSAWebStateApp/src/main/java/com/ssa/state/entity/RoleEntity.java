package com.ssa.state.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * Entity for ROLE_MASTER table
 * @author VISHAL
 *
 */
@Data
@Entity
@Table(name = "ROLE_MASTER")
public class RoleEntity {
	@Id
	private Integer roleId;
	private String roleName;
	
}
