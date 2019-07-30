package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ʱ����
 * @author 45��ը
 *
 */
@Entity
@Table(name="clubdeeds",catalog="mysoccer")
public class ClubDeeds {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String clubDeedsId; //�¼�Id
	
	private String clubDeedsTitle;	//�¼�����
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date clubDeedsDate;//�¼�ʱ��
	
	private String clubDeedsContent; //�¼�����
	
	@ManyToOne(targetEntity=Club.class)
	@JoinColumn(name="deeds_club")
	private Club deedsClub; //���ֲ�

	public ClubDeeds() {
		
	}
	
	public ClubDeeds(String clubDeedsId, Date clubDeedsDate,
			String clubDeedsContent, Club deedsClub) {
		super();
		this.clubDeedsId = clubDeedsId;
		this.clubDeedsDate = clubDeedsDate;
		this.clubDeedsContent = clubDeedsContent;
		this.deedsClub = deedsClub;
	}

	public String getClubDeedsId() {
		return clubDeedsId;
	}

	public void setClubDeedsId(String clubDeedsId) {
		this.clubDeedsId = clubDeedsId;
	}

	public Date getClubDeedsDate() {
		return clubDeedsDate;
	}

	public void setClubDeedsDate(Date clubDeedsDate) {
		this.clubDeedsDate = clubDeedsDate;
	}

	public String getClubDeedsContent() {
		return clubDeedsContent;
	}

	public void setClubDeedsContent(String clubDeedsContent) {
		this.clubDeedsContent = clubDeedsContent;
	}

	public Club getDeedsClub() {
		return deedsClub;
	}

	public void setDeedsClub(Club deedsClub) {
		this.deedsClub = deedsClub;
	}
	
	
}











