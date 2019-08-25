package com.ssa.state.co;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssa.state.exception.COTriggerException;
import com.ssa.state.model.COTriggerModel;
import com.ssa.state.service.COTriggerServiceImpl;
import com.ssa.state.service.ICOTriggerService;

import lombok.Data;

@Data
@Component
public class Process implements ICOProcess {
	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Process.class);

	@Autowired(required = true)
	private ICOTriggerService coTriggerService;

	private ICOProcess nextProcess;
	private List<COTriggerModel> coTriggerModels;
	private ExecutorService service;

	@Override
	public void execute() {
		System.out.println("Process...");
		try {
			executeService();
			//next();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void executeService() throws InterruptedException {

		LOGGER.info("executeService for Process start");
		service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Callable<Integer>> callableList = new ArrayList<Callable<Integer>>(coTriggerModels.size());
		for (COTriggerModel coTriggerModel : coTriggerModels) {
			callableList.add(new Task(coTriggerModel));
		}

		List<Future<Integer>> futureList = service.invokeAll(callableList);
		futureList.stream().map(future -> {
			Integer trgId = null;
			String msg = "Default msg";
			try {
				trgId = future.get();
				LOGGER.debug("Trigger ID {} joined", trgId);
				int updateCount = coTriggerService.updateTriggerStatusCompleted(trgId);
				msg = "Success Trigger ID : " + trgId + " updated : " + updateCount;
			} catch (InterruptedException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				msg = e.getMessage();
			} catch (ExecutionException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				msg = e.getMessage();
			} catch (COTriggerException e) {
				LOGGER.error(e.getMessage());
				msg = e.getMessage();
			}
			return msg;
		}).forEach(msg -> {
			LOGGER.debug(msg);
		});
		service.shutdown();

		LOGGER.debug("Service shut down...");
		LOGGER.info("executeService for Process end");

		//next();
	}

	@Override
	public void next() {
		nextProcess = new PostProcess();
		LOGGER.info("Next Process is PostProcess...");
		nextProcess.execute();
	}

}
