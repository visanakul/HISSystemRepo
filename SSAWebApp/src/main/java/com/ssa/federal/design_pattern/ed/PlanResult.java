package com.ssa.federal.design_pattern.ed;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(value = {PlanApproved.class,PlanDenied.class})
public abstract class PlanResult {

}
