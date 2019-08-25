package com.ssa.state.co;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssa.state.exception.COTriggerException;
import com.ssa.state.model.COTriggerModel;

public class Task implements Callable<Integer> {
	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

	private COTriggerModel coTriggerModel;

	public Task(COTriggerModel coTriggerModel) {
		this.coTriggerModel = coTriggerModel;
	}

	@Override
	public Integer call() {
		LOGGER.info("Task call() start");
		try {
			LOGGER.debug("Task For ID : " + coTriggerModel.getTrgId());
			return coTriggerModel.getTrgId();
		} catch (Exception e) {
			LOGGER.error("Task call() error for trigger id : {}", coTriggerModel.getTrgId());
			throw new COTriggerException(
					"Trigger with id : " + coTriggerModel.getTrgId() + " failed:\nError : " + e.getMessage());
		} finally {
			LOGGER.info("Task call() end");
		}

	}
}
