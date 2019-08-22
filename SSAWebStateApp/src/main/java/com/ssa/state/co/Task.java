package com.ssa.state.co;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<String> {
	private String msg;

	public Task(String msg) {
		this.msg = msg;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Task For "+msg);
		TimeUnit.SECONDS.sleep(5);
		return new StringBuilder(msg).reverse().toString();
	}
}
