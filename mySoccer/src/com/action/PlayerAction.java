package com.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.domain.Player;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IPlayerService;
import com.utils.UUIDUtils;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerAction extends ActionSupport {
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	private File playerPhoto; //���ڽ����ϴ����ļ�
	private String playerPhotoFileName; //ͷ��
	private String playerNumber; //�����˺�
	private String playerPassword; //����
	private String playerName; //�ǳ�
	private String playerIntroduction; //���
	private String playerRealName; //��ʵ����
	private String playerBirthday; //����
	private String playerGender; //�Ա�
	private String playerAddress; //��ַ
	private String playerTelePhone; //��Ա��ϵ�绰
	private String playerLocation;//����λ��
	

	public String getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(String playerLocation) {
		this.playerLocation = playerLocation;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public File getPlayerPhoto() {
		return playerPhoto;
	}

	public void setPlayerPhoto(File playerPhoto) {
		this.playerPhoto = playerPhoto;
	}

	public String getPlayerPhotoFileName() {
		return playerPhotoFileName;
	}

	public void setPlayerPhotoFileName(String playerPhotoFileName) {
		this.playerPhotoFileName = playerPhotoFileName;
	}

	public String getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(String playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerIntroduction() {
		return playerIntroduction;
	}

	public void setPlayerIntroduction(String playerIntroduction) {
		this.playerIntroduction = playerIntroduction;
	}

	public String getPlayerRealName() {
		return playerRealName;
	}

	public void setPlayerRealName(String playerRealName) {
		this.playerRealName = playerRealName;
	}

	public String getPlayerBirthday() {
		return playerBirthday;
	}

	public void setPlayerBirthday(String playerBirthday) {
		this.playerBirthday = playerBirthday;
	}

	public String getPlayerGender() {
		return playerGender;
	}

	public void setPlayerGender(String playerGender) {
		this.playerGender = playerGender;
	}

	public String getPlayerAddress() {
		return playerAddress;
	}

	public void setPlayerAddress(String playerAddress) {
		this.playerAddress = playerAddress;
	}

	public String getPlayerTelePhone() {
		return playerTelePhone;
	}

	public void setPlayerTelePhone(String playerTelePhone) {
		this.playerTelePhone = playerTelePhone;
	}

	//��ת���޸���Ա��Ϣ����
	@Action(value="JumpToUpdatePlayerInformation",results={@Result(name="success",location="/jsp/player/playerInformation.jsp")})
	public String JumpToUpdatePlayerInformation() {
		Player existPlayer = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		Player player = playerService.findPlayerById(existPlayer.getPid());
		ServletActionContext.getRequest().getSession().setAttribute("updatePlayer", player);
		return SUCCESS;
	}
	
	//�޸���Ա��Ϣ
	@Action(value="updatePlayerInformation",results={@Result(name="success",location="/jsp/success.jsp")})
	public String updatePlayerInformation() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		if(playerPhotoFileName != null) { //ֻ�е����ڱ��л�ȡ����Ƭʱ�Ŵ�������Ϊ��ʱ�����
			playerPhotoFileName = UUIDUtils.getUUID()+playerPhotoFileName;
			//�����ϴ��ļ�
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/pPhoto"),playerPhotoFileName);
			try {
				FileUtils.copyFile(playerPhoto, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String clubPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/pPhoto/"+playerPhotoFileName;
			player.setpPhoto(clubPhotosrc);	
	
		}
		//System.out.println(playerName+playerRealName+playerPassword+"-------------------------------");
		//System.out.println("----------------------------------------------------------");
		
		player.setpName(playerName);
		player.setPassword(playerPassword);
		player.setpAddress(playerAddress);
		player.setpBirthday(playerBirthday);
		player.setpIntroduction(playerIntroduction);
		player.setpGender(playerGender);
		player.setpRealName(playerRealName);
		player.setpTelePhone(playerTelePhone);
		player.setpPosition(playerLocation);
		
		
		playerService.updatePlayer(player);
		
		
		ServletActionContext.getRequest().setAttribute("msg", "��ϲ���޸���Ϣ�ɹ���");
		return SUCCESS;
	}
}
















