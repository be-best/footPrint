package com.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * �ο�ʵ��
 * @author 45��ը
 *
 */
@Entity
@Table(name="tourist",catalog="mysoccer")
public class Tourist {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String tId; //id
	private String tPassword; //����
	private String tName; //�ǳ�
	private String tEmail; //����
	private String tCollection; //�ղ�
	private String tPhoto; //ͷ��
	private Integer tCode; //״̬
	private String tGender; //�Ա�
	private String tAddress;//����
	private String tIntroduction;//���˼��
	
	@OneToMany(targetEntity = CollectionItem.class,mappedBy="collectionTourist")
	private List<CollectionItem> collectionList;
	/*private List<String> tCNewsCollectionList;//���ֲ���̬�ղ�
	private List<String> tShareCollectionList;//���������ղ�
	private List<String> tDiaryCollectionList;//��Ա�ռ��ղ�
	//private List<String> tANewsCollectionList;//�ٷ���Ѷ�ղ�
	private List<String> tTNewsCollectionList;//�ο���Ѷ�ղ�
*/	
	@OneToMany(targetEntity=TNews.class,mappedBy="newsTourist")
	private Map<String,TNews> tNews; //����
	
	@OneToMany(targetEntity=TourComments.class,mappedBy="commentsTourist")
	private Map<String,TourComments> tComments; //����
	
	public Tourist() {
		
	}

	
	public Tourist(String tId, String tPassword, String tName, String tEmail,
			String tCollection, String tPhoto, Integer tCode, String tGender,
			String tAddress, String tIntroduction, Map<String, TNews> tNews,
			Map<String, TourComments> tComments) {
		super();
		this.tId = tId;
		this.tPassword = tPassword;
		this.tName = tName;
		this.tEmail = tEmail;
		this.tCollection = tCollection;
		this.tPhoto = tPhoto;
		this.tCode = tCode;
		this.tGender = tGender;
		this.tAddress = tAddress;
		this.tIntroduction = tIntroduction;
		this.tNews = tNews;
		this.tComments = tComments;
	}


	
	public String gettName() {
		return tName;
	}




	public void settName(String tName) {
		this.tName = tName;
	}




	public String gettGender() {
		return tGender;
	}

	public void settGender(String tGender) {
		this.tGender = tGender;
	}

	public String gettAddress() {
		return tAddress;
	}

	public void settAddress(String tAddress) {
		this.tAddress = tAddress;
	}

	public String gettIntroduction() {
		return tIntroduction;
	}

	public void settIntroduction(String tIntroduction) {
		this.tIntroduction = tIntroduction;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String gettPassword() {
		return tPassword;
	}

	public void settPassword(String tPassword) {
		this.tPassword = tPassword;
	}

	public String gettEmail() {
		return tEmail;
	}

	public void settEmail(String tEmail) {
		this.tEmail = tEmail;
	}

	public String gettCollection() {
		return tCollection;
	}

	public void settCollection(String tCollection) {
		this.tCollection = tCollection;
	}

	public String gettPhoto() {
		return tPhoto;
	}

	public void settPhoto(String tPhoto) {
		this.tPhoto = tPhoto;
	}

	public Integer gettCode() {
		return tCode;
	}

	public void settCode(Integer tCode) {
		this.tCode = tCode;
	}

	
	public Map<String, TNews> gettNews() {
		return tNews;
	}

	public void settNews(Map<String, TNews> tNews) {
		this.tNews = tNews;
	}

	public Map<String, TourComments> gettComments() {
		return tComments;
	}

	public void settComments(Map<String, TourComments> tComments) {
		this.tComments = tComments;
	}
	
	
}




