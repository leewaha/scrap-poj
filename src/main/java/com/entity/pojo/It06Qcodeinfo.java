package com.entity.pojo;

import java.io.Serializable;

// default package

import java.util.Date;


/**
 * 扫码授权 用户企业信息
 * 
 * @author leewaha 2019-1-8
 * 
 */

public class It06Qcodeinfo implements Serializable{
	private static final long serialVersionUID = 1L;
	// Fields
	private String it06Id;
	private String qrid;
	private String qrinfo;
	private String qrimage;
	private String operflag;
	private String licenceentity;
	private String licencepubkey;
	private String uniscid;
	private String entname;
	private String enttypeCn;
	private String regorgCn;
	private String apprdate;
	private String legalname;
	private String regcap;
	private String dom;
	private String estdate;
	private String opfrom;
	private String opto;
	private String opscope;
	private String licencesn;
	private String operator;
	private String authinfo;
	private String image;
	private String authName;
	private String authIdcard;
	private String authPhonenum;
	private String authCompanyname;
	private String authUniscid;
	private String authContent;
	private String authTerm;
	private String useFlag;
	private String remark;
	private Date createTime;
	private Date updateTime;
	private String createUser;
	private String signdata;
	private String plainvalue;
	private String tragersn;
	private String regno;
	private String oper;

	// Constructors

	/** default constructor */
	public It06Qcodeinfo() {
	}

	/** minimal constructor */
	public It06Qcodeinfo(String it06Id, String useFlag, Date createTime,
			Date updateTime, String createUser) {
		this.it06Id = it06Id;
		this.useFlag = useFlag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUser = createUser;
	}

	// Property accessors

	public String getIt06Id() {
		return this.it06Id;
	}

	public void setIt06Id(String it06Id) {
		this.it06Id = it06Id;
	}

	public String getQrid() {
		return this.qrid;
	}

	public void setQrid(String qrid) {
		this.qrid = qrid;
	}

	public String getQrimage() {
		return this.qrimage;
	}

	public void setQrimage(String qrimage) {
		this.qrimage = qrimage;
	}

	public String getOperflag() {
		return operflag;
	}

	public void setOperflag(String operflag) {
		this.operflag = operflag;
	}

	public String getLicenceentity() {
		return this.licenceentity;
	}

	public void setLicenceentity(String licenceentity) {
		this.licenceentity = licenceentity;
	}

	public String getLicencepubkey() {
		return this.licencepubkey;
	}

	public void setLicencepubkey(String licencepubkey) {
		this.licencepubkey = licencepubkey;
	}

	public String getUniscid() {
		return this.uniscid;
	}

	public void setUniscid(String uniscid) {
		this.uniscid = uniscid;
	}

	public String getEntname() {
		return this.entname;
	}

	public void setEntname(String entname) {
		this.entname = entname;
	}

	public String getEnttypeCn() {
		return this.enttypeCn;
	}

	public void setEnttypeCn(String enttypeCn) {
		this.enttypeCn = enttypeCn;
	}

	public String getRegorgCn() {
		return this.regorgCn;
	}

	public void setRegorgCn(String regorgCn) {
		this.regorgCn = regorgCn;
	}

	public String getApprdate() {
		return this.apprdate;
	}

	public void setApprdate(String apprdate) {
		this.apprdate = apprdate;
	}

	public String getLegalname() {
		return this.legalname;
	}

	public void setLegalname(String legalname) {
		this.legalname = legalname;
	}

	public String getRegcap() {
		return this.regcap;
	}

	public void setRegcap(String regcap) {
		this.regcap = regcap;
	}

	public String getDom() {
		return this.dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getEstdate() {
		return this.estdate;
	}

	public void setEstdate(String estdate) {
		this.estdate = estdate;
	}

	public String getOpfrom() {
		return this.opfrom;
	}

	public void setOpfrom(String opfrom) {
		this.opfrom = opfrom;
	}

	public String getOpto() {
		return this.opto;
	}

	public void setOpto(String opto) {
		this.opto = opto;
	}

	public String getOpscope() {
		return this.opscope;
	}

	public void setOpscope(String opscope) {
		this.opscope = opscope;
	}

	public String getLicencesn() {
		return this.licencesn;
	}

	public void setLicencesn(String licencesn) {
		this.licencesn = licencesn;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAuthinfo() {
		return this.authinfo;
	}

	public void setAuthinfo(String authinfo) {
		this.authinfo = authinfo;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthName() {
		return this.authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthIdcard() {
		return this.authIdcard;
	}

	public void setAuthIdcard(String authIdcard) {
		this.authIdcard = authIdcard;
	}

	public String getAuthPhonenum() {
		return this.authPhonenum;
	}

	public void setAuthPhonenum(String authPhonenum) {
		this.authPhonenum = authPhonenum;
	}

	public String getAuthCompanyname() {
		return this.authCompanyname;
	}

	public void setAuthCompanyname(String authCompanyname) {
		this.authCompanyname = authCompanyname;
	}

	public String getAuthUniscid() {
		return this.authUniscid;
	}

	public void setAuthUniscid(String authUniscid) {
		this.authUniscid = authUniscid;
	}

	public String getAuthContent() {
		return this.authContent;
	}

	public void setAuthContent(String authContent) {
		this.authContent = authContent;
	}

	public String getAuthTerm() {
		return this.authTerm;
	}

	public void setAuthTerm(String authTerm) {
		this.authTerm = authTerm;
	}

	public String getUseFlag() {
		return this.useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getQrinfo() {
		return qrinfo;
	}

	public void setQrinfo(String qrinfo) {
		this.qrinfo = qrinfo;
	}

	public String getSigndata() {
		return signdata;
	}

	public void setSigndata(String signdata) {
		this.signdata = signdata;
	}

	public String getPlainvalue() {
		return plainvalue;
	}

	public void setPlainvalue(String plainvalue) {
		this.plainvalue = plainvalue;
	}

	public String getTragersn() {
		return tragersn;
	}

	public void setTragersn(String tragersn) {
		this.tragersn = tragersn;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getOper() {
		return oper;
	}

	@Override
	public String toString() {
		return "It06Qcodeinfo [it06Id=" + it06Id + ", qrid=" + qrid + ", qrinfo=" + qrinfo + ", qrimage=" + qrimage
				+ ", operflag=" + operflag + ", licenceentity=" + licenceentity + ", licencepubkey=" + licencepubkey
				+ ", uniscid=" + uniscid + ", entname=" + entname + ", enttypeCn=" + enttypeCn + ", regorgCn="
				+ regorgCn + ", apprdate=" + apprdate + ", legalname=" + legalname + ", regcap=" + regcap + ", dom="
				+ dom + ", estdate=" + estdate + ", opfrom=" + opfrom + ", opto=" + opto + ", opscope=" + opscope
				+ ", licencesn=" + licencesn + ", operator=" + operator + ", authinfo=" + authinfo + ", image=" + image
				+ ", authName=" + authName + ", authIdcard=" + authIdcard + ", authPhonenum=" + authPhonenum
				+ ", authCompanyname=" + authCompanyname + ", authUniscid=" + authUniscid + ", authContent="
				+ authContent + ", authTerm=" + authTerm + ", useFlag=" + useFlag + ", remark=" + remark
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", createUser=" + createUser
				+ ", signdata=" + signdata + ", plainvalue=" + plainvalue + ", tragersn=" + tragersn + ", regno="
				+ regno + ", oper=" + oper + "]";
	}

	
	
}