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

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ���ֲ�����
 * @author 45��ը
 *
 */
@Entity
@Table(name="announcement",catalog="mysoccer")
public class Announcement {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String aId; //����id
	
	@ManyToOne(targetEntity=Club.class)
	@JoinColumn(name="a_club") //announcement�����������Ϊa_club
	private Club announcementClub; //���ֲ�
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date aTime; //���淢��ʱ��
	
	private String aContent; //��������
	
	public Announcement() {
		
	}
	
	
	public Announcement(String aId, Club announcementClub, Date aTime,
			String aContent) {
		super();
		this.aId = aId;
		this.announcementClub = announcementClub;
		this.aTime = aTime;
		this.aContent = aContent;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	
	public Club getAnnouncementClub() {
		return announcementClub;
	}
	public void setAnnouncementClub(Club announcementClub) {
		this.announcementClub = announcementClub;
	}
	public Date getaTime() {
		return aTime;
	}
	public void setaTime(Date aTime) {
		this.aTime = aTime;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	
	
	
	

}
