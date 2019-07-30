package com.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.opensymphony.xwork2.ModelDriven;
import com.service.ICoachService;
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CoachPlanAction extends ActionSupport implements ModelDriven<CoachPlan> {
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	private CoachPlan coachPlan = new CoachPlan();

	@Override
	public CoachPlan getModel() {
		// TODO Auto-generated method stub
		return coachPlan;
	}
	
	//�½�ѵ��
	@Action(value="writeCoachPlan",results={@Result(name="success",location="showUndoneCoachPlan?currPage=1",type="redirectAction")})
	public String writeCoachPlan() {
		coachPlan.setPlanCoach((Coach)ServletActionContext.getRequest().getSession().getAttribute("existCoach"));
		coachPlan.setPlanFlag(0);
		String planDate = ServletActionContext.getRequest().getParameter("planTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			coachPlan.setPlanTime(sdf.parse(planDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coachPlan.setPlanWriteDate(new Date());
		coachService.writeCoachPlan(coachPlan);
		return SUCCESS;
	}
	
	//��ʾ�����ѵ��
	@Action(value="showAllCoachPlan",results={@Result(name="success",location="/jsp/coach/showAllCoachPlan.jsp")})
	public String showAllCoachPlan() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<CoachPlan> pageBean = coachService.showAllCoachPlan(currPage,(Coach)ServletActionContext.getRequest().getSession().getAttribute("existCoach"));
/*		Integer code = Integer.parseInt( ServletActionContext.getRequest().getParameter("code"));
*/		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		/*if(code == 1) {
			//����ɾ��ѵ������
			String flag = "1";
			ServletActionContext.getRequest().setAttribute("deletePlan", flag);
		}*/
		return SUCCESS;
	}
	
	//��ʾδ���ѵ��
	@Action(value="showUndoneCoachPlan",results={@Result(name="success",location="/jsp/coach/showUndoneCoachPlan.jsp")})
	public String showUndoneCoachPlan() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<CoachPlan> pageBean = coachService.showUndoneCoachPlan(currPage,(Coach)ServletActionContext.getRequest().getSession().getAttribute("existCoach"));
/*		Integer code = Integer.parseInt( ServletActionContext.getRequest().getParameter("code"));
*/		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		/*if(code == 1) {
			//����ɾ��ѵ������
			String flag = "1";
			ServletActionContext.getRequest().setAttribute("deletePlan", flag);
		}*/
		return SUCCESS;
	}
	
	//��ʾѵ������
	/*@Action(value="showCoachPlanContent")
	public void showCoachPlanContent() {
		String planId = ServletActionContext.getRequest().getParameter("planId");
		CoachPlan coachPlan = coachService.findCoachPlanByPlanId(planId);
		ServletActionContext.getRequest().getSession().setAttribute("coachPlan", coachPlan);
	}*/
	
	//�鿴ѵ����������
		@Action("showCoachPlanContent")
		public void showCoachPlanContent() {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			String planId = ServletActionContext.getRequest().getParameter("planId");
			CoachPlan coachPlan = coachService.findCoachPlanByPlanId(planId);
			String planContent = coachPlan.getPlanContent();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String planTime = sdf.format(coachPlan.getPlanTime()); 
			//String planTime = coachPlan.getPlanTime();
			String planAddress = coachPlan.getPlanAddress();
			planContent = coachPlan.getPlanContent()+"(�����ڣ�"+coachPlan.getPlanWriteDate().toString().substring(0,19)+")";
			Map<String,String> map = new HashMap<String,String>();
			map.put("planTime", planTime);
			map.put("planAddress", planAddress);
			map.put("planContent",planContent );
			//coachPlan.setPlanContent(planContent);
			//��json���ݽ��й��ˣ����ҽ���ѭ������
			/*PropertyFilter filter = new PropertyFilter() {

				@Override
				public boolean apply(Object arg0, String filedName, Object arg2) {
					if("planId".equalsIgnoreCase(filedName)) {
						return false;
					}
					if("plan_coach".equalsIgnoreCase(filedName)) {
						return false;
					}
					if("planWriteDate".equalsIgnoreCase(filedName)) {
						return false;
					}
					if("planFlag".equalsIgnoreCase(filedName)) {
						return false;
					}
					return true;
				}
				
			};
			String json = JSONArray.toJSONString(coachPlan,filter,SerializerFeature.DisableCircularReferenceDetect);*/
			//System.out.println("--------------aaaaaaaaaaaaa--------------------");
			//String json = JSONArray.toJSONString(map);
			//System.out.println("json="+json);
			//System.out.println("----------------------------------------");
			//��json��Ӧ�������
			try {
				//String json1 = "[{\"planTime\":\""+planTime+"\",\"planAddress\":\""+planAddress+"\",\"planContent\":\""+planContent+"\"}]";
				//System.out.println(json1);
				//List<String> list = new ArrayList<String>();
				//list.add(planTime);
				//list.add(planAddress);
				//list.add(planContent);
				String json = JSONArray.toJSONString(map);
				System.out.println(json);
				json="["+json+"]";
				ServletActionContext.getResponse().getWriter().write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//ɾ��ѵ��
		@Action(value="deleteCoachPlanByCoach",results={@Result(name="nodonePage",location="showUndoneCoachPlan?currPage=1",type="redirectAction"),
				@Result(name="donePage",location="showAllCoachPlan?currPage=1",type="redirectAction")})
		public String deleteCoachPlanByCoach() {
			String planId = ServletActionContext.getRequest().getParameter("planId");
			Integer code = Integer.parseInt( ServletActionContext.getRequest().getParameter("code"));
			System.out.println(planId+"--------------------");
			coachService.deleteCoachPlan(planId);
			System.out.println(planId+"--------------------");

			if(code == 0) { //0Ϊδ���
				return "nodonePage";
			} else { //1Ϊ�����
				return "donePage";
			}
		}
		
		//�޸Ĳ���һ����ȡԭ��Ϣ����ת���޸Ľ���
		@Action(value="updateCoachPlanJump",results={@Result(name="success",location="/jsp/coach/updateCoachPlan.jsp")})
		public String updateCoachPlanJump() {
			String planId = ServletActionContext.getRequest().getParameter("planId");
			CoachPlan updateCoachPlan = coachService.findCoachPlanByPlanId(planId);
			ServletActionContext.getRequest().getSession().setAttribute("updateCoachPlan", updateCoachPlan);
			return SUCCESS;
		}
		
		//�޸Ĳ�����������޸���Ϣ
		@Action(value="updateCoachPlan",results={@Result(name="success",location="showUndoneCoachPlan?currPage=1",type="redirectAction")})
		public String updateCoachPlan() {
			String planId = ServletActionContext.getRequest().getParameter("planId");
			CoachPlan oldCoachPlan = coachService.findCoachPlanByPlanId(planId);
			String planTime = ServletActionContext.getRequest().getParameter("planTime");
			String planAddress = ServletActionContext.getRequest().getParameter("planAddress");
			String planContent = ServletActionContext.getRequest().getParameter("planContent");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				oldCoachPlan.setPlanTime(sdf.parse(planTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//oldCoachPlan.setPlanTime(planTime);
			oldCoachPlan.setPlanAddress(planAddress);
			oldCoachPlan.setPlanContent(planContent);
			CoachPlan updateCoachPlan = (CoachPlan) ServletActionContext.getRequest().getSession().getAttribute("updateCoachPlan");
			updateCoachPlan.setPlanTime(null);
			updateCoachPlan.setPlanContent(null);
			updateCoachPlan.setPlanAddress(null);
			ServletActionContext.getRequest().getSession().setAttribute("updateCoachPlan", updateCoachPlan);
			coachService.updateCoachPlan(oldCoachPlan);
			return SUCCESS;
		}
		
		//����ѵ���ܽ����
		@Action(value="writeSummary",results={@Result(name="success",location="/jsp/coach/summary.jsp")})
		public String writeSummary() {
			String planId = ServletActionContext.getRequest().getParameter("planId");
			if(planId == null) {
				planId = (String) ServletActionContext.getRequest().getSession().getAttribute("planId");
				ServletActionContext.getRequest().getSession().setAttribute("planId", "");
			}
			CoachPlan coachPlan = coachService.findCoachPlanByPlanId(planId);
			Coach planCoach = coachPlan.getPlanCoach();
			List<Player> coachPlayer = planCoach.getCoachPlayer();
			/*for (Player player : coachPlayer) {
				System.out.println(player.getpRealName());
			}*/
			List<Summary> list  = coachService.findSummaryByPlan(planId);
			ServletActionContext.getRequest().setAttribute("summaryList", list);
			ServletActionContext.getRequest().setAttribute("coachPlayer", coachPlayer);
			ServletActionContext.getRequest().setAttribute("coachPlan", coachPlan);
			return SUCCESS;
		}
		
		//дѵ�����ۺϲ���
		@Action(value="writeCoachPlanSum",results={@Result(name="success",location="writeSummary",type="redirectAction")})
		public String writeCoachPlanSum() {
			String planId = ServletActionContext.getRequest().getParameter("planId");
			String planCoachSum = ServletActionContext.getRequest().getParameter("planCoachSum");
			System.out.println(planId);
			System.out.println(planCoachSum);
			CoachPlan coachPlan = coachService.findCoachPlanByPlanId(planId);
			
			//�ж��Ƿ��Ѿ�����ܽ�
			List<Summary> summaryList = coachService.findSummaryByPlan(planId);
			int playerNumber = coachPlan.getPlanCoach().getCoachPlayer().size();
			if(summaryList.size() == playerNumber) {
				coachPlan.setPlanFlag(1);
			}
			coachPlan.setPlanCoachSum(planCoachSum);
			coachService.updateCoachPlan(coachPlan);
			//��planId�浽Session���ض����writeSummary�Ż�ȡ�õ�planId;
			ServletActionContext.getRequest().getSession().setAttribute("planId", planId);
			return SUCCESS;
		}
		
		
		//����׫дѵ������Ա��������
		@Action(value="writeCoachPlanSummary",results={@Result(name="success",location="writeSummary",type="redirectAction")})
		public String writeCoachPlanSummary(){
			//System.out.println("-------------0000000000000000-----------");
			String planId = ServletActionContext.getRequest().getParameter("planId");
			String pid = ServletActionContext.getRequest().getParameter("pid");
			//System.out.println("-------------1111111111111--------------------");
			List<Summary> list = coachService.findSummaryByPlan(planId);
			//System.out.println("--------222222222222222222-------------");
			if(list.size() != 0) {
				for (Summary checkSummary : list) {
					//System.out.println("------------33333333333--------");
					if(checkSummary.getSummaryPlayerId().equals(pid)) {
						//System.out.println("------------444444444444-----------");
						coachService.deleteSummary(checkSummary);
					}
				}
				
			}
			String summaryContent = ServletActionContext.getRequest().getParameter("summaryContent");
			String summarySuggest = ServletActionContext.getRequest().getParameter("summarySuggest");
			Double summaryScore = Double.parseDouble(ServletActionContext.getRequest().getParameter("summaryScore"));
			CoachPlan coachPlan = coachService.findCoachPlanByPlanId(planId);
			Player player = coachService.findPlayerByPid(pid);
			
			Summary summary = new Summary();
			summary.setSummaryContent(summaryContent);
			summary.setSummarySuggest(summarySuggest);
			summary.setSummaryScore(summaryScore);
			summary.setSummaryPlayerId(player.getPid());
			summary.setSummaryPlayerRealName(player.getpRealName());
			summary.setSummaryCoachPlan(coachPlan);
			coachService.writeCoachPlanSummary(summary);
			ServletActionContext.getRequest().getSession().setAttribute("planId", planId);
			
			//�жϽ����Ƿ��Ѿ�����ܽ�
			List<Summary> checkList = coachService.findSummaryByPlan(planId);
			CoachPlan newCoachPlan = coachService.findCoachPlanByPlanId(planId);
			String planCoachSum = newCoachPlan.getPlanCoachSum();
			int playerNumber = summary.getSummaryCoachPlan().getPlanCoach().getCoachPlayer().size();
			//��������Ա�������� = ��Ա�����ۺ����۲�Ϊ��
			if((checkList.size() == playerNumber)&&(planCoachSum != null)) {
				newCoachPlan.setPlanFlag(1);
				coachService.updateCoachPlan(newCoachPlan);
			}
			
			return SUCCESS;
			
		}
		
		
		//�޸��ۺ�����
		@Action(value="updateSummary",results={@Result(name="success",location="/jsp/coach/summary.jsp")})
		public String updateSummary() {
			String planId = ServletActionContext.getRequest().getParameter("planId");
			CoachPlan coachPlan = coachService.findCoachPlanByPlanId(planId);
			String planCoachSum = coachPlan.getPlanCoachSum();
			ServletActionContext.getRequest().setAttribute("updateplanCoachSum", planCoachSum);
			return SUCCESS;
		}
		
		//��ת���޸�ѵ������Ա����
		/*@Action(value="updateSummaryJump",results={@Result(name="success",location="/jsp/coach/summary.jsp")})
		public String updateSummaryJump() {
			String summaryId = ServletActionContext.getRequest().getParameter("summaryId");
			Summary summary = coachService.findSummaryBySummaryId(summaryId);
			ServletActionContext.getRequest().setAttribute("updateSummary", summary);
			return SUCCESS;
		}*/
		@Action(value="updateSummaryJump")
		public void updateSummaryJump() {
			String summaryId = ServletActionContext.getRequest().getParameter("summaryId");
			Summary summary = coachService.findSummaryBySummaryId(summaryId);
			ServletActionContext.getRequest().setAttribute("updateSummary", summary);
			//return SUCCESS;
		}
		
		//�޸�ѵ������Ա����
		@Action(value="updateCoachPlanSummary",results={@Result(name="success",location="writeSummary",type="redirectAction")})
		public String updateCoachPlanSummary() {
			String planId = ServletActionContext.getRequest().getParameter("planId");
			String pid = ServletActionContext.getRequest().getParameter("pid");
			//System.out.println(planId+"-----------------------------");
			//System.out.println(pid+"------------------------------");
			List<Summary> list = coachService.findSummaryByPlan(planId);
			Summary summary = new Summary();
			for (Summary summarys : list) {
				//System.out.println(summary.getSummaryPlayerId());
				if(summarys.getSummaryPlayerId() == pid) {
					summary = summarys;
					break;
				}
			}
			//System.out.println(summary.getSummaryContent()+"----------------------------");
			//Summary summary = coachService.findSummaryByPlanIdAndPId(planId,pid);
			//Summary summary = coachService.findSummaryBySummaryId(summaryId);
			String summaryContent = ServletActionContext.getRequest().getParameter("summaryContent");
			String summarySuggest = ServletActionContext.getRequest().getParameter("summarySuggest");
			Double summaryScore = Double.parseDouble(ServletActionContext.getRequest().getParameter("summaryScore"));

			summary.setSummaryContent(summaryContent);
			summary.setSummarySuggest(summarySuggest);
			summary.setSummaryScore(summaryScore);
			coachService.updateSummary(summary);
			
			ServletActionContext.getRequest().getSession().setAttribute("planId", summary.getSummaryCoachPlan().getPlanId());
			return SUCCESS;
		}
		
		//public String 
		
}










