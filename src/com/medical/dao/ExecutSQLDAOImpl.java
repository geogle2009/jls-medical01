package com.medical.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.medical.model.ExecutSQL;

@SuppressWarnings("unchecked")
public class ExecutSQLDAOImpl extends SqlMapClientDaoSupport implements
		ExecutSQLDAO {

	@SuppressWarnings("rawtypes")
	@Override
	public List<HashMap> queryAll(ExecutSQL executSQL) throws SQLException {
		List queryForList = getSqlMapClientTemplate().queryForList(
				"ExeuteSQL.queryAll", executSQL);
		return queryForList;
	}

	@Override
	public Integer queryCnt(ExecutSQL executSQL) throws SQLException {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ExeuteSQL.queryCnt", executSQL);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<HashMap> queryRow(ExecutSQL executSQL) throws SQLException {
		List queryForList = getSqlMapClientTemplate().queryForList(
				"ExeuteSQL.queryRow", executSQL);
		return queryForList;
	}

	@Override
	public void updateSQL(ExecutSQL executSQL) throws SQLException {
		getSqlMapClientTemplate().update("ExeuteSQL.updateSQL", executSQL);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public HashMap countAssist(HashMap paramMap) throws SQLException {
		HashMap resultmap = (HashMap) getSqlMapClientTemplate().queryForObject(
				"ExeuteSQL.countAssist", paramMap);
		return resultmap;
	}
}
