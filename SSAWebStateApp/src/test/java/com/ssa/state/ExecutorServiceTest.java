package com.ssa.state;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.co.PostProcess;
import com.ssa.state.co.PreProcess;
import com.ssa.state.co.Process;
import com.ssa.state.co.StartProcess;
import com.ssa.state.model.COBatchModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutorServiceTest {
	@Autowired(required = true)
	private PreProcess preProcess;
	@Autowired(required = true)
	private StartProcess startProcess;
	@Autowired(required = true)
	private Process process;
	@Autowired(required = true)
	private PostProcess postProcess;

	@Transactional
	@Rollback(false)
	@Test
	public void test1_executeService() {
		System.out.println("Test start");
		COBatchModel coBatchModel = new COBatchModel();
		coBatchModel.setBacthStatus("S");
		coBatchModel.setBatchName("Batch1");

		preProcess.setCoBatchModel(coBatchModel);
		preProcess.execute();

		startProcess.setBucketSize(5);
		startProcess.setBucketNumber(0);
		startProcess.execute();

		process.setCoTriggerModels(startProcess.getCoTriggerModels());
		process.execute();

		coBatchModel.setBacthStatus("E");
		System.out.println("coBatchModel : " + coBatchModel);
		postProcess.setCoBatchModel(coBatchModel);
		postProcess.execute();
		System.out.println("Test end");
	}

}
