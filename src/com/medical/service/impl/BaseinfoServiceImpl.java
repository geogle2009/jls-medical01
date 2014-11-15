package com.medical.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.medical.common.Pager;
import com.medical.dao.ExecutSQLDAO;
import com.medical.dao.JzActDAO;
import com.medical.dao.JzBizDAO;
import com.medical.dao.JzMedicalafterDAO;
import com.medical.dao.MemberBaseinfoDAO;
import com.medical.dao.TestSsnDAO;
import com.medical.dto.BaseInfoDTO;
import com.medical.dto.BizDTO;
import com.medical.dto.CheckDTO;
import com.medical.dto.HealthDTO;
import com.medical.dto.MedicalafterDTO;
import com.medical.model.ExecutSQL;
import com.medical.model.JzAct;
import com.medical.model.JzActExample;
import com.medical.model.JzBiz;
import com.medical.model.JzBizExample;
import com.medical.model.JzMedicalafter;
import com.medical.model.JzMedicalafterExample;
import com.medical.model.MemberBaseinfo;
import com.medical.model.MemberBaseinfoExample;
import com.medical.model.MemberBaseinfoExample.Criteria;
import com.medical.model.TestSsn;
import com.medical.model.TestSsnExample;
import com.medical.service.BaseinfoService;

public class BaseinfoServiceImpl implements BaseinfoService {
	private MemberBaseinfoDAO memberBaseinfoDAO;
	private ExecutSQLDAO executSQLDAO;
	private JzBizDAO jzBizDAO;
	private Pager pager;
	private String toolsmenu;
	private TestSsnDAO testSsnDAO;
	private JzMedicalafterDAO jzMedicalafterDAO;
	private JzActDAO jzActDAO;

