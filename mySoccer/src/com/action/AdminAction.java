package com.action;

import java.io.IOException;
import java.util.List;

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
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Tourist;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.IAdminService;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
import com.service.ITouristService;
import com.service.impl.PlayerServiceImpl;

/**
 * ����Ա
 * @author 45��ը
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	@Autowired
	@Qualifier("touristService")
	private ITouristService touristService;
	
	private Admin admin = new Admin();
	
	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}
	
	//��ӹ���Ա
	@Action(value="addNewAdmin",results={@Result(name="success",location="/jsp/success.jsp")})
	public String addANewdmin() {
		Admin admin = new Admin();
		String adminName = ServletActionContext.getRequest().getParameter("adminName");
		String adminPassword = ServletActionContext.getRequest().getParameter("adminPassword");
		admin.setAdminName(adminName);
		admin.setAdminPassword(adminPassword);
		String[] adminPermission = ServletActionContext.getRequest().getParameterValues("permission");
		
		for(int i = 0;i < adminPermission.length;++i) {
			String permission = adminPermission[i];
			System.out.println(permission);
			Integer flag = Integer.parseInt(permission);
			if(flag == 1) { //�û�����Ȩ��
				admin.setUserFlag(1); 
			} else if(flag == 2) { //���ֲ�����Ȩ��
				admin.setClubFlag(1);
			} else if(flag == 3) { //����Ա����Ȩ��
				admin.setAdminerFlag(1);
			} else if(flag == 4) { //���¹���Ȩ��
				admin.setArticleFlag(1);
			} else if(flag == 5) { //���Ź���Ȩ��
				admin.setNewsFlag(1);
			} else if(flag == 6) { //�ֲ�ͼ����Ȩ��
				admin.setPhotoFlag(1);
			}
		}
		adminService.addNewAdmin(admin);
		return SUCCESS;
	}
	
	//�޸Ĺ���Ա����ת���޸Ľ���
	@Action(value="jumpToUpdateAdmin",results={@Result(name="success",location="/jsp/admin/updateAdmin.jsp")})
	public String jumpToUpdateAdmin() {
		String  adminId = ServletActionContext.getRequest().getParameter("adminId");
		Admin admin = adminService.findAdminById(adminId);
		ServletActionContext.getRequest().setAttribute("updateAdmin", admin);
		return SUCCESS;
	}
	
	//�޸Ĺ���Ա����
	@Action(value="updateAdmin",results={@Result(name="success",location="findAllAdmin",type="redirectAction")})
	public String updateAdmin() {
		String adminId = ServletActionContext.getRequest().getParameter("adminId");
		Admin admin = adminService.findAdminById(adminId);
		String adminPassword = ServletActionContext.getRequest().getParameter("adminPassword");
		String adminRealName = ServletActionContext.getRequest().getParameter("adminRealName");
		String adminTelePhone = ServletActionContext.getRequest().getParameter("adminTelePhone");
		admin.setAdminPassword(adminPassword);
		admin.setAdminRealName(adminRealName);
		admin.setAdminTelePhone(adminTelePhone);
		String[] adminPermission = ServletActionContext.getRequest().getParameterValues("permission");
		//��ԭ����Ȩ����Ϊ��
		admin.setUserFlag(null);
		admin.setClubFlag(null);
		admin.setArticleFlag(null);
		admin.setNewsFlag(null);
		admin.setPhotoFlag(null);
		admin.setAdminerFlag(null);
		for(int i = 0;i < adminPermission.length;++i) {
			String permission = adminPermission[i];
			System.out.println(permission);
			Integer flag = Integer.parseInt(permission);
			if(flag == 1) { //�û�����Ȩ��
				admin.setUserFlag(1); 
			} else if(flag == 2) { //���ֲ�����Ȩ��
				admin.setClubFlag(1);
			} else if(flag == 3) { //����Ա����Ȩ��
				admin.setAdminerFlag(1);
			} else if(flag == 4) { //���¹���Ȩ��
				admin.setArticleFlag(1);
			} else if(flag == 5) { //���Ź���Ȩ��
				admin.setNewsFlag(1);
			} else if(flag == 6) { //�ֲ�ͼ����Ȩ��
				admin.setPhotoFlag(1);
			}
		}
		adminService.updateAdmin(admin);
		return SUCCESS;
	}
	
	//�������еĹ���Ա
	@Action(value="findAllAdmin",results={@Result(name="success",location="/jsp/admin/findAllAdmin.jsp")})
	public String findAllAdmin() {
		List<Admin> adminList = adminService.findAllAdmin();
		Admin existAdmin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("existAdmin");
		if(!(existAdmin.getAdminId().equals("38492756374285983724172381223486723864"))) {
			//ע��Ҫ��equals����,������"=="
			for (Admin admin : adminList) {
				//System.out.println(admin.getAdminName());
				if(admin.getAdminId().equals("38492756374285983724172381223486723864")) {
					//System.out.println(admin.getAdminId());
					adminList.remove(admin);
					break;//ɾ����������������ѭ��������������������
					
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("adminList", adminList);
		return SUCCESS;
		
	}
	
	//ɾ������Ա
	@Action(value="deleteAdmin",results={@Result(name="success",location="findAllAdmin",type="redirectAction")})
	public String deleteAdmin() {
		String adminId = ServletActionContext.getRequest().getParameter("adminId");
		adminService.deleteAdmin(adminId);
		return SUCCESS;
		
	}
	
	//����Ա��¼
	@Action(value="adminLogin",results={@Result(name="success",location="/jsp/admin/adminHomePage.jsp"),
			@Result(name="fail",location="/jsp/userLogin.jsp")})
	public String adminLogin() {
		String username = ServletActionContext.getRequest().getParameter("username");
		String password = ServletActionContext.getRequest().getParameter("password");
		Admin loginAdmin = new Admin();
		loginAdmin.setAdminName(username);
		loginAdmin.setAdminPassword(password);
		Admin existAdmin = adminService.adminLogin(loginAdmin);
		if(existAdmin != null) {
			ServletActionContext.getRequest().getSession().setAttribute("existAdmin", existAdmin);
			return SUCCESS;
		} else {
			ServletActionContext.getRequest().setAttribute("msg", "�û��������������");
			return "fail";
		}
	}
	
	//Ѱ����Ա
	@Action(value="findAllPlayerByAdmin",results={@Result(name="success",location="/jsp/admin/findAllPlayer.jsp")})
	public String findAllPlayerByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Player> pageBean = adminService.findAllPlayer(currPage);
		//����Ա���þ��ֲ�����
		List<Player> list = pageBean.getList();
		for (Player player : list) {
			String clubId = player.getpClubId();
			if(clubId != null) {
				Club club = clubService.findClubNameByClubId(clubId);
				player.setpClubName(club.getClubName());
				
			}
		}
		pageBean.setList(list);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//Ѱ�ҽ���
	@Action(value="findAllCoachByAdmin",results={@Result(name="success",location="/jsp/admin/findAllCoach.jsp")})
	public String findAllCoachByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Coach> pageBean = adminService.findAllCoach(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//ɾ����Ա
	@Action(value="deletePlayerByAdmin",results={@Result(name="success",location="findAllPlayerByAdmin?currPage=1",type="redirectAction")})
	public String deletePlayerByAdmin() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		adminService.deletePlayerByAdmin(pid);
		return SUCCESS;
	}
	
	//ɾ������
	@Action(value="deleteCoachByAdmin",results={@Result(name="success",location="findAllCoachByAdmin?currPage=1",type="redirectAction")})
	public String deleteCoachByAdmin() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		adminService.deleteCoachByAdmin(coachId);
		return SUCCESS;
	}
	
	
	//����δ��˾��ֲ�
	@Action(value="findUnCheckClubByAdmin",results={@Result(name="success",location="/jsp/admin/findUnCheckClub.jsp")})
	public String findUnCheckClubByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Club> pageBean = adminService.findClubByAdmin(0,currPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
	
	//��������˾��ֲ�
	@Action(value="findCheckClubByAdmin",results={@Result(name="success",location="/jsp/admin/findClub.jsp")})
	public String findCheckClubByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Club> pageBean = adminService.findClubByAdmin(1,currPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		ServletActionContext.getRequest().setAttribute("clubList", pageBean.getList());
		return SUCCESS;
	}
	
	//��˾��ֲ�
	@Action(value="checkClub",results={@Result(name="success",location="findUnCheckClubByAdmin?currPage=1",type="redirectAction")})
	public String checkClub() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		Club club = clubService.findClubNameByClubId(cid);
		club.setClubCode(1);
		clubService.updateClub(club);
		return SUCCESS;
	}
	
	//���δͨ��,ֱ��ɾ��
	@Action(value="refuseClub",results={@Result(name="success",location="findUnCheckClubByAdmin?currPage=1",type="redirectAction")})
	public String refuseClub() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		adminService.deleteClubByAdmin(cid);
		return SUCCESS;
	}
	
	//ɾ�����ֲ�
	@Action(value="deleteClub",results={@Result(name="success",location="findCheckClubByAdmin?currPage=1",type="redirectAction")})
	public String deleteClub() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		adminService.deleteClubByAdmin(cid);
		return SUCCESS;
	}
	
	//Ѱ���ο�
	@Action(value="findTouristByAdmin",results={@Result(name="success",location="/jsp/admin/findTourist.jsp")})
	public String findTouristByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Tourist> pageBean = adminService.findTouristByAdmin(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	//ɾ���ο�
	@Action(value="deleteTouristByAdmin",results={@Result(name="success",location="findTouristByAdmin?currPage=1",type="redirectAction")})
	public String deleteTouristByAdmin() {
		String tId = ServletActionContext.getRequest().getParameter("tId");
		adminService.deleteTourist(tId);
		return SUCCESS;
	}
	
	//���ֲ�����ҳ
	@Action(value="setMainPage")
	public void setMainPage() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		Club club = clubService.findClubNameByClubId(cid);
		club.setClubMainPage(1);
		clubService.updateClub(club);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ȡ�����ֲ���ҳ
	@Action(value="downMainPage")
	public void downMainPage() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		Club club = clubService.findClubNameByClubId(cid);
		club.setClubMainPage(null);
		clubService.updateClub(club);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��Ա����ҳ
	@Action(value="setPlayerMainPage")
	public void setPlayerMainPage() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player player = playerService.findPlayerById(pid);
		player.setPlayerMainPage(1);
		playerService.updatePlayer(player);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ȡ����Ա��ҳ
	@Action(value="downPlayerMainPage")
	public void downPlayerMainPage() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player player = playerService.findPlayerById(pid);
		player.setPlayerMainPage(null);
		playerService.updatePlayer(player);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//��������ҳ
	@Action(value="setCoachMainPage")
	public void setCoachMainPage() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		Coach coach = coachService.findCoachById(coachId);
		coach.setCoachMainPage(1);
		coachService.updateCoach(coach);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ȡ��������ҳ
	@Action(value="downCoachMainPage")
	public void downCoachMainPage() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		Coach coach = coachService.findCoachById(coachId);
		coach.setCoachMainPage(null);
		coachService.updateCoach(coach);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}




















