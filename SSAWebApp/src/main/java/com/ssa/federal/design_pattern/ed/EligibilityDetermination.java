package com.ssa.federal.design_pattern.ed;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@XmlRootElement(name = "eligibility-determination")
@Data
public class EligibilityDetermination {
	private CitizenData citizenData;
	private PlanDetails planDetails;
}
