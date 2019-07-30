package com.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * �ռ�
 * @author 45��ը
 *
 */
@Entity
@Table(name="diary",catalog="mysoccer")
public class Diary {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String diaryId; //�ռ�id
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date diaryDate; //�ռ�����
	
	private String diaryTitle; //�ռǱ���
	
	private Integer viewNumber;//�����
	
	private Integer diaryTopFlag; //�ö�
	
	@Lob
	private String diaryContents; //�ռ�����
	@Lob   
	@Column(name="content", columnDefinition="TEXT", nullable=true)
	public String getDiaryContents() {
		return diaryContents;
	}
	
	private Integer diaryPermission; //�ռ�Ȩ��
	private Integer diaryReadingNumber; //�ռ��Ķ���
	private String diaryHeadContent;//��ʾͷ
	
	@ManyToOne(targetEntity=Player.class)
	@JoinColumn(name="d_player")
	private Player diaryPlayer; //��Ա

/*	@OneToMany(targetEntity = CollectionItem.class,mappedBy="collectionDiary")
	private List<CollectionItem> collectionList;*/
	
	public Diary() {
		
	}
	
	
	


	public Diary(String diaryId, Date diaryDate, String diaryTitle,
		Integer viewNumber, Integer diaryTopFlag, String diaryContents,
		Integer diaryPermission, Integer diaryReadingNumber,
		String diaryHeadContent, Player diaryPlayer) {
	super();
	this.diaryId = diaryId;
	this.diaryDate = diaryDate;
	this.diaryTitle = diaryTitle;
	this.viewNumber = viewNumber;
	this.diaryTopFlag = diaryTopFlag;
	this.diaryContents = diaryContents;
	this.diaryPermission = diaryPermission;
	this.diaryReadingNumber = diaryReadingNumber;
	this.diaryHeadContent = diaryHeadContent;
	this.diaryPlayer = diaryPlayer;
}





	public Integer getViewNumber() {
		return viewNumber;
	}





	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}





	public Integer getDiaryTopFlag() {
		return diaryTopFlag;
	}


	public void setDiaryTopFlag(Integer diaryTopFlag) {
		this.diaryTopFlag = diaryTopFlag;
	}


	public String getDiaryHeadContent() {
		return diaryHeadContent;
	}


	public void setDiaryHeadContent(String diaryHeadContent) {
		this.diaryHeadContent = diaryHeadContent;
	}


	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	

	

	public void setDiaryContents(String diaryContents) {
		this.diaryContents = diaryContents;
	}


	public Integer getDiaryPermission() {
		return diaryPermission;
	}

	public void setDiaryPermission(Integer diaryPermission) {
		this.diaryPermission = diaryPermission;
	}

	public Integer getDiaryReadingNumber() {
		return diaryReadingNumber;
	}

	public void setDiaryReadingNumber(Integer diaryReadingNumber) {
		this.diaryReadingNumber = diaryReadingNumber;
	}

	public Player getDiaryPlayer() {
		return diaryPlayer;
	}

	public void setDiaryPlayer(Player diaryPlayer) {
		this.diaryPlayer = diaryPlayer;
	}

//	@OneToMany(targetEntity=Comments.class,mappedBy="commentsClub")
//	private Comments Comments; //����

	
}
