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
 * ����¼
 * @author 45��ը
 *
 */
@Entity
@Table(name="playermemo",catalog="mysoccer")
public class PlayerMemo {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String playerMemoId; //Id
	private String playerMemoContent; //����
	private Integer playerMemoFlag;//��־�Ƿ������
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date playerMemoTime; //ʱ��
	
	@ManyToOne(targetEntity=Player.class)
	@JoinColumn(name="memo_player")
	private Player memoPlayer; //��Ա

	public PlayerMemo() {
		
	}
	
	

	public PlayerMemo(String playerMemoId, String playerMemoContent,
			Integer playerMemoFlag, Date playerMemoTime, Player memoPlayer) {
		super();
		this.playerMemoId = playerMemoId;
		this.playerMemoContent = playerMemoContent;
		this.playerMemoFlag = playerMemoFlag;
		this.playerMemoTime = playerMemoTime;
		this.memoPlayer = memoPlayer;
	}



	public Integer getPlayerMemoFlag() {
		return playerMemoFlag;
	}



	public void setPlayerMemoFlag(Integer playerMemoFlag) {
		this.playerMemoFlag = playerMemoFlag;
	}



	public String getPlayerMemoId() {
		return playerMemoId;
	}

	public void setPlayerMemoId(String playerMemoId) {
		this.playerMemoId = playerMemoId;
	}

	public String getPlayerMemoContent() {
		return playerMemoContent;
	}

	public void setPlayerMemoContent(String playerMemoContent) {
		this.playerMemoContent = playerMemoContent;
	}

	public Date getPlayerMemoTime() {
		return playerMemoTime;
	}

	public void setPlayerMemoTime(Date playerMemoTime) {
		this.playerMemoTime = playerMemoTime;
	}

	public Player getMemoPlayer() {
		return memoPlayer;
	}

	public void setMemoPlayer(Player memoPlayer) {
		this.memoPlayer = memoPlayer;
	}
	
	

}
