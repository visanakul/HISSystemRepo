package com.ssa.state.service;

import com.ssa.state.model.COBatchModel;
public interface ICOBatchService {
	public boolean saveBatchStartData(COBatchModel coBatchModel);
	public boolean updateBatchEndData(COBatchModel coBatchModel);
}
