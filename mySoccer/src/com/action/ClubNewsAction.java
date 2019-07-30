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

import com.domain.CNews;
import com.domain.Club;
import com.domain.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IClubService;


@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ClubNewsAction extends ActionSupport {
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	//����������
//	@Action(value="writeCNews",results={@Result(name="success",location="/index.jsp")})
	@Action(value="writeCNews")
	public void writeCNews()  {
		String newsContent = ServletActionContext.getRequest().getParameter("newsContent");
		String newsTitle = ServletActionContext.getRequest().getParameter("newsTitle");
		CNews cnews = new CNews();
		cnews.setNewsClub((Club) ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		cnews.setNewsContents(newsContent);
		cnews.setNewsTitle(newsTitle);
		cnews.setNewsDate(new Date());
		StringBuilder strBui = new StringBuilder();
		String finaHeadContent;
		if(newsContent.length()>150) {
			String headContent = newsContent.replaceAll(" ", "").substring(0, 150)+" ";
			//System.out.println(headContent+"-------------");
			char[] strHead = headContent.toCharArray();
			//System.out.println(strHead.length);
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
			finaHeadContent = strBui.toString();
		} else {
			String headContent = newsContent.replaceAll(" ", "")+"";
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
			finaHeadContent = strBui.toString().replaceAll(" ", "");
		}
		cnews.setNewsHeadContent(finaHeadContent);
		//System.out.println(strBui+"---------------");
		clubService.writeCNews(cnews);
		try {
			ServletActionContext.getResponse().getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return SUCCESS;
	}
	
	//��ʾ��������
	@Action(value="showAllCNews",results={@Result(name="success",location="/jsp/club/showAllCNews.jsp")})
	public String showAllCNews() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<CNews> pageBean = clubService.showAllCNews(currPage,(Club)ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//��ʾ��������
	@Action(value="showCNewsArtical",results={@Result(name="success",location="/jsp/club/clubNewsArtical.jsp")})
	public String showCNewsArtical() {
		//��������������ʹ�û��棬��ôÿ���һ�ε�ʱ��ͻ�ȡ�����ж�Ӧ�����£�������viewNumber,����ʱ���־û������ݿ⣬���Զ�ʱ����
		String newsId = ServletActionContext.getRequest().getParameter("newsId");
		CNews cnews = clubService.findCNewsArtical(newsId);
		ServletActionContext.getRequest().setAttribute("cnews", cnews);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
	}
	
	
	//�޸���������,��ת���༭������
	@Action(value="updateCNews",results={@Result(name="success",location="/jsp/club/clubNewsEditor.jsp")})
	public String updateCNews() {
		String newsId = ServletActionContext.getRequest().getParameter("newsId");
		CNews cnews = clubService.findCNewsArtical(newsId);
		//��ͼƬ����һ����ʽ�Ĵ����������ʱ����ʱ�༭�������
		String newsContent = cnews.getNewsContents();
		newsContent = newsContent.replaceAll("\"", "\"+\"");
		cnews.setNewsContents(newsContent);
		ServletActionContext.getRequest().setAttribute("cnews", cnews);
		ServletActionContext.getRequest().setAttribute("update", "1");
		return SUCCESS;
	}
	
	//�޸�����
	@Action(value="updateCNewsArtical")
	public void updateCNewsArtical() {
		//CNews cnews = (CNews) ServletActionContext.getRequest().getSession().getAttribute("cnews");
		CNews cnews = new CNews();
		String newsId = ServletActionContext.getRequest().getParameter("id");
		cnews.setNewsId(newsId);
		cnews = clubService.findCNewsArtical(newsId);
		String newsContents = ServletActionContext.getRequest().getParameter("newsContent");
		String newsTitle = ServletActionContext.getRequest().getParameter("newsTitle");
		cnews.setNewsContents(newsContents);
		cnews.setNewsTitle(newsTitle);
		StringBuilder strBui = new StringBuilder();
		String finaHeadContent;
		if(newsContents.length()>150) {
			String headContent = newsContents.replaceAll(" ", "").substring(0, 150)+" ";
			//System.out.println(headContent+"-------------");
			char[] strHead = headContent.toCharArray();
			//System.out.println(strHead.length);
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
			finaHeadContent = strBui.toString();
		} else {
			String headContent = newsContents.replaceAll(" ", "")+"";
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
			finaHeadContent = strBui.toString().replaceAll(" ", "");
		}
		cnews.setNewsHeadContent(finaHeadContent);
		clubService.updateCNewsArtical(cnews);
		try {
			ServletActionContext.getResponse().getWriter().write("2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//ɾ������
	@Action(value="deleteCNews",results={@Result(name="success",location="showAllCNews?currPage=1",type="redirectAction")})
	public String deleteCNews() {
		String newsId = ServletActionContext.getRequest().getParameter("newsId");
		clubService.deletCNews(newsId);
		return SUCCESS;
	}
}




