	@Override
	public HealthDTO findByMemberId(String memberId, String ds) {
		HealthDTO dto = new HealthDTO();
		/*
		 * MemberBaseinfoExample memberexample = new MemberBaseinfoExample();
		 * com.medical.model.MemberBaseinfoExample.Criteria membercriteria =
		 * memberexample .createCriteria();
		 * membercriteria.andMemberIdEqualTo(memberId).andDsEqualTo(ds);
		 * memberBaseinfoDAO.selectByExample(memberexample);
		 * Iterator<MemberBaseinfo> it = memberBaseinfoDAO.selectByExample(
		 * memberexample).iterator(); if (it.hasNext()) { MemberBaseinfo
		 * memberBaseinfo = it.next();
		 * dto.setMemberId(memberBaseinfo.getMemberId());
		 * dto.setFamilyno(memberBaseinfo.getFamilyno());
		 * dto.setMasterName(memberBaseinfo.getMasterName());
		 * dto.setRelmaster(memberBaseinfo.getRelmaster());
		 * dto.setMembername(memberBaseinfo.getMembername());
		 * dto.setPaperid(memberBaseinfo.getPaperid());
		 * dto.setSsn(memberBaseinfo.getSsn());
		 * dto.setSex(memberBaseinfo.getSex());
		 * dto.setBirthday(memberBaseinfo.getBirthday());
		 * dto.setRprkind(memberBaseinfo.getRprkind());
		 * dto.setRprtype(memberBaseinfo.getRprtype());
		 * dto.setRpraddress(memberBaseinfo.getRpraddress());
		 * dto.setLinkmode(memberBaseinfo.getLinkmode());
		 * dto.setHealth(memberBaseinfo.getHealth());
		 * dto.setSickentype(memberBaseinfo.getSickentype());
		 * dto.setSickenname(memberBaseinfo.getSickenname());
		 * dto.setDeformity(memberBaseinfo.getDeformity());
		 * dto.setDefgrade(memberBaseinfo.getDefgrade()); }
		 */
		String ssn = memberId;
		if (ssn != null && !"".equals(ssn)) {
			JzBizExample bizexample = new JzBizExample();
			com.medical.model.JzBizExample.Criteria bizCriteria = bizexample
					.createCriteria();
			bizCriteria.andSsnEqualTo(ssn);
			Iterator<JzBiz> itbiz = jzBizDAO.selectByExample(bizexample)
					.iterator();
			List<BizDTO> bizlist = new ArrayList<BizDTO>();
			while (itbiz.hasNext()) {
				BizDTO bizDTO = new BizDTO();
				JzBiz biz = itbiz.next();
				bizDTO.setBizId(biz.getBizId());
				// bizDTO.setHospitalId(biz.getHospitalId());
				if ("H015".equals(biz.getHospitalId())) {
					bizDTO.setHospitalId("ҽҩѧԺ����ҽԺ");
				} else if ("H005".equals(biz.getHospitalId())) {
					bizDTO.setHospitalId("����ʡ����ҽ���ҽԺ");
				} else if ("H007".equals(biz.getHospitalId())) {
					bizDTO.setHospitalId("��ҽԺ");
				} else if ("H001".equals(biz.getHospitalId())) {
					bizDTO.setHospitalId("����ҽԺ");
				}
				bizDTO.setCenterId(biz.getCenterId());
				if ("10".equals(biz.getBizType())) {
					bizDTO.setBizType("��ҩ");
				} else if ("11".equals(biz.getBizType())) {
					bizDTO.setBizType("����");
				} else if ("12".equals(biz.getBizType())) {
					bizDTO.setBizType("סԺ");
				} else if ("13".equals(biz.getBizType())) {
					bizDTO.setBizType("�������Բ����ش󼲲�");
				} else if ("14".equals(biz.getBizType())) {
					bizDTO.setBizType("��ͥ����");
				} else if ("16".equals(biz.getBizType())) {
					bizDTO.setBizType("�������ⲡ(�����ؼ�)");
				} else if ("41".equals(biz.getBizType())) {
					bizDTO.setBizType("��������");
				} else if ("42".equals(biz.getBizType())) {
					bizDTO.setBizType("����סԺ");
				} else if ("51".equals(biz.getBizType())) {
					bizDTO.setBizType("��������");
				} else if ("52".equals(biz.getBizType())) {
					bizDTO.setBizType("����סԺ");
				}
				bizDTO.setBizName(biz.getBizName());
				bizDTO.setSerialNo(biz.getSerialNo());
				bizDTO.setSsn(biz.getSsn());
				bizDTO.setFamilyId(biz.getFamilyId());
				bizDTO.setName(biz.getName());
				bizDTO.setSex(biz.getSex());
				bizDTO.setBirthday(biz.getBirthday());
				bizDTO.setIdcard(biz.getIdcard());
				bizDTO.setTreatmentType(biz.getTreatmentType());
				bizDTO.setTreatmentName(biz.getTreatmentName());
				bizDTO.setBizTimes(biz.getBizTimes());
				bizDTO.setRegDate(biz.getRegDate());
				bizDTO.setBeginDate(biz.getBeginDate());
				bizDTO.setInDeptName(biz.getInDeptName());
				bizDTO.setInAreaName(biz.getInAreaName());
				bizDTO.setInBed(biz.getInBed());
				bizDTO.setInDisease(biz.getInDisease());
				bizDTO.setInDiseaseName(biz.getInDiseaseName());
				bizDTO.setDiagnose(biz.getDiagnose());
				bizDTO.setDiagnoseName(biz.getDiagnoseName());
				bizDTO.setDiagnoseDate(biz.getDiagnoseDate());
				bizDTO.setFinDisease(biz.getFinDisease());
				bizDTO.setFinDiseaseName(biz.getFinDiseaseName());
				bizDTO.setEndDate(biz.getEndDate());
				bizDTO.setOutDeptName(biz.getOutDeptName());
				bizDTO.setComplications(biz.getComplications());
				bizDTO.setInDays(biz.getInDays());
				bizDTO.setAssistType(biz.getAssistType());
				bizDTO.setStaus(biz.getStaus());
				bizDTO.setPatientId(biz.getPatientId());
				bizDTO.setFinDate(biz.getFinDate());
				bizDTO.setInmoney(biz.getInmoney());
				bizDTO.setOperuid(biz.getOperuid());
				bizDTO.setOpertime(biz.getOpertime());
				bizDTO.setAssistFlag(biz.getAssistFlag());
				bizDTO.setPhotopath(biz.getPhotopath());
				bizDTO.setAllmoney(biz.getAllmoney());
				bizDTO.setPersonpay(biz.getPersonpay());
				bizDTO.setInsurancepay(biz.getInsurancepay());
				bizDTO.setAssistpay(biz.getAssistpay());
				bizDTO.setAssistpaymsg(biz.getAssistpaymsg());
				bizDTO.setPhotopath2(biz.getPhotopath2());
				bizDTO.setConfirmFlag(biz.getConfirmFlag());
				bizDTO.setInMoney(biz.getInMoney());
				bizDTO.setPersonType(biz.getPersonType());
				bizDTO.setFeeBatch(biz.getFeeBatch());
				bizDTO.setOutFlag(biz.getOutFlag());
				bizDTO.setAlreadyGet(biz.getAlreadyGet());
				bizDTO.setGatherFlag(biz.getGatherFlag());
				bizDTO.setSmsState(biz.getSmsState());
				bizDTO.setCheckState(biz.getCheckState());
				bizlist.add(bizDTO);
			}
			dto.setBizDTOs(bizlist);
		}
		return dto;
	}

