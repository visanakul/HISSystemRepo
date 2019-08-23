package com.ssa.federal.design_pattern.ed;

import javax.xml.bind.annotation.XmlSeeAlso;
import lombok.Data;

@Data
@XmlSeeAlso(value = {SnapPlanData.class})
public abstract class PlanData {
	protected PlanInfo planInfo;
}
