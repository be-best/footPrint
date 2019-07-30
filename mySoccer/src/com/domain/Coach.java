package com.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ����ʵ��
 * @author 45��ը
 *
 */
@Entity
@Table(name="coach",catalog="mysoccer")
public class Coach {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String coachId; //����id
	private String coachNumber; //�˺�
	private String coachName; //�ǳ�
	private String coachPassword;//����
	private String coachIntroduction; //���
	private String coachRealName; //��ʵ����
	private String coachPhoto; //ͷ��
	private String coachBirthday; //����
	private String coachAddress; //����
	private String coachGender; //�Ա�
	private String coachApplyId;//���ֲ������Id
	private String coachTelPhone;//�����ĵ绰
	private Integer coachMainPage;//����ҳ
	
	@ManyToOne(targetEntity=Club.class)
	@JoinColumn(name="c_club") //coach�����������Ϊt_club
	private Club coachClub; //���ֲ�
	
	@OneToMany(targetEntity=Player.class,mappedBy="pCoach")
	private List<Player> coachPlayer; //��Ա
	
	@OneToMany(targetEntity=CoachPlan.class,mappedBy="planCoach")
	private List<CoachPlan> coachPlan; //ѵ���ƻ�
	
	@OneToMany(targetEntity=CoachMemo.class,mappedBy="memoCoach")
	private Map<String,CoachMemo> coachMemo; //����¼
	
	/*@OneToMany(targetEntity=SuggestForClub.class,mappedBy="sugClubCoach")
	private Map<String,SuggestForClub> coachSuggest; //����
*/	
	@OneToMany(targetEntity=SuggestForCoach.class,mappedBy="sugCoach")
	private List<SuggestForCoach> coachSuggest; //����
	
	@OneToMany(targetEntity=Share.class,mappedBy="shareCoach")
	private Map<String,Share> coachShare; //��������
	
	@OneToMany(targetEntity=CoachDeeds.class,mappedBy="deedsCoach")
	private Map<String,CoachDeeds> coachDeeds; //ʱ�����¼� 
	private String coachAssessment; //���ֲ�����
	
	public Coach() {
		
	}

	




	public Coach(String coachId, String coachNumber, String coachName,
			String coachPassword, String coachIntroduction,
			String coachRealName, String coachPhoto, String coachBirthday,
			String coachAddress, String coachGender, String coachApplyId,
			String coachTelPhone, Integer coachMainPage, Club coachClub,
			List<Player> coachPlayer, List<CoachPlan> coachPlan,
			Map<String, CoachMemo> coachMemo,
			List<SuggestForCoach> coachSuggest, Map<String, Share> coachShare,
			Map<String, CoachDeeds> coachDeeds, String coachAssessment) {
		super();
		this.coachId = coachId;
		this.coachNumber = coachNumber;
		this.coachName = coachName;
		this.coachPassword = coachPassword;
		this.coachIntroduction = coachIntroduction;
		this.coachRealName = coachRealName;
		this.coachPhoto = coachPhoto;
		this.coachBirthday = coachBirthday;
		this.coachAddress = coachAddress;
		this.coachGender = coachGender;
		this.coachApplyId = coachApplyId;
		this.coachTelPhone = coachTelPhone;
		this.coachMainPage = coachMainPage;
		this.coachClub = coachClub;
		this.coachPlayer = coachPlayer;
		this.coachPlan = coachPlan;
		this.coachMemo = coachMemo;
		this.coachSuggest = coachSuggest;
		this.coachShare = coachShare;
		this.coachDeeds = coachDeeds;
		this.coachAssessment = coachAssessment;
	}





	public Integer getCoachMainPage() {
		return coachMainPage;
	}






	public void setCoachMainPage(Integer coachMainPage) {
		this.coachMainPage = coachMainPage;
	}






	public String getCoachTelPhone() {
		return coachTelPhone;
	}


	public void setCoachTelPhone(String coachTelPhone) {
		this.coachTelPhone = coachTelPhone;
	}


	public String getCoachApplyId() {
		return coachApplyId;
	}





	public void setCoachApplyId(String coachApplyId) {
		this.coachApplyId = coachApplyId;
	}





	public void setCoachPlayer(List<Player> coachPlayer) {
		this.coachPlayer = coachPlayer;
	}


	public List<Player> getCoachPlayer() {
		return coachPlayer;
	}


	public String getCoachPassword() {
		return coachPassword;
	}


	public void setCoachPassword(String coachPassword) {
		this.coachPassword = coachPassword;
	}


	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}
	public String getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getCoachIntroduction() {
		return coachIntroduction;
	}
	public void setCoachIntroduction(String coachIntroduction) {
		this.coachIntroduction = coachIntroduction;
	}
	public String getCoachRealName() {
		return coachRealName;
	}
	public void setCoachRealName(String coachRealName) {
		this.coachRealName = coachRealName;
	}
	public String getCoachPhoto() {
		return coachPhoto;
	}
	public void setCoachPhoto(String coachPhoto) {
		this.coachPhoto = coachPhoto;
	}
	public String getCoachBirthday() {
		return coachBirthday;
	}
	public void setCoachBirthday(String coachBirthday) {
		this.coachBirthday = coachBirthday;
	}
	public String getCoachAddress() {
		return coachAddress;
	}
	public void setCoachAddress(String coachAddress) {
		this.coachAddress = coachAddress;
	}
	public String getCoachGender() {
		return coachGender;
	}
	public void setCoachGender(String coachGender) {
		this.coachGender = coachGender;
	}
	public Club getCoachClub() {
		return coachClub;
	}
	public void setCoachClub(Club coachClub) {
		this.coachClub = coachClub;
	}
	
	public List<CoachPlan> getCoachPlan() {
		return coachPlan;
	}


	public void setCoachPlan(List<CoachPlan> coachPlan) {
		this.coachPlan = coachPlan;
	}


	public Map<String, CoachMemo> getCoachMemo() {
		return coachMemo;
	}

	public void setCoachMemo(Map<String, CoachMemo> coachMemo) {
		this.coachMemo = coachMemo;
	}


	public List<SuggestForCoach> getCoachSuggest() {
		return coachSuggest;
	}



	public void setCoachSuggest(List<SuggestForCoach> coachSuggest) {
		this.coachSuggest = coachSuggest;
	}



	public Map<String, Share> getCoachShare() {
		return coachShare;
	}
	public void setCoachShare(Map<String, Share> coachShare) {
		this.coachShare = coachShare;
	}
	public Map<String, CoachDeeds> getCoachDeeds() {
		return coachDeeds;
	}
	public void setCoachDeeds(Map<String, CoachDeeds> coachDeeds) {
		this.coachDeeds = coachDeeds;
	}
	public String getCoachAssessment() {
		return coachAssessment;
	}
	public void setCoachAssessment(String coachAssessment) {
		this.coachAssessment = coachAssessment;
	}

	
}
