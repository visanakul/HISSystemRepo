package com.ssa.federal.design_pattern;

import com.ssa.exception.DroolFileException;

import lombok.Data;

@Data
public abstract class RuleExecutorTemplate {
	protected String drlFilePath;
	protected String errorMsg;
	protected PlanData planData;

	public RuleExecutorTemplate(String drlFilePath, PlanData planData) {
		super();
		this.drlFilePath = drlFilePath;
		this.planData = planData;
	}

	public abstract void init();

	public abstract boolean isValid();

	public abstract void openSession();

	public abstract void closeSession();

	public abstract void executeRules();

	public void execute() {
		init();
		if (isValid()) {
			openSession();
			executeRules();
			closeSession();
		} else {
			throw new DroolFileException(errorMsg);
		}
	}
}
