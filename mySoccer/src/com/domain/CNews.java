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
 * ����
 * @author 45��ը
 *
 */
@Entity
@Table(name="cnews",catalog="mysoccer")
public class CNews {
	/**
	 * �������ŵ��û�id 
	����date
	����context
	�Ķ��� readingNumber
	״̬ Code��0/1��
	 */
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String newsId; //����id
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date newsDate; //��������
	
	private String newsHeadContent; //��ʾͷ
	
	private Integer newsTopFlag; //�ö�
	
	private Integer viewNumber;//�����
	
	private String newsTitle; //���±���
	@Lob
	private String newsContents; //��������
	@Lob   
	@Column(name="content", columnDefinition="TEXT", nullable=true)
	public String getNewsContents() {
		return newsContents;
	}
	//private String newsCode; //����״̬
	private Integer newsReadingNumber; //�Ķ���
	
	@ManyToOne(targetEntity=Club.class)
	@JoinColumn(name="n_club")
	private Club newsClub; //���ֲ�

	/*@OneToMany(targetEntity = CollectionItem.class,mappedBy="collectionCNews")
	private List<CollectionItem> collectionList;*/
	
	
	public CNews() {
		
	}
	
	


	public CNews(String newsId, Date newsDate, String newsHeadContent,
			Integer newsTopFlag, Integer viewNumber, String newsTitle,
			String newsContents, Integer newsReadingNumber, Club newsClub) {
		super();
		this.newsId = newsId;
		this.newsDate = newsDate;
		this.newsHeadContent = newsHeadContent;
		this.newsTopFlag = newsTopFlag;
		this.viewNumber = viewNumber;
		this.newsTitle = newsTitle;
		this.newsContents = newsContents;
		this.newsReadingNumber = newsReadingNumber;
		this.newsClub = newsClub;
	}




	public Integer getViewNumber() {
		return viewNumber;
	}




	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}




	public Integer getNewsTopFlag() {
		return newsTopFlag;
	}




	public void setNewsTopFlag(Integer newsTopFlag) {
		this.newsTopFlag = newsTopFlag;
	}




	public String getNewsHeadContent() {
		return newsHeadContent;
	}






	public void setNewsHeadContent(String newsHeadContent) {
		this.newsHeadContent = newsHeadContent;
	}






	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	

	public void setNewsContents(String newsContents) {
		this.newsContents = newsContents;
	}


	public Integer getNewsReadingNumber() {
		return newsReadingNumber;
	}

	public void setNewsReadingNumber(Integer newsReadingNumber) {
		this.newsReadingNumber = newsReadingNumber;
	}

	public Club getNewsClub() {
		return newsClub;
	}

	public void setNewsClub(Club newsClub) {
		this.newsClub = newsClub;
	}

	
	

}
