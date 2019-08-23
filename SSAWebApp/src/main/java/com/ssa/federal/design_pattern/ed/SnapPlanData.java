package com.ssa.federal.design_pattern.ed;

import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@XmlType
public class SnapPlanData extends PlanData {
	protected String familyIncome;
	protected String isEmployed;

	@Override
	public String toString() {
		return "SnapPlanData [familyIncome=" + familyIncome + ", isEmployed=" + isEmployed + ", planInfo=" + planInfo
				+ "]";
	}

}
