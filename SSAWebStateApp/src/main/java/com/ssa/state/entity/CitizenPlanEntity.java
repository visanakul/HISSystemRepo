package com.ssa.state.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class CitizenPlanEntity {
//	 @Id
//	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
//	    @GenericGenerator(
//	        name = "book_seq", 
//	        strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator", 
//	        parameters = {
//	            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
//	            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
//	            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
//	    
	private String caseNo;
	@OneToOne
	private CitizenEntity citizenEntity;
}
