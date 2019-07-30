package com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ѵ�������Ա����
 * @author 45��ը
 *
 */
@Entity
@Table(name="summary",catalog="mysoccer")
public class Summary {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String summaryId; // ����ID
	private String summaryPlayerId; //���۶�Ӧ����ԱId
	private String summaryPlayerRealName; //���۶�Ӧ����Ա����
	private String summaryContent; //��������
	private String summarySuggest;//���۽���
	private Double summaryScore;//��������
	private String summaryPlayerSum;//��Ա��������
	
	@ManyToOne(targetEntity=CoachPlan.class)
	@JoinColumn(name="summary_coachPlan")
	private CoachPlan summaryCoachPlan; //����ѵ��
	
	public Summary() {
		
	}

	

	public Summary(String summaryId, String summaryPlayerId,
			String summaryPlayerRealName, String summaryContent,
			String summarySuggest, Double summaryScore,
			String summaryPlayerSum, CoachPlan summaryCoachPlan) {
		super();
		this.summaryId = summaryId;
		this.summaryPlayerId = summaryPlayerId;
		this.summaryPlayerRealName = summaryPlayerRealName;
		this.summaryContent = summaryContent;
		this.summarySuggest = summarySuggest;
		this.summaryScore = summaryScore;
		this.summaryPlayerSum = summaryPlayerSum;
		this.summaryCoachPlan = summaryCoachPlan;
	}



	public String getSummaryPlayerSum() {
		return summaryPlayerSum;
	}



	public void setSummaryPlayerSum(String summaryPlayerSum) {
		this.summaryPlayerSum = summaryPlayerSum;
	}



	public String getSummarySuggest() {
		return summarySuggest;
	}




	public void setSummarySuggest(String summarySuggest) {
		this.summarySuggest = summarySuggest;
	}

	public Double getSummaryScore() {
		return summaryScore;
	}


	public void setSummaryScore(Double summaryScore) {
		this.summaryScore = summaryScore;
	}


	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}

	public String getSummaryPlayerId() {
		return summaryPlayerId;
	}



	public void setSummaryPlayerId(String summaryPlayerId) {
		this.summaryPlayerId = summaryPlayerId;
	}



	public String getSummaryPlayerRealName() {
		return summaryPlayerRealName;
	}



	public void setSummaryPlayerRealName(String summaryPlayerRealName) {
		this.summaryPlayerRealName = summaryPlayerRealName;
	}



	public String getSummaryContent() {
		return summaryContent;
	}

	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}

	public CoachPlan getSummaryCoachPlan() {
		return summaryCoachPlan;
	}

	public void setSummaryCoachPlan(CoachPlan summaryCoachPlan) {
		this.summaryCoachPlan = summaryCoachPlan;
	}
	
	
	
	
	
	
}
