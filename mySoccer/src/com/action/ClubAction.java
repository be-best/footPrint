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

import com.domain.Admin;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Player;
import com.domain.Tourist;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
import com.service.ITouristService;
import com.utils.UUIDUtils;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ClubAction extends ActionSupport  {
	
	@Autowired
	@Qualifier("touristService")
	private ITouristService touristService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	private File clubPhoto; //���ڽ����ϴ����ļ�
	private String clubPhotoFileName; //ͷ��
	private String clubName; //���ֲ�ȫ��
	private String clubPassword; //����
	private String clubEmail; //����
	private String clubPhone; //��ϵ�绰
	private String clubCity; //���ֲ�����
	private String clubIntroduction; //���

	
	public String getClubIntroduction() {
		return clubIntroduction;
	}

	public void setClubIntroduction(String clubIntroduction) {
		this.clubIntroduction = clubIntroduction;
	}

	public IClubService getClubService() {
		return clubService;
	}

	public void setClubService(IClubService clubService) {
		this.clubService = clubService;
	}

	public File getClubPhoto() {
		return clubPhoto;
	}

	public void setClubPhoto(File clubPhoto) {
		this.clubPhoto = clubPhoto;
	}

	public String getClubPhotoFileName() {
		return clubPhotoFileName;
	}

	public void setClubPhotoFileName(String clubPhotoFileName) {
		this.clubPhotoFileName = clubPhotoFileName;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubPassword() {
		return clubPassword;
	}

	public void setClubPassword(String clubPassword) {
		this.clubPassword = clubPassword;
	}

	public String getClubEmail() {
		return clubEmail;
	}

	public void setClubEmail(String clubEmail) {
		this.clubEmail = clubEmail;
	}

	public String getClubPhone() {
		return clubPhone;
	}

	public void setClubPhone(String clubPhone) {
		this.clubPhone = clubPhone;
	}

	public String getClubCity() {
		return clubCity;
	}

	public void setClubCity(String clubCity) {
		this.clubCity = clubCity;
	}

	
	//��ת��ע��ҳ��
	@Action(value="clubRegistUI",results={@Result(name="success",location="/jsp/clubRegist.jsp")})
	public String clubRegistUI() {
		return SUCCESS;
	}
	
	//���ֲ�ע��
	@Action(value="clubRegist",results={@Result(name="success",location="/jsp/userLogin.jsp")})
	public String clubRegist() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		Club club = new Club();
		if(clubPhotoFileName!=null) {
			clubPhotoFileName = UUIDUtils.getUUID()+clubPhotoFileName;
			//�����ϴ��ļ�
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/clubPhoto"),clubPhotoFileName);
			try {
				FileUtils.copyFile(clubPhoto, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String clubPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/clubPhoto/"+clubPhotoFileName;
			club.setClubPhoto(clubPhotosrc);
			
		}
		club.setClubName(clubName);
		club.setClubPassword(clubPassword);
		club.setClubPhone(clubPhone);
		club.setClubEmail(clubEmail);
		club.setClubCity(clubCity);
		club.setCid(UUIDUtils.getUUID());
		club.setClubCode(0);//1Ϊ���ͨ��
		club.setClubCoachFlag(0);//0Ϊû�н���
		clubService.clubRegist(club);
		return SUCCESS;
		
	}
	
	//���ֲ���¼
//	@Action(value="delCustomer",results={@Result(name="success",location="findAllCustomer",type="redirectAction")})
	@Action(value="clubLogin",results={@Result(name="success",location="/jsp/clubHomePage.jsp"),@Result(name="fail",location="/jsp/userLogin.jsp")})
	public String clubLogin() {
		Club club = new Club();
		//System.out.println(clubName+clubPassword);
		String clubName = ServletActionContext.getRequest().getParameter("username");
		String clubPassword = ServletActionContext.getRequest().getParameter("password");
		club.setClubName(clubName);
		club.setClubPassword(clubPassword);
		Club existClub = clubService.clubLogin(club);
		if(existClub == null) {
			ServletActionContext.getRequest().setAttribute("msg", "�û��������������");
			//System.out.println("��¼ʧ��---------------------------");
			return "fail";
		}
		else {
			ServletActionContext.getRequest().getSession().setAttribute("existClub", existClub);
			//System.out.println("��¼�ɹ�---------------------------");
			return SUCCESS;
		}
	}
	
	//���ֲ��˳���¼
	@Action(value="clubLogOut",results={@Result(name="success",location="/jsp/userLogin.jsp")})
	public String clubLogOut() {
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}
	
	
	//��ת���޸��û���Ϣ����
	@Action(value="JumpToUpdateClubInformation",results={@Result(name="success",location="/jsp/club/clubInformation.jsp")})
	public String JumpToUpdateClubInformation() {
		Club existClub = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		//��ȡ���ֲ�
		Club club = clubService.findClubNameByClubId(existClub.getCid());
		ServletActionContext.getRequest().setAttribute("updateClub",club);
		return SUCCESS;
	}

	//�޸��û���Ϣ
	@Action(value="updateClubInformation",results={@Result(name="success",location="/jsp/success.jsp")})
	public String updateClubInformation() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		Club club = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		if(clubPhotoFileName != null) { //ֻ�е����ڱ��л�ȡ����Ƭʱ�Ŵ�������Ϊ��ʱ�����
			clubPhotoFileName = UUIDUtils.getUUID()+clubPhotoFileName;
			//�����ϴ��ļ�
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/clubPhoto"),clubPhotoFileName);
			try {
				FileUtils.copyFile(clubPhoto, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String clubPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/clubPhoto/"+clubPhotoFileName;
			club.setClubPhoto(clubPhotosrc);	
	
		}
		//System.out.println(clubName+clubPassword+clubCity+"----------------------------");
		club.setClubName(clubName);
		club.setClubPassword(clubPassword);
		club.setClubPhone(clubPhone);
		club.setClubEmail(clubEmail);
		club.setClubCity(clubCity);
		club.setClubIntroduction(clubIntroduction);

		
		clubService.updateClub(club);
		ServletActionContext.getRequest().setAttribute("msg", "��ϲ���޸���Ϣ�ɹ���");
		return SUCCESS;
	}
	
	
	//�û��˳���¼
	@Action(value="userLogOut",results={@Result(name="success",location="/jsp/userLogin.jsp")})
	public String userLogOut() {
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}
	
	//�첽У���û���
	@Action(value="checkUserName")
	public void checkUserName() {
		String clubName = ServletActionContext.getRequest().getParameter("clubName");
		String coachNumber = ServletActionContext.getRequest().getParameter("coachNumber");
		String playerNumber = ServletActionContext.getRequest().getParameter("playerNumber");
		String touristEmail = ServletActionContext.getRequest().getParameter("touristEmail");
		String adminName = ServletActionContext.getRequest().getParameter("adminName");
		try {
			if(clubName != null) {
				Club club = clubService.findClubByName(clubName);
				if(club != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
			} else if(coachNumber != null) {
				Coach coach = coachService.findCoachByNumber(coachNumber);
				if(coach != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
			} else if(playerNumber != null) {
				Player player = playerService.findPlayerByNumber(playerNumber);
				if(player != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
			} else if(touristEmail != null) {
				Tourist tourist = touristService.findTouristByNumber(touristEmail);
				if(tourist != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
			} else if(adminName != null){
				Admin admin = adminService.findAdminByName(adminName);
				if(admin != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
				
			} else {
				ServletActionContext.getResponse().getWriter().print(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	
	
}




















