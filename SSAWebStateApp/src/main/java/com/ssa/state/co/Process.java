package com.ssa.state.co;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Process implements ICOProcess {
	private ICOProcess nextProcess;
	private List<String> list;
	private ExecutorService service;

	public Process(List<String> list) {
		this.list = list;

	}

	@Override
	public void execute() {
		System.out.println("Process...");
		try {
			executeService();
			next();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void executeService() throws InterruptedException {
		service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Callable<String>> callableList = new ArrayList<Callable<String>>();
		for (String x : list) {
			callableList.add(new Task(x));
		}

		List<Future<String>> futureList = service.invokeAll(callableList);
		futureList.stream().map(future -> {
			String rev;
			try {
				rev = future.get();
				//System.out.println("Reversed : " + rev);
				return rev;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			return null;
		}).forEach(System.out::println);
		service.shutdown();
		System.out.println("Service shut down...");
	}

	@Override
	public void next() {
		nextProcess = new PostProcess();
		System.out.println("Next Process is PostProcess...");
		nextProcess.execute();
	}

}
