package com.medical.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.medical.model.ExecutSQL;

public interface ExecutSQLDAO {
	@SuppressWarnings("rawtypes")
	public List<HashMap> queryAll(ExecutSQL executSQL) throws SQLException;

	@SuppressWarnings("rawtypes")
	public List<HashMap> queryRow(ExecutSQL executSQL) throws SQLException;

	public Integer queryCnt(ExecutSQL executSQL) throws SQLException;
	
	public void updateSQL(ExecutSQL executSQL) throws SQLException;
	
	@SuppressWarnings("rawtypes")
	public HashMap countAssist(HashMap paramMap) throws SQLException;
}
