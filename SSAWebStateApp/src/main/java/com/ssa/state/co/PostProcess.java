package com.ssa.state.co;

public class PostProcess implements ICOProcess{
	
	@Override
	public void execute() {
		System.out.println("PostProcess...");
		next();
	}

	@Override
	public void next() {
		System.out.println("End of Process");
	}

}
