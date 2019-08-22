package com.ssa.state.co;

public class PreProcess implements ICOProcess{
	private ICOProcess nextProcess;
	
	@Override
	public void execute() {
		System.out.println("Preprocessing...");
		next();
	}

	@Override
	public void next() {
		System.out.println("Next Process is StartProcess...");
		nextProcess=new StartProcess();
		nextProcess.execute();
	}

}
