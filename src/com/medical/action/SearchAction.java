package com.medical.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jfree.chart.JFreeChart;

import com.medical.dto.ChronicBillDTO;
import com.medical.dto.DeptDTO;
import com.medical.dto.FeeDTO;
import com.medical.dto.OrganDTO;
import com.medical.dto.SearchDTO;
import com.medical.dto.UserInfoDTO;
import com.medical.dto.Yearmonth;
import com.medical.service.JfreechartService;
import com.medical.service.SearchService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class SearchAction extends ActionSupport {
	static Logger log = Logger.getLogger(SearchAction.class);
	private static final long serialVersionUID = 1L;
	private String cur_page;
	private String DIAGNOSENAME;
	private String DIAGNOSENAMEKey;
	private String hid;
	private String hflag;
	private List<DeptDTO> hs;
	private List<SearchDTO> members;
	private String oid;
	private String mid;
	private String operational;
	private String biztype;
	private String opertime1;
	private String opertime2;
	private List<OrganDTO> orgs;
	private List<Yearmonth> months;
	@SuppressWarnings("rawtypes")
	private List result;
	private SearchDTO searchDTO;
	private SearchService searchService;
	private JfreechartService jfreechartService;
	private String term;
	private String toolsmenu;
	private String value;
	private String totalstr_tou;
	private String totalstr;
	private HashMap<String, String> biztypes;
	private JFreeChart chart;
	private String stats;
	private List<ChronicBillDTO> cbills;
	private List<FeeDTO> feelist;
	private String xml;
	private String title;
	private String scope;
	private String ds;

	public String getDs() {
		return ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbyregion() {

		String sql = "select (select oo.asorgname  "
				+ " from sys_t_organization oo "
				+ " where oo.organization_id = org.organization_id) as c1, "
				+ " sum(decode(vsb.hosflag, '1', 1, 0)) as c2, "
				+ " sum(decode(vsb.hosflag, '1', vsb.totalmoney, 0)) as c3, "
				+ " sum(decode(vsb.hosflag, '1', vsb.assistmoney, 0)) as c4, "
				+ " sum(decode(vsb.hosflag, '0', 1, 0)) as c5, "
				+ " sum(decode(vsb.hosflag, '0', vsb.totalmoney, 0)) as c6, "
				+ " sum(decode(vsb.hosflag, '0', vsb.assistmoney, 0)) as c7, "
				+ " sum(1) as c8, " + " sum(vsb.totalmoney) as c9, "
				+ " sum(vsb.assistmoney) as c10 "
				+ " from v_stat_bill vsb, sys_t_organization org "
				+ " where vsb.family_id like org.organization_id || '%' "
				+ " and (org.organization_id = '" + this.oid
				+ "' or org.parentorgid = '" + this.oid + "') ";
		String jwhere = "";

		if ("true".equals(hflag)) {

			if ((opertime1.equals("") || null == opertime1)
					&& (opertime2.equals("") || null == opertime2)) {
			} else if (opertime1.equals("") || null == opertime1) {
				jwhere = jwhere + " AND vsb.opertime <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";

			} else if (opertime2.equals("") || null == opertime2) {
				jwhere = jwhere + " AND vsb.opertime >= TO_DATE('"
						+ opertime1.substring(0, 10) + "', 'yyyy-MM-dd') ";

			} else {
				jwhere = jwhere + " AND vsb.opertime BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ "', 'yyyy-MM-dd') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}
		} else {
			jwhere = jwhere + "and  to_char ( vsb.opertime, 'yyyyMM') ='"
					+ this.mid + "'";
		}

		sql = sql + " " + jwhere
				+ " group by org.organization_id order by org.organization_id ";
		log.error("stat1:" + sql);
		this.setStats(this.searchService.getStat(sql, new String[] { "C1",
				"C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10" }));

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setMonths(this.searchService.getYearMonth());

		return SUCCESS;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbyregioninit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setMonths(this.searchService.getYearMonth());
		return SUCCESS;
	}

	/**
	 * 被救助人地区分布情况图表
	 * 
	 * @return
	 */
	public String distributionbyregionchart() {
		Document doc;
		try {
			if (!"".equals(xml)) {
				title = new String(title.getBytes("ISO8859_1"), "utf-8");
				xml = new String(xml.getBytes("ISO8859_1"), "utf-8");
				doc = DocumentHelper.parseText(xml);
				this.setChart(this.jfreechartService.createPie3D(doc, title));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbyhospital() {

		String sql = "select (select oo.name " + " from dm_bizdept oo "
				+ " where vsb.hospital_id = oo.hospital_id) as c1, "
				+ " sum(decode(vsb.hosflag, '1', 1, 0)) as c2, "
				+ " sum(decode(vsb.hosflag, '1', vsb.totalmoney, 0)) as c3, "
				+ " sum(decode(vsb.hosflag, '1', vsb.assistmoney, 0)) as c4, "
				+ " sum(decode(vsb.hosflag, '0', 1, 0)) as c5, "
				+ " sum(decode(vsb.hosflag, '0', vsb.totalmoney, 0)) as c6, "
				+ " sum(decode(vsb.hosflag, '0', vsb.assistmoney, 0)) as c7, "
				+ " sum(1) as c8, " + " sum(vsb.totalmoney) as c9, "
				+ " sum(vsb.assistmoney) as c10 " + " from v_stat_bill vsb "
				+ " where vsb.family_id like '" + this.oid + "%'";
		String jwhere = "";

		if ("true".equals(hflag)) {

			if ((opertime1.equals("") || null == opertime1)
					&& (opertime2.equals("") || null == opertime2)) {
			} else if (opertime1.equals("") || null == opertime1) {
				jwhere = jwhere + " AND vsb.opertime <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd') ";

			} else if (opertime2.equals("") || null == opertime2) {
				jwhere = jwhere + " AND vsb.opertime >= TO_DATE('"
						+ opertime1.substring(0, 10) + "', 'yyyy-MM-dd') ";

			} else {
				jwhere = jwhere + " AND vsb.opertime BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ "', 'yyyy-MM-dd') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}
		} else {
			jwhere = jwhere + "and  to_char ( vsb.opertime, 'yyyyMM') ='"
					+ this.mid + "'";
		}

		sql = sql + " " + jwhere
				+ " group by vsb.hospital_id order by vsb.hospital_id ";
		log.error("stat1:" + sql);
		this.setStats(this.searchService.getStat(sql, new String[] { "C1",
				"C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10" }));

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setMonths(this.searchService.getYearMonth());

		return SUCCESS;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbyhospitalinit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setMonths(this.searchService.getYearMonth());
		return SUCCESS;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	public String distributionbyhospitalchart() {
		Document doc;
		try {
			if (!"".equals(xml)) {
				title = new String(title.getBytes("ISO8859_1"), "utf-8");
				xml = new String(xml.getBytes("ISO8859_1"), "utf-8");
				doc = DocumentHelper.parseText(xml);
				this.setChart(this.jfreechartService.createPie3D(doc, title));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbyyear() {

		String sql = "select a.yy as c1, "
				+ " b.rc as c2, "
				+ " a.zm as c3, "
				+ " b.am as c4, "
				+ " round((b.mm / a.zm), 4) * 100 || '%' as c5, "
				+ " round((b.hm / a.zm), 4) * 100 || '%' as c6 "
				+ " from (select sum(pm.previewmoney) as zm, substr(pm.pm_month, 0, 4) as yy "
				+ " from sys_previewmoney pm "
				+ " where pm.organization_id = '" + oid + "' "
				+ " group by substr(pm.pm_month, 0, 4)) a, "
				+ " (select sum(vsb.assistmoney) as am, "
				+ " sum(decode(vsb.hosflag, '1', vsb.assistmoney, 0)) as mm, "
				+ " sum(decode(vsb.hosflag, '0', vsb.assistmoney, 0)) as hm, "
				+ " sum(1) as rc, " + " to_char(vsb.opertime, 'yyyy') as yy "
				+ " from v_stat_bill vsb "
				+ " group by to_char(vsb.opertime, 'yyyy')) b "
				+ " where a.yy = b.yy " + " order by a.yy";

		log.error("stat3:" + sql);
		this.setStats(this.searchService.getStat(sql, new String[] { "C1",
				"C2", "C3", "C4", "C5", "C6" }));

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		// this.setMonths(this.searchService.getYearMonth());

		return SUCCESS;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbyyearinit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		// this.setMonths(this.searchService.getYearMonth());
		return SUCCESS;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	public String distributionbyyearchart() {
		Document doc;
		try {
			if (!"".equals(xml)) {
				title = new String(title.getBytes("ISO8859_1"), "utf-8");
				xml = new String(xml.getBytes("ISO8859_1"), "utf-8");
				doc = DocumentHelper.parseText(xml);
				this.setChart(this.jfreechartService.createBar3D(doc, title,
						"a", "b"));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 年度救助资金使用情况
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbymoney() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");

		String sql = "select tt.monthname as c1, tt.zm as c2, "
				+ " sum(zm) over(order by tt.monthname)  as c3, "
				+ " round((tt.zm / (select sum(pm.previewmoney) "
				+ " from sys_previewmoney pm "
				+ " where pm.organization_id = '"
				+ user.getOrganizationId()
				+ "' "
				+ " and pm.pm_month like '"
				+ this.mid
				+ "%')) * 100, "
				+ " 2) || '%' as c4, "
				+ " (select sum(pm.previewmoney) "
				+ " from sys_previewmoney pm "
				+ " where pm.organization_id = '"
				+ user.getOrganizationId()
				+ "' "
				+ " and pm.pm_month like '"
				+ this.mid
				+ "%') as c5 "
				+ " from (select to_char(t.opertime, 'yyyyMM') as monthname, "
				+ "  "
				+ " sum(t.assistmoney) as zm "
				+ " from v_stat_bill t "
				+ " where to_char(t.opertime, 'yyyy') like '"
				+ this.mid
				+ "%' "
				+ " and t.family_id like '"
				+ user.getOrganizationId()
				+ "%' "
				+ " group by to_char(t.opertime, 'yyyyMM') "
				+ " order by to_char(t.opertime, 'yyyyMM')) tt";

		log.error("stat4:" + sql);
		this.setStats(this.searchService.getStat(sql, new String[] { "C1",
				"C2", "C3", "C4", "C5" }));

		this.setMonths(this.searchService.getYear());

		return SUCCESS;
	}

	/**
	 * 年度救助资金使用情况
	 * 
	 * @return
	 */
	public String distributionbymoneyinit() {
		this.setMonths(this.searchService.getYear());
		return SUCCESS;
	}

	/**
	 * 年度救助资金使用情况
	 * 
	 * @return
	 */
	public String distributionbymoneychart() {
		Document doc;
		try {
			if (!"".equals(xml)) {
				title = new String(title.getBytes("ISO8859_1"), "utf-8");
				xml = new String(xml.getBytes("ISO8859_1"), "utf-8");
				doc = DocumentHelper.parseText(xml);
				this.setChart(this.jfreechartService.createBar3D(doc, title,
						"a", "b"));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 门诊救助金使用情况
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbysicko() {

		String sql = "select icd.name as c1, " + " tt.rc as c2, "
				+ " tt.zm as c3, " + " tt.jzm as c4, "
				+ " 100 * round(tt.rc / sum(tt.rc) over(), 4) || '%' as c5 "
				+ " from (select count(1) as rc, "
				+ " sum(vsb.assistmoney) as jzm, "
				+ " sum(vsb.totalmoney) as zm, " + " biz.diagnose "
				+ " from v_stat_bill vsb, jz_biz biz "
				+ " where biz.biz_id = vsb.biz_id " + " and vsb.hosflag = 1 "
				+ " and vsb.family_id like '" + this.oid + "%' ";

		String jwhere = "";

		if ("true".equals(hflag)) {

			if ((opertime1.equals("") || null == opertime1)
					&& (opertime2.equals("") || null == opertime2)) {
			} else if (opertime1.equals("") || null == opertime1) {
				jwhere = jwhere + " AND vsb.opertime <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";

			} else if (opertime2.equals("") || null == opertime2) {
				jwhere = jwhere + " AND vsb.opertime >= TO_DATE('"
						+ opertime1.substring(0, 10) + "', 'yyyy-MM-dd') ";

			} else {
				jwhere = jwhere + " AND vsb.opertime BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ "', 'yyyy-MM-dd') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}
		} else {
			jwhere = jwhere + "and  to_char ( vsb.opertime, 'yyyyMM') ='"
					+ this.mid + "'";
		}

		sql = sql + " " + jwhere + " group by biz.diagnose) tt, "
				+ " icd10 icd " + " where icd.icdcode = tt.diagnose";
		log.error("stat5:" + sql);
		this.setStats(this.searchService.getStat(sql, new String[] { "C1",
				"C2", "C3", "C4", "C5" }));

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setMonths(this.searchService.getYearMonth());

		return SUCCESS;
	}

	/**
	 * 门诊救助金使用情况
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbysickoinit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setMonths(this.searchService.getYearMonth());
		return SUCCESS;
	}

	/**
	 * 门诊救助金使用情况
	 * 
	 * @return
	 */
	public String distributionbysickochart() {
		Document doc;
		try {
			if (!"".equals(xml)) {
				title = new String(title.getBytes("ISO8859_1"), "utf-8");
				xml = new String(xml.getBytes("ISO8859_1"), "utf-8");
				doc = DocumentHelper.parseText(xml);
				this.setChart(this.jfreechartService.createPie3D(doc, title));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 住院救助金使用情况
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbysickh() {

		String sql = "select icd.name as c1, "
				+ " tt.rc as c2, "
				+ " tt.zm as c3, "
				+ " tt.jzm as c4, "
				+ " 100 * round(tt.rc / sum(tt.rc) over(), 4) || '%' as c5 "
				+ " from (select count(1) as rc, "
				+ " sum(vsb.assistmoney) as jzm, "
				+ " sum(vsb.totalmoney) as zm, "
				+ " biz.diagnose "
				+ " from v_stat_bill vsb, (select decode(diagnose, null, '000000', diagnose) as diagnose, biz_id  "
				+ "from jz_biz biz   where biz.family_id like '" + this.oid
				+ "%') biz " + " where biz.biz_id = vsb.biz_id "
				+ " and vsb.hosflag = 0 " + " and vsb.family_id like '"
				+ this.oid + "%' ";

		String jwhere = "";

		if ("true".equals(hflag)) {

			if ((opertime1.equals("") || null == opertime1)
					&& (opertime2.equals("") || null == opertime2)) {
			} else if (opertime1.equals("") || null == opertime1) {
				jwhere = jwhere + " AND vsb.opertime <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";

			} else if (opertime2.equals("") || null == opertime2) {
				jwhere = jwhere + " AND vsb.opertime >= TO_DATE('"
						+ opertime1.substring(0, 10) + "', 'yyyy-MM-dd') ";

			} else {
				jwhere = jwhere + " AND vsb.opertime BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ "', 'yyyy-MM-dd') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}
		} else {
			jwhere = jwhere + "and  to_char ( vsb.opertime, 'yyyyMM') ='"
					+ this.mid + "'";
		}

		sql = sql + " " + jwhere + " group by biz.diagnose) tt, "
				+ " icd10 icd " + " where icd.icdcode = tt.diagnose";
		log.error("stat6:" + sql);
		this.setStats(this.searchService.getStat(sql, new String[] { "C1",
				"C2", "C3", "C4", "C5" }));

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setMonths(this.searchService.getYearMonth());

		return SUCCESS;
	}

	/**
	 * 住院救助金使用情况
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbysickhinit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setMonths(this.searchService.getYearMonth());
		return SUCCESS;
	}

	/**
	 * 住院救助金使用情况
	 * 
	 * @return
	 */
	public String distributionbysickhchart() {
		Document doc;
		try {
			if (!"".equals(xml)) {
				title = new String(title.getBytes("ISO8859_1"), "utf-8");
				xml = new String(xml.getBytes("ISO8859_1"), "utf-8");
				doc = DocumentHelper.parseText(xml);
				this.setChart(this.jfreechartService.createPie3D(doc, title));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String findByterm() {
		if (null != DIAGNOSENAME && !"".equals(DIAGNOSENAME)) {
			searchDTO.setDiagnoseName(DIAGNOSENAME);
		}

		Map map = ActionContext.getContext().getSession();
		if (null == cur_page || "".equals(cur_page)) {
			searchDTO.setCurpage(1);
			map.put("sql", searchDTO);
		} else {
			searchDTO = (SearchDTO) map.get("sql");
			searchDTO.setCurpage(new Integer(cur_page));
		}
		searchDTO.setUrl("searchExecute.action");
		searchDTO.setPageSize(6);
		result = searchService.findByTerm(searchDTO);
		toolsmenu = searchService.getToolsmenu();
		if (result != null && result.size() > 0) {
			return SUCCESS;
		} else {
			return "search";
		}
	}

	public String findForBizlist() {
		result = searchService.findForBizlist();
		return SUCCESS;
	}

	public String getBiztype() {
		return biztype;
	}

	public HashMap<String, String> getBiztypes() {
		return biztypes;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public String getCur_page() {
		return cur_page;
	}

	public String getDIAGNOSENAME() {
		return DIAGNOSENAME;
	}

	public String getDIAGNOSENAMEKey() {
		return DIAGNOSENAMEKey;
	}

	public String getHflag() {
		return hflag;
	}

	public String getHid() {
		return hid;
	}

	public List<DeptDTO> getHs() {
		return hs;
	}

	public JfreechartService getJfreechartService() {
		return jfreechartService;
	}

	public List<SearchDTO> getMembers() {
		return members;
	}

	public String getMid() {
		return mid;
	}

	public List<Yearmonth> getMonths() {
		return months;
	}

	public String getOid() {
		return oid;
	}

	public String getOperational() {
		return operational;
	}

	public String getOpertime1() {
		return opertime1;
	}

	public String getOpertime2() {
		return opertime2;
	}

	public List<OrganDTO> getOrgs() {
		return orgs;
	}

	@SuppressWarnings("rawtypes")
	public List getResult() {
		return result;
	}

	public SearchDTO getSearchDTO() {
		return searchDTO;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public String getTerm() {
		return term;
	}

	public String getToolsmenu() {
		return toolsmenu;
	}

	public String getTotalstr() {
		return totalstr;
	}

	public String getValue() {
		return value;
	}

	public String init() {
		return SUCCESS;
	}

	/**
	 * 医疗救助对账单明细查询
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String searchMedicalMembers() {
		String jwhere = "";
		Map session = ActionContext.getContext().getSession();
		if (null == cur_page || "".equals(cur_page)) {
			/*
			 * //<option value="">全部</option> <option value="10">购药</option>
			 * <option value="13">医后报销</option> <option value="11">门诊</option>
			 * <option value="12" selected="selected">住院</option>
			 */
			jwhere = jwhere + " and biz.ASSIST_FLAG = '1' and ALREADY_GET=1 ";
			if (null == biztype || "".equals(biztype)) {
			} else {
				if ("11".equals(biztype)) {
					jwhere = jwhere
							+ " and  biz.treatment_type in (110,131,132) ";
				} else if ("12".equals(biztype)) {
					jwhere = jwhere + " and biz.treatment_type in (120)  ";
				} else if ("13".equals(biztype)) {
					jwhere = jwhere + " and biz.gather_flag in (3,4)   ";
				} else if ("10".equals(biztype)) {
					jwhere = jwhere + " and biz.biz_type in (10)   ";
				}
			}
			if ("".equals(scope)) {

			} else if ("1".equals(scope.toString())) {
				jwhere = jwhere + " and biz.gather_flag <>'5'";
			} else if ("2".equals(scope.toString())) {
				jwhere = jwhere + " and biz.gather_flag ='5'";
			}
			if (null == hid || "".equals(hid)) {

			} else {
				jwhere = jwhere + " and DEPT.HOSPITAL_ID ='" + hid + "' ";
			}
			if ("".equals(opertime1) && "".equals(opertime2)) {
				jwhere = jwhere + " and 1=1 ";
			} else if ("".equals(opertime1) && !"".equals(opertime2)) {
				jwhere = jwhere + " AND BIZ.OPERTIME <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";
			} else if ("".equals(opertime2) && !"".equals(opertime1)) {
				jwhere = jwhere + " AND BIZ.OPERTIME <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";
			} else {
				jwhere = jwhere + " AND BIZ.OPERTIME BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ "', 'yyyy-MM-dd') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}

			if (oid == null || "".equals(oid)) {

			} else {
				jwhere = jwhere + " and ( BIZ.FAMILY_ID like '" + this.oid
						+ "%' or BIZ.FAMILY_ID like '" + oid.substring(0, 2)
						+ "0" + oid.substring(2, oid.length()) + "%' ) ";
			}

			session.put("sql", jwhere);
			cur_page = "1";
		} else {
			jwhere = (String) session.get("sql");
		}
		// 执行顺序
		this.setMembers(this.searchService.queryMembers(jwhere, new BigDecimal(
				cur_page).intValue(), "searchMedicalMembers.action"));
		setToolsmenu(searchService.getPager().getToolsmenu());

		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getHospitals());

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("", "全部");
		map.put("11", "门诊");
		map.put("12", "住院");
		map.put("10", "购药");
		map.put("13", "医后报销");
		this.setBiztypes(map);

		this.setTotalstr(this.searchService.queryMemberCounts(jwhere));

		return SUCCESS;
	}

	/**
	 * 医疗救助对账单明细查询
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public String viewpayinfo() {
		Map session = ActionContext.getContext().getSession();
		String sql = "select  biz.family_no, biz.name, biz.id_card, t.name as hname, f.item_name, f.price, f.dosage, f.money ,f.oper_time "
				+ " from jz_biz@med biz, jz_feelist@med f, bizdept@med t "
				+ " where f.biz_id = biz.biz_id "
				+ " and t.hospital_id = biz.hospital_id "
				+ " and biz.biz_id='"
				+ searchDTO.getBizId() + "' order by f.oper_time ";
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		String a = user.getOrganizationId().substring(0, 4);
		this.totalstr_tou = "/gy/" + searchDTO.getYear() + "/" + a + "/YZ/YZ"
				+ searchDTO.getBizId() + ".jpg";
		this.totalstr = "/gy/" + searchDTO.getYear() + "/" + a + "/GY/GY"
				+ searchDTO.getBizId() + ".jpg";
		System.out.println(sql);
		setFeelist(searchService.findFeeList1(sql));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String searchMedicalMembersinit() {
		setMembers(null);
		setOpertime1(null);
		setOpertime2(null);
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getHospitals());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("", "全部");
		map.put("12", "住院");
		map.put("11", "门诊");
		map.put("10", "购药");
		map.put("13", "医后报销");
		this.setBiztypes(map);
		return SUCCESS;
	}

	/**
	 * 医疗救助对象查询
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String searchmembers() {

		String var = value;
		String jwhere = "";
		Map map = ActionContext.getContext().getSession();
		if (null == cur_page || "".equals(cur_page)) {
			if (!"".equals(var)) {
				if ("=".equals(operational)) {
					var = " = '" + var + "'";
				} else if ("like".equals(operational)) {
					var = "like  '%" + var + "%'";
				} else {
					var = "";
				}

				if ("SSN".equals(term)) {
					jwhere = jwhere + " and BIZ.SSN  " + var;
				} else if ("FAMILYNO".equals(term)) {
					jwhere = jwhere + " and BIZ.FAMILY_ID  " + var;
				} else if ("MEMBERNAME".equals(term)) {
					jwhere = jwhere + " and  BIZ.NAME  " + var;
				} else if ("PAPERID".equals(term)) {
					jwhere = jwhere + " and  BIZ.IDCARD " + var;
				} else {
				}
			}

			if (null == hid || "".equals(hid)) {

			} else {
				jwhere = jwhere + " and DEPT.HOSPITAL_ID ='" + hid + "' ";
			}

			if ((opertime1.equals("") || null == opertime1)
					&& (opertime2.equals("") || null == opertime2)) {
			} else if (opertime1.equals("") || null == opertime1) {
				jwhere = jwhere + " AND BIZ.OPERTIME <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";

			} else if (opertime2.equals("") || null == opertime2) {
				jwhere = jwhere + " AND BIZ.OPERTIME >= TO_DATE('"
						+ opertime1.substring(0, 10) + "', 'yyyy-MM-dd') ";

			} else {
				jwhere = jwhere + " AND BIZ.OPERTIME BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ "', 'yyyy-MM-dd') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}
			String orgno1 = this.oid.substring(0, 2) + "0"
					+ this.oid.substring(2, this.oid.length());
			if (oid == null || "".equals(oid)) {

			} else {
				jwhere = jwhere + " and (  BIZ.FAMILY_ID like '" + this.oid
						+ "%'  or  BIZ.FAMILY_ID like '" + orgno1 + "%' )";
			}
			if ("".equals(ds)) {

			} else if ("1".equals(ds)) {
				jwhere = jwhere + " and  length(biz.family_id) = 14  ";
			} else if ("2".equals(ds)) {
				jwhere = jwhere + " and  length(biz.family_id) = 15   ";
			}

			map.put("sql", jwhere);
			cur_page = "1";
		} else {
			jwhere = (String) map.get("sql");
		}
		// 执行顺序
		this.setMembers(this.searchService.queryMembers(jwhere, new BigDecimal(
				cur_page).intValue(), "searchmembers.action"));
		setToolsmenu(searchService.getPager().getToolsmenu());

		UserInfoDTO user = (UserInfoDTO) map.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getHospitals());
		this.setTotalstr(this.searchService.queryMemberCounts(jwhere));
		return SUCCESS;
	}

	/**
	 * 医疗救助对象查询 页面初始化
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String searchmembersinit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getHospitals());
		return SUCCESS;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public void setBiztypes(HashMap<String, String> biztypes) {
		this.biztypes = biztypes;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public void setCur_page(String cur_page) {
		this.cur_page = cur_page;
	}

	public void setDIAGNOSENAME(String diagnosename) {
		DIAGNOSENAME = diagnosename;
	}

	public void setDIAGNOSENAMEKey(String key) {
		DIAGNOSENAMEKey = key;
	}

	public void setHflag(String hflag) {
		this.hflag = hflag;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public void setHs(List<DeptDTO> hs) {
		this.hs = hs;
	}

	public void setJfreechartService(JfreechartService jfreechartService) {
		this.jfreechartService = jfreechartService;
	}

	public void setMembers(List<SearchDTO> members) {
		this.members = members;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setMonths(List<Yearmonth> months) {
		this.months = months;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setOperational(String operational) {
		this.operational = operational;
	}

	public void setOpertime1(String opertime1) {
		this.opertime1 = opertime1;
	}

	public void setOpertime2(String opertime2) {
		this.opertime2 = opertime2;
	}

	public void setOrgs(List<OrganDTO> orgs) {
		this.orgs = orgs;
	}

	@SuppressWarnings("rawtypes")
	public void setResult(List result) {
		this.result = result;
	}

	public void setSearchDTO(SearchDTO searchDTO) {
		this.searchDTO = searchDTO;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setToolsmenu(String toolsmenu) {
		this.toolsmenu = toolsmenu;
	}

	public void setTotalstr(String totalstr) {
		this.totalstr = totalstr;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setCbills(List<ChronicBillDTO> cbills) {
		this.cbills = cbills;
	}

	public List<ChronicBillDTO> getCbills() {
		return cbills;
	}

	public void setFeelist(List<FeeDTO> feelist) {
		this.feelist = feelist;
	}

	public List<FeeDTO> getFeelist() {
		return feelist;
	}

	/**
	 * 被救助人地区分布情况统计表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbypersoninit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getHospitals());
		return SUCCESS;
	}

	/**
	 * 救助对象统计
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String distributionbyperson() {

		String var = value;
		String jwhere = "";
		String sql = "";
		Map map = ActionContext.getContext().getSession();
		if (null == cur_page || "".equals(cur_page)) {
			if (!"".equals(var)) {
				if ("=".equals(operational)) {
					var = " = '" + var + "'";
				} else if ("like".equals(operational)) {
					var = "like  '%" + var + "%'";
				} else {
					var = "";
				}

				if ("SSN".equals(term)) {
					jwhere = jwhere + " and m.SSN  " + var;
				} else if ("FAMILYNO".equals(term)) {
					jwhere = jwhere + " and m.familyno  " + var;
				} else if ("MEMBERNAME".equals(term)) {
					jwhere = jwhere + " and  m.membername  " + var;
				} else if ("PAPERID".equals(term)) {
					jwhere = jwhere + " and  m.paperid " + var;
				} else {
				}
			}

			String jwhere2 = "";

			if (null == hid || "".equals(hid)) {

			} else {
				jwhere2 = jwhere2 + " and biz.HOSPITAL_ID ='" + hid + "' ";
			}

			if ((opertime1.equals("") || null == opertime1)
					&& (opertime2.equals("") || null == opertime2)) {
			} else if (opertime1.equals("") || null == opertime1) {
				jwhere2 = jwhere2 + " AND BIZ.OPERTIME <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";

			} else if (opertime2.equals("") || null == opertime2) {
				jwhere2 = jwhere2 + " AND BIZ.OPERTIME >= TO_DATE('"
						+ opertime1.substring(0, 10) + "', 'yyyy-MM-dd') ";

			} else {
				jwhere2 = jwhere2 + " AND BIZ.OPERTIME BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ "', 'yyyy-MM-dd') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}

			if (oid == null || "".equals(oid)) {

			} else {
				jwhere2 = jwhere2 + " and  BIZ.FAMILY_ID like '" + this.oid
						+ "%' ";
			}

			sql = "select m.member_id," + "m.ssn, m.familyno, "
					+ " m.membername, " + " m.paperid, " + " m.sex, "
					+ " ms.cs, " + " ms.totalmoney, " + " ms.insurancepay, "
					+ " ms.assismoney, " + " ms.realplay "
					+ " from member_baseinfo m, " + " (select mem.member_id, "
					+ " count(1) as cs, "
					+ " sum(a.totalmoney) as totalmoney, "
					+ " sum(a.insurancepay) as insurancepay, "
					+ " sum(a.assismoney) as assismoney, "
					+ " sum(a.realplay) as realplay "
					+ " from member_baseinfo mem, " + " jz_biz b, "
					+ " (select sum(h.totalmoney) as totalmoney, "
					+ " sum(h.insurancepay) as insurancepay, "
					+ " sum(h.assistmoney) as assismoney, "
					+ " sum(h.realpay) as realplay, " + " biz.biz_id "
					+ " from jz_biz biz, jz_biz_history h "
					+ " where h.biz_id = biz.biz_id "
					+ " and biz.staus = '1'  and biz.ASSIST_FLAG = '1'  "
					+ jwhere2 + " group by biz.biz_id) a "
					+ " where b.biz_id = a.biz_id " + " and b.staus = '1' "
					+ " and b.ssn = mem.ssn " + " group by mem.member_id) ms "
					+ " where ms.member_id = m.member_id " + jwhere
					+ " and m.familyno like '" + this.oid + "%'";

			map.put("sql", sql);
			cur_page = "1";
			log.error("person>>>>>>>>>>>>>>" + sql);
		} else {
			sql = (String) map.get("sql");
		}
		// 执行顺序
		this.setMembers(this.searchService.queryMemberstat(sql, new BigDecimal(
				cur_page).intValue(), "distributionbyperson.action"));
		setToolsmenu(searchService.getPager().getToolsmenu());

		UserInfoDTO user = (UserInfoDTO) map.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getHospitals());
		this.setTotalstr(this.searchService.queryMemberStatCount(sql));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String searchMedicalMembers1() {
		String jwhere = "";
		String var = value;
		Map session = ActionContext.getContext().getSession();
		System.out.println("cp>>>>" + cur_page);
		if (null == cur_page || "".equals(cur_page)) {

			if (!"".equals(var)) {
				if ("=".equals(operational)) {
					var = " = '" + var + "'";
				} else if ("like".equals(operational)) {
					var = "like  '%" + var + "%'";
				} else {
					var = "";
				}

				if ("SSN".equals(term)) {
					jwhere = jwhere + " and t.SSN  " + var;
				} else if ("FAMILYNO".equals(term)) {
					jwhere = jwhere + " and biz.FAMILY_ID  " + var;
				} else if ("MEMBERNAME".equals(term)) {
					jwhere = jwhere + " and  biz.name  " + var;
				} else if ("PAPERID".equals(term)) {
					jwhere = jwhere + " and  biz.IDCARD " + var;
				} else {
				}
			}

			if (null == hid || "".equals(hid)) {

			} else {
				jwhere = jwhere + " and  biz.hospital_id ='" + hid + "' ";
			}
			if ("".equals(opertime1) && "".equals(opertime2)) {
				jwhere = jwhere + " and 1=1 ";
			} else if ("".equals(opertime1) && !"".equals(opertime2)) {
				jwhere = jwhere + " AND BIZ.OPER_TIME > TO_DATE('"
						+ opertime2.substring(0, 10) + " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			} else if ("".equals(opertime2) && !"".equals(opertime1)) {
				jwhere = jwhere + " AND BIZ.OPER_TIME < TO_DATE('"
						+ opertime1.substring(0, 10) + " 00:00:00', 'yyyy-MM-dd hh24:mi:ss')";
			} else {
				jwhere = jwhere + " AND BIZ.OPER_TIME BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ " 00:00:00', 'yyyy-MM-dd hh24:mi:ss') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}
			if (oid == null || "".equals(oid)) {

			} else {
				jwhere = jwhere + " and ( BIZ.FAMILY_ID like '" + this.oid
						+ "%' or BIZ.FAMILY_ID like '" + oid.substring(0, 2)
						+ "0" + oid.substring(2, oid.length()) + "%' ) ";
			}
			session.put("sql", jwhere);
			cur_page = "1";
		} else {
			jwhere = (String) session.get("sql");
		}
		// 执行顺序
		this.setMembers(this.searchService.queryMembers1(jwhere,
				new BigDecimal(cur_page).intValue(),
				"searchMedicalMembers1.action"));
		setToolsmenu(searchService.getPager().getToolsmenu());
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getYaodians());
		this.setTotalstr(this.searchService.queryMemberCounts1(jwhere));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String searchMedicalMembersinit1() {
		setMembers(null);
		setOpertime1(null);
		setOpertime2(null);
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getYaodians());
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String searchMedicalMembersinit2() {
		setOpertime1(null);
		setOpertime2(null);
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String searchMedicalMembers2() {
		String var = value;
		String jwhere = "";
		String sql = "";
		Map map = ActionContext.getContext().getSession();
		if (null == cur_page || "".equals(cur_page)) {
			if (!"".equals(var)) {
				if ("=".equals(operational)) {
					var = " = '" + var + "'";
				} else if ("like".equals(operational)) {
					var = "like  '%" + var + "%'";
				} else {
					var = "";
				}

				if ("SSN".equals(term)) {
					jwhere = jwhere + " and t.SSN  " + var;
				} else if ("FAMILYNO".equals(term)) {
					jwhere = jwhere + " and t.family_id  " + var;
				} else if ("MEMBERNAME".equals(term)) {
					jwhere = jwhere + " and  t.name  " + var;
				} else if ("PAPERID".equals(term)) {
					jwhere = jwhere + " and  t.paperid " + var;
				} else {
				}
			}
			String jwhere2 = "";
			if ((opertime1.equals("") || null == opertime1)
					&& (opertime2.equals("") || null == opertime2)) {
			} else if (opertime1.equals("") || null == opertime1) {
				jwhere2 = jwhere2 + " AND t.opttime <= TO_DATE('"
						+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";

			} else if (opertime2.equals("") || null == opertime2) {
				jwhere2 = jwhere2 + " AND t.opttime >= TO_DATE('"
						+ opertime1.substring(0, 10) + "', 'yyyy-MM-dd') ";

			} else {
				jwhere2 = jwhere2 + " AND t.opttime BETWEEN TO_DATE('"
						+ opertime1.substring(0, 10)
						+ "', 'yyyy-MM-dd') AND TO_DATE('"
						+ opertime2.substring(0, 10)
						+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
			}

			if (oid == null || "".equals(oid)) {

			} else {
				jwhere = jwhere + " and ( t.family_id like '" + this.oid
						+ "%' or t.family_id like '" + oid.substring(0, 2)
						+ "0" + oid.substring(2, oid.length()) + "%' ) ";
			}

			sql = "select  chronicbill_id, family_id, name, ssn, subject, income, payout, balance, opttime, member_id, member_type from"
					+ " jz_chronicbill t where 1=1 "
					+ jwhere
					+ jwhere2
					+ " order by t.family_id, t.member_id, t.opttime";

			map.put("sql", sql);
			cur_page = "1";
			log.error("person>>>>>>>>>>>>>>" + sql);
		} else {
			sql = (String) map.get("sql");
		}
		// 执行顺序
		cbills = searchService.queryChronicBillss(sql,
				new BigDecimal(cur_page).intValue(),
				"searchMedicalMembers2.action");
		setToolsmenu(searchService.getPager().getToolsmenu());
		UserInfoDTO user = (UserInfoDTO) map.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String printbillone1() {
		Map session = ActionContext.getContext().getSession();
		String jwhere = "";
		if ("".equals(opertime1) && "".equals(opertime2)) {
			jwhere = jwhere + " and 1=1 ";
		} else if ("".equals(opertime1) && !"".equals(opertime2)) {
			jwhere = jwhere + " AND BIZ.OPER_TIME <= TO_DATE('"
					+ opertime2.substring(0, 10) + "', 'yyyy-MM-dd')";
		} else if ("".equals(opertime2) && !"".equals(opertime1)) {
			jwhere = jwhere + " AND BIZ.OPER_TIME <= TO_DATE('"
					+ opertime1.substring(0, 10) + "', 'yyyy-MM-dd')";
		} else {
			jwhere = jwhere + " AND BIZ.OPER_TIME BETWEEN TO_DATE('"
					+ opertime1.substring(0, 10)
					+ "', 'yyyy-MM-dd') AND TO_DATE('"
					+ opertime2.substring(0, 10)
					+ " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
		}
		if (oid == null || "".equals(oid)) {

		} else {
			jwhere = jwhere + " and ( BIZ.family_no like '" + this.oid
					+ "%' or BIZ.family_no like '" + oid.substring(0, 2) + "0"
					+ oid.substring(2, oid.length()) + "%' ) ";
		}

		String sql = "select d.name as HNAME, sum(c.ASSISTPAY) as ASSISTPAY, "
				+ " sum(c.ALLMONEY) as ALLMONEY, count(biz.BIZ_ID) as SUMNUM from jz_biz@med biz, "
				+ " (select sum(b.pay_total) as ALLMONEY, "
				+ " sum(b.pay_assist) as ASSISTPAY, "
				+ " sum(b.PAY_SELF) as payself, " + " b.biz_id "
				+ " from jz_pay@med b " + " where mod(b.seq, 2) = 1 "
				+ " and b.sts = 1 " + " and 1 = 1 " + " group by b.biz_id) c, "
				+ " bizdept@med d, " + " icd10@med e "
				+ " where c.biz_id = biz.biz_id " + " and biz.assist_flag = 1 "
				+ " and biz.biz_type = 3 "
				+ " and d.hospital_id(+) = biz.hospital_id "
				+ " and e.icd_id(+) = biz.icd_id " + " and 1 = 1 " + jwhere
				+ " group by d.name";
		session.put("sql", sql);
		setMembers(searchService.queryMembersStat1(sql));
		return SUCCESS;
	}

	public String viewfeelist() {
		String sql = "select t.name as hname, f.item_name, f.price, f.dosage, f.money "
				+ " from jz_biz@med biz, jz_feelist@med f, bizdept@med t "
				+ " where f.biz_id = biz.biz_id "
				+ " and t.hospital_id = biz.hospital_id "
				+ " and biz.member_id = '"
				+ oid
				+ "' "
				+ " and biz.member_type = '" + mid + "' order by f.oper_time ";
		System.out.println(sql);
		setFeelist(searchService.findFeeList(sql));
		return SUCCESS;
	}

	public String getTotalstr_tou() {
		return totalstr_tou;
	}

	public void setTotalstr_tou(String totalstr_tou) {
		this.totalstr_tou = totalstr_tou;
	}

}
