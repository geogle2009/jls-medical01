package com.medical.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.medical.common.Pager;
import com.medical.dao.ExecutSQLDAO;
import com.medical.dao.JzBizDAO;
import com.medical.dao.JzBizcheckDAO;
import com.medical.dto.BizCheckDTO;
import com.medical.dto.BusinessDTO;
import com.medical.model.ExecutSQL;
import com.medical.model.JzBiz;
import com.medical.model.JzBizcheck;
import com.medical.service.BizCheckService;

public class BizCheckServiceImpl implements BizCheckService {
	private JzBizcheckDAO jzBizcheckDAO;
	private JzBizDAO jzBizDAO;
	private ExecutSQLDAO executSQLDAO;
	private Pager pager;

	@SuppressWarnings("rawtypes")
	@Override
	public BizCheckDTO findByBizIdAndBizcheckId(Integer bizId,
			BigDecimal bizcheckId) {
		BizCheckDTO bizCheckDTO = new BizCheckDTO();
		if (bizId != null) {
			try {
				String sql = "select  biz.begin_date, "
						+ " biz.biz_id,"
						+ " inf.familyno,"
						+ " dept.name as hospital,"
						+ " biz.in_dept_name,"
						+ " inf.membername,"
						+ " inf.paperid,"
						+ " biz.diagnose_name,"
						+ " inf.ssn,"
						+ " biz.sms_state,"
						+ " biz.check_state,"
						+ " biz.estimate,"
						+ " chk.bizcheck_id,"
						+ " chk.checked1,"
						+ " chk.checked2,"
						+ " chk.checked3,"
						+ " chk.checked4"
						+ " from jz_biz biz, member_baseinfo inf, dm_bizdept dept,jz_bizcheck chk"
						+ " where biz.ssn = inf.ssn "
						+ " and biz.hospital_id = dept.hospital_id(+) "
						+ " and biz.biz_id=chk.biz_id(+) "
						+ " and biz.biz_id = " + bizId;
				ExecutSQL executSQL = new ExecutSQL();
				executSQL.setExecutsql(sql);
				List<HashMap> rs = executSQLDAO.queryAll(executSQL);
				Iterator<HashMap> it = rs.iterator();
				if (it.hasNext()) {
					HashMap map = it.next();
					bizCheckDTO = rstoDTO(map);
				}
				JzBizcheck bizcheck = jzBizcheckDAO
						.selectByPrimaryKey(bizcheckId);
				if (bizcheck != null) {
					bizCheckDTO.setBizcheckId(bizcheck.getBizcheckId());
					// 社区审批信息
					bizCheckDTO.setChecked1(bizcheck.getChecked1());
					bizCheckDTO.setChecktime1(bizcheck.getChecktime1());
					bizCheckDTO.setDetail1(bizcheck.getDetail1());
					bizCheckDTO.setOperator1(bizcheck.getOperator1());
					bizCheckDTO.setOpttime1(bizcheck.getOpttime1());
					// 街道审批信息
					bizCheckDTO.setChecked2(bizcheck.getChecked2());
					bizCheckDTO.setChecktime2(bizcheck.getChecktime2());
					bizCheckDTO.setDetail2(bizcheck.getDetail2());
					bizCheckDTO.setOperator2(bizcheck.getOperator2());
					bizCheckDTO.setOpttime2(bizcheck.getOpttime2());
					// 区县审批信息
					bizCheckDTO.setChecked3(bizcheck.getChecked3());
					bizCheckDTO.setChecktime3(bizcheck.getChecktime3());
					bizCheckDTO.setDetail3(bizcheck.getDetail3());
					bizCheckDTO.setOperator3(bizcheck.getOperator3());
					bizCheckDTO.setOpttime3(bizcheck.getOpttime3());
					// 低保中心审批信息
					bizCheckDTO.setChecked4(bizcheck.getChecked4());
					bizCheckDTO.setChecktime4(bizcheck.getChecktime4());
					bizCheckDTO.setDetail4(bizcheck.getDetail4());
					bizCheckDTO.setOperator4(bizcheck.getOperator4());
					bizCheckDTO.setOpttime4(bizcheck.getOpttime4());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bizCheckDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<BizCheckDTO> findByTerm(BizCheckDTO bizCheckDTO ,String type) {
		List<BizCheckDTO> result = new ArrayList<BizCheckDTO>();
		String sql = "select biz.begin_date,"
				+ " biz.biz_id,"
				+ " inf.familyno,"
				+ " dept.name as hospital,"
				+ " biz.in_dept_name,"
				+ " inf.membername,"
				+ " inf.paperid,"
				+ " biz.diagnose_name,"
				+ " inf.ssn,"
				+ " biz.sms_state,"
				+ " biz.check_state,"
				+ " biz.estimate,"
				+ " chk.bizcheck_id,"
				+ " chk.checked1,"
				+ " chk.checked2,"
				+ " chk.checked3,"
				+ " chk.checked4"
				+ " from jz_biz biz, member_baseinfo inf, dm_bizdept dept, jz_bizcheck chk "
				+ " where biz.ssn = inf.ssn and biz.hospital_id = dept.hospital_id(+) and biz.biz_id = chk.biz_id(+) "
				+ bizCheckDTO.getJwhere() + " order by  biz.begin_date ,  inf.familyno";

		ExecutSQL executSQL = new ExecutSQL();
		try {
			executSQL.setExecutsql(sql);

			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl("initQueryPage.action?type="+type);
			pager.setCurrentpage(bizCheckDTO.getCur_page());
			pager.setPagesize(12);
			pager.getToolsmenu();

			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());

			List<HashMap> rss = executSQLDAO.queryRow(executSQL);

			for (HashMap map : rss) {
				BizCheckDTO dto = rstoDTO(map);
				result.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	private BizCheckDTO rstoDTO(HashMap map) {
		BizCheckDTO dto = new BizCheckDTO();
		String membername = (String) map.get("membername".toUpperCase());
		if (null == membername || "".equals(membername)) {
			membername = "";
		}
		Date begin_date = (Date) map.get("begin_date".toUpperCase());
		if (null == begin_date || "".equals(begin_date)) {
			begin_date = null;
		}
		BigDecimal biz_id = (BigDecimal) map.get("biz_id".toUpperCase());
		if (null == biz_id || "".equals(biz_id)) {
			biz_id = new BigDecimal(0);
		}
		String familyno = (String) map.get("familyno".toUpperCase());
		if (null == familyno || "".equals(familyno)) {
			familyno = "";
		}
		String hospital = (String) map.get("hospital".toUpperCase());
		if (null == hospital || "".equals(hospital)) {
			hospital = "";
		}
		String in_dept_name = (String) map.get("in_dept_name".toUpperCase());
		if (null == in_dept_name || "".equals(in_dept_name)) {
			in_dept_name = "";
		}
		String paperid = (String) map.get("paperid".toUpperCase());
		if (null == paperid || "".equals(paperid)) {
			paperid = "";
		}
		String diagnose_name = (String) map.get("diagnose_name".toUpperCase());
		if (null == diagnose_name || "".equals(diagnose_name)) {
			diagnose_name = "";
		}
		BigDecimal sms_state = (BigDecimal) map.get("sms_state".toUpperCase());
		if (null == sms_state || "".equals(sms_state)) {
			sms_state = new BigDecimal(0);
		}
		BigDecimal check_state = (BigDecimal) map.get("check_state"
				.toUpperCase());
		if (null == check_state || "".equals(check_state)) {
			check_state = new BigDecimal(-1);
		}
		String ssn = (String) map.get("ssn".toUpperCase());
		if (null == ssn || "".equals(ssn)) {
			ssn = "0";
		}
		BigDecimal estimate = ((BigDecimal) map.get("estimate".toUpperCase()));
		if (null == estimate || "".equals(estimate)) {
			estimate = new BigDecimal(0);
		}
		BigDecimal bizcheckId = (BigDecimal) map.get("bizcheck_id"
				.toUpperCase());

		dto.setBizcheckId(bizcheckId);
		
		if (check_state == null || check_state.intValue() == -1) {
			if (estimate.doubleValue() >= 2000 && estimate.doubleValue() < 5000) {
				BigDecimal checked2 = (BigDecimal) map.get("checked2"
						.toUpperCase());
				if (checked2 == null) {
					dto.setCheckorg("街道未审核");
				} else {
					dto.setCheckorg("街道已审核");
				}
			} else if (estimate.doubleValue() >= 5000
					&& estimate.doubleValue() < 10000) {
				BigDecimal checked3 = (BigDecimal) map.get("checked3"
						.toUpperCase());
				if (checked3 == null) {
					dto.setCheckorg("区县未审核");
				} else {
					dto.setCheckorg("区县已审核");
				}
			} else if (estimate.doubleValue() >= 10000) {
				BigDecimal checked4 = (BigDecimal) map.get("checked4"
						.toUpperCase());
				if (checked4 == null) {
					dto.setCheckorg("市低保中心未审核");
				} else {
					dto.setCheckorg("市低保中心已审核");
				}
			}
		}else {
			dto.setCheckorg("已审核");
		}
		dto.setMembername(membername);
		dto.setBegintime(null);
		dto.setBizId(biz_id.intValue());
		dto.setFamilyno(familyno);
		dto.setHospital(hospital);
		dto.setInDeptName(in_dept_name);
		dto.setPaperid(paperid);
		dto.setSickname(diagnose_name);
		dto.setSmsState(sms_state);
		dto.setCheckState(check_state);
		dto.setSsn(ssn);
		dto.setEstimate(estimate);
		dto.setBizcheckId(bizcheckId);
		dto.setBegintime(begin_date);
		return dto;
	}

	public ExecutSQLDAO getExecutSQLDAO() {
		return executSQLDAO;
	}

	public JzBizcheckDAO getJzBizcheckDAO() {
		return jzBizcheckDAO;
	}

	public JzBizDAO getJzBizDAO() {
		return jzBizDAO;
	}

	public Pager getPager() {
		return pager;
	}

	@Override
	public Boolean saveBizCheck(BizCheckDTO bizCheckDTO) {

		if (bizCheckDTO != null && bizCheckDTO.getBizId() != null) {
			BigDecimal bizcheckId = bizCheckDTO.getBizcheckId();
			JzBiz z = new JzBiz();
			JzBizcheck j = new JzBizcheck();
			j.setBizId(bizCheckDTO.getBizId());
			j.setBizcheckId(bizCheckDTO.getBizcheckId());
			int smsState = Integer.parseInt(bizCheckDTO.getSmsState()
					.toString());
			switch (bizCheckDTO.getLevel()) {
			case 4:
				j.setChecked4(bizCheckDTO.getChecked());
				j.setChecktime4(bizCheckDTO.getChecktime());
				j.setOperator4(bizCheckDTO.getOperator());
				j.setDetail4(bizCheckDTO.getDetail());
				// 业务表
				z.setBizId(bizCheckDTO.getBizId());
				z.setCheckState(bizCheckDTO.getChecked());
				z.setSmsState(new BigDecimal(smsState & 0x0));
				break;
			case 6:
				j.setChecked3(bizCheckDTO.getChecked());
				j.setChecktime3(bizCheckDTO.getChecktime());
				j.setOperator3(bizCheckDTO.getOperator());
				j.setDetail3(bizCheckDTO.getDetail());
				// 业务表
				if (bizCheckDTO.getEstimate() != null
						&& bizCheckDTO.getEstimate().floatValue() < 10000) {
					z.setCheckState(bizCheckDTO.getChecked());
				}
				z.setBizId(bizCheckDTO.getBizId());
				z.setSmsState(new BigDecimal(smsState & 0xb));
				break;
			case 8:
				j.setChecked2(bizCheckDTO.getChecked());
				j.setChecktime2(bizCheckDTO.getChecktime());
				j.setOperator2(bizCheckDTO.getOperator());
				j.setDetail2(bizCheckDTO.getDetail());
				// 业务表
				if (bizCheckDTO.getEstimate() != null
						&& bizCheckDTO.getEstimate().floatValue() < 5000) {
					z.setCheckState(bizCheckDTO.getChecked());
				}
				z.setBizId(bizCheckDTO.getBizId());
				z.setSmsState(new BigDecimal(smsState & 0xd));
				break;
			case 10:
				j.setChecked1(bizCheckDTO.getChecked());
				j.setChecktime1(bizCheckDTO.getChecktime());
				j.setOperator1(bizCheckDTO.getOperator());
				j.setDetail1(bizCheckDTO.getDetail());
				// 业务表
				z.setBizId(bizCheckDTO.getBizId());
				z.setSmsState(new BigDecimal(smsState & 0xe));
				break;
			}

			if (null == bizcheckId) {
				this.jzBizcheckDAO.insert(j);
				this.jzBizDAO.updateByPrimaryKeySelective(z);
			} else {
				j.setBizcheckId(bizcheckId);
				this.jzBizcheckDAO.updateByPrimaryKeySelective(j);
				this.jzBizDAO.updateByPrimaryKeySelective(z);
			}

			return true;
		} else {
			return false;
		}

	}

	public void setExecutSQLDAO(ExecutSQLDAO executSQLDAO) {
		this.executSQLDAO = executSQLDAO;
	}

	public void setJzBizcheckDAO(JzBizcheckDAO jzBizcheckDAO) {
		this.jzBizcheckDAO = jzBizcheckDAO;
	}

	public void setJzBizDAO(JzBizDAO jzBizDAO) {
		this.jzBizDAO = jzBizDAO;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	@SuppressWarnings("rawtypes")
	public List<BusinessDTO> findSalvationQuery(BizCheckDTO bizCheckDTO) {
		List<BusinessDTO> list = new ArrayList<BusinessDTO>();

		String sql = "SELECT BIZ.BIZ_ID, " + " BIZ.NAME,"
				+ " DEPT.NAME AS HNAME," + " DEPT.HOSPITAL_ID,"
				+ " BIZ.BIZ_TYPE," + " SSN," + " FAMILY_ID," + " BEGIN_DATE,"
				+ " BIZ.END_DATE,"
				+ " ROUND(BIZ.END_DATE - BIZ.BEGIN_DATE) AS INDAYS,"
				+ " IN_DISEASE_NAME," + " DIAGNOSE_NAME,"
				+ " FIN_DISEASE_NAME," + " " + " SAL," + " CNT,"
				+ " DM.BIZ_NAME_SHOW"
				+ ", BIZ.IN_DEPT_NAME as deptname FROM JZ_BIZ BIZ,"
				+ " DM_BIZ_TYPE DM," + " (SELECT T.BIZ_ID,"
				+ " SUM(DECODE(T.FUNC_ID, '111', T.REAL_PAY)) AS CNT,"
				+ " SUM(DECODE(T. FUNC_ID, 'Z01', T.REAL_PAY)) AS SAL"
				+ " FROM JZ_PAYLIST T" + " WHERE T.SUM_FLAG = 1"
				+ " GROUP BY T.BIZ_ID) FY," + " DM_BIZDEPT DEPT"
				+ " WHERE DM.BIZ_TYPE = BIZ.BIZ_TYPE"
				+ " AND BIZ.BIZ_ID = FY.BIZ_ID(+)"
				+ " AND DEPT.HOSPITAL_ID = BIZ.HOSPITAL_ID";

		sql = sql + bizCheckDTO.getJwhere()+" order by BEGIN_DATE desc, FAMILY_ID";
		
		try {
			ExecutSQL executSQL = new ExecutSQL();

			executSQL.setExecutsql(sql);
			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl("salvationQuery.action");
			pager.setCurrentpage(bizCheckDTO.getCur_page());
			pager.setPagesize(14);
			pager.getToolsmenu();

			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());

			List<HashMap> rs = executSQLDAO.queryRow(executSQL);

			for (HashMap map : rs) {
				BusinessDTO dto = new BusinessDTO();

				String ssn = (String) map.get("ssn".toUpperCase());
				if (null == ssn || "".equals(ssn)) {
					ssn = "";
				}
				String FAMILY_ID = (String) map.get("FAMILY_ID".toUpperCase());
				if (null == FAMILY_ID || "".equals(FAMILY_ID)) {
					FAMILY_ID = "";
				}
				String membername = (String) map.get("name".toUpperCase());
				if (null == membername || "".equals(membername)) {
					membername = "";
				}
				Date begin_date = (Date) map.get("begin_date".toUpperCase());
				if (null == begin_date || "".equals(begin_date)) {
					begin_date = null;
				}
				Date end_date = (Date) map.get("END_DATE".toUpperCase());
				if (null == end_date || "".equals(end_date)) {
					end_date = null;
				}
				String hname = (String) map.get("hname".toUpperCase());
				if (null == hname || "".equals(hname)) {
					hname = "";
				}
				String DIAGNOSE_NAME = (String) map.get("IN_DISEASE_NAME"
						.toUpperCase());
				if (null == DIAGNOSE_NAME || "".equals(DIAGNOSE_NAME)) {
					DIAGNOSE_NAME = "";
				}
				BigDecimal INDAYS = (BigDecimal) map
						.get("INDAYS".toUpperCase());
				if (null == INDAYS || "".equals(INDAYS)) {
					INDAYS = new BigDecimal(0);
				}
				BigDecimal CNT = (BigDecimal) map.get("CNT".toUpperCase());
				if (null == CNT || "".equals(CNT)) {
					CNT = new BigDecimal(0);
				}
				BigDecimal SAL = (BigDecimal) map.get("SAL".toUpperCase());
				if (null == SAL || "".equals(SAL)) {
					SAL = new BigDecimal(0);
				}

				String deptname = (String) map.get("deptname".toUpperCase());
				if (null == deptname || "".equals(deptname)) {
					deptname = "";
				}
				String BIZ_NAME_SHOW = (String) map.get("BIZ_NAME_SHOW"
						.toUpperCase());
				if (null == BIZ_NAME_SHOW || "".equals(BIZ_NAME_SHOW)) {
					BIZ_NAME_SHOW = "";
				}

				if ("住院".equals(BIZ_NAME_SHOW) && null != end_date) {
					BIZ_NAME_SHOW = "出院";
				}
				if ("住院".equals(BIZ_NAME_SHOW) && null == end_date) {
					BIZ_NAME_SHOW = "住院";
				}
				BigDecimal BIZ_ID = (BigDecimal) map
						.get("BIZ_ID".toUpperCase());
				if (null == BIZ_ID || "".equals(BIZ_ID)) {
					BIZ_ID = new BigDecimal(0);
				}

				dto.setHospital(hname);
				dto.setEndtime(end_date);
				dto.setBegintime(begin_date);
				dto.setDays(INDAYS.toString());
				dto.setSickname(DIAGNOSE_NAME);
				dto.setMedicaltypename(BIZ_NAME_SHOW);
				dto.setAllmoney(SAL.toString());
				dto.setFamilyno(FAMILY_ID);
				dto.setBizId(BIZ_ID.toString());
				dto.setSsn(ssn);
				dto.setBearpayment(membername);
				dto.setBigpayment(deptname);
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
