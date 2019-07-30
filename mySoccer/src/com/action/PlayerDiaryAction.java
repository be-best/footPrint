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

import com.domain.Coach;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Share;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.IPlayerService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerDiaryAction extends ActionSupport  {
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	
	//д���ռ�
	@Action(value="createNewDiary")
	public void createNewDiary() {
		String diaryTitle = ServletActionContext.getRequest().getParameter("diaryTitle");
		String diaryContents = ServletActionContext.getRequest().getParameter("diaryContents");
		//String diaryPermission1 = ServletActionContext.getRequest().getParameter("diaryPermission");
		System.out.println(diaryTitle+"---"+diaryContents+"---");
		Integer diaryPermission = Integer.parseInt(ServletActionContext.getRequest().getParameter("diaryPermission"));
		Diary diary = new Diary();
		diary.setDiaryPermission(diaryPermission);//Ȩ�����
		diary.setDiaryTitle(diaryTitle);
		diary.setDiaryContents(diaryContents);
		diary.setDiaryDate(new Date());
		diary.setDiaryPlayer((Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer"));
		//��������ʾͷ����һ������
		StringBuilder strBui = new StringBuilder();
		String finalHeadContent;
		if(diaryContents.length()>150) {
			String headContent = diaryContents.replaceAll(" ", "").substring(0, 150)+" ";
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
			String headContent = diaryContents.replaceAll(" ", "")+"";
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
		diary.setDiaryHeadContent(finalHeadContent);
		playerService.createDiary(diary);
		try {
			ServletActionContext.getResponse().getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��ʾ�ռ�����
	@Action(value="findDiaryArtical",results={@Result(name="success",location="/jsp/player/diaryArtical.jsp")})
	public String findDiaryArtical() {
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		Diary diary = playerService.findDiaryById(diaryId);
		ServletActionContext.getRequest().setAttribute("diary", diary);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
	}
	
	
	//���������ռ�
	@Action(value="findAllDiary",results={@Result(name="success",location="/jsp/player/allDiary.jsp")})
	public String findAllDiary() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Diary> pageBean = playerService.findAllDiary(currPage,(Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//�޸��ռǣ���ת
	@Action(value="jumpToUpdateDiary",results={@Result(name="success",location="/jsp/player/diaryUpdateEditor.jsp")})
	public String jumpToUpdateDiary() {
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		Diary updateDiary = playerService.findDiaryById(diaryId);
		String diaryContents = updateDiary.getDiaryContents();
		diaryContents = diaryContents.replaceAll("\"", "\"+\"");
		updateDiary.setDiaryContents(diaryContents);
		ServletActionContext.getRequest().setAttribute("updateDiary", updateDiary);
		return SUCCESS;
	}
	
	//�޸��ռǣ��޸�����
	@Action(value="updateDiary")
	public void updateDiary() {
		String diaryId = ServletActionContext.getRequest().getParameter("id");
		Diary diary = playerService.findDiaryById(diaryId);
		String diaryContents = ServletActionContext.getRequest().getParameter("diaryContents");
		String diaryTitle = ServletActionContext.getRequest().getParameter("diaryTitle");
		String diaryPermission = ServletActionContext.getRequest().getParameter("diaryPermission");
		diary.setDiaryPermission(Integer.parseInt(diaryPermission));
		diary.setDiaryContents(diaryContents);
		diary.setDiaryTitle(diaryTitle);
		//����ʾͷ����һ������
		StringBuilder strBui = new StringBuilder();
		String finalHeadContent;
		if(diaryContents.length()>150) {
			String headContent = diaryContents.replaceAll(" ", "").substring(0, 150)+" ";
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
			String headContent = diaryContents.replaceAll(" ", "")+"";
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
		diary.setDiaryHeadContent(finalHeadContent);
		playerService.updateDiary(diary);
		//playerService.createDiary(diary);
		try {
			ServletActionContext.getResponse().getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//ɾ���ռ�
	@Action(value="deleteDiaryById",results={@Result(name="allDiary",location="findAllDiary?currPage=1",type="redirectAction"),
			@Result(name="onlySelfDiary",location="findOnlySelfDiary",type="redirectAction"),
			@Result(name="onlyCoachAndTeamDiary",location="findOnlyCoachAndTeamDiary",type="redirectAction")})
	public String deleteDiaryById() {
		Integer code = Integer.parseInt(ServletActionContext.getRequest().getParameter("code"));
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		playerService.deleteDiaryById(diaryId);
		if(code == 0) {
			return "allDiary";
		}
		else if(code == 1) {
			return "onlySelfDiary";
		}
		else {
			return "onlyCoachAndTeamDiary";
		}

	}
	
	//���Լ��ɼ�
	@Action(value="findOnlySelfDiary",results={@Result(name="success",location="/jsp/player/allDiary.jsp")})
	public String findOnlySelfDiary() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Diary> pageBean = playerService.findPageDiary(1,currPage,(Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//�������ѿɼ�
	@Action(value="findOnlyCoachAndTeamDiary",results={@Result(name="success",location="/jsp/player/allDiary.jsp")})
	public String findOnlyCoachAndTeamDiary() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Diary> pageBean = playerService.findPageDiary(2,currPage,(Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//���ѵ����
	@Action(value="findTeammatesDiary",results={@Result(name="success",location="/jsp/player/allDiary.jsp")})
	public String findTeammatesDiary() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Diary> pageBean = playerService.findTeammatesDiary(currPage,(Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}

}

