	@Override
	public List<HealthDTO> findForperson(HealthDTO healthDTO, String onno) {

		String term = healthDTO.getTerm();
		String operational = healthDTO.getOperational();
		String value = healthDTO.getValue();
		List<HealthDTO> result = new ArrayList<HealthDTO>();
		MemberBaseinfoExample example = new MemberBaseinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andOnNoLike(onno + "%");
		if (null != value & !"".equals(value)) {
			if ("SSN".equals(term)) {
				// ��ᱣ�պ�
				if ("=".equals(operational)) {
					criteria.andSsnEqualTo(value);
				} else if ("like".equals(operational)) {
					criteria.andSsnLike("%" + value + "%");
				}
			} else if ("FAMILYNO".equals(term)) {
				// ��ͥ���
				if ("=".equals(operational)) {
					criteria.andFamilynoEqualTo(value);
				} else if ("like".equals(operational)) {
					criteria.andFamilynoLike("%" + value + "%");
				}
			} else if ("MEMBERNAME".equals(term)) {
				// ����
				if ("=".equals(operational)) {
					criteria.andMembernameEqualTo(value);

				} else if ("like".equals(operational)) {
					criteria.andMembernameLike("%" + value + "%");
				}
			} else if ("PAPERID".equals(term)) {
				// ����֤��
				if ("=".equals(operational)) {
					criteria.andPaperidEqualTo(value);
				} else if ("like".equals(operational)) {
					criteria.andPaperidLike("%" + value + "%");
				}
			}
		}
		this.pager.setCurrentpage(healthDTO.getCurpage());
		this.pager.setAll(memberBaseinfoDAO.countByExample(example));
		this.pager.setUrl(healthDTO.getUrl());
		this.pager.setPagesize(healthDTO.getPageSize());
		setToolsmenu(this.pager.genToolsmenu());

		Iterator<MemberBaseinfo> it = memberBaseinfoDAO.selectByExample(
				example, pager.getStart(), pager.getPagesize()).iterator();

		while (it.hasNext()) {
			MemberBaseinfo memberBaseinfo = it.next();
			HealthDTO dto = new HealthDTO();
			dto.setMemberId(memberBaseinfo.getMemberId());
			dto.setFamilyno(memberBaseinfo.getFamilyno());
			dto.setMasterName(memberBaseinfo.getMasterName());
			dto.setRelmaster(memberBaseinfo.getRelmaster());
			dto.setMembername(memberBaseinfo.getMembername());
			dto.setPaperid(memberBaseinfo.getPaperid());
			dto.setSsn(memberBaseinfo.getSsn());
			dto.setSex(memberBaseinfo.getSex());
			dto.setBirthday(memberBaseinfo.getBirthday());
			dto.setRprkind(memberBaseinfo.getRprkind());
			dto.setRprtype(memberBaseinfo.getRprtype());
			dto.setRpraddress(memberBaseinfo.getRpraddress());
			dto.setLinkmode(memberBaseinfo.getLinkmode());
			result.add(dto);
		}

		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public List<CheckDTO> findAllMemberInfo(String url, Integer curpage,
			String sql) {
		List<CheckDTO> list = new ArrayList<CheckDTO>();
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
		pager.setCurrentpage(curpage);
		pager.setUrl(url);
		pager.setPagesize(16);
		try {
			pager.setAll(executSQLDAO.queryCnt(executSQL));
			pager.setUrl(url);
			pager.getToolsmenu();
			executSQL.setEnd(pager.getEnd());
			executSQL.setStart(pager.getStart());
			List<HashMap> rs = executSQLDAO.queryRow(executSQL);
			for (HashMap s : rs) {
				CheckDTO e = new CheckDTO();
				e.setFamilyno((String) s.get("FAMILYNO"));
				e.setMembername((String) s.get("MEMBERNAME"));
				e.setPaperid((String) s.get("PAPERID"));
				e.setMemberId((String) s.get("MEMBER_ID"));
				e.setDs((String) s.get("DS"));
				e.setSsn((String) s.get("SSN"));
				e.setSsn1((String) s.get("SSN1"));
				e.setSsn2((String) s.get("SSN2"));
				e.setSsn3((String) s.get("SSN3"));
				e.setPersonstate((String) s.get("PERSONSTATE"));
				e.setAssistType((String) s.get("ASSIST_TYPE"));
				e.setAsort((BigDecimal) s.get("ASORT"));
				list.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	public CheckDTO findMemberInfo(CheckDTO checkDTO) {
		CheckDTO cdto = new CheckDTO();
		MemberBaseinfoExample example = new MemberBaseinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andMemberIdEqualTo(checkDTO.getMemberId());
		criteria.andDsEqualTo(checkDTO.getDs());
		MemberBaseinfo info = memberBaseinfoDAO.selectByExample(example).get(0);
		cdto.setFamilyno(info.getFamilyno());
		cdto.setMembername(info.getMembername());
		cdto.setPaperid(info.getPaperid());
		cdto.setSsn(info.getSsn());
		cdto.setDs(info.getDs());
		cdto.setMemberId(info.getMemberId());
		cdto.setAssistType(info.getAssistType());
		cdto.setAsort(info.getAsort());
		cdto.setPersonstate(info.getPersonstate());
		cdto.setMasterName(info.getMasterName());
		return cdto;
	}

	public int updateTestSsn(CheckDTO checkDTO) {
		int u = 0;
		int m = 0;
		int return_flag = 0;
		TestSsn record = new TestSsn();
		TestSsnExample example = new TestSsnExample();
		com.medical.model.TestSsnExample.Criteria criteria = example
				.createCriteria();
		criteria.andMemberIdEqualTo(checkDTO.getMemberId());
		criteria.andDsEqualTo(checkDTO.getDs());
		record.setSsn1(checkDTO.getSsn1());
		record.setSsn2(checkDTO.getSsn2());
		record.setSsn3(checkDTO.getSsn3());
		// ����test_ssn��
		u = testSsnDAO.updateByExampleSelective(record, example);
		// ����member_baseinfo��ͼ
		MemberBaseinfo record1 = new MemberBaseinfo();
		MemberBaseinfoExample example1 = new MemberBaseinfoExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andMemberIdEqualTo(checkDTO.getMemberId());
		criteria1.andDsEqualTo(checkDTO.getDs());
		record1.setSsn(checkDTO.getSsn1());
		m = memberBaseinfoDAO.updateByExampleSelective(record1, example1);
		// ���³�����Ա��Ϣ��
		if ("1".equals(checkDTO.getDs())) {
			String sql = " update familymember@cs.regress.rdbms.dev.us.oracle.com fcs "
					+ " set fcs.indi_id='"
					+ checkDTO.getSsn1()
					+ "'"
					+ " where fcs.fm_id='" + checkDTO.getMemberId() + "'";
			ExecutSQL executSQL = new ExecutSQL();
			executSQL.setExecutsql(sql);
			try {
				executSQLDAO.updateSQL(executSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("2".equals(checkDTO.getDs())) {
			u = -1;
		}
		if (u == 1 && m == 1) {
			return_flag = 1;
		} else {
			return_flag = 0;
		}
		return return_flag;
	}

	public List<BaseInfoDTO> findMemberByPaperId(BaseInfoDTO baseInfoDTO) {
		List<BaseInfoDTO> mbdtos = new ArrayList<BaseInfoDTO>();
		MemberBaseinfoExample example = new MemberBaseinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andPaperidEqualTo(baseInfoDTO.getPaperid());
		criteria.andPersonstateEqualTo("����");
		List<String> values = new ArrayList<String>();
		values.add("10");
		values.add("11");
		criteria.andAssistTypeIn(values);
		List<MemberBaseinfo> info = memberBaseinfoDAO.selectByExample(example);
		for (MemberBaseinfo s : info) {
			BaseInfoDTO e = new BaseInfoDTO();
			e.setFamilyno(s.getFamilyno());
			e.setMembername(s.getMembername());
			e.setMasterName(s.getMasterName());
			e.setSex(s.getSex());
			e.setAddress(s.getAddress());
			e.setPaperid(s.getPaperid());
			e.setSsn(s.getSsn());
			e.setDs(s.getDs());
			e.setMemberId(s.getMemberId());
			e.setAssistType(s.getAssistType());
			e.setAsort(s.getAsort());
			e.setPersonstate(s.getPersonstate());
			e.setMasterName(s.getMasterName());
			e.setRelmaster(s.getRelmaster());
			e.setOnNo(s.getOnNo());
			mbdtos.add(e);
		}
		return mbdtos;
	}

	public List<MedicalafterDTO> findMedicalaftersByPaperId(
			BaseInfoDTO baseInfoDTO) {
		List<MedicalafterDTO> madtos = new ArrayList<MedicalafterDTO>();
		JzMedicalafterExample example = new JzMedicalafterExample();
		com.medical.model.JzMedicalafterExample.Criteria criteria = example
				.createCriteria();
		criteria.andPaperidEqualTo(baseInfoDTO.getPaperid());
		List<JzMedicalafter> info = jzMedicalafterDAO.selectByExample(example);
		for (JzMedicalafter s : info) {
			MedicalafterDTO e = new MedicalafterDTO();
			e.setMaId(s.getMaId());
			e.setFamilyno(s.getFamilyno());
			e.setMembername(s.getMembername());
			e.setPaperid(s.getPaperid());
			e.setSsn(s.getSsn());
			e.setHospital(s.getHospital());
			e.setHospitallevel(s.getHospitallevel());
			e.setSickencontent(s.getSickencontent());
			e.setBegintime(s.getBegintime());
			e.setEndtime(s.getEndtime());
			e.setApproveresult(s.getApproveresult());
			e.setApprovecontent(s.getApprovecontent());
			e.setTotalcost(s.getTotalcost());
			e.setInsurepay(s.getInsurepay());
			e.setOutpay(s.getOutpay());
			e.setCapay(s.getCapay());
			e.setBusinesspay(s.getBusinesspay());
			e.setAsisstpay(s.getAsisstpay());
			e.setCreatetime(s.getCreatetime());
			e.setUpdatetime(s.getUpdatetime());
			e.setMemberId(s.getMemberId());
			e.setMemberType(s.getMemberType());
			e.setImplsts(s.getImplsts());
			e.setTiketno(s.getTiketno());
			e.setMedicaltype(s.getMedicaltype());
			e.setInsuretype(s.getInsuretype());
			e.setPersontype(s.getPersontype());
			e.setOnNo(s.getOnNo());
			madtos.add(e);
		}
		return madtos;
	}

	public List<MedicalafterDTO> queryMedicalafters(
			JzMedicalafterExample example, Integer curpage) {
		List<JzMedicalafter> info = jzMedicalafterDAO.selectByExample(example);
		List<MedicalafterDTO> madtos = new ArrayList<MedicalafterDTO>();

		pager.setAll(info.size());
		pager.setUrl("queryafter.action");
		pager.setCurrentpage(curpage.intValue());
		pager.setPagesize(14);
		pager.getToolsmenu();

		for (int i = 0; i < pager.getPagesize(); i++) {
			if (pager.getStart() + i < pager.getAll()) {
				JzMedicalafter s = info.get(pager.getStart() + i);
				MedicalafterDTO e = new MedicalafterDTO();
				e.setMaId(s.getMaId());
				e.setFamilyno(s.getFamilyno());
				e.setMembername(s.getMembername());
				e.setPaperid(s.getPaperid());
				e.setSsn(s.getSsn());
				e.setHospital(s.getHospital());
				e.setHospitallevel(s.getHospitallevel());
				e.setSickencontent(s.getSickencontent());
				e.setBegintime(s.getBegintime());
				e.setEndtime(s.getEndtime());
				e.setApproveresult(s.getApproveresult());
				e.setApprovecontent(s.getApprovecontent());
				e.setTotalcost(s.getTotalcost());
				e.setInsurepay(s.getInsurepay());
				e.setOutpay(s.getOutpay());
				e.setCapay(s.getCapay());
				e.setBusinesspay(s.getBusinesspay());
				e.setAsisstpay(s.getAsisstpay());
				e.setCreatetime(s.getCreatetime());
				e.setUpdatetime(s.getUpdatetime());
				e.setMemberId(s.getMemberId());
				e.setMemberType(s.getMemberType());
				e.setImplsts(s.getImplsts());
				e.setTiketno(s.getTiketno());
				e.setMedicaltype(s.getMedicaltype());
				e.setInsuretype(s.getInsuretype());
				e.setPersontype(s.getPersontype());
				madtos.add(e);
			}
		}
		return madtos;
	}

	public MedicalafterDTO findMemberByID(BaseInfoDTO baseInfoDTO) {
		MedicalafterDTO e = new MedicalafterDTO();
		MemberBaseinfoExample example = new MemberBaseinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andMemberIdEqualTo(baseInfoDTO.getMemberId());
		criteria.andDsEqualTo(baseInfoDTO.getDs());
		MemberBaseinfo s = memberBaseinfoDAO.selectByExample(example).get(0);
		e.setFamilyno(s.getFamilyno());
		e.setMembername(s.getMembername());
		e.setMasterName(s.getMasterName());
		e.setSex(s.getSex());
		e.setAddress(s.getAddress());
		e.setPaperid(s.getPaperid());
		e.setSsn(s.getSsn());
		e.setMemberType(s.getDs());
		e.setMemberId(s.getMemberId());
		e.setAssistType(s.getAssistType());
		e.setAsort(s.getAsort());
		e.setPersonstate(s.getPersonstate());
		e.setRelmaster(s.getRelmaster());
		e.setOnNo(s.getOnNo());
		return e;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MedicalafterDTO saveAfterApply(MedicalafterDTO medicalafterDTO) {
	
			/*JzActExample example = new JzActExample();
			example.createCriteria()
					.andMemberIdEqualTo(medicalafterDTO.getMemberId())
					.andMemberTypeEqualTo(medicalafterDTO.getMemberType())
					.andActYearEqualTo((short) Calendar.YEAR);

			List<JzAct> acts = jzActDAO.selectByExample(example);
			JzAct currentact = new JzAct();
			if (null != acts && acts.size() > 0) {
				currentact = acts.get(0);
			} else {
				currentact.setMemberId(medicalafterDTO.getMemberId());
				currentact.setMemberType(medicalafterDTO.getMemberType());
				currentact.setActYear((short) Calendar.YEAR);
				currentact.setActBizInhospitalTimes((short) 0);
				currentact.setActBizMoney(new BigDecimal(0));
				currentact.setActBizMoney2(new BigDecimal(0));
				currentact.setActId(jzActDAO.insertSelective(currentact));
			}*/

			JzMedicalafter record = new JzMedicalafter();
			if (null == medicalafterDTO.getMaId()) {
				record.setFamilyno(medicalafterDTO.getFamilyno());
				record.setMembername(medicalafterDTO.getMembername());
				record.setPaperid(medicalafterDTO.getPaperid());
				record.setSsn(medicalafterDTO.getSsn());
				record.setHospital(medicalafterDTO.getHospital());
				record.setHospitallevel(medicalafterDTO.getHospitallevel());
				record.setSickencontent(medicalafterDTO.getSickencontent());
				record.setBegintime(medicalafterDTO.getBegintime());
				record.setEndtime(medicalafterDTO.getEndtime());
				record.setApproveresult(medicalafterDTO.getApproveresult());
				record.setTotalcost(medicalafterDTO.getTotalcost());
				record.setInsurepay(medicalafterDTO.getInsurepay());
				record.setOutpay(medicalafterDTO.getOutpay());
				record.setCapay(medicalafterDTO.getCapay());
				record.setBusinesspay(medicalafterDTO.getBusinesspay());
				record.setAsisstpay(medicalafterDTO.getAsisstpay());
				record.setCreatetime(new Date());
				record.setUpdatetime(record.getCreatetime());
				record.setMemberId(medicalafterDTO.getMemberId());
				record.setMemberType(medicalafterDTO.getMemberType());
				record.setImplsts("0");
				record.setTiketno(medicalafterDTO.getTiketno());
				record.setMedicaltype(medicalafterDTO.getMedicaltype());
				record.setInsuretype(medicalafterDTO.getInsuretype());
				record.setPersontype(medicalafterDTO.getPersontype());
				record.setOnNo(medicalafterDTO.getOnNo());
				record.setPayLine(medicalafterDTO.getPayLine());
				record.setHospitalpay(medicalafterDTO.getHospitalpay());
				BigDecimal id = jzMedicalafterDAO.insertSelective(record);
				medicalafterDTO.setMaId(id);
/*
				HashMap paramMap = new HashMap();
				paramMap.put("DS", new Integer(record.getMemberType()));
				paramMap.put("MEDICARE_TYPE",
						new Integer(record.getMedicaltype()));
				if ("111".equals(record.getPersontype())) {
					paramMap.put("ZBZ_FLAG", 2);
				} else if ("101".equals(record.getPersontype())) {
					paramMap.put("ZBZ_FLAG", 2);
				} else {
					paramMap.put("ZBZ_FLAG", 1);
				}
				paramMap.put("HOSPITAL_LEVEL",
						new Integer(record.getHospitallevel()));
				paramMap.put("BIZ_TYPE", new Integer(record.getMedicaltype()));
				paramMap.put("DIAGNOSE", new Integer(record.getSickencontent()));

				paramMap.put("ZYCOUNT", currentact.getActBizInhospitalTimes());// סԺ����
				paramMap.put("SINGLE_FLAG", 0);// �����ֱ�־��ҽ�����Ϊ0
				paramMap.put("SINGLE_COUNT", 0);// �����ִ�����ҽ�����Ϊ0

				paramMap.put("PAY_SUMMZ", currentact.getActBizMoney2());// ����������ܾ�����
				paramMap.put("PAY_SUMZY", currentact.getActBizMoney());// �����סԺ�ܾ�����

				paramMap.put("PAY_TOTAL", record.getTotalcost().doubleValue());
				paramMap.put("PAY_BEGINLINE", record.getPayLine().doubleValue());
				paramMap.put("PAY_MEDICARE", record.getInsurepay()
						.doubleValue());
				paramMap.put("PAY_HOSADD", record.getHospitalpay()
						.doubleValue());
				paramMap.put("PAY_OUT", record.getOutpay().doubleValue());
				paramMap.put("PAY_DBBX", record.getCapay().doubleValue());
				paramMap.put("PAY_COMM", record.getBusinesspay().doubleValue());
				paramMap.put("PAY_ASSIST", 0);
				paramMap.put("CALC_DESC", "��ʼ����");
				HashMap resultmap = executSQLDAO.countAssist(paramMap);

				resultmap.get("PAY_ASSIST");
				resultmap.get("CALC_DESC");*/

			} else {
			}
		 
		return medicalafterDTO;
	}
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public MedicalafterDTO findCountAssist(MedicalafterDTO medicalafterDTO) {
		try {
			JzActExample example = new JzActExample();
			example.createCriteria()
					.andMemberIdEqualTo(medicalafterDTO.getMemberId())
					.andMemberTypeEqualTo(medicalafterDTO.getMemberType())
					.andActYearEqualTo((short) Calendar.YEAR);

			List<JzAct> acts = jzActDAO.selectByExample(example);
			JzAct currentact = new JzAct();
			if (null != acts && acts.size() > 0) {
				currentact = acts.get(0);
			} else {
				currentact.setMemberId(medicalafterDTO.getMemberId());
				currentact.setMemberType(medicalafterDTO.getMemberType());
				currentact.setActYear((short) Calendar.YEAR);
				currentact.setActBizInhospitalTimes((short) 0);
				currentact.setActBizMoney(new BigDecimal(0));
				currentact.setActBizMoney2(new BigDecimal(0));
				currentact.setActId(jzActDAO.insertSelective(currentact));
			}
			/*v_result := func_calcassist(ds => v_ds,
                    medicare_type => v_medicare_type,
                    zbz_flag => v_zbz_flag,
                    hospital_level => v_hospital_level,
                    biz_type => v_biz_type,
                    diagnose => v_diagnose,
                    zycount => v_zycount,
                    single_flag => v_single_flag,
                    single_count => v_single_count,
                    pay_summz => v_pay_summz,
                    pay_sumzy => v_pay_sumzy,
                    pay_total => v_pay_total,
                    pay_beginline => v_pay_beginline,
                    pay_medicare => v_pay_medicare,
                    pay_hosadd => v_pay_hosadd,
                    pay_out => v_pay_out,
                    pay_dbbx => v_pay_dbbx,
                    pay_comm => v_pay_comm);*/
			String ZBZ_FLAG="";
			if ("111".equals(medicalafterDTO.getPersontype())) {
				 ZBZ_FLAG ="2";
			} else if ("101".equals(medicalafterDTO.getPersontype())) {
				 ZBZ_FLAG ="2";
			} else {
				 ZBZ_FLAG ="1";
			}
			String sql = "select func_calcassist('"+medicalafterDTO.getMemberType()+"', "+
                      " '"+medicalafterDTO.getMedicaltype()+"', "+
                      " '"+ZBZ_FLAG+"', "+
                      " '1', "+
                      " '1', "+
                      " '0002', "+
                      " '4', "+
                      " '0', "+
                      "  '0', "+
                      " '15100', "+
                      " '6000', "+
                      " '6000', "+
                      " '600', "+
                      " '1000', "+
                      " '100', "+
                      " '1000', "+
                      " '0', "+
                      " '0') as r from dual";
		ExecutSQL executSQL = new ExecutSQL();
		executSQL.setExecutsql(sql);
	 	List<HashMap> maps=executSQLDAO.queryAll(executSQL);
	 	
		} catch (SQLException e) {
		 
			e.printStackTrace();
		}
		return medicalafterDTO;
	}
	public MemberBaseinfoDAO getMemberBaseinfoDAO() {
		return memberBaseinfoDAO;
	}

	public void setMemberBaseinfoDAO(MemberBaseinfoDAO memberBaseinfoDAO) {
		this.memberBaseinfoDAO = memberBaseinfoDAO;
	}

	public void setToolsmenu(String toolsmenu) {
		this.toolsmenu = toolsmenu;
	}

	public String getToolsmenu() {
		return toolsmenu;
	}

	public void setJzBizDAO(JzBizDAO jzBizDAO) {
		this.jzBizDAO = jzBizDAO;
	}

	public JzBizDAO getJzBizDAO() {
		return jzBizDAO;
	}

	public ExecutSQLDAO getExecutSQLDAO() {
		return executSQLDAO;
	}

	public void setExecutSQLDAO(ExecutSQLDAO executSQLDAO) {
		this.executSQLDAO = executSQLDAO;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public TestSsnDAO getTestSsnDAO() {
		return testSsnDAO;
	}

	public void setTestSsnDAO(TestSsnDAO testSsnDAO) {
		this.testSsnDAO = testSsnDAO;
	}

	public JzMedicalafterDAO getJzMedicalafterDAO() {
		return jzMedicalafterDAO;
	}

	public void setJzMedicalafterDAO(JzMedicalafterDAO jzMedicalafterDAO) {
		this.jzMedicalafterDAO = jzMedicalafterDAO;
	}

	public JzActDAO getJzActDAO() {
		return jzActDAO;
	}

	public void setJzActDAO(JzActDAO jzActDAO) {
		this.jzActDAO = jzActDAO;
	}

	

}