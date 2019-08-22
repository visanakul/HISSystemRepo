package com.ssa.state.co;

public class ProcessManager {
	public static void main(String[] args) {
		ICOProcess process=new PreProcess();
		process.execute();
	}
	 
}
