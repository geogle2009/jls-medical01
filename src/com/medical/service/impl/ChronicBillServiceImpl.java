package com.medical.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.medical.common.Pager;
import com.medical.dao.ExecutSQLDAO;
import com.medical.dao.JzChronicbillDAO;
import com.medical.dao.JzChronicbillemployDAO;
import com.medical.dao.JzChronicstatusDAO;
import com.medical.dao.SysTOrganizationDAO;
import com.medical.dto.ChronicBillDTO;
import com.medical.dto.ChronicBillEmployDTO;
import com.medical.dto.ChronicBillStatDTO;
import com.medical.dto.ChronicStatusDTO;
import com.medical.dto.OrganDTO;
import com.medical.model.ExecutSQL;
import com.medical.model.JzChronicbill;
import com.medical.model.JzChronicbillExample;
import com.medical.model.JzChronicbillemploy;
import com.medical.model.JzChronicbillemployExample;
import com.medical.model.JzChronicstatus;
import com.medical.model.JzChronicstatusExample;
import com.medical.model.SysTOrganization;
import com.medical.model.SysTOrganizationExample;
import com.medical.service.ChronicBillService;
import com.medical.system.DictionaryHandle;
import com.medical.system.impl.DictionaryHandleImpl;

public class ChronicBillServiceImpl implements ChronicBillService {
	private ExecutSQLDAO executSQLDAO;
	private JzChronicbillDAO jzChronicbillDAO;
	private JzChronicstatusDAO jzChronicstatusDAO;
	private SysTOrganizationDAO sysTOrganizationDAO;
	private JzChronicbillemployDAO jzChronicbillemployDAO;
	private Pager pager;

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

