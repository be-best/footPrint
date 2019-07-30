package com.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.domain.Club;
import com.domain.Coach;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ICoachService;
import com.utils.UUIDUtils;

/**
 * 
 * @author 45��ը
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CoachAction extends ActionSupport {
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	private File coachPhoto; //���ڽ����ϴ����ļ�
	private String coachPhotoFileName; //ͷ��
	private String coachNumber; //�˺�
	private String coachName; //�ǳ�
	private String coachPassword;//����
	private String coachIntroduction; //���
	private String coachRealName; //��ʵ����
	private String coachBirthday; //����
	private String coachAddress; //����
	private String coachGender; //�Ա�
	private String coachTelPhone;//�����ĵ绰
	public ICoachService getCoachService() {
		return coachService;
	}
	public void setCoachService(ICoachService coachService) {
		this.coachService = coachService;
	}
	public File getCoachPhoto() {
		return coachPhoto;
	}
	public void setCoachPhoto(File coachPhoto) {
		this.coachPhoto = coachPhoto;
	}
	public String getCoachPhotoFileName() {
		return coachPhotoFileName;
	}
	public void setCoachPhotoFileName(String coachPhotoFileName) {
		this.coachPhotoFileName = coachPhotoFileName;
	}
	public String getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getCoachPassword() {
		return coachPassword;
	}
	public void setCoachPassword(String coachPassword) {
		this.coachPassword = coachPassword;
	}
	public String getCoachIntroduction() {
		return coachIntroduction;
	}
	public void setCoachIntroduction(String coachIntroduction) {
		this.coachIntroduction = coachIntroduction;
	}
	public String getCoachRealName() {
		return coachRealName;
	}
	public void setCoachRealName(String coachRealName) {
		this.coachRealName = coachRealName;
	}
	public String getCoachBirthday() {
		return coachBirthday;
	}
	public void setCoachBirthday(String coachBirthday) {
		this.coachBirthday = coachBirthday;
	}
	public String getCoachAddress() {
		return coachAddress;
	}
	public void setCoachAddress(String coachAddress) {
		this.coachAddress = coachAddress;
	}
	public String getCoachGender() {
		return coachGender;
	}
	public void setCoachGender(String coachGender) {
		this.coachGender = coachGender;
	}
	public String getCoachTelPhone() {
		return coachTelPhone;
	}
	public void setCoachTelPhone(String coachTelPhone) {
		this.coachTelPhone = coachTelPhone;
	}
	
	//��ת���޸Ľ�����Ϣ����
	@Action(value="JumpToupdateCoachInformation",results={@Result(name="success",location="/jsp/coach/coachInformation.jsp")})
	public String JumpToupdateCoachInformation() {
		Coach existCoach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		Coach coach = coachService.findCoachById(existCoach.getCoachId());
		ServletActionContext.getRequest().setAttribute("updateCoach", coach);
		return SUCCESS;
	}
	
	@Action(value="updateCoachInformation",results={@Result(name="success",location="/jsp/success.jsp")})
	public String updateCoachInformation() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		if(coachPhotoFileName != null) { //ֻ�е����ڱ��л�ȡ����Ƭʱ�Ŵ�������Ϊ��ʱ�����
			coachPhotoFileName = UUIDUtils.getUUID()+coachPhotoFileName;
			//�����ϴ��ļ�
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/coachPhoto"),coachPhotoFileName);
			try {
				FileUtils.copyFile(coachPhoto, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String clubPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/coachPhoto/"+coachPhotoFileName;
			coach.setCoachPhoto(clubPhotosrc);	
	
		}
		
		coach.setCoachName(coachName);
		coach.setCoachRealName(coachRealName);
		coach.setCoachPassword(coachPassword);
		coach.setCoachTelPhone(coachTelPhone);
		coach.setCoachAddress(coachAddress);
		coach.setCoachBirthday(coachBirthday);
		coach.setCoachIntroduction(coachIntroduction);
		coach.setCoachGender(coachGender);
		
		coachService.updateCoach(coach);
		
		
		ServletActionContext.getRequest().setAttribute("msg", "��ϲ���޸���Ϣ�ɹ���");
		return SUCCESS;
	}
	
	
}










