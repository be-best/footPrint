package com.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * ѵ���ƻ�(����ѵ����ʱ�����µ��Ǹ�������ʷѵ��)
 * @author 45��ը
 *
 */
@Entity
@Table(name="coachplan",catalog="mysoccer")
public class CoachPlan {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String planId;//�ƻ�Id
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date planTime;//ѵ��ʱ��
	private String planAddress;//ѵ���ص�
	private Integer planFlag;//�Ƿ����
	private String planCoachSum; //�����ܽ�
	
	@Temporal(TemporalType.TIMESTAMP)
	//@JSONField(format="yyyy-MM-dd")
	private Date planWriteDate;//����ʱ��
	private String planContent;//ѵ������
	
	@ManyToOne(targetEntity=Coach.class)
	@JoinColumn(name="plan_coach")
	private Coach planCoach;//����
	
	@OneToMany(targetEntity = Summary.class,mappedBy="summaryCoachPlan")
	@Cascade(CascadeType.DELETE)//����������ɾ��ѵ��ʱɾ��ѵ���ܽ�
	private List<Summary> coachPlanSummary;//ѵ���ܽ�

	public CoachPlan() {
		
	}


	public CoachPlan(String planId, Date planTime, String planAddress,
			Integer planFlag, String planCoachSum, Date planWriteDate,
			String planContent, Coach planCoach, List<Summary> coachPlanSummary) {
		super();
		this.planId = planId;
		this.planTime = planTime;
		this.planAddress = planAddress;
		this.planFlag = planFlag;
		this.planCoachSum = planCoachSum;
		this.planWriteDate = planWriteDate;
		this.planContent = planContent;
		this.planCoach = planCoach;
		this.coachPlanSummary = coachPlanSummary;
	}


	public String getPlanCoachSum() {
		return planCoachSum;
	}


	public void setPlanCoachSum(String planCoachSum) {
		this.planCoachSum = planCoachSum;
	}


	public Date getPlanWriteDate() {
		return planWriteDate;
	}


	public void setPlanWriteDate(Date planWriteDate) {
		this.planWriteDate = planWriteDate;
	}



	public Integer getPlanFlag() {
		return planFlag;
	}




	public void setPlanFlag(Integer planFlag) {
		this.planFlag = planFlag;
	}




	public String getPlanAddress() {
		return planAddress;
	}







	public void setPlanAddress(String planAddress) {
		this.planAddress = planAddress;
	}







	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	

	public Date getPlanTime() {
		return planTime;
	}


	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}


	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

	public Coach getPlanCoach() {
		return planCoach;
	}

	public void setPlanCoach(Coach planCoach) {
		this.planCoach = planCoach;
	}



	public List<Summary> getCoachPlanSummary() {
		return coachPlanSummary;
	}



	public void setCoachPlanSummary(List<Summary> coachPlanSummary) {
		this.coachPlanSummary = coachPlanSummary;
	}




	



	
	
	

}