	@SuppressWarnings("unchecked")
	@Override
	public ChronicBillStatDTO findCountInfo() {
		ChronicBillStatDTO dto = new ChronicBillStatDTO();
		ExecutSQL executSQL = new ExecutSQL();
		String sql = "";
		HashMap map;
		try {
			// * zrc:总救助人次ndzrc:本年救助人次 zxf:总消费金额ndzxf:年度总消费金额 dqrs:享受救助人数
			// String sql =
			// "select count(decode(sign(bil.income), 1, 1)) as zrc,count(decode(sign(bil.income),1,decode(to_char(bil.opttime, 'yyyy'),to_char(sysdate, 'yyyy'),1))) as ndzrc,nvl(sum(bil.payout),0) as zxf,nvl(sum(decode(to_char(bil.opttime, 'yyyy'),to_char(sysdate, 'yyyy'),bil.payout)),0) as ndzxf from jz_chronicbill bil";

			// 年 救助人数 救助金额
			sql = "select count(decode(sign(bil.income), 1, 1)) as ndzrc, sum(bil.income) as NDZSR   "
					+ "from JZ_CHRONICBILL bil  where bil.payout = 0 and substr(bil.subject, 1, 2) <> '退费' "
					+ "and to_char(bil.opttime, 'yyyy') = to_char(sysdate, 'yyyy')";
			executSQL = new ExecutSQL();
			executSQL.setExecutsql(sql);
			map = executSQLDAO.queryAll(executSQL).get(0);
			dto.setNdzrc((BigDecimal) map.get("NDZRC"));
			dto.setNdzsr((BigDecimal) map.get("NDZSR"));

			// 当前救助人数
			sql = "select count(decode(sign(bil.income), 1, 1)) as zrc, sum(bil.income) as ZSR   "
					+ "from JZ_CHRONICBILL bil  where bil.payout = 0 and substr(bil.subject, 1, 2) <> '退费'";
			executSQL = new ExecutSQL();
			executSQL.setExecutsql(sql);
			map = executSQLDAO.queryAll(executSQL).get(0);
			dto.setZrc((BigDecimal) map.get("ZRC"));
			dto.setZsr((BigDecimal) map.get("ZSR"));

			sql = "select count(*) as dqrs from jz_chronicstatus where state = 1 and flag = 1";
			executSQL = new ExecutSQL();
			executSQL.setExecutsql(sql);
			map = executSQLDAO.queryAll(executSQL).get(0);
			dto.setDqrs((BigDecimal) map.get("DQRS"));

			sql = "select  ( "
					+ dto.getZsr()
					+ " - sum(b.balance)) as zxf, sum(b.balance) zbal  from jz_chronicbill b  where b.chronicbill_id in "
					+ " (select max(bill.chronicbill_id) from JZ_CHRONICBILL bill  group by bill.member_id, bill.member_type) ";
			executSQL = new ExecutSQL();
			executSQL.setExecutsql(sql);
			map = executSQLDAO.queryAll(executSQL).get(0);
			dto.setZbal((BigDecimal) map.get("ZBAL"));
			dto.setZxf((BigDecimal) map.get("ZXF"));

			sql = "select  ( "
					+ dto.getNdzsr()
					+ " - sum(b.balance)) as ndzxf, sum(b.balance) znbal  from jz_chronicbill b  where b.chronicbill_id in "
					+ " (select max(bill.chronicbill_id) from JZ_CHRONICBILL bill  group by bill.member_id, bill.member_type) and to_char(b.opttime, 'yyyy') = to_char(sysdate, 'yyyy')";
			executSQL = new ExecutSQL();
			executSQL.setExecutsql(sql);
			map = executSQLDAO.queryAll(executSQL).get(0);
			dto.setZnbal((BigDecimal) map.get("ZNBAL"));
			dto.setNdzxf((BigDecimal) map.get("NDZXF"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public void saveBatchBill(int opt, String subject, BigDecimal money) {
		String reason;
		try {
			if (opt == 0 || opt == 2) {
				reason = subject + (opt == 2 ? "原账户余额冲减清零" : "");
				String sql = "insert into jz_chronicbill (chronicbill_id, family_id, name, ssn, subject, income, payout, balance, opttime,member_id ,member_type) "
						+ " select xchronicbill_id.nextval, bil.family_id, bil.name, bil.ssn,'"
						+ reason
						+ "', -nvl(bil.balance, 0), 0, 0, sysdate,member_id ,member_type from jz_chronicbill bil"
						+ " where chronicbill_id = (select max(bil1.chronicbill_id) from jz_chronicbill bil1 where  (bil1.member_id = bil.member_id and bil1.member_type=bil.member_type)  ) "
						+ " and bil.balance > 0 "
						+ " and exists (select * from jz_chronicstatus cs where cs.state = '1' and cs.flag = '1' and  (cs.member_id = bil.member_id and cs.member_type=bil.member_type) )";
				ExecutSQL executSQL = new ExecutSQL();
				executSQL.setExecutsql(sql);
				executSQLDAO.queryAll(executSQL);

			}
			if (opt == 1 || opt == 2) {
				String sql = "insert into jz_chronicbill (chronicbill_id, family_id, name, ssn, subject, income, payout, balance, opttime ,member_id ,member_type) "
						+ " select xchronicbill_id.nextval, bil.family_id, bil.name, bil.ssn, '"
						+ subject
						+ "',"
						+ money
						+ ", 0, bil.balance +"
						+ money
						+ ", sysdate ,member_id ,member_type"
						+ " from jz_chronicbill bil "
						+ " where chronicbill_id = (select max(bil1.chronicbill_id) from jz_chronicbill bil1 where ( bil1.member_id = bil.member_id and bil1.member_type=bil.member_type)) "
						+ " and exists (select * from jz_chronicstatus cs where cs.state = '1' and cs.flag = '1' and  (cs.member_id = bil.member_id and cs.member_type=bil.member_type))";
				ExecutSQL executSQL = new ExecutSQL();
				executSQL.setExecutsql(sql);
				executSQLDAO.queryAll(executSQL);
				sql = "insert into jz_chronicbill (chronicbill_id, family_id, name, ssn, subject, income, payout, balance, opttime,member_id ,member_type) "
						+ " select xchronicbill_id.nextval, cs.family_id, cs.name, cs.ssn, '"
						+ subject
						+ "',"
						+ money
						+ ", 0, "
						+ money
						+ ", sysdate , cs.member_id ,cs.member_type   from jz_chronicstatus cs, jz_chronicbill bil "
						+ "where cs.state = '1' and cs.flag = '1' and "
						+ " (cs.member_id = bil.member_id(+) and cs.member_type=bil.member_type(+)) "
						+ "and (bil.member_id is null and bil.member_type is null)";
				executSQL = new ExecutSQL();
				executSQL.setExecutsql(sql);
				executSQLDAO.queryAll(executSQL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ChronicStatusDTO> findChronicList(String url, int curpage,
			JzChronicstatusExample example) {
		DictionaryHandle d = new DictionaryHandleImpl();
		List<ChronicStatusDTO> list = new ArrayList<ChronicStatusDTO>();
		pager.setCurrentpage(curpage);
		pager.setAll(jzChronicstatusDAO.countByExample(example));
		pager.setUrl(url);
		pager.setPagesize(16);
		List<JzChronicstatus> rs = jzChronicstatusDAO.selectByExample(example,
				pager.getStart(), pager.getPagesize());

		for (JzChronicstatus s : rs) {
			ChronicStatusDTO e = new ChronicStatusDTO();
			e.setChronicstatusId(s.getChronicstatusId());
			e.setFamilyId(s.getFamilyId());
			e.setName(s.getName());
			e.setSsn(s.getSsn());
			e.setEntity(s.getEntity());
			Integer a = s.getEntity();
			if (null == a) {
			} else {
				e.setEntityname(d.getDictValue(s.getEntity().toString()));
			}
			e.setState(s.getState());
			e.setApptime(s.getApptime());
			e.setFlag(s.getFlag());
			e.setMemberId(s.getMemberId());
			e.setMemberType(s.getMemberType());
			list.add(e);
		}

		return list;

	}

	public List<ChronicBillDTO> findChronicBillBySSN(String memberId,
			String memberType) {
		List<ChronicBillDTO> list = new ArrayList<ChronicBillDTO>();
		JzChronicbillExample example = new JzChronicbillExample();
		example.createCriteria().andMemberIdEqualTo(memberId)
				.andMemberTypeEqualTo(memberType);
		example.setOrderByClause("chronicbill_id asc");
		List<JzChronicbill> rs = jzChronicbillDAO.selectByExample(example);
		for (JzChronicbill s : rs) {
			ChronicBillDTO e = new ChronicBillDTO();
			e.setBalance(s.getBalance());
			e.setChronicbillId(s.getChronicbillId());
			e.setFamilyId(s.getFamilyId());
			e.setIncome(s.getIncome());
			e.setName(s.getName());
			e.setOpttime(s.getOpttime());
			e.setPayout(s.getPayout());
			e.setSsn(s.getSsn());
			e.setSubject(s.getSubject());
			e.setMemberId(s.getMemberId());
			e.setMemberType(s.getMemberType());
			list.add(e);
		}
		return list;
	}

	public String saveChronicBill(ChronicBillDTO chronicBillDTO,
			ChronicStatusDTO chronicStatusDTO, String opt) {
		String result = "保存成功";

		String ssn = chronicBillDTO.getSsn();
		JzChronicbillExample example = new JzChronicbillExample();
		example.createCriteria().andSsnEqualTo(ssn);
		example.setOrderByClause("chronicbill_id asc");
		List<JzChronicbill> rs = jzChronicbillDAO.selectByExample(example);
		if (rs != null && rs.size() > 0) {
			String subject = chronicBillDTO.getSubject();
			BigDecimal balance = rs.get((rs.size() - 1)).getBalance();
			BigDecimal income = chronicBillDTO.getIncome();
			JzChronicstatus jzChronicstatus = jzChronicstatusDAO
					.selectByPrimaryKey(chronicStatusDTO.getChronicstatusId());
			if ("0".equals(opt)) {
				JzChronicbill record = new JzChronicbill();
				if (balance.compareTo(new BigDecimal(0)) >= 0) {
					record.setBalance(new BigDecimal(0));
					record.setIncome(balance.multiply(new BigDecimal(-1)));
					record.setPayout(new BigDecimal(0));
					record.setFamilyId(jzChronicstatus.getFamilyId());
					record.setName(jzChronicstatus.getName());
					record.setSsn(ssn);
					record.setSubject(subject);
					record.setOpttime(new Date());
					record.setMemberId(jzChronicstatus.getMemberId());
					record.setMemberType(jzChronicstatus.getMemberType());
					jzChronicbillDAO.insertSelective(record);
				} else {
					result = "余额小于0元，不能够清零";
				}
			} else if ("1".equals(opt)) {
				JzChronicbill record = new JzChronicbill();
				balance = balance.add(income);
				if (balance.compareTo(new BigDecimal(0)) >= 0) {
					record.setBalance(balance);
					record.setIncome(income);
					record.setPayout(new BigDecimal(0));
					record.setFamilyId(jzChronicstatus.getFamilyId());
					record.setName(jzChronicstatus.getName());
					record.setSsn(ssn);
					record.setSubject(subject);
					record.setOpttime(new Date());
					record.setMemberId(jzChronicstatus.getMemberId());
					record.setMemberType(jzChronicstatus.getMemberType());
					jzChronicbillDAO.insertSelective(record);
				} else {
					result = "余额小于0元，不能够存入";
				}

			} else if ("2".equals(opt)) {
				JzChronicbill record = new JzChronicbill();
				if (balance.compareTo(new BigDecimal(0)) >= 0) {
					record.setBalance(new BigDecimal(0));
					record.setIncome(balance.multiply(new BigDecimal(-1)));
					record.setPayout(new BigDecimal(0));
					record.setFamilyId(jzChronicstatus.getFamilyId());
					record.setName(jzChronicstatus.getName());
					record.setSsn(ssn);
					record.setSubject(subject);
					record.setOpttime(new Date());
					record.setMemberId(jzChronicstatus.getMemberId());
					record.setMemberType(jzChronicstatus.getMemberType());
					jzChronicbillDAO.insertSelective(record);
					balance = record.getBalance();
				} else {
					result = "余额小于0元，不能够清零";
				}

				record = new JzChronicbill();
				balance = balance.add(income);
				if (balance.compareTo(new BigDecimal(0)) >= 0) {
					record.setBalance(balance);
					record.setIncome(income);
					record.setPayout(new BigDecimal(0));
					record.setFamilyId(jzChronicstatus.getFamilyId());
					record.setName(jzChronicstatus.getName());
					record.setSsn(ssn);
					record.setSubject(subject);
					record.setOpttime(new Date());
					record.setMemberId(jzChronicstatus.getMemberId());
					record.setMemberType(jzChronicstatus.getMemberType());
					jzChronicbillDAO.insertSelective(record);
				} else {
					result = "余额小于0元，不能够存入";
				}
			}
		} else {
			result = "没有账单记录，补录不成功";
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> findChronicStatBySsn(String memberId,
			String memberType) {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select count(1) as jzcs ,sum(bill.income) as ffje  from jz_chronicbill bill where bill.member_id ='"
				+ memberId
				+ "' and bill.member_type= '"
				+ memberType
				+ "' and bill.income >0";
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			List<HashMap> rs = executSQLDAO.queryAll(executSQL);
			if (null != rs && rs.size() > 0) {
				HashMap m = rs.get(0);
				map.put("JZCS", "" + m.get("JZCS").toString());
				map.put("FFJE", "" + m.get("FFJE").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	public ExecutSQLDAO getExecutSQLDAO() {
		return executSQLDAO;
	}

	public JzChronicbillDAO getJzChronicbillDAO() {
		return jzChronicbillDAO;
	}

	public Pager getPager() {
		return pager;
	}

	public void setExecutSQLDAO(ExecutSQLDAO executSQLDAO) {
		this.executSQLDAO = executSQLDAO;
	}

	public void setJzChronicbillDAO(JzChronicbillDAO jzChronicbillDAO) {
		this.jzChronicbillDAO = jzChronicbillDAO;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public void setJzChronicstatusDAO(JzChronicstatusDAO jzChronicstatusDAO) {
		this.jzChronicstatusDAO = jzChronicstatusDAO;
	}

	public JzChronicstatusDAO getJzChronicstatusDAO() {
		return jzChronicstatusDAO;
	}

	public SysTOrganizationDAO getSysTOrganizationDAO() {
		return sysTOrganizationDAO;
	}

	public void setSysTOrganizationDAO(SysTOrganizationDAO sysTOrganizationDAO) {
		this.sysTOrganizationDAO = sysTOrganizationDAO;
	}

	public JzChronicbillemployDAO getJzChronicbillemployDAO() {
		return jzChronicbillemployDAO;
	}

	public void setJzChronicbillemployDAO(
			JzChronicbillemployDAO jzChronicbillemployDAO) {
		this.jzChronicbillemployDAO = jzChronicbillemployDAO;
	}

	@SuppressWarnings("unchecked")
	public List<ChronicBillDTO> findChronicBills(String url, Integer curPage,
			String sql) {
		List<ChronicBillDTO> list = new ArrayList<ChronicBillDTO>();
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		try {
			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl(url);
			pager.setCurrentpage(curPage);
			pager.setPagesize(14);
			pager.getToolsmenu();
			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());
			List<HashMap> rs = executSQLDAO.queryRow(executSQL);
			//CHRONICBILL_ID  FAMILY_ID  NAME  SSN  SUBJECT  INCOME  PAYOUT  BALANCE  OPTTIME  MEMBER_ID  MEMBER_TYPE
			for(HashMap s : rs){
				ChronicBillDTO e=new ChronicBillDTO();
				e.setBalance((BigDecimal) s.get("BALANCE"));
				e.setPayout((BigDecimal) s.get("PAYOUT"));
				e.setIncome((BigDecimal) s.get("INCOME"));
				e.setMemberId((String) s.get("MEMBER_ID"));
				e.setMemberType((String) s.get("MEMBER_TYPE"));
				e.setFamilyId((String) s.get("FAMILY_ID"));
				e.setName((String) s.get("NAME"));
				e.setOpttime((Date) s.get("OPTTIME"));
				e.setSubject((String) s.get("SUBJECT"));
				e.setSsn((String) s.get("SSN"));
				e.setPaperid((String) s.get("paperid"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ChronicBillEmployDTO findChronicBillEmploy(ChronicBillEmployDTO cbedto){
		ChronicBillEmployDTO cbe = new ChronicBillEmployDTO();
		JzChronicbillemployExample example = new JzChronicbillemployExample();
		example.createCriteria().andPasswordEqualTo(cbedto.getPassword()).andStatusEqualTo(new BigDecimal("1"));
		List<JzChronicbillemploy> rs = jzChronicbillemployDAO.selectByExample(example);
		if(rs.size()>0){
			JzChronicbillemploy cc = rs.get(0);
			cbe.setEmployeeId(cc.getEmployeeId());
			cbe.setAccount(cc.getAccount());
			cbe.setPassword(cc.getPassword());
			cbe.setStatus(cc.getStatus());
		}
		return cbe;
	}

}
