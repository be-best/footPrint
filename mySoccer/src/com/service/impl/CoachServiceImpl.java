package com.service.impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ICoachDao;
import com.domain.Coach;
import com.domain.CoachPlan;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Share;
import com.domain.SuggestForCoach;
import com.domain.Summary;
import com.service.ICoachService;

@Service("coachService")
@Transactional
public class CoachServiceImpl implements ICoachService {

	@Autowired
	@Qualifier("coachDao")
	private ICoachDao coachDao;
	
	//���������Ա
	@Override
	public void addPlayerByCoach(Player player) {
		// TODO Auto-generated method stub
		coachDao.addPlayerByCoach(player);
	}
                      
	//��ʾ���е���Ա
	@Override
	public PageBean<Player> showAllPlayer(Integer currPage, Coach coach ) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 6;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = coachDao.findTotalPlayerByCoach(coach);
		//�������С�ڵ�ҳ�����򲻽��з�ҳ
		if(totalCount < pageSize) {			
			ServletActionContext.getRequest().getSession().setAttribute("totalPlayer",totalCount.toString());
		}
		else {			
			ServletActionContext.getRequest().getSession().setAttribute("totalPlayer","");
		}
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = coachDao.showAllPlayer(coach,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	 //���������Ա
	@Override
	public void deletePlayerByCoach(Player player) {
		// TODO Auto-generated method stub
		coachDao.deletePlayerByCoach(player);
	}
	
	//�����·���
	@Override
	public void wiriteShare(Share share) {
		// TODO Auto-generated method stub
		coachDao.wiriteShare(share);
	}

	//��ʾ��������
	@Override
	public PageBean<Share> showAllShare(Integer currPage, Coach coach) {
		// TODO Auto-generated method stub
		PageBean<Share> pageBean = new PageBean<Share>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = coachDao.findTotalShareByCoach(coach);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Share> list = coachDao.showAllShare(coach,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//��ʾ��������
	@Override
	public Share showShareArtical(String shareId) {
		// TODO Auto-generated method stub
		return coachDao.showShareArtical(shareId);
	}

	//�޸ķ���
	@Override
	public void updateShareArtical(Share share) {
		// TODO Auto-generated method stub
		coachDao.updateShareArtical(share);
	}

	//ɾ������
	@Override
	public void deleteShare(String shareId) {
		// TODO Auto-generated method stub
		coachDao.deleteShare(shareId);
	}

	//��Ա��¼
	@Override
	public Player playerLogin(Player player) {
		// TODO Auto-generated method stub
		return coachDao.playerLogin(player);
	}

	//�½�ѵ��
	@Override
	public void writeCoachPlan(CoachPlan coachPlan) {
		// TODO Auto-generated method stub
		coachDao.writeCoachPlan(coachPlan);
	}

	//��ʾ�������ѵ��
	@Override
	public PageBean<CoachPlan> showAllCoachPlan(Integer currPage,
			Coach coach) {
		// TODO Auto-generated method stub
		PageBean<CoachPlan> pageBean = new PageBean<CoachPlan>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 6;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = coachDao.findTotalshowAllCoachPlan(coach);
		//�������С�ڵ�ҳ�����򲻽��з�ҳ
		if(totalCount < pageSize) {			
			ServletActionContext.getRequest().getSession().setAttribute("totalCoachPlan",totalCount.toString());
		}
		else {			
			ServletActionContext.getRequest().getSession().setAttribute("totalCoachPlan","");
		}
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<CoachPlan> list = coachDao.showAllCoachPlan(coach,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//����ĳ��ѵ��
	@Override
	public CoachPlan findCoachPlanByPlanId(String planId) {
		// TODO Auto-generated method stub
		return coachDao.findCoachPlanByPlanId(planId);
	}

	//ɾ��ѵ��
	@Override
	public void deleteCoachPlan(String planId) {
		// TODO Auto-generated method stub
		coachDao.deleteCoachPlan(planId);
	}

	//��ʾδ���ѵ��
	@Override
	public PageBean<CoachPlan> showUndoneCoachPlan(Integer currPage,
			Coach coach) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
			PageBean<CoachPlan> pageBean = new PageBean<CoachPlan>();
			// ���ò�����
			// ���õ�ǰҳ����
			pageBean.setCurrPage(currPage);
			// ����ÿҳ��ʾ�ļ�¼��:
			Integer pageSize = 6;
			pageBean.setPageSize(pageSize);
			// �����ܼ�¼��:
			Integer totalCount = coachDao.findTotalUndoneCoachPlan(coach);
			//�������С�ڵ�ҳ�����򲻽��з�ҳ
			if(totalCount < pageSize) {			
				ServletActionContext.getRequest().getSession().setAttribute("totalCoachPlan",totalCount.toString());
			}
			else {			
				ServletActionContext.getRequest().getSession().setAttribute("totalCoachPlan","");
			}
			pageBean.setTotalCount(totalCount);
			// ������ҳ��:
			double tc = totalCount;
			Double num = Math.ceil(tc / pageSize);
			pageBean.setTotalPage(num.intValue());
			// ����ÿҳ��ʾ�����ݵļ���:
			int begin = (currPage - 1) * pageSize;
			List<CoachPlan> list = coachDao.showUndoneCoachPlan(coach,begin,pageSize);
			pageBean.setList(list);
			return pageBean;
	}

	//�޸�ѵ���ƻ�
	@Override
	public void updateCoachPlan(CoachPlan oldCoachPlan) {
		// TODO Auto-generated method stub
		coachDao.updateCoachPlan(oldCoachPlan);
	}

	//����������Ա
	@Override
	public List<Player> findAllPlayerByCoach(Coach planCoach) {
		// TODO Auto-generated method stub
		return coachDao.findAllPlayerByCoach(planCoach);
	}

	//����ID������Ա 
	@Override
	public Player findPlayerByPid(String pId) {
		// TODO Auto-generated method stub
		return coachDao.findPlayerByPid(pId);
	}

	//����summary
	@Override
	public void writeCoachPlanSummary(Summary summary) {
		// TODO Auto-generated method stub
		coachDao.writeCoachPlanSummary(summary);
	}

	//Ѱ��plan�µ�summary
	@Override
	public List<Summary> findSummaryByPlan(String planId) {
		// TODO Auto-generated method stub
		return coachDao.findSummaryByPlan(planId);
	}

	//Ѱ��summary
	@Override
	public Summary findSummaryBySummaryId(String summaryId) {
		// TODO Auto-generated method stub
		return coachDao.findSummaryBySummaryId(summaryId);
	}

	//����ѵ������
	@Override
	public void updateSummary(Summary summary) {
		// TODO Auto-generated method stub
		coachDao.updateSummary(summary);
	}

	//����planId��pId����summary
	@Override
	public String findSummaryByPlanIdAndPId(String planId,String pid) {
		// TODO Auto-generated method stub
		return coachDao.findSummaryByPlanIdAndPId(planId,pid);
	}

	//ɾ��summary
	@Override
	public void deleteSummary(Summary checkSummary) {
		// TODO Auto-generated method stub
		coachDao.deleteSummary(checkSummary);
	}

	//��Ա������д����
	@Override
	public void writeNewSuggestForCoach(SuggestForCoach suggestForCoach) {
		// TODO Auto-generated method stub
		coachDao.writeNewSuggestForCoach(suggestForCoach);
	}

	//ɾ����Ա�������Ľ���
	@Override
	public void deleteSuggestForCoach(String sugCoachId) {
		// TODO Auto-generated method stub
		coachDao.deleteSuggestForCoach(sugCoachId);
	}

	//Ѱ��δ��ǽ���
	@Override
	public List<SuggestForCoach> findUnMarkSuggestByCoach(Coach coach) {
		// TODO Auto-generated method stub
		return coachDao.findUnMarkSuggestByCoach(coach);
	}

	//Ѱ��ĳ������
	@Override
	public SuggestForCoach findSuggestForCoachById(String sugCoachId) {
		// TODO Auto-generated method stub
		return coachDao.findSuggestForCoachById(sugCoachId);
	}

	//���½���
	@Override
	public void updateSugForCoach(SuggestForCoach suggestForCoach) {
		// TODO Auto-generated method stub
		coachDao.updateSugForCoach(suggestForCoach);
	}

	//Ѱ���ѱ�ǵĽ���
	@Override
	public List<SuggestForCoach> findMarkSuggest(Coach coach) {
		// TODO Auto-generated method stub
		return coachDao.findMarkSuggest(coach);
	}

	//Ѱ��ǰ�ߴ�ѵ��
	@Override
	public List<CoachPlan> getPracticeScore(CoachPlan plan) {
		// TODO Auto-generated method stub
		return coachDao.getPracticeScore(plan);
	}

	//�޸Ľ�����Ϣ
	@Override
	public void updateCoach(Coach coach) {
		// TODO Auto-generated method stub
		coachDao.updateCoach(coach);
	}

	//Ѱ�ҽ���
	@Override
	public Coach findCoachById(String coachId) {
		// TODO Auto-generated method stub
		return coachDao.findCoachById(coachId);
	}

	//Ѱ��share��ʾ����ҳ
	@Override
	public List<Share> findShareForInfo(Coach infoCoach) {
		// TODO Auto-generated method stub
		return coachDao.findShareForInfo(infoCoach);
	}

	//��ȡͷ10��summary
	@Override
	public List<Summary> getSummaryForInfo(String pid) {
		// TODO Auto-generated method stub
		return coachDao.getSummaryForInfo(pid);
	}

	//��ȡ���µ�10��ѵ��
	@Override
	public List<CoachPlan> getPracticeScoreForInfo(String pid) {
		// TODO Auto-generated method stub
		return coachDao.getPracticeScoreForInfo(pid);
	}

	//�����˺Ų��ҽ���
	@Override
	public Coach findCoachByNumber(String coachNumber) {
		// TODO Auto-generated method stub
		return coachDao.findCoachByNumber(coachNumber);
	}

	//Ѱ��ĳ��Ա���е�ѵ������
	@Override
	public List<Summary> findSummaryByPlayer(Player player) {
		// TODO Auto-generated method stub
		return coachDao.findSummaryByPlayer(player);
	}

	//Ѱ������ҳ�Ľ���
	@Override
	public List<Coach> findMainPageCoach() {
		// TODO Auto-generated method stub
		return coachDao.findMainPageCoach();
	}

}











