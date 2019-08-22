package com.ssa.state.co;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartProcess implements ICOProcess {
	private ICOProcess nextProcess;
	private List<String> list;
	private ExecutorService service;

	@Override
	public void execute() {
		System.out.println("StartProcess...");
		list = new ArrayList<>();
		list.add("First");
		list.add("Second");
		list.add("Third");
		list.add("Fourth");
		list.add("Fifth");
		next();
	}

	

	@Override
	public void next() {
		System.out.println("Next Process is Process...");
		nextProcess = new Process(list);
		nextProcess.execute();
	}

}
