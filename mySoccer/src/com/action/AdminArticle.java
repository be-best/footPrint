package com.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.domain.ANews;
import com.domain.Admin;
import com.domain.CNews;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Share;
import com.domain.TNews;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
import com.service.ITouristService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class AdminArticle extends ActionSupport {
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
	
	//չʾ���еľ��ֲ�news
	@Action(value="findClubNewsByAdmin",results={@Result(name="success",location="/jsp/admin/findCNews.jsp")})
	public String findClubNewsByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<CNews> pageBean = adminService.findAllCNewsByAdmin(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;	
	}
	
	//����Աɾ������
	@Action(value="deleteCNewsByAdmin",results={@Result(name="success",location="findClubNewsByAdmin?currPage=1",type="redirectAction")})
	public String deleteCNewsByAdmin() {
		String newsId = ServletActionContext.getRequest().getParameter("newsId");
		clubService.deletCNews(newsId);
		return SUCCESS;
	}
	
	//Ѱ�ҽ�������
	@Action(value="findCoachShareByAdmin",results={@Result(name="success",location="/jsp/admin/findShare.jsp")})
	public String findCoachShareByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Share> pageBean = adminService.findAllShareByAdmin(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;	
		
	}
	
	//����Աɾ����������
	@Action(value="deleteShareByAdmin",results={@Result(name="success",location="findCoachShareByAdmin?currPage=1",type="redirectAction")})
	public String deleteShareByAdmin() {
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		coachService.deleteShare(shareId);
		return SUCCESS;
	}
	
	//������Ա�ռ�
	@Action(value="findPlayerDiaryByAdmin",results={@Result(name="success",location="/jsp/admin/findDiary.jsp")})
	public String findPlayerDiaryByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Diary> pageBean = adminService.findAllDiaryByAdmin(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;	
	}
	
	//ɾ����Ա�ռ�
	@Action(value="deleteDiaryByAdmin",results={@Result(name="success",location="findPlayerDiaryByAdmin?currPage=1",type="redirectAction")})
	public String deleteDiaryByAdmin() {
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		playerService.deleteDiaryById(diaryId);
		return SUCCESS;
	}
	
	//����Ա��������
	@Action(value="createANewsByAdmin")
	public void createANewsByAdmin() {
		ANews anews = new ANews();
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("existAdmin");
		String anewsContents = ServletActionContext.getRequest().getParameter("anewsContents");
		String anewsTitle = ServletActionContext.getRequest().getParameter("anewsTitle");
		//System.out.println(anewsContents);
		//System.out.println(anewsTitle);
		//System.out.println("-------------------------------------------");
		anews.setAnewsContents(anewsContents);
		anews.setAnewsTitle(anewsTitle);
		anews.setAnewsDate(new Date());
		anews.setAnewsAdmin(admin);
		
		//��������ʾͷ����һ������
		StringBuilder strBui = new StringBuilder();
		String finalHeadContent;
		if(anewsContents.length()>150) {
			String headContent = anewsContents.replaceAll(" ", "").substring(0, 150)+" ";
			char[] strHead = headContent.toCharArray();
			for(int i=1;i < strHead.length-1;++i) {
				if(strHead[i-1] == '>') {
					while(strHead[i]!='<') {
						strBui.append(strHead[i]);
						++i;
						if(i>=strHead.length-1) {
							break;
						}
					  	
					}
				}
				
			}
			finalHeadContent = strBui.toString();
		} else {
			String headContent = anewsContents.replaceAll(" ", "")+"";
			char[] strHead = headContent.toCharArray();
			for(int i = 0;i<strHead.length;++i) {
				if(strHead[i] == '<') {
					while(strHead[i]!='>') {
						strHead[i] = ' ';
						strBui.append(strHead[i]);
						++i;
					}
					strHead[i] = ' ';
				}
				strBui.append(strHead[i]);
			}
			finalHeadContent = strBui.toString().replaceAll(" ", "");
		}
		anews.setAnewsHeadContent(finalHeadContent);
		adminService.createANews(anews);
		try {
			ServletActionContext.getResponse().getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//����һ������Ա�˻�������
	@Action(value="findANews",results={@Result(name="success",location="/jsp/admin/findANews.jsp")})
	public String findANews() {
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("existAdmin");
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<ANews> pageBean = adminService.findANews(currPage,admin);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;	
	}

	//�鿴����Ա��������
	@Action(value="findANewsArticle",results={@Result(name="success",location="/jsp/admin/findANewsArticle.jsp")})
	public String findANewsArticle() {
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		ANews anews = adminService.findANewsById(anewsId);
		ServletActionContext.getRequest().setAttribute("anews", anews);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
	}
	
	//��ת���޸Ĺ���Ա���Ž���
	@Action(value="jumpToUpdateANews",results={@Result(name="success",location="/jsp/admin/updateANews.jsp")})
	public String jumpToUpdateANews() {
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		ANews updateANews = adminService.findANewsById(anewsId);
		String anewsContents = updateANews.getAnewsContents();
		anewsContents = anewsContents.replaceAll("\"", "\"+\"");
		updateANews.setAnewsContents(anewsContents);
		ServletActionContext.getRequest().setAttribute("updateANews", updateANews);
		return SUCCESS;
	}
	
	//�޸Ĺ���Ա����
	@Action(value="updateANews")
	public void updateANews() {
		String anewsId = ServletActionContext.getRequest().getParameter("id");
		ANews anews = adminService.findANewsById(anewsId);
		String anewsContents = ServletActionContext.getRequest().getParameter("anewsContents");
		String anewsTitle = ServletActionContext.getRequest().getParameter("anewsTitle");
		anews.setAnewsContents(anewsContents);
		anews.setAnewsTitle(anewsTitle);
		
		//��������ʾͷ����һ������
		StringBuilder strBui = new StringBuilder();
		String finalHeadContent;
		if(anewsContents.length()>150) {
			String headContent = anewsContents.replaceAll(" ", "").substring(0, 150)+" ";
			char[] strHead = headContent.toCharArray();
			for(int i=1;i < strHead.length-1;++i) {
				if(strHead[i-1] == '>') {
					while(strHead[i]!='<') {
						strBui.append(strHead[i]);
						++i;
						if(i>=strHead.length-1) {
							break;
						}
					  	
					}
				}
				
			}
			finalHeadContent = strBui.toString();
		} else {
			String headContent = anewsContents.replaceAll(" ", "")+"";
			char[] strHead = headContent.toCharArray();
			for(int i = 0;i<strHead.length;++i) {
				if(strHead[i] == '<') {
					while(strHead[i]!='>') {
						strHead[i] = ' ';
						strBui.append(strHead[i]);
						++i;
					}
					strHead[i] = ' ';
				}
				strBui.append(strHead[i]);
			}
			finalHeadContent = strBui.toString().replaceAll(" ", "");
		}
		anews.setAnewsHeadContent(finalHeadContent);
		
		adminService.updateANews(anews);
		try {
			ServletActionContext.getResponse().getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//ɾ������Ա�Լ�������
	@Action(value="deleteOwnANews",results={@Result(name="success",location="findANews?currPage=1",type="redirectAction")})
	public String deleteOwnANews() {
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		adminService.deleteANewsById(anewsId);
		return SUCCESS;
	}
	
	//���й���Ա������
	@Action(value="findAllANews",results={@Result(name="success",location="/jsp/admin/findAllANews.jsp")})
	public String findAllANews() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<ANews> pageBean = adminService.findAllANews(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;	
	}
	
	//ɾ������Ա����(�Լ��ĺ������˵Ķ�����)
	@Action(value="deleteANews",results={@Result(name="success",location="findAllANews?currPage=1",type="redirectAction")})
	public String deleteANews() {
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		adminService.deleteANewsById(anewsId);
		return SUCCESS;
	}
	
	//�����ο�����
	@Action(value="findTNews",results={@Result(name="success",location="/jsp/admin/findTNews.jsp")})
	public String findTNews() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<TNews> pageBean = adminService.findTNews(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;	
	}
	
	//ɾ���ο�����
	@Action(value="deleteTNewsByAdmin",results={@Result(name="success",location="findTNews?currPage=1",type="redirectAction")})
	public String deleteTNewsByAdmin() {
		String newsId = ServletActionContext.getRequest().getParameter("newsId");
		touristService.deleteTNewsById(newsId);
		return SUCCESS;
	}
	
	//�����ö�
	@Action(value="setTop")
	public void setTop() {
		String cnewsId = ServletActionContext.getRequest().getParameter("cnewsId");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tnewsId = ServletActionContext.getRequest().getParameter("tnewsId");
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		if(cnewsId != null) {
			CNews cnews = clubService.findCNewsArtical(cnewsId);
			cnews.setNewsTopFlag(1); 
			clubService.updateCNewsArtical(cnews);
			
		} else if(shareId != null) {
			Share share = coachService.showShareArtical(shareId);
			share.setShareTopFlag(1);
			coachService.updateShareArtical(share);
			
		} else if(diaryId != null) {
			Diary diary = playerService.findDiaryById(diaryId);
			diary.setDiaryTopFlag(1);
			playerService.updateDiary(diary);
			
			
		} else if(tnewsId != null) {
			TNews tnews = touristService.findTNewsArticle(tnewsId);
			tnews.setNewsTopFlag(1);
			touristService.updateTNews(tnews);
			
		} else {
			ANews anews = adminService.findANewsById(anewsId);
			anews.setAnewsTopFlag(1);
			adminService.updateANews(anews);
			
		}
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ȡ���ö�
	@Action(value="downTop")
	public void downTop() {
		String cnewsId = ServletActionContext.getRequest().getParameter("cnewsId");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tnewsId = ServletActionContext.getRequest().getParameter("tnewsId");
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		if(cnewsId != null) {
			CNews cnews = clubService.findCNewsArtical(cnewsId);
			cnews.setNewsTopFlag(null); 
			clubService.updateCNewsArtical(cnews);
			
		} else if(shareId != null) {
			Share share = coachService.showShareArtical(shareId);
			share.setShareTopFlag(null);
			coachService.updateShareArtical(share);
			
		} else if(diaryId != null) {
			Diary diary = playerService.findDiaryById(diaryId);
			diary.setDiaryTopFlag(null);
			playerService.updateDiary(diary);
			
			
		} else if(tnewsId != null) {
			TNews tnews = touristService.findTNewsArticle(tnewsId);
			tnews.setNewsTopFlag(null);
			touristService.updateTNews(tnews);
			
		} else {
			ANews anews = adminService.findANewsById(anewsId);
			anews.setAnewsTopFlag(null);
			adminService.updateANews(anews);
			
		}
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}











