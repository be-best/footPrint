package com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * �ο��ղ�
 * @author 45��ը
 *
 */
@Entity
@Table(name="collectionItem",catalog="mysoccer")
public class CollectionItem {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String collectionId;
	

	private String collectionTourist; //�ο�
	

	private String collectionCNews; //���ֲ���̬
	

	private String collectionShare; //��������
	

	private String collectionDiary; //��Ա�ռ�
	

	private String collectionTNews; //�ο���Ѷ
	

	private String collectionANews; //�ٷ�����
	
	/*@ManyToOne(targetEntity=Tourist.class)
	@JoinColumn(name="c_tourist")
	private Tourist collectionTourist; //�ο�
	
	@ManyToOne(targetEntity=CNews.class)
	@JoinColumn(name="c_cnews")
	private CNews collectionCNews; //���ֲ���̬
	
	@ManyToOne(targetEntity=Share.class)
	@JoinColumn(name="c_share")
	private Share collectionShare; //��������
	
	@ManyToOne(targetEntity=Diary.class)
	@JoinColumn(name="c_diary")
	private Diary collectionDiary; //��Ա�ռ�
	
	@ManyToOne(targetEntity=TNews.class)
	@JoinColumn(name="c_tnews")
	private TNews collectionTNews; //�ο���Ѷ
	
	@ManyToOne(targetEntity=ANews.class)
	@JoinColumn(name="c_anews")
	private ANews collectionANews; //�ٷ�����
*/	
	public CollectionItem() {
		
	}
	
	

	public CollectionItem(String collectionId, String collectionTourist,
			String collectionCNews, String collectionShare,
			String collectionDiary, String collectionTNews,
			String collectionANews) {
		super();
		this.collectionId = collectionId;
		this.collectionTourist = collectionTourist;
		this.collectionCNews = collectionCNews;
		this.collectionShare = collectionShare;
		this.collectionDiary = collectionDiary;
		this.collectionTNews = collectionTNews;
		this.collectionANews = collectionANews;
	}



	public String getCollectionId() {
		return collectionId;
	}



	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}



	public String getCollectionTourist() {
		return collectionTourist;
	}



	public void setCollectionTourist(String collectionTourist) {
		this.collectionTourist = collectionTourist;
	}



	public String getCollectionCNews() {
		return collectionCNews;
	}



	public void setCollectionCNews(String collectionCNews) {
		this.collectionCNews = collectionCNews;
	}



	public String getCollectionShare() {
		return collectionShare;
	}



	public void setCollectionShare(String collectionShare) {
		this.collectionShare = collectionShare;
	}



	public String getCollectionDiary() {
		return collectionDiary;
	}



	public void setCollectionDiary(String collectionDiary) {
		this.collectionDiary = collectionDiary;
	}



	public String getCollectionTNews() {
		return collectionTNews;
	}



	public void setCollectionTNews(String collectionTNews) {
		this.collectionTNews = collectionTNews;
	}



	public String getCollectionANews() {
		return collectionANews;
	}



	public void setCollectionANews(String collectionANews) {
		this.collectionANews = collectionANews;
	}



}
