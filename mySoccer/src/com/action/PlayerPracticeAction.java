package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

import com.alibaba.fastjson.JSONArray;
import com.domain.Coach;
import com.domain.CoachPlan;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Summary;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ICoachService;
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerPracticeAction extends ActionSupport {
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;//�õ���coachService
	
	//��ȡδ���ѵ��
	@Action(value="getUnDoneCoachPlan",results={@Result(name="success",location="/jsp/player/playerUnDonePractice.jsp")})
	public String getUnDoneCoachPlan() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		Coach coach = player.getpCoach();
		if(coach != null) {
			PageBean<CoachPlan> pageBean = coachService.showUndoneCoachPlan(currPage, coach);
			ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
			
		}
		return SUCCESS;
		
	}
	
	//��ȡ�����ѵ��
	@Action(value="getDoneCoachPlan",results={@Result(name="success",location="/jsp/player/playerDonePractice.jsp")})
	public String getDoneCoachPlan() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		Coach coach = player.getpCoach();
		if(coach != null) {
			PageBean<CoachPlan> pageBean = coachService.showAllCoachPlan(currPage, coach);
			ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
			
		}
		if(coach == null) { //���û�н���������summary��ȡ��ʷѵ��
			List<Summary> list = coachService.findSummaryByPlayer(player);
			if(list.size() != 0) {
				PageBean<CoachPlan> pageBean = new PageBean<CoachPlan>();
				List<CoachPlan> planList = new ArrayList<CoachPlan>();
				for (Summary summary : list) {
					CoachPlan coachplan = summary.getSummaryCoachPlan();
					planList.add(coachplan);
					
				}
				pageBean.setList(planList);
				ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
				
			}
		}
		return SUCCESS;
	}
	
	//��ת���鿴�ܽ��������
	//����ѵ���ܽ����
	@Action(value="findPlayerSummary",results={@Result(name="success",location="/jsp/player/playerSummary.jsp")})
	public String findPlayerSummary() {
		String planId = ServletActionContext.getRequest().getParameter("planId");
		if(planId == null) {
			planId = (String) ServletActionContext.getRequest().getSession().getAttribute("planId");
			ServletActionContext.getRequest().getSession().setAttribute("planId", "");
		}
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		String pid = player.getPid();
		String summaryId = coachService.findSummaryByPlanIdAndPId(planId, pid);
		Summary playerSummary = coachService.findSummaryBySummaryId(summaryId);
		//System.out.println(playerSummary.getSummaryScore()+playerSummary.getSummaryContent()+playerSummary.getSummarySuggest());
		//System.out.println("-----------------------------------------");
		CoachPlan coachPlan = coachService.findCoachPlanByPlanId(planId);
		
		ServletActionContext.getRequest().setAttribute("planId", planId);
		ServletActionContext.getRequest().setAttribute("playerSummary", playerSummary);
		ServletActionContext.getRequest().setAttribute("coachPlan", coachPlan);
		return SUCCESS;
	} 
	
	//��ȡ��ȥ�ߴ�ѵ��������
	@Action(value="getPracticeScore")
	public void getPracticeScore() {
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		String planId = ServletActionContext.getRequest().getParameter("planId");
		//String planId = "297e1ddb6a567814016a5678edf60000";
		CoachPlan plan = coachService.findCoachPlanByPlanId(planId);
		List<CoachPlan> coachPlans = coachService.getPracticeScore(plan);
		System.out.println(coachPlans.size()+"------------------");
		List<Double> list = new ArrayList<Double>();
		for (CoachPlan coachPlan : coachPlans) {
			System.out.println(coachPlan.getPlanTime());
		}
		int i = 1;
		for (CoachPlan coachPlan : coachPlans) {
			String summaryId = coachService.findSummaryByPlanIdAndPId(coachPlan.getPlanId(), player.getPid());
			Summary summary = coachService.findSummaryBySummaryId(summaryId);
			Double summaryScore = summary.getSummaryScore();
			System.out.println(summaryScore+"--------"+i);
			++i;
			list.add(summaryScore);
		}
		/*for (Double double1 : list) {
			System.out.println(double1);
		}*/
		Collections.reverse(list); //��תһ�£�����Խ��ǰ��Խ��ǰ��
		String json = JSONArray.toJSONString(list);
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//��ʾѵ���������ҳ
	@Action(value="getPracticeForInfo")
	public void getPracticeForInfo() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		if(pid.length() == 0) {
			Player infoPlayer = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
			pid = infoPlayer.getPid();
		}
		List<CoachPlan> planList = coachService.getPracticeScoreForInfo(pid);
		List<Summary> summaryList = coachService.getSummaryForInfo(pid);
		List<Double> list = new ArrayList<Double>();

		for (Summary summary : summaryList) {
			list.add(summary.getSummaryScore());
		}
		Collections.reverse(list); //��תһ�£�����Խ��ǰ��Խ��ǰ��
		String json = JSONArray.toJSONString(list);
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	
	//д�����ܽ�
	@Action(value="writePlayerSumByPlayer",results={@Result(name="success",location="findPlayerSummary",type="redirectAction")})
	public String writePlayerSumByPlayer() {
		String summaryId = ServletActionContext.getRequest().getParameter("summaryId");
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		System.out.println(summaryId+"-------------------------");
		String summaryPlayerSum = ServletActionContext.getRequest().getParameter("summaryPlayerSum");
		Summary summary = coachService.findSummaryBySummaryId(summaryId);
		summary.setSummaryPlayerSum(summaryPlayerSum);
		coachService.updateSummary(summary);
		ServletActionContext.getRequest().getSession().setAttribute("planId", summary.getSummaryCoachPlan().getPlanId());
		return SUCCESS;
	}
	
	@Action(value="getPracticeScore1")
	public void getPracticeScore1() {
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		//String planId = ServletActionContext.getRequest().getParameter("planId");
		String planId = ServletActionContext.getRequest().getParameter("planId");
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(100);
		list.add(90);
		list.add(95);
		list.add(99);
		list.add(98);
		list.add(92);
		list.add(96);
		
		String json = JSONArray.toJSONString(list);
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}





















