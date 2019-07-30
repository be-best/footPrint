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
 * ����
 * @author 45��ը
 *
 */
@Entity
@Table(name="tourcomments",catalog="suggest")
public class TourComments {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String commentsId; //Id
	private String commentsContent; //����
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentsTime; //ʱ��
	
	@ManyToOne(targetEntity=Tourist.class)
	@JoinColumn(name="comments_tourist")
	private Tourist commentsTourist;//�ο�
	
	@ManyToOne(targetEntity=TNews.class)
	@JoinColumn(name="comments_news")
	private TNews commentsNews;

	public TourComments() {
		
	}
	
	

	public TourComments(String commentsId, String commentsContent,
			Date commentsTime, Tourist commentsTourist, TNews commentsNews) {
		super();
		this.commentsId = commentsId;
		this.commentsContent = commentsContent;
		this.commentsTime = commentsTime;
		this.commentsTourist = commentsTourist;
		this.commentsNews = commentsNews;
	}



	public TNews getCommentsNews() {
		return commentsNews;
	}



	public void setCommentsNews(TNews commentsNews) {
		this.commentsNews = commentsNews;
	}



	public String getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(String commentsId) {
		this.commentsId = commentsId;
	}

	public String getCommentsContent() {
		return commentsContent;
	}

	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}

	public Date getCommentsTime() {
		return commentsTime;
	}

	public void setCommentsTime(Date commentsTime) {
		this.commentsTime = commentsTime;
	}

	public Tourist getCommentsTourist() {
		return commentsTourist;
	}

	public void setCommentsTourist(Tourist commentsTourist) {
		this.commentsTourist = commentsTourist;
	}
	
	

}








