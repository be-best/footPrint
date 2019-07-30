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
import com.domain.Tourist;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ICoachService;
import com.service.ITouristService;
import com.utils.UUIDUtils;
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class TouristAction extends ActionSupport{
	@Autowired
	@Qualifier("touristService")
	private ITouristService touristService;
	
	//����Ҫ��ȫ�ƣ�����tourist,������ղ�������
	private File touristPhoto; //���ڽ����ϴ����ļ�
	private String touristPhotoFileName; //ͷ��
	private String touristPassword; //����
	private String touristEmail; //����
	private String touristAddress; //����
	private String touristGender; //�Ա�
	private String touristIntroduction;//���˼��
	private String touristName; //�ǳ�
	public ITouristService getTouristService() {
		return touristService;
	}
	
	


	public String getTouristIntroduction() {
		return touristIntroduction;
	}




	public void setTouristIntroduction(String touristIntroduction) {
		this.touristIntroduction = touristIntroduction;
	}




	public String getTouristName() {
		return touristName;
	}




	public void setTouristName(String touristName) {
		this.touristName = touristName;
	}




	public File getTouristPhoto() {
		return touristPhoto;
	}


	public void setTouristPhoto(File touristPhoto) {
		this.touristPhoto = touristPhoto;
	}


	public String getTouristPhotoFileName() {
		return touristPhotoFileName;
	}


	public void setTouristPhotoFileName(String touristPhotoFileName) {
		this.touristPhotoFileName = touristPhotoFileName;
	}


	public String getTouristPassword() {
		return touristPassword;
	}


	public void setTouristPassword(String touristPassword) {
		this.touristPassword = touristPassword;
	}


	public String getTouristEmail() {
		return touristEmail;
	}


	public void setTouristEmail(String touristEmail) {
		this.touristEmail = touristEmail;
	}


	public String getTouristAddress() {
		return touristAddress;
	}


	public void setTouristAddress(String touristAddress) {
		this.touristAddress = touristAddress;
	}


	public String getTouristGender() {
		return touristGender;
	}


	public void setTouristGender(String touristGender) {
		this.touristGender = touristGender;
	}


	public void setTouristService(ITouristService touristService) {
		this.touristService = touristService;
	}


	//��ת��ע��ҳ��
	@Action(value="touristRegistUI",results={@Result(name="success",location="/jsp/tourist/touristRegist.jsp")})
	public String touristRegistUI() {
		return SUCCESS;
	}
	
	//���ֲ�ע��
		@Action(value="touristRegist",results={@Result(name="success",location="/jsp/userLogin.jsp")})
		public String touristRegist() {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			Tourist tourist = new Tourist();
			if(touristPhotoFileName!=null) {
				touristPhotoFileName = UUIDUtils.getUUID()+touristPhotoFileName;
				//�����ϴ��ļ�
				File destFile = new File(ServletActionContext.getServletContext().getRealPath("/touristPhoto"),touristPhotoFileName);
				try {
					FileUtils.copyFile(touristPhoto, destFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String tPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/touristPhoto/"+touristPhotoFileName;
				tourist.settPhoto(tPhotosrc);
				
			}
			//System.out.println(touristEmail+touristPassword+touristGender);
			//System.out.println("------------------------------");
			tourist.settEmail(touristEmail);
			tourist.settPassword(touristPassword);
			tourist.settAddress(touristAddress);
			tourist.settGender(touristGender);
			touristService.touristRegist(tourist);
			ServletActionContext.getRequest().setAttribute("msg", "ע��ɹ���");
			return SUCCESS;
			
		}
	
		//�ο͵�¼
		@Action(value="touristLogin",results={@Result(name="success",location="/jsp/tourist/touristHomePage.jsp"),
				@Result(name="fail",location="/jsp/userLogin.jsp")})
		public String touristLogin() {
			String username = ServletActionContext.getRequest().getParameter("username");
			String password = ServletActionContext.getRequest().getParameter("password");
			Tourist tourist = new Tourist();
			tourist.settEmail(username);
			tourist.settPassword(password);
			Tourist existTourist = touristService.touristLogin(tourist);
			if(existTourist != null) {
				ServletActionContext.getRequest().getSession().setAttribute("existTourist", existTourist);
				return SUCCESS;
			} else {
				ServletActionContext.getRequest().setAttribute("msg", "�˺Ż��������");
				return "fail";
			}
			
		}
		
		//��ת����Ϣ�޸Ľ���
		@Action(value="updateInformationUI",results={@Result(name="success",location="/jsp/tourist/touristInformation.jsp")})
		public String updateInformationUI() {
			Tourist existTourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
			Tourist tourist = touristService.findTourist(existTourist.gettId());
			ServletActionContext.getRequest().setAttribute("updateTourist", tourist);
			return SUCCESS;
		}
		
		//�޸���Ϣ
		@Action(value="updateTouristInformation",results={@Result(name="success",location="/jsp/success.jsp")})
		public String updateTouristInformation() {
			Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
			if(touristPhotoFileName!=null) {
				touristPhotoFileName = UUIDUtils.getUUID()+touristPhotoFileName;
				//�����ϴ��ļ�
				File destFile = new File(ServletActionContext.getServletContext().getRealPath("/touristPhoto"),touristPhotoFileName);
				try {
					FileUtils.copyFile(touristPhoto, destFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String tPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/touristPhoto/"+touristPhotoFileName;
				tourist.settPhoto(tPhotosrc);
				
			}
			tourist.settEmail(touristEmail);
			tourist.settPassword(touristPassword);
			tourist.settAddress(touristAddress);
			tourist.settGender(touristGender);
			tourist.settIntroduction(touristIntroduction);
			tourist.settName(touristName);
			
			touristService.updateTourist(tourist);
			
			ServletActionContext.getRequest().setAttribute("msg", "��ϲ���޸���Ϣ�ɹ���");
			return SUCCESS;
		}
	
}













