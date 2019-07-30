package com.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ��Աʵ��
 * @author 45��ը
 *
 */
@Entity
@Table(name="player",catalog="mysoccer")
public class Player {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String pid; //�û�uid
	private String pNumber; //�����˺�
	private String password; //����
	private String pName; //�ǳ�
	private String pIntroduction; //���
	private String pRealName; //��ʵ����
	private String pPhoto; //ͷ��
	private String pBirthday; //����
	private String pGender; //�Ա�
	private String pAddress; //��ַ
	private String pTelePhone; //��Ա��ϵ�绰
	private String pApplyId;//���������ֲ�ʱ�õ�
	private String pPosition; //����λ��
	private Integer playerMainPage;//��Ա����ҳ��־
	
/*	@ManyToOne(targetEntity=Club.class)
	@JoinColumn(name="p_club")*/
	private String pClubId; //���ֲ�
	
	private String pClubName;
	
	@ManyToOne(targetEntity=Coach.class)
	@JoinColumn(name="p_coach")
	private Coach pCoach; //����
	
	
	@OneToMany(targetEntity=Diary.class,mappedBy="diaryPlayer")
	private Map<String,Diary> pDiary; //�ռ�
	
	@OneToMany(targetEntity=PlayerMemo.class,mappedBy="memoPlayer")
	private Map<String,PlayerMemo> pMemo; //����¼
	
	/*@OneToMany(targetEntity=SuggestForCoach.class,mappedBy="sugCoachPlayer")
	private Map<String,SuggestForCoach> pSuggest; //����
*/	
	@OneToMany(targetEntity=PlayerDeeds.class,mappedBy="deedsPlayer")
	private List<PlayerDeeds> pDeeds; //ʱ�����¼�
	
	@OneToMany(targetEntity=Evaluation.class,mappedBy="evaPlayer")
	private Map<String,Evaluation> pEvaluation; //ÿ������
	
	@OneToOne(targetEntity=Report.class,mappedBy="reportPlayer")
	private Report pReport; //��̽����

	public Player() {
		
	}


	public Player(String pid, String pNumber, String password, String pName,
			String pIntroduction, String pRealName, String pPhoto,
			String pBirthday, String pGender, String pAddress,
			String pTelePhone, String pApplyId, String pPosition,
			Integer playerMainPage, String pClubId, String pClubName,
			Coach pCoach, Map<String, Diary> pDiary,
			Map<String, PlayerMemo> pMemo, List<PlayerDeeds> pDeeds,
			Map<String, Evaluation> pEvaluation, Report pReport) {
		super();
		this.pid = pid;
		this.pNumber = pNumber;
		this.password = password;
		this.pName = pName;
		this.pIntroduction = pIntroduction;
		this.pRealName = pRealName;
		this.pPhoto = pPhoto;
		this.pBirthday = pBirthday;
		this.pGender = pGender;
		this.pAddress = pAddress;
		this.pTelePhone = pTelePhone;
		this.pApplyId = pApplyId;
		this.pPosition = pPosition;
		this.playerMainPage = playerMainPage;
		this.pClubId = pClubId;
		this.pClubName = pClubName;
		this.pCoach = pCoach;
		this.pDiary = pDiary;
		this.pMemo = pMemo;
		this.pDeeds = pDeeds;
		this.pEvaluation = pEvaluation;
		this.pReport = pReport;
	}


	

	public Integer getPlayerMainPage() {
		return playerMainPage;
	}


	public void setPlayerMainPage(Integer playerMainPage) {
		this.playerMainPage = playerMainPage;
	}


	public String getpClubName() {
		return pClubName;
	}



	public void setpClubName(String pClubName) {
		this.pClubName = pClubName;
	}



	public String getpApplyId() {
		return pApplyId;
	}

	public void setpApplyId(String pApplyId) {
		this.pApplyId = pApplyId;
	}

	public List<PlayerDeeds> getpDeeds() {
		return pDeeds;
	}

	public void setpDeeds(List<PlayerDeeds> pDeeds) {
		this.pDeeds = pDeeds;
	}




	public String getpClubId() {
		return pClubId;
	}







	public void setpClubId(String pClubId) {
		this.pClubId = pClubId;
	}







	public String getpTelePhone() {
		return pTelePhone;
	}




	public void setpTelePhone(String pTelePhone) {
		this.pTelePhone = pTelePhone;
	}




	public String getpNumber() {
		return pNumber;
	}


	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}


	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpIntroduction() {
		return pIntroduction;
	}

	public void setpIntroduction(String pIntroduction) {
		this.pIntroduction = pIntroduction;
	}

	public String getpRealName() {
		return pRealName;
	}

	public void setpRealName(String pRealName) {
		this.pRealName = pRealName;
	}

	public String getpPhoto() {
		return pPhoto;
	}

	public void setpPhoto(String pPhoto) {
		this.pPhoto = pPhoto;
	}

	public String getpBirthday() {
		return pBirthday;
	}

	public void setpBirthday(String pBirthday) {
		this.pBirthday = pBirthday;
	}

	public String getpGender() {
		return pGender;
	}

	public void setpGender(String pGender) {
		this.pGender = pGender;
	}

	public String getpAddress() {
		return pAddress;
	}

	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}


	public Coach getpCoach() {
		return pCoach;
	}

	public void setpCoach(Coach pCoach) {
		this.pCoach = pCoach;
	}

	public String getpPosition() {
		return pPosition;
	}

	public void setpPosition(String pPosition) {
		this.pPosition = pPosition;
	}

	public Map<String, Diary> getpDiary() {
		return pDiary;
	}

	public void setpDiary(Map<String, Diary> pDiary) {
		this.pDiary = pDiary;
	}

	public Map<String, PlayerMemo> getpMemo() {
		return pMemo;
	}

	public void setpMemo(Map<String, PlayerMemo> pMemo) {
		this.pMemo = pMemo;
	}

	public Map<String, Evaluation> getpEvaluation() {
		return pEvaluation;
	}

	public void setpEvaluation(Map<String, Evaluation> pEvaluation) {
		this.pEvaluation = pEvaluation;
	}



	public Report getpReport() {
		return pReport;
	}



	public void setpReport(Report pReport) {
		this.pReport = pReport;
	}

	
	
}
