package com.action;

import java.util.ArrayList;
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

import com.domain.ANews;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Share;
import com.domain.TNews;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IClubService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class HomePageAction extends ActionSupport {
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	//���Ҿ��ֲ���ʾ����ҳ
	@Action(value="findClubForHomePage",results={@Result(name="success",location="/jsp/homePage/clubHomePage.jsp")})
	public String findClubForHomePage() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Club> pageBean = adminService.findClubByAdmin(1,currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
	
	//��ʾ����������ҳ
	@Action(value="findShareForHomePage",results={@Result(name="success",location="/jsp/homePage/shareHomePage.jsp")})
	public String findShareForHomePage() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Share> pageBean = adminService.findAllShareByAdmin(currPage);
		List<Share> list = pageBean.getList();
		if(currPage == 1) {
			List<Share> topList = new ArrayList<Share>();
			for (Share share : list) {
				if(share.getShareTopFlag() != null) {
					topList.add(share);
				}
			}
			list.removeAll(topList); //�Ƴ�pageBean���ö�������
			pageBean.setList(list);
			ServletActionContext.getRequest().setAttribute("topList", topList);
		}
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		
		List<Share> rightList = adminService.findShareByViewNumber();
		ServletActionContext.getRequest().setAttribute("rightList", rightList);
		return SUCCESS;	
	}
	
	//��ʾ��Ա�ռǵ���ҳ
	@Action(value="findDiaryForHomePage",results={@Result(name="success",location="/jsp/homePage/diaryHomePage.jsp")})
	public String findDiaryForHomePage() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Diary> pageBean = adminService.findDiaryShowByAdmin(currPage);
		List<Diary> list = pageBean.getList();
		if(currPage == 1) {
			List<Diary> topList = new ArrayList<Diary>();
			for (Diary diary : list) {
				if(diary.getDiaryTopFlag() != null) {
					topList.add(diary);
				}
			}
			list.removeAll(topList); //�Ƴ�pageBean���ö�������
			pageBean.setList(list);
			ServletActionContext.getRequest().setAttribute("topList", topList);
		}
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		
		//����������Ƽ�����
		List<Diary> rightList = adminService.findDiaryByViewNumber();
		ServletActionContext.getRequest().setAttribute("rightList", rightList);
		return SUCCESS;	
	}
	
	//��ʾ����Ա���ŵ���ҳ
	@Action(value="findANewsForHomePage",results={@Result(name="success",location="/jsp/homePage/anewsHomePage.jsp")})
	public String findANewsForHomePage() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<ANews> pageBean = adminService.findAllANews(currPage);
		List<ANews> list = pageBean.getList();
		if(currPage == 1) {
			List<ANews> topList = new ArrayList<ANews>();
			for (ANews aNews : list) {
				if(aNews.getAnewsTopFlag() != null) {
					topList.add(aNews);
				}
			} 
			list.removeAll(topList); //�Ƴ�pageBean���ö�������
			pageBean.setList(list);
			ServletActionContext.getRequest().setAttribute("topList", topList);
		}
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		
		
		//����������Ƽ�����
		List<ANews> rightList = adminService.findANewsByViewNumber();
		ServletActionContext.getRequest().setAttribute("rightList", rightList);
		return SUCCESS;	
	}
	
	//��ʾ��ѵ�������ŵ���ҳ
	@Action(value="findCNewsForHomePage",results={@Result(name="success",location="/jsp/homePage/cnewsHomePage.jsp")})
	public String findCNewsForHomePage() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<CNews> pageBean = adminService.findAllCNewsByAdmin(currPage);
		List<CNews> list = pageBean.getList();
		if(currPage == 1) {
			List<CNews> topList = new ArrayList<CNews>();
			for (CNews cNews : list) {
				if(cNews.getNewsTopFlag() != null) { //��cNews.getNewsTopFlag() == 1����ָ���쳣
					topList.add(cNews);
				} 
			}
			list.removeAll(topList); //�Ƴ�pageBean���ö�������
			pageBean.setList(list);
			ServletActionContext.getRequest().setAttribute("topList", topList);
		} 
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		
		//����������Ƽ�����
		List<CNews> rightList = adminService.findCNewsByViewNumber();
		ServletActionContext.getRequest().setAttribute("rightList", rightList);
		return SUCCESS;	
	}
	
	//��ʾ�ο���Ѷ����ҳ
	@Action(value="findTNewsForHomePage",results={@Result(name="success",location="/jsp/homePage/tnewsHomePage.jsp")})
	public String findTNewsForHomePage() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<TNews> pageBean = adminService.findTNews(currPage);
		List<TNews> list = pageBean.getList();
		if(currPage == 1) {
			List<TNews> topList = new ArrayList<TNews>();
			for (TNews tNews : list) {
				if(tNews.getNewsTopFlag() != null) { //��cNews.getNewsTopFlag() == 1����ָ���쳣
					topList.add(tNews);
				} 
			}
			list.removeAll(topList); //�Ƴ�pageBean���ö�������
			pageBean.setList(list);
			ServletActionContext.getRequest().setAttribute("topList", topList);
		} 
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		
		//����������Ƽ�����
		List<TNews> rightList = adminService.findTNewsByViewNumber();
		ServletActionContext.getRequest().setAttribute("rightList", rightList);
		return SUCCESS;
	}
	
	
	
}


















