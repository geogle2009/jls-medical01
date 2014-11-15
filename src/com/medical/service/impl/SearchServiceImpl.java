package com.medical.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.medical.common.Pager;
import com.medical.dao.DmBizdeptDAO;
import com.medical.dao.ExecutSQLDAO;
import com.medical.dao.SysTOrganizationDAO;
import com.medical.dao.VbSearchinfoDAO;
import com.medical.dto.ChronicBillDTO;
import com.medical.dto.DeptDTO;
import com.medical.dto.FeeDTO;
import com.medical.dto.OrganDTO;
import com.medical.dto.SearchDTO;
import com.medical.dto.Yearmonth;
import com.medical.model.DmBizdept;
import com.medical.model.DmBizdeptExample;
import com.medical.model.ExecutSQL;
import com.medical.model.SysTOrganization;
import com.medical.model.SysTOrganizationExample;
import com.medical.model.VbSearchinfo;
import com.medical.model.VbSearchinfoExample;
import com.medical.model.VbSearchinfoExample.Criteria;
import com.medical.service.SearchService;

@SuppressWarnings("unchecked")
public class SearchServiceImpl implements SearchService {
	private VbSearchinfoDAO vbSearchinfoDAO;
	private Pager pager;
	private String toolsmenu;
	private SysTOrganizationDAO sysTOrganizationDAO;
	private ExecutSQLDAO executSQLDAO;
	private DmBizdeptDAO dmBizdeptDAO;

	public DmBizdeptDAO getDmBizdeptDAO() {
		return dmBizdeptDAO;
	}

	public void setDmBizdeptDAO(DmBizdeptDAO dmBizdeptDAO) {
		this.dmBizdeptDAO = dmBizdeptDAO;
	}

	public ExecutSQLDAO getExecutSQLDAO() {
		return executSQLDAO;
	}

