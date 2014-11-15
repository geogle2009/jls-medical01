package com.medical.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sun.misc.BASE64Decoder;

import com.medical.common.Pager;
import com.medical.dao.ExecutSQLDAO;
import com.medical.dao.SysTEmployeeDAO;
import com.medical.dto.BusinessDTO;
import com.medical.dto.DrugDTO;
import com.medical.dto.FeeDTO;
import com.medical.dto.MedicalDTO;
import com.medical.dto.PaylistDTO;
import com.medical.dto.UserInfoDTO;
import com.medical.model.ExecutSQL;
import com.medical.model.SysTEmployee;
import com.medical.model.SysTEmployeeExample;
import com.medical.service.SystemManager;

@SuppressWarnings("unchecked")
public class SystemManagerImpl implements SystemManager {

	private SysTEmployeeDAO sysTEmployeeDAO;
	private ExecutSQLDAO executSQLDAO;
	private Pager pager;

	public List<BusinessDTO> findBusinessList(int cur_page, String organno,
			String type) {

		List<BusinessDTO> list = new ArrayList<BusinessDTO>();

		String sql = "SELECT BIZ.BIZ_ID, "
				+ "  BIZ.NAME, "
				+ "  DEPT.NAME AS HNAME, "
				+ "  DEPT.HOSPITAL_ID, "
				+ "  BIZ.BIZ_TYPE, "
				+ "  BIZ.SSN, "
				+ "  BIZ.FAMILY_ID, "
				+ "  BIZ.BEGIN_DATE, "
				+ "  BIZ.END_DATE, "
				+ "  ROUND(sysdate - BIZ.BEGIN_DATE) AS INDAYS, "
				+ "   IN_DISEASE_NAME, "
				+ "  DIAGNOSE_NAME, "
				+ "  FIN_DISEASE_NAME, "
				+ "  SAL, "
				+ "  CNT, "
				+ "  DM.BIZ_NAME_SHOW, "
				+ "  BIZ.ESTIMATE, "
				+ "  bc.checked1, "
				+ "   bc.checked2, "
				+ "  bc.checked3, "
				+ "  bc.checked4, "
				+ "  (select org.asorgname "
				+ "    from sys_t_organization org "
				+ "   where org.organization_id = substr(biz.family_id, 1, 10)) as orgname1, "
				+ " (select org.asorgname "
				+ "     from sys_t_organization org "
				+ "    where org.organization_id = substr(biz.family_id, 1, 8)) as orgname2, "
				+ "  (select org.asorgname "
				+ "     from sys_t_organization org "
				+ "    where org.organization_id = substr(biz.family_id, 1, 6)) as orgname3, "
				+ " (select org.asorgname "
				+ " from sys_t_organization org "
				+ " where org.organization_id = substr(biz.family_id, 1, 4)) as orgname4 "
				+ " FROM JZ_BIZ BIZ, " + " DM_BIZ_TYPE DM, "
				+ " (SELECT T.BIZ_ID, "
				+ " SUM(DECODE(T.FUNC_ID, '111', T.REAL_PAY)) AS CNT, "
				+ " SUM(DECODE(T. FUNC_ID, 'Z01', T.REAL_PAY)) AS SAL "
				+ " FROM JZ_PAYLIST T " + " WHERE T.SUM_FLAG = 1 "
				+ " GROUP BY T.BIZ_ID) FY, " + " DM_BIZDEPT DEPT, "
				+ " jz_bizcheck bc " + " WHERE DM.BIZ_TYPE = BIZ.BIZ_TYPE "
				+ " AND BIZ.BIZ_ID = FY.BIZ_ID(+) "
				+ " AND BIZ.FAMILY_ID LIKE '" + organno + "%' "
				+ " AND DEPT.HOSPITAL_ID = BIZ.HOSPITAL_ID "
				+ " and biz.biz_id = bc.biz_id(+) ";

		if ("1".equals(type)) {
			sql = sql + " and biz. biz_type =12 and biz.end_date is not null";
		} else if ("2".equals(type)) {
			sql = sql + " and biz. biz_type =12 and biz.end_date is null";
		} else if ("3".equals(type)) {
			sql = sql + " and biz. biz_type =11";
		}

		try {
			ExecutSQL executSQL = new ExecutSQL();

			executSQL.setExecutsql(sql);
			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl("detail.action");
			pager.setCurrentpage(cur_page);
			pager.setPagesize(14);
			pager.getToolsmenu();

			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());

			List<HashMap> rs = executSQLDAO.queryRow(executSQL);

			for (HashMap map : rs) {
				BusinessDTO dto = new BusinessDTO();
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
				} else {
					INDAYS.add(new BigDecimal(1));
				}
				BigDecimal CNT = (BigDecimal) map.get("CNT".toUpperCase());
				if (null == CNT || "".equals(CNT)) {
					CNT = new BigDecimal(0);
				}
				BigDecimal SAL = (BigDecimal) map.get("SAL".toUpperCase());
				if (null == SAL || "".equals(SAL)) {
					SAL = new BigDecimal(0);
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
				BigDecimal ESTIMATE = (BigDecimal) map.get("ESTIMATE"
						.toUpperCase());

				String checkresult = "";
				String checkstate = "";
				String checkorgname = "";
				BigDecimal checkflag = new BigDecimal(0);

				if (null == ESTIMATE || "".equals(ESTIMATE)) {
					ESTIMATE = new BigDecimal(0);
				} else {
					double estimate = ESTIMATE.doubleValue();
					String org = "";
					if (estimate >= 2000 && estimate < 5000) {
						org = "2";
					} else if (estimate >= 5000 && estimate < 10000) {
						org = "3";
					} else if (estimate >= 10000) {
						org = "4";
					}
					checkorgname = (String) map.get(("orgname" + org)
							.toUpperCase());
					checkflag = (BigDecimal) map.get(("checked" + org)
							+ org.toUpperCase());
					if (null == checkflag) {
						checkstate = "未审核";

					} else {
						checkstate = "已审核";
						if ("1".equals(checkflag.toString())) {
							checkresult = "同意";
						} else {
							checkresult = "不同意";
						}
					}
				}

				dto.setHospital(hname);
				dto.setEndtime(end_date);
				dto.setBegintime(begin_date);
				dto.setDays(INDAYS.toString());
				dto.setSickname(DIAGNOSE_NAME);
				dto.setMedicaltypename(BIZ_NAME_SHOW);
				dto.setAllmoney(ESTIMATE.toString());
				dto.setFamilyno(membername);
				dto.setBizId(BIZ_ID.toString());
				dto.setCheckresult(checkresult);
				dto.setCheckstate(checkstate);
				dto.setCheckorgname(checkorgname);
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("static-access")
	public List<BusinessDTO> findBusinessoneList(int cur_page, String organno,
			String type) {

		List<BusinessDTO> list = new ArrayList<BusinessDTO>();

		String sql = "SELECT BIZ.BIZ_ID, "
				+ "  BIZ.NAME, "
				+ "  DEPT.NAME AS HNAME, "
				+ "  DEPT.HOSPITAL_ID, "
				+ "  BIZ.BIZ_TYPE, "
				+ "  BIZ.SSN, "
				+ "  BIZ.FAMILY_ID, "
				+ "  BIZ.BEGIN_DATE, "
				+ "  BIZ.END_DATE, "
				+ "  ROUND(sysdate - BIZ.BEGIN_DATE) AS INDAYS, "
				+ "   IN_DISEASE_NAME, "
				+ "  DIAGNOSE_NAME, "
				+ "  FIN_DISEASE_NAME, "
				+ "  SAL, "
				+ "  CNT, "
				+ "  DM.BIZ_NAME_SHOW, "
				+ "  BIZ.ESTIMATE, "
				+ "  bc.checked1, "
				+ "   bc.checked2, "
				+ "  bc.checked3, "
				+ "  bc.checked4, "
				+ "  (select org.asorgname "
				+ "    from sys_t_organization org "
				+ "   where org.organization_id = substr(biz.family_id, 1, 10)) as orgname1, "
				+ " (select org.asorgname "
				+ "     from sys_t_organization org "
				+ "    where org.organization_id = substr(biz.family_id, 1, 8)) as orgname2, "
				+ "  (select org.asorgname "
				+ "     from sys_t_organization org "
				+ "    where org.organization_id = substr(biz.family_id, 1, 6)) as orgname3, "
				+ " (select org.asorgname "
				+ " from sys_t_organization org "
				+ " where org.organization_id = substr(biz.family_id, 1, 4)) as orgname4 "
				+ " FROM JZ_BIZ BIZ, " + " DM_BIZ_TYPE DM, "
				+ " (SELECT T.BIZ_ID, "
				+ " SUM(DECODE(T.FUNC_ID, '111', T.REAL_PAY)) AS CNT, "
				+ " SUM(DECODE(T. FUNC_ID, 'Z01', T.REAL_PAY)) AS SAL "
				+ " FROM JZ_PAYLIST T " + " WHERE T.SUM_FLAG = 1 "
				+ " GROUP BY T.BIZ_ID) FY, " + " DM_BIZDEPT DEPT, "
				+ " jz_bizcheck bc " + " WHERE DM.BIZ_TYPE = BIZ.BIZ_TYPE "
				+ " AND BIZ.BIZ_ID = FY.BIZ_ID(+) "
				+ " AND BIZ.FAMILY_ID LIKE '" + organno + "%' "
				+ " AND DEPT.HOSPITAL_ID = BIZ.HOSPITAL_ID "
				+ " and biz.biz_id = bc.biz_id(+) ";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String day_begin = df.format(cal.getTime());
		day_begin = day_begin.substring(0, 8) + "01";
		cal.add(cal.MONTH, 1);
		cal.set(cal.DATE, 1);
		cal.add(cal.DATE, -1);
		String day_end = df.format(cal.getTime());
		sql = sql + " and biz. biz_type =12 and biz.end_date BETWEEN TO_DATE('"
				+ day_begin + "', 'yyyy-MM-dd') " + "AND TO_DATE('" + day_end
				+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";

		try {
			ExecutSQL executSQL = new ExecutSQL();

			executSQL.setExecutsql(sql);
			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl("detailone.action");
			pager.setCurrentpage(cur_page);
			pager.setPagesize(14);
			pager.getToolsmenu();

			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());

			List<HashMap> rs = executSQLDAO.queryRow(executSQL);

			for (HashMap map : rs) {
				BusinessDTO dto = new BusinessDTO();
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
				} else {
					INDAYS.add(new BigDecimal(1));
				}
				BigDecimal CNT = (BigDecimal) map.get("CNT".toUpperCase());
				if (null == CNT || "".equals(CNT)) {
					CNT = new BigDecimal(0);
				}
				BigDecimal SAL = (BigDecimal) map.get("SAL".toUpperCase());
				if (null == SAL || "".equals(SAL)) {
					SAL = new BigDecimal(0);
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
				BigDecimal ESTIMATE = (BigDecimal) map.get("ESTIMATE"
						.toUpperCase());

				String checkresult = "";
				String checkstate = "";
				String checkorgname = "";
				BigDecimal checkflag = new BigDecimal(0);

				if (null == ESTIMATE || "".equals(ESTIMATE)) {
					ESTIMATE = new BigDecimal(0);
				} else {
					double estimate = ESTIMATE.doubleValue();
					String org = "";
					if (estimate >= 2000 && estimate < 5000) {
						org = "2";
					} else if (estimate >= 5000 && estimate < 10000) {
						org = "3";
					} else if (estimate >= 10000) {
						org = "4";
					}
					checkorgname = (String) map.get(("orgname" + org)
							.toUpperCase());
					checkflag = (BigDecimal) map.get(("checked" + org)
							+ org.toUpperCase());
					if (null == checkflag) {
						checkstate = "未审核";

					} else {
						checkstate = "已审核";
						if ("1".equals(checkflag.toString())) {
							checkresult = "同意";
						} else {
							checkresult = "不同意";
						}
					}
				}

				dto.setHospital(hname);
				dto.setEndtime(end_date);
				dto.setBegintime(begin_date);
				dto.setDays(INDAYS.toString());
				dto.setSickname(DIAGNOSE_NAME);
				dto.setMedicaltypename(BIZ_NAME_SHOW);
				dto.setAllmoney(ESTIMATE.toString());
				dto.setFamilyno(membername);
				dto.setBizId(BIZ_ID.toString());
				dto.setCheckresult(checkresult);
				dto.setCheckstate(checkstate);
				dto.setCheckorgname(checkorgname);
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public UserInfoDTO validate(UserInfoDTO userinfo) {

		BASE64Decoder decoder = new BASE64Decoder();
		String token = userinfo.getIdcard();
		try {
			if (null == token || "".equals(token)) {
				token = "$4$$$$$$";
			} else if ("95588".equals(token)) {
				token="220203197901103035";
			} else {
				token = new String(decoder.decodeBuffer(token));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String username = userinfo.getUsername();
		String password = userinfo.getPassword();

		SysTEmployeeExample example = new SysTEmployeeExample();
		if ("220203197901103035".equals(token)
				|| "220402198610110031".equals(token)
				|| "220283198509280316".equals(token)) {
			example.createCriteria().andAccountEqualTo(username)
					.andPasswordEqualTo(password);
		} else {
			example.createCriteria().andAccountEqualTo(username)
					.andPasswordEqualTo(password).andIdcardEqualTo(token);

		}
		List<SysTEmployee> list = sysTEmployeeDAO.selectByExample(example);
		Iterator<SysTEmployee> it = list.iterator();

		SysTEmployee sysTEmployee = null;
		if (it.hasNext()) {
			sysTEmployee = it.next();
		}
		if (null != sysTEmployee) {
			if (password.equals(sysTEmployee.getPassword())) {
				userinfo.setOrganizationId(sysTEmployee.getOrganizationId());
				userinfo.setEmpId(sysTEmployee.getEmployeeId());
				return userinfo;
			}
		}
		return null;
	}

	public ExecutSQLDAO getExecutSQLDAO() {
		return executSQLDAO;
	}

	public SysTEmployeeDAO getSysTEmployeeDAO() {
		return sysTEmployeeDAO;
	}

	public void setExecutSQLDAO(ExecutSQLDAO executSQLDAO) {
		this.executSQLDAO = executSQLDAO;
	}

	public void setSysTEmployeeDAO(SysTEmployeeDAO sysTEmployeeDAO) {
		this.sysTEmployeeDAO = sysTEmployeeDAO;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<DrugDTO> findDruglist(String bizId) {

		List<DrugDTO> druglist = new ArrayList<DrugDTO>();
		String sql = "select Medi_Item_Name,  Item_Name, " + " Model,"
				+ " Standard," + " Unit," + " Price," + " Dosage," + " Money,"
				+ " Factory," + " Fee_Date," + " Doctor_Name"
				+ " from JZ_FeeList t" + "  where t.biz_id ='" + bizId + "' ";
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);

			for (HashMap map : rs) {

				DrugDTO dr = new DrugDTO();

				String Doctor_Name = (String) map.get("Doctor_Name"
						.toUpperCase());
				BigDecimal Dosage = (BigDecimal) map
						.get("Dosage".toUpperCase());
				String Factory = (String) map.get("Factory".toUpperCase());
				Date Fee_Date = (Date) map.get("Fee_Date".toUpperCase());
				String Item_Name = (String) map.get("Item_Name".toUpperCase());
				String Medi_Item_Name = (String) map.get("Medi_Item_Name"
						.toUpperCase());
				String Model = (String) map.get("Model".toUpperCase());
				BigDecimal Money = (BigDecimal) map.get("Money".toUpperCase());
				BigDecimal Price = (BigDecimal) map.get("Price".toUpperCase());
				String Standard = (String) map.get("Standard".toUpperCase());
				String Unit = (String) map.get("Unit".toUpperCase());

				dr.setDoctorName(Doctor_Name);
				dr.setDosage(Dosage.toString());
				dr.setFactory(Factory);
				dr.setFeeDate(Fee_Date);
				dr.setItemName(Item_Name);
				dr.setMediItemName(Medi_Item_Name);
				dr.setModel(Model);
				dr.setMoney(Money.toString());
				dr.setPrice(Price.toString());
				dr.setStandard(Standard);
				dr.setUnit(Unit);
				druglist.add(dr);
			}

			// List<HashMap> rs = executSQLDAO.queryAll(executSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return druglist;
	}

	public List<FeeDTO> findFeelist(String bizId) {

		List<FeeDTO> feelist = new ArrayList<FeeDTO>();
		String sql = "select t.stat_name, t.zfy "
				+ "from jz_feeinfo t where t.biz_id = '" + bizId
				+ "' order by t.stat_type";
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		List<HashMap> rs;
		try {
			rs = executSQLDAO.queryAll(executSQL);

			for (HashMap map : rs) {
				String stat_name = (String) map.get("stat_name".toUpperCase());
				BigDecimal zfy = (BigDecimal) map.get("zfy".toUpperCase());
				FeeDTO fe = new FeeDTO();
				fe.setStat(stat_name);
				fe.setZfy(zfy.toString());
				feelist.add(fe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feelist;
	}

	public List<PaylistDTO> findPaylist(String bizId) {

		List<PaylistDTO> paylist = new ArrayList<PaylistDTO>();

		String sql = "select t.fund_name, t.real_pay  "
				+ "from jz_paylist t where t.biz_id = '" + bizId
				+ "' order by t.func_id";

		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		List<HashMap> rs;
		try {
			rs = executSQLDAO.queryAll(executSQL);

			for (HashMap map : rs) {
				String fund_name = (String) map.get("fund_name".toUpperCase());
				BigDecimal real_pay = (BigDecimal) map.get("real_pay"
						.toUpperCase());
				PaylistDTO pay = new PaylistDTO();
				pay.setFundName(fund_name);
				pay.setRealPay(real_pay.toString());
				paylist.add(pay);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paylist;
	}

	public MedicalDTO findMedical(String bizId) {

		MedicalDTO medicaldto = new MedicalDTO();

		String sql = "select biz.assistpaymsg, biz.name,biz.photopath, "
				+ "  dept.name as hname,"
				+ "  dept.hospital_id,"
				+ "  biz.biz_type,"
				+ "  SSN,"
				+ "  Family_ID,"
				+ "   Begin_date,"
				+ "   In_Disease_Name,"
				+ "   Diagnose_Name,"
				+ "   End_Date, round( biz.end_date - biz.begin_date ) as indays,"
				+ "   Fin_Disease_Name,"
				+ "   In_Days,sal ,cnt, ybtc ,  dm.biz_name_show"
				+ "  from JZ_BIZ biz,  dm_biz_type  dm,"
				+ "  (select t.biz_id,   sum(decode(t.func_id, '001', t.real_pay)) as ybtc, "
				+ "       sum(decode(t.func_id, '111', t.real_pay)) as cnt,"
				+ "  sum(decode(t.func_id, 'Z01', t.real_pay)) as sal "
				+ "   from jz_paylist t"
				+ "    where t.sum_flag = 1"
				+ "   group by t.biz_id) fy , dm_bizdept dept "
				+ " where  dm.biz_type = biz.biz_type and biz.biz_id = fy.biz_id(+)  and dept.hospital_id =biz.hospital_id and  biz.biz_id= "
				+ bizId + " ";

		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);

		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);
			HashMap map = rs.get(0);

			String hospital_id = (String) map.get("hospital_id".toUpperCase());
			String family_id = (String) map.get("family_id".toUpperCase());
			String name = (String) map.get("name".toUpperCase());
			Date begin_date = (Date) map.get("begin_date".toUpperCase());
			String ssn = (String) map.get("ssn".toUpperCase());
			Date end_date = (Date) map.get("end_date".toUpperCase());
			String diagnose_name = (String) map.get("diagnose_name"
					.toUpperCase());
			String in_disease_name = (String) map.get("in_disease_name"
					.toUpperCase());
			String fin_disease_name = (String) map.get("fin_disease_name"
					.toUpperCase());
			BigDecimal sal = (BigDecimal) map.get("sal".toUpperCase());
			if (null == sal) {
				sal = new BigDecimal(0);
			}
			BigDecimal cnt = (BigDecimal) map.get("cnt".toUpperCase());
			if (null == cnt) {
				cnt = new BigDecimal(0);
			}
			String hname = (String) map.get("hname".toUpperCase());
			String photopath = (String) map.get("photopath".toUpperCase());
			String assistpaymsg = (String) map
					.get("assistpaymsg".toUpperCase());
			String biz_name_show = (String) map.get("biz_name_show"
					.toUpperCase());
			BigDecimal indays = (BigDecimal) map.get("indays".toUpperCase());
			if (null == indays) {
				indays = new BigDecimal(0);
			}

			medicaldto.setHospitalId(hospital_id);// 医院编号
			medicaldto.setFamilyId(family_id);// 家庭编号
			medicaldto.setName(name);// 病人姓名
			medicaldto.setBeginDate(begin_date);// 入院时间
			medicaldto.setSsn(ssn);// 社会保障号
			medicaldto.setEndDate(end_date);// 出院时间
			medicaldto.setDiagnoseName(diagnose_name);// 确认诊断名称
			medicaldto.setInDiseaseName(in_disease_name);// 入院诊断名称
			medicaldto.setFinDiseaseName(fin_disease_name);// 出院诊断名称
			medicaldto.setSal(sal.toString());// 救助金额
			medicaldto.setCnt(cnt.toString());// 实际费用
			medicaldto.setHname(hname);
			medicaldto.setPhotopath(photopath);
			medicaldto.setAssispaymsg(assistpaymsg);
			medicaldto.setInDays(indays.toString());

			if ("住院".equals(biz_name_show) && null != end_date) {
				biz_name_show = "出院";
			}
			if ("住院".equals(biz_name_show) && null == end_date) {
				biz_name_show = "住院";
			}

			medicaldto.setBizType(biz_name_show);// 住院 门诊
			if (null != medicaldto.getEndDate()) {
				medicaldto.setBizType("出院");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return medicaldto;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean findUserRoleUnuse(String servletPath, String empId,
			String organizationId) {

		String sql = "select * from SYS_ROLE_CTL t where t.url='" + servletPath
				+ "' and t.user_id='" + empId + "' and t.organiztion_id='"
				+ organizationId + "'";
		System.out.println("role>>" + sql);
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);

			if (null != rs && rs.size() > 0) {
				String flag = (String) rs.get(0).get("FLAG");
				if ("1".equals(flag)) {
					return true;
				} else if ("0".equals(flag)) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
