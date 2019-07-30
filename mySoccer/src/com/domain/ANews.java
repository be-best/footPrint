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
@Entity
@Table(name="anews",catalog="mysoccer")
public class ANews {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String anewsId; //����id
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date anewsDate; //��������
	
	private String anewsHeadContent; //��ʾͷ
	
	private Integer anewsTopFlag; //�ö�
	
	private Integer viewNumber;//�����
	
	private String anewsTitle; //���±���
	@Lob
	private String anewsContents; //��������
	@Lob   
	@Column(name="content", columnDefinition="TEXT", nullable=true)
	public String getAnewsContents() {
		return anewsContents;
	}
	
	//private String newsCode; //����״̬
	private Integer anewsReadingNumber; //�Ķ���
	
	@ManyToOne(targetEntity=Admin.class)
	@JoinColumn(name="anews_admin")
	private Admin anewsAdmin; //����Ա
	
	/*@OneToMany(targetEntity = CollectionItem.class,mappedBy="collectionANews")
	private List<CollectionItem> collectionList;
	*/
	
	public ANews() {
		
	}


	
	


	public ANews(String anewsId, Date anewsDate, String anewsHeadContent,
			Integer anewsTopFlag, Integer viewNumber, String anewsTitle,
			String anewsContents, Integer anewsReadingNumber, Admin anewsAdmin) {
		super();
		this.anewsId = anewsId;
		this.anewsDate = anewsDate;
		this.anewsHeadContent = anewsHeadContent;
		this.anewsTopFlag = anewsTopFlag;
		this.viewNumber = viewNumber;
		this.anewsTitle = anewsTitle;
		this.anewsContents = anewsContents;
		this.anewsReadingNumber = anewsReadingNumber;
		this.anewsAdmin = anewsAdmin;
	}






	public Integer getViewNumber() {
		return viewNumber;
	}






	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}






	public Integer getAnewsTopFlag() {
		return anewsTopFlag;
	}



	public void setAnewsTopFlag(Integer anewsTopFlag) {
		this.anewsTopFlag = anewsTopFlag;
	}



	public String getAnewsId() {
		return anewsId;
	}

	public void setAnewsId(String anewsId) {
		this.anewsId = anewsId;
	}

	public Date getAnewsDate() {
		return anewsDate;
	}

	public void setAnewsDate(Date anewsDate) {
		this.anewsDate = anewsDate;
	}

	public String getAnewsHeadContent() {
		return anewsHeadContent;
	}

	public void setAnewsHeadContent(String anewsHeadContent) {
		this.anewsHeadContent = anewsHeadContent;
	}

	public String getAnewsTitle() {
		return anewsTitle;
	}

	public void setAnewsTitle(String anewsTitle) {
		this.anewsTitle = anewsTitle;
	}

	public Integer getAnewsReadingNumber() {
		return anewsReadingNumber;
	}

	public void setAnewsReadingNumber(Integer anewsReadingNumber) {
		this.anewsReadingNumber = anewsReadingNumber;
	}

	public Admin getAnewsAdmin() {
		return anewsAdmin;
	}

	public void setAnewsAdmin(Admin anewsAdmin) {
		this.anewsAdmin = anewsAdmin;
	}

	public void setAnewsContents(String anewsContents) {
		this.anewsContents = anewsContents;
	}
	
	

}
