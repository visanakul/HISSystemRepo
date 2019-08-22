package com.ssa.state.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "CO_BATCH_MASTER")
@Data
public class COBatchEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_id_seq")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "batch_id_seq", sequenceName = "batch_id_seq")
	private Integer batchId;
	@Column(updatable = false)
	private String batchName;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(updatable = false)
	private Date startDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String bacthStatus;
}