	public void setExecutSQLDAO(ExecutSQLDAO executSQLDAO) {
		this.executSQLDAO = executSQLDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medical.service.impl.SearchService#findByTerm(java.lang.String)
	 */
	public List<SearchDTO> findByTerm(SearchDTO searchDTO) {

		List<SearchDTO> list = new ArrayList<SearchDTO>();

		VbSearchinfoExample example = new VbSearchinfoExample();
		Criteria c = example.createCriteria();
		if (searchDTO != null) {
			String membername = searchDTO.getMembername();
			if (membername != null && !"".equals(membername)) {
				String operator = searchDTO.getOp_membername();
				if ("=".equals(operator)) {
					c.andMembernameEqualTo(membername);
				} else if ("like".equals(operator)) {
					c.andMembernameLike("%" + membername + "%");
				} else if ("left".equals(operator)) {
					c.andMembernameLike("%" + membername);
				} else if ("right".equals(operator)) {
					c.andMembernameLike(membername + "%");
				}
			}
			String paperid = searchDTO.getPaperid();
			if (paperid != null && !"".equals(paperid)) {
				String operator = searchDTO.getOp_paperid();
				if ("=".equals(operator)) {
					c.andPaperidEqualTo(paperid);
				} else if ("like".equals(operator)) {
					c.andPaperidLike("%" + paperid + "%");
				}
			}
			String ssn = searchDTO.getSsn();
			if (ssn != null && !"".equals(ssn)) {
				String operator = searchDTO.getOp_paperid();
				if ("=".equals(operator)) {
					c.andSsnEqualTo(ssn);
				} else if ("like".equals(operator)) {
					c.andSsnLike("%" + ssn + "%");
				}
			}
			String familyno = searchDTO.getFamilyno();
			if (familyno != null && !"".equals(familyno)) {
				String operator = searchDTO.getOp_familyno();
				if ("=".equals(operator)) {
					c.andFamilynoEqualTo(familyno);
				} else if ("like".equals(operator)) {
					c.andFamilynoLike("%" + familyno + "%");
				} else if ("left".equals(operator)) {
					c.andFamilynoLike(familyno + "%");

				}
			}
			String hospitalId = searchDTO.getHospitalId();
			if (hospitalId != null && !"".equals(hospitalId)) {
				String operator = searchDTO.getOp_hospitalId();
				if ("=".equals(operator)) {
					c.andHospitalIdEqualTo(hospitalId);
				}
			}
			String diagnoseName = searchDTO.getDiagnoseName();
			if (diagnoseName != null && !"".equals(diagnoseName)) {
				String operator = searchDTO.getOp_diagnoseName();
				if ("=".equals(operator)) {
					c.andDiagnoseNameEqualTo(diagnoseName);
				}
			}
			Date beginDate1 = searchDTO.getBeginDate1();
			Date beginDate2 = searchDTO.getBeginDate2();
			if (beginDate1 != null && beginDate2 != null) {
				String operator = searchDTO.getOp_beginDate();
				if ("between".equals(operator)) {
					c.andBeginDateBetween(beginDate1, beginDate2);
				}
			}
			String assistType = searchDTO.getAssistType();
			if (assistType != null && !"".equals(assistType)) {
				String operator = searchDTO.getOp_assistType();
				if ("=".equals(operator)) {
					c.andAssistTypeEqualTo(assistType);
				}
			}
			String allmoney = searchDTO.getAllmoney();
			if (allmoney != null && !"".equals(allmoney)) {
				BigDecimal value = new BigDecimal(allmoney);
				String operator = searchDTO.getOp_allmoney();
				if (">".equals(operator)) {
					c.andAllmoneyGreaterThan(value);
				} else if ("<".equals(operator)) {
					c.andAllmoneyLessThan(value);
				} else if ("=".equals(operator)) {
					c.andAllmoneyEqualTo(value);
				}
			}
			Short outFlag = searchDTO.getOutFlag();
			if (outFlag != null && !"".equals(outFlag)) {
				String operator = searchDTO.getOp_outFlag();
				if ("=".equals(operator)) {
					c.andOutFlagEqualTo(outFlag);
				}
			}
			//
			this.pager.setCurrentpage(searchDTO.getCurpage());
			this.pager.setAll(vbSearchinfoDAO.countByExample(example));
			this.pager.setUrl(searchDTO.getUrl());
			this.pager.setPagesize(searchDTO.getPageSize());
			toolsmenu = this.pager.genToolsmenu();

			//
			Iterator<VbSearchinfo> it = vbSearchinfoDAO.selectByExample(
					example, pager.getStart(), pager.getPagesize()).iterator();
			while (it.hasNext()) {
				SearchDTO dto = new SearchDTO();
				VbSearchinfo po = it.next();
				dto.setMembername(po.getMembername());
				dto.setFamilyno(po.getFamilyno());
				dto.setSsn(po.getSsn());
				dto.setPaperid(po.getPaperid());
				dto.setBeginDate1(po.getBeginDate());
				dto.setHospitalName(po.getHospitalName());
				dto.setDiagnoseName(po.getDiagnoseName());
				if (po.getAllmoney() != null) {
					dto.setAllmoney(po.getAllmoney().toString());
				}
				if (null == po.getInsurancepay()) {
				} else {
					dto.setInsurepay(po.getInsurancepay().toString());
				}
				if (null == po.getAssistpay()) {
				} else {
					dto.setAssistpay(po.getAssistpay());
				}
				if (null == po.getPersonpay()) {
				} else {
					dto.setPersonpay(po.getPersonpay());
				}
				list.add(dto);
			}
		}
		return list;
	}

	public SysTOrganizationDAO getSysTOrganizationDAO() {
		return sysTOrganizationDAO;
	}

	public void setSysTOrganizationDAO(SysTOrganizationDAO sysTOrganizationDAO) {
		this.sysTOrganizationDAO = sysTOrganizationDAO;
	}

	public List<SearchDTO> findForBizlist() {
		List<SearchDTO> list = new ArrayList<SearchDTO>();
		VbSearchinfoExample example = new VbSearchinfoExample();
		example.createCriteria().andAllmoneyGreaterThan(new BigDecimal(0));
		example.setOrderByClause("BEGIN_DATE ,FAMILYNO");
		Iterator<VbSearchinfo> it = vbSearchinfoDAO.selectByExample(example)
				.iterator();
		while (it.hasNext()) {
			VbSearchinfo po = it.next();
			SearchDTO dto = new SearchDTO();
			dto.setMembername(po.getMembername());
			dto.setFamilyno(po.getFamilyno());
			dto.setBeginDate1(po.getBeginDate());
			dto.setHospitalName(po.getHospitalName());
			dto.setAllmoney(po.getAllmoney().toString());
			dto.setAssistpay(po.getAssistpay());
			dto.setPersonpay(po.getPersonpay());
			list.add(dto);
		}
		return list;
	}

	public VbSearchinfoDAO getVbSearchinfoDAO() {
		return vbSearchinfoDAO;
	}

	public void setVbSearchinfoDAO(VbSearchinfoDAO vbSearchinfoDAO) {
		this.vbSearchinfoDAO = vbSearchinfoDAO;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getToolsmenu() {

		return toolsmenu;
	}

	public void setToolsmenu(String toolsmenu) {
		this.toolsmenu = toolsmenu;
	}

	public List<OrganDTO> getOrganList(String organid) {

		List<SysTOrganization> rs = new ArrayList<SysTOrganization>();
		List<OrganDTO> orgs = new ArrayList<OrganDTO>();

		try {

			SysTOrganizationExample example = new SysTOrganizationExample();
			example.createCriteria().andParentorgidEqualTo(organid);
			example.setOrderByClause("ORGANIZATION_ID");
			rs = this.sysTOrganizationDAO.selectByExample(example);

			rs.add(0, this.sysTOrganizationDAO.selectByPrimaryKey(organid));

			for (SysTOrganization sysTOrganization : rs) {

				OrganDTO organDTO = new OrganDTO();
				organDTO.setFullname(sysTOrganization.getFullname());
				organDTO.setOrgid(sysTOrganization.getOrganizationId());
				organDTO.setOrgname(sysTOrganization.getAsorgname());
				orgs.add(organDTO);

			}

		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return orgs;
	}

	@SuppressWarnings("rawtypes")
	public String queryMemberCounts(String jwhere) {
		String str = "";
		ExecutSQL executSQL = new ExecutSQL();
		String sql = "SELECT  count(*) as cnt , sum(biz.allmoney) as allmoney,  sum(a.ASSISTPAY) as ASSISTPAY ,  sum(a.INSURANCEPAY) INSURANCEPAY"
				+ " FROM JZ_BIZ BIZ, DM_BIZDEPT DEPT, "
				+ " (select t.biz_id, sum(t.assistmoney) as ASSISTPAY, "
				+ " sum(t.insurancepay) AS INSURANCEPAY from JZ_BIZ_HISTORY T "
				+ " group by t.biz_id) a "
				+ " WHERE DEPT.HOSPITAL_ID = BIZ.HOSPITAL_ID "
				+ " and biz.ASSIST_FLAG = '1' "
				+ jwhere
				+ " and a.biz_id = biz.biz_id " + " AND BIZ.STAUS = '1' ";

		sql = "SELECT   count(*) as cnt , sum(biz.allmoney) as allmoney,  sum(a.ASSISTPAY) as ASSISTPAY , "
				+ " sum(a.INSURANCEPAY) INSURANCEPAY FROM JZ_BIZ BIZ, DM_BIZDEPT DEPT  ,"
				+ " (select t.biz_id, sum(t.assistmoney) as ASSISTPAY, "
				+ " sum(t.insurancepay) AS INSURANCEPAY from JZ_BIZ_HISTORY T "
				+ " group by t.biz_id) a "
				+ " WHERE DEPT.HOSPITAL_ID = BIZ.HOSPITAL_ID "
				+ " and biz.ASSIST_FLAG = '1' "
				+ jwhere
				+ " and a.biz_id = biz.biz_id "
				+ " AND BIZ.STAUS = '1'  "
				+ " order by BIZ.OPERTIME, BIZ.FAMILY_ID";
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);
			if (null != rs && rs.size() > 0) {
				HashMap map = (HashMap) rs.get(0);
				BigDecimal m = (BigDecimal) map.get("ASSISTPAY");
				BigDecimal m1 = (BigDecimal) map.get("ALLMONEY");
				BigDecimal m2 = (BigDecimal) map.get("INSURANCEPAY");
				if (null == m) {
					m = new BigDecimal(0);
				}
				if (null == m1) {
					m1 = new BigDecimal(0);
				}
				str = "救助人次：" + map.get("CNT") + "   总费用：" + m1.toString()
						+ "元      总救助金：" + m.toString() + "元           统筹金额："
						+ m2.toString() + "元";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return str;
	}

	public String queryMemberStatCount(String sql) {

		String str = "";

		ExecutSQL executSQL = new ExecutSQL();
		sql = "select count(cs) as c1 , sum(totalmoney) as c2 ,sum(insurancepay) as c3, sum(assismoney) as c4,sum(realplay) as c5 from ("
				+ sql + ")";
		executSQL.setExecutsql(sql);

		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);
			if (null != rs && rs.size() > 0) {
				HashMap map = (HashMap) rs.get(0);
				BigDecimal m1 = (BigDecimal) map.get("C1");
				if (null == m1) {
					m1 = new BigDecimal(0);
				}
				BigDecimal m2 = (BigDecimal) map.get("C2");
				if (null == m2) {
					m2 = new BigDecimal(0);
				}
				BigDecimal m3 = (BigDecimal) map.get("C3");
				if (null == m3) {
					m3 = new BigDecimal(0);
				}
				BigDecimal m4 = (BigDecimal) map.get("C4");
				if (null == m4) {
					m4 = new BigDecimal(0);
				}
				BigDecimal m5 = (BigDecimal) map.get("C5");
				if (null == m5) {
					m5 = new BigDecimal(0);
				}
				str = "救助人数：" + m1.toString() + "&nbsp;&nbsp;总费用："
						+ m2.toString() + "元&nbsp;&nbsp;总救助金：" + m4.toString()
						+ "元&nbsp;&nbsp;医保救助:" + m3.toString()
						+ "元&nbsp;&nbsp;个人支付:" + m5.toString() + "元";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return str;
	}

	public List<SearchDTO> queryMembers(String jwhere, int cur_page, String url) {
		List<SearchDTO> list = new ArrayList<SearchDTO>();
		/*
		 * + " and (BIZ.FAMILY_ID like '2202%' or biz.family_id like '22002%') "
		 * + " and length(biz.family_id) = 14 "
		 */
		String sql = "SELECT BIZ.BIZ_ID, BIZ.BIZ_TYPE, BIZ.BIZ_NAME, "
				+ " BIZ.IDCARD,  BIZ.DIAGNOSE_NAME, BIZ.FAMILY_ID, "
				+ " BIZ.SSN, BIZ.NAME, BIZ.OUT_FLAG, BIZ.ALLMONEY, "
				+ " a.ASSISTPAY, a.INSURANCEPAY, DEPT.NAME as HANME, "
				+ " BIZ.BEGIN_DATE, BIZ.END_DATE, BIZ.OPERTIME ,'a'as member_id , 'a' as ds"
				+ " FROM JZ_BIZ BIZ, DM_BIZDEPT DEPT  ,"
				+ " (select t.biz_id, sum(t.assistmoney) as ASSISTPAY, "
				+ " sum(t.insurancepay) AS INSURANCEPAY from JZ_BIZ_HISTORY T "
				+ " group by t.biz_id) a "
				+ " WHERE DEPT.HOSPITAL_ID = BIZ.HOSPITAL_ID "
				+ " and biz.ASSIST_FLAG = '1' " + jwhere
				+ " and a.biz_id = biz.biz_id " + " AND BIZ.STAUS = '1'  "
				+ " order by BIZ.OPERTIME, BIZ.FAMILY_ID";

		System.out.println("aaa>>" + sql);
		try {
			ExecutSQL executSQL = new ExecutSQL();

			executSQL.setExecutsql(sql);
			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl(url);
			pager.setCurrentpage(cur_page);
			pager.setPagesize(14);
			pager.getToolsmenu();

			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());

			List<HashMap> rs = executSQLDAO.queryRow(executSQL);

			for (HashMap map : rs) {

				SearchDTO dto = new SearchDTO();

				String FAMILY_ID = (String) map.get("FAMILY_ID");
				String NAME = (String) map.get("NAME");
				String DIAGNOSE_NAME = (String) map.get("DIAGNOSE_NAME");
				BigDecimal ASSISTPAY = (BigDecimal) map.get("ASSISTPAY");
				String HANME = (String) map.get("HANME");
				String SSN = (String) map.get("SSN");
				Date OPERTIME = (Date) map.get("OPERTIME");
				String IDCARD = (String) map.get("IDCARD");
				// String MEMBER_ID = (String) map.get("MEMBER_ID");
				BigDecimal ALLMONEY = (BigDecimal) map.get("ALLMONEY");
				if (null == ALLMONEY) {
					ALLMONEY = new BigDecimal(0);
				}
				BigDecimal INSURANCEPAY = (BigDecimal) map.get("INSURANCEPAY");
				if (null == INSURANCEPAY) {
					INSURANCEPAY = new BigDecimal(0);
				}

				dto.setFamilyno(FAMILY_ID);
				dto.setDiagnoseName(DIAGNOSE_NAME);
				dto.setHospitalName(HANME);
				dto.setMembername(NAME);
				dto.setAssistpay(ASSISTPAY);
				dto.setSsn(SSN);
				dto.setBeginDate1(OPERTIME);
				dto.setPaperid(IDCARD);
				// dto.setMemberId(MEMBER_ID);
				dto.setAllmoney(ALLMONEY.toString());
				dto.setInsurepay(INSURANCEPAY.toString());
				dto.setMemberId((String) map.get("MEMBER_ID"));
				dto.setDs((String) map.get("DS"));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<SearchDTO> queryMemberstat(String sql, int cur_page, String url) {
		List<SearchDTO> list = new ArrayList<SearchDTO>();
		try {
			ExecutSQL executSQL = new ExecutSQL();

			executSQL.setExecutsql(sql);
			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl(url);
			pager.setCurrentpage(cur_page);
			pager.setPagesize(14);
			pager.getToolsmenu();

			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());

			List<HashMap> rs = executSQLDAO.queryRow(executSQL);

			for (HashMap map : rs) {

				SearchDTO dto = new SearchDTO();
				String MEMBER_ID = (String) map.get("MEMBER_ID");
				String MEMBERNAME = (String) map.get("MEMBERNAME");
				String SEX = (String) map.get("SEX");
				String FAMILYNO = (String) map.get("FAMILYNO");
				String SSN = (String) map.get("SSN");
				String PAPERID = (String) map.get("PAPERID");
				BigDecimal CS = (BigDecimal) map.get("CS");
				if (null == CS) {
					CS = new BigDecimal(0);
				}
				BigDecimal TOTALMONEY = (BigDecimal) map.get("TOTALMONEY");
				if (null == TOTALMONEY) {
					TOTALMONEY = new BigDecimal(0);
				}
				BigDecimal INSURANCEPAY = (BigDecimal) map.get("INSURANCEPAY");
				if (null == INSURANCEPAY) {
					INSURANCEPAY = new BigDecimal(0);
				}
				BigDecimal ASSISMONEY = (BigDecimal) map.get("ASSISMONEY");
				if (null == ASSISMONEY) {
					ASSISMONEY = new BigDecimal(0);
				}
				BigDecimal REALPLAY = (BigDecimal) map.get("REALPLAY");
				if (null == REALPLAY) {
					REALPLAY = new BigDecimal(0);
				}
				dto.setAssistpay(ASSISMONEY);
				dto.setSsn(SSN);
				dto.setAllmoney(TOTALMONEY.toString());
				dto.setMemberId(MEMBER_ID);
				dto.setFamilyno(FAMILYNO);
				dto.setInsurepay(INSURANCEPAY.toString());
				dto.setCurpage(CS.intValue());
				dto.setMembername(MEMBERNAME);
				dto.setPersonpay(REALPLAY);
				dto.setPaperid(PAPERID);
				dto.setUrl(SEX);

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<DeptDTO> getHospitals() {

		List<DeptDTO> hs = new ArrayList<DeptDTO>();

		DmBizdeptExample example = new DmBizdeptExample();
		List<DmBizdept> rs = this.dmBizdeptDAO.selectByExample(example);

		for (DmBizdept dmBizdept : rs) {
			DeptDTO deptDTO = new DeptDTO();
			deptDTO.setHid(dmBizdept.getHospitalId());
			deptDTO.setHname(dmBizdept.getName());
			hs.add(deptDTO);
		}

		return hs;
	}

	public List<HashMap> getAll(String sql) {
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			return this.executSQLDAO.queryAll(executSQL);
		} catch (SQLException e) {
			return null;
		}
	}

	public String getStat(String sql, String[] cols) {
		StringBuffer stat = new StringBuffer();
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = this.executSQLDAO.queryAll(executSQL);
			int r = 0;
			for (HashMap map : rs) {
				stat.append("<tr id=\"r" + r + "\">");
				for (int i = 0; i < cols.length; i++) {
					Object o = (Object) map.get(cols[i]);
					if ("java.lang.String".equals(o.getClass().getName())) {
						stat.append("<td id=\"r" + r + "d" + i + "\">"
								+ o.toString() + "</td>");
					} else if ("java.math.BigDecimal".equals(o.getClass()
							.getName())) {
						stat.append("<td id=\"r" + r + "d" + i + "\">"
								+ o.toString() + "</td>");
					}
				}
				stat.append("</tr>");
				r++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stat.toString();
	}

	public List<Yearmonth> getYearMonth() {
		List<Yearmonth> yms = new ArrayList<Yearmonth>();
		String sql = "select distinct( to_char(vsb.opertime, 'yyyy-mm')) as ym from v_stat_bill vsb order by ym";
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = this.executSQLDAO.queryAll(executSQL);
			for (HashMap map : rs) {
				String ym = (String) map.get("YM");
				String key = ym.substring(0, 4) + ym.substring(5, 7);
				String value = ym.replaceAll("-", "年") + "月";
				Yearmonth ymmap = new Yearmonth();
				ymmap.setMonthid(key);
				ymmap.setYearmonth(value);
				yms.add(ymmap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return yms;
	}

	public List<Yearmonth> getYear() {
		List<Yearmonth> yms = new ArrayList<Yearmonth>();
		String sql = "select distinct( to_char(vsb.opertime, 'yyyy')) as ym from v_stat_bill vsb order by ym";
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = this.executSQLDAO.queryAll(executSQL);
			for (HashMap map : rs) {
				String ym = (String) map.get("YM");
				String key = ym;
				String value = ym + "年";
				Yearmonth ymmap = new Yearmonth();
				ymmap.setMonthid(key);
				ymmap.setYearmonth(value);
				yms.add(ymmap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return yms;
	}

	public List<DeptDTO> getYaodians() {
		List<DeptDTO> hs = new ArrayList<DeptDTO>();
		String sql = "select HOSPITAL_ID, ORGANIZATION_ID, NAME, DEPT_TYPE, DEPT_LEVEL, STATUS from BIZDEPT@med  "
				+ "where (ORGANIZATION_ID = '2202' and DEPT_TYPE = 0)";
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		List<HashMap> rs;
		try {
			rs = this.executSQLDAO.queryAll(executSQL);
			for (HashMap s : rs) {
				DeptDTO e = new DeptDTO();
				e.setHid(s.get("HOSPITAL_ID").toString());
				e.setHname((String) s.get("NAME"));
				hs.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return hs;
	}

	@Override
	public List<SearchDTO> queryMembers1(String jwhere, int cur_page, String url) {
		List<SearchDTO> list = new ArrayList<SearchDTO>();

		String sql = " select * from chronic_remote biz where 1=1  " + jwhere
				+ " order by biz.end_time desc";
		System.out.println(sql);
		try {
			ExecutSQL executSQL = new ExecutSQL();

			executSQL.setExecutsql(sql);
			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl(url);
			pager.setCurrentpage(cur_page);
			pager.setPagesize(14);
			pager.getToolsmenu();

			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());

			List<HashMap> rs = executSQLDAO.queryRow(executSQL);

			for (HashMap map : rs) {

				SearchDTO dto = new SearchDTO();
				String bizId = map.get("BIZ_ID").toString();
				String FAMILY_ID = (String) map.get("FAMILY_ID");
				String NAME = (String) map.get("NAME");
				String DIAGNOSE_NAME = (String) map.get("DIAGNOSE_NAME");
				BigDecimal ASSISTPAY = (BigDecimal) map.get("ASSISTPAY");
				String HANME = (String) map.get("HANME");
				String SSN = (String) map.get("SSN");
				Date OPERTIME = (Date) map.get("OPER_TIME");
				String IDCARD = (String) map.get("IDCARD");
				// String MEMBER_ID = (String) map.get("MEMBER_ID");
				BigDecimal ALLMONEY = (BigDecimal) map.get("ALLMONEY");
				if (null == ALLMONEY) {
					ALLMONEY = new BigDecimal(0);
				}
				BigDecimal INSURANCEPAY = (BigDecimal) map.get("INSURANCEPAY");
				if (null == INSURANCEPAY) {
					INSURANCEPAY = new BigDecimal(0);
				}
				dto.setBizId(bizId);
				dto.setFamilyno(FAMILY_ID);
				dto.setDiagnoseName(DIAGNOSE_NAME);
				dto.setHospitalName(HANME);
				dto.setMembername(NAME);
				dto.setAssistpay(ASSISTPAY);
				dto.setSsn(SSN);
				dto.setBeginDate1(OPERTIME);
				dto.setPaperid(IDCARD);
				// dto.setMemberId(MEMBER_ID);
				dto.setAllmoney(ALLMONEY.toString());
				dto.setInsurepay(INSURANCEPAY.toString());
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public String queryMemberCounts1(String jwhere) {

		String str = "";

		ExecutSQL executSQL = new ExecutSQL();

		String sql = "select  count(*) as CNT, sum(c.ASSISTPAY) as ASSISTPAY,  sum(c.ASSISTPAY) as ALLMONEY from jz_biz@med biz, "
				+ " (select sum(b.pay_total) as ALLMONEY, sum(b.pay_assist) as ASSISTPAY, "
				+ " sum(b.PAY_SELF) as payself,  b.biz_id "
				+ " from jz_pay@med b  where mod(b.seq, 2) = 1  and b.sts = 1  and 1 = 1 "
				+ " group by b.biz_id) c,   bizdept@med d,  icd10@med e "
				+ " where c.biz_id = biz.biz_id  and biz.assist_flag = 1 and biz.biz_type = 3 "
				+ " and d.hospital_id(+) = biz.hospital_id and e.icd_id(+) = biz.icd_id   "
				+ jwhere + "";
		sql = " select  "
				+ " sum(decode(biz.member_type, '1', 1, 0)) as csrs, "
				+ " sum(decode(biz.member_type, '1', biz.assistpay, 0)) as csassispay, "
				+ " sum(decode(biz.member_type, '2', 1, 0)) as ncrs, "
				+ " sum(decode(biz.member_type, '2', biz.assistpay, 0)) as ncassispay "
				+ "from chronic_remote biz where 1=1  " + jwhere
				+ " order by biz.end_time desc";
		executSQL.setExecutsql(sql);

		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);
			if (null != rs && rs.size() > 0) {
				HashMap map = (HashMap) rs.get(0);
				BigDecimal rs1 = (BigDecimal) map.get("CSRS");
				BigDecimal m1 = (BigDecimal) map.get("CSASSISPAY");
				BigDecimal rs2 = (BigDecimal) map.get("NCRS");
				BigDecimal m2 = (BigDecimal) map.get("NCASSISPAY");
				if (null == m1) {
					m1 = new BigDecimal(0);
				}
				if (null == m2) {
					m2 = new BigDecimal(0);
				}
				if(null == rs1) {
					rs1 = new BigDecimal(0);
				}
				if(null == rs2) {
					rs2 = new BigDecimal(0);
				}
				str = "[城市人次：" + rs1.toString() + "    费用：" + m1.toString()
						+ "][农村人次：" + rs2.toString() + "    费用："
						+ m2.toString() + "]";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return str;
	}

	@Override
	public List<SearchDTO> queryMembersStat1(String sql) {
		List<SearchDTO> list = new ArrayList<SearchDTO>();
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);

		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);
			for (HashMap s : rs) {
				SearchDTO e = new SearchDTO();
				String hname = (String) s.get("HNAME");
				BigDecimal m = (BigDecimal) s.get("ASSISTPAY");
				BigDecimal m1 = (BigDecimal) s.get("ALLMONEY");
				BigDecimal m2 = (BigDecimal) s.get("SUMNUM");
				e.setHospitalName(hname);
				e.setAllmoney(m1.toString());
				e.setAssistpay(m);
				e.setSumnum(m2.toString());
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public List<ChronicBillDTO> queryChronicBillss(String sql, int cur_page,
			String url) {
		List<ChronicBillDTO> list = new ArrayList<ChronicBillDTO>();
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			pager.setAll(executSQLDAO.queryCnt(executSQL));

			pager.setUrl(url);
			pager.setCurrentpage(cur_page);
			pager.setPagesize(14);
			pager.getToolsmenu();

			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());

			List<HashMap> rs = executSQLDAO.queryRow(executSQL);
			// CHRONICBILL_ID FAMILY_ID NAME SSN SUBJECT INCOME PAYOUT
			// BALANCE OPTTIME MEMBER_ID MEMBER_TYPE
			for (HashMap s : rs) {
				ChronicBillDTO e = new ChronicBillDTO();
				e.setBalance((BigDecimal) s.get("BALANCE"));
				e.setIncome((BigDecimal) s.get("INCOME"));
				e.setPayout((BigDecimal) s.get("PAYOUT"));
				e.setMemberId((String) s.get("MEMBER_ID"));
				e.setMemberType((String) s.get("MEMBER_TYPE"));
				e.setOpttime((Date) s.get("OPTTIME"));
				e.setName((String) s.get("NAME"));
				e.setSsn((String) s.get("SSN"));
				e.setSubject((String) s.get("SUBJECT"));
				e.setFamilyId((String) s.get("FAMILY_ID"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FeeDTO> findFeeList(String sql) {
		List<FeeDTO> list = new ArrayList<FeeDTO>();
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);
			// HNAME ITEM_NAME PRICE DOSAGE MONEY
			for (HashMap s : rs) {
				FeeDTO e = new FeeDTO();
				e.setDosage((BigDecimal) s.get("DOSAGE"));
				e.setPrice((BigDecimal) s.get("PRICE"));
				e.setMoney((BigDecimal) s.get("MONEY"));
				e.setHname((String) s.get("HNAME"));
				e.setItemName((String) s.get("ITEM_NAME"));
				e.setOperTime((Date) s.get("OPER_TIME"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FeeDTO> findFeeList1(String sql) {
		List<FeeDTO> list = new ArrayList<FeeDTO>();
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);
			// HNAME ITEM_NAME PRICE DOSAGE MONEY FAMILY_NO NAME ID_CARD
			for (HashMap s : rs) {
				FeeDTO e = new FeeDTO();
				e.setDosage((BigDecimal) s.get("DOSAGE"));
				e.setPrice((BigDecimal) s.get("PRICE"));
				e.setMoney((BigDecimal) s.get("MONEY"));
				e.setHname((String) s.get("HNAME"));
				e.setItemName((String) s.get("ITEM_NAME"));
				e.setOperTime((Date) s.get("OPER_TIME"));
				e.setFamilyno((String) s.get("FAMILY_NO"));
				e.setMembername((String) s.get("NAME"));
				e.setPaperid((String) s.get("ID_CARD"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}