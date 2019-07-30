package com.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * ���ֲ�ʵ��
 * @author 45��ը
 *
 */
@Entity
@Table(name="club",catalog="mysoccer")
public class Club {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String cid; //���ֲ�id
	private String clubName; //���ֲ�ȫ��
	private String clubPassword; //����
	private String clubIntroduction; //���
	private String clubPhoto; //ͷ��
	private String clubEmail; //����
	private String clubPhone; //��ϵ�绰
	private Integer clubCode; //����״̬
	private String clubCity; //���ֲ�����
	private Integer clubCoachFlag;//�ж��Ƿ��н���
	private Integer clubMainPage;//����ҳ��־
	
	//һ�Զ࣬��coachClubά�����
	@OneToMany(targetEntity = Coach.class,mappedBy="coachClub")
	private List<Coach> clubCoach; //����
	
	//@OneToMany(targetEntity = Player.class,mappedBy="pClub")
	//private Set<Player> clubPlayer; //��Ա
	
	@OneToMany(targetEntity=SuggestForClub.class,mappedBy="sugClub")
	private List<SuggestForClub> clubSuggest; //����
	
	@OneToMany(targetEntity = Announcement.class,mappedBy="announcementClub")
	private Map<String,Announcement> clubAnnouncement; //����
	
	@OneToMany(targetEntity = ClubDeeds.class,mappedBy="deedsClub")
	private Map<String,ClubDeeds> clubDeeds; //ʱ�����¼�
	
	@OneToMany(targetEntity = CNews.class,mappedBy="newsClub")
	private Map<String,CNews> clubNews; //���Ŷ�̬

	public Club() {
		
	}


	public Club(String cid, String clubName, String clubPassword,
			String clubIntroduction, String clubPhoto, String clubEmail,
			String clubPhone, Integer clubCode, String clubCity,
			Integer clubCoachFlag, Integer clubMainPage, List<Coach> clubCoach,
			List<SuggestForClub> clubSuggest,
			Map<String, Announcement> clubAnnouncement,
			Map<String, ClubDeeds> clubDeeds, Map<String, CNews> clubNews) {
		super();
		this.cid = cid;
		this.clubName = clubName;
		this.clubPassword = clubPassword;
		this.clubIntroduction = clubIntroduction;
		this.clubPhoto = clubPhoto;
		this.clubEmail = clubEmail;
		this.clubPhone = clubPhone;
		this.clubCode = clubCode;
		this.clubCity = clubCity;
		this.clubCoachFlag = clubCoachFlag;
		this.clubMainPage = clubMainPage;
		this.clubCoach = clubCoach;
		this.clubSuggest = clubSuggest;
		this.clubAnnouncement = clubAnnouncement;
		this.clubDeeds = clubDeeds;
		this.clubNews = clubNews;
	}




	public Integer getClubMainPage() {
		return clubMainPage;
	}




	public void setClubMainPage(Integer clubMainPage) {
		this.clubMainPage = clubMainPage;
	}




	public Integer getClubCoachFlag() {
		return clubCoachFlag;
	}


	public void setClubCoachFlag(Integer clubCoachFlag) {
		this.clubCoachFlag = clubCoachFlag;
	}


	public List<SuggestForClub> getClubSuggest() {
		return clubSuggest;
	}


	public void setClubSuggest(List<SuggestForClub> clubSuggest) {
		this.clubSuggest = clubSuggest;
	}


	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubPassword() {
		return clubPassword;
	}

	public void setClubPassword(String clubPassword) {
		this.clubPassword = clubPassword;
	}

	public String getClubIntroduction() {
		return clubIntroduction;
	}

	public void setClubIntroduction(String clubIntroduction) {
		this.clubIntroduction = clubIntroduction;
	}

	public String getClubPhoto() {
		return clubPhoto;
	}

	public void setClubPhoto(String clubPhoto) {
		this.clubPhoto = clubPhoto;
	}

	public String getClubEmail() {
		return clubEmail;
	}

	public void setClubEmail(String clubEmail) {
		this.clubEmail = clubEmail;
	}

	

	public String getClubPhone() {
		return clubPhone;
	}


	public void setClubPhone(String clubPhone) {
		this.clubPhone = clubPhone;
	}


	public Integer getClubCode() {
		return clubCode;
	}


	public void setClubCode(Integer clubCode) {
		this.clubCode = clubCode;
	}


	public String getClubCity() {
		return clubCity;
	}

	public void setClubCity(String clubCity) {
		this.clubCity = clubCity;
	}

	
	public List<Coach> getClubCoach() {
		return clubCoach;
	}




	public void setClubCoach(List<Coach> clubCoach) {
		this.clubCoach = clubCoach;
	}




	public Map<String, Announcement> getClubAnnouncement() {
		return clubAnnouncement;
	}

	public void setClubAnnouncement(Map<String, Announcement> clubAnnouncement) {
		this.clubAnnouncement = clubAnnouncement;
	}

	public Map<String, ClubDeeds> getClubDeeds() {
		return clubDeeds;
	}

	public void setClubDeeds(Map<String, ClubDeeds> clubDeeds) {
		this.clubDeeds = clubDeeds;
	}

	public Map<String, CNews> getClubNews() {
		return clubNews;
	}

	public void setClubNews(Map<String, CNews> clubNews) {
		this.clubNews = clubNews;
	}
	
	
}








