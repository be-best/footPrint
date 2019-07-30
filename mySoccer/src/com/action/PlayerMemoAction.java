package com.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.domain.PlayerMemo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.IPlayerService;

/**
 * ��Ա������
 * @author 45��ը
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerMemoAction extends ActionSupport implements ModelDriven<PlayerMemo> {
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	private PlayerMemo playerMemo = new PlayerMemo();
	@Override
	public PlayerMemo getModel() {
		// TODO Auto-generated method stub
		return playerMemo;
	}
	
	//����������
	@Action(value="writeNewPlayerMemo",results={@Result(name="success",location="findPlayerMemoByPlayer",type="redirectAction")})
	public String writeNewPlayerMemo() {
		playerMemo.setPlayerMemoFlag(0); // ��δ���
		playerMemo.setPlayerMemoTime(new Date());
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		playerMemo.setMemoPlayer(player);
		playerService.writeNewPlayerMemo(playerMemo);
		return SUCCESS;
	}
	
	//������ʾ����(��Ϊ��������)
	@Action(value="findPlayerMemoByPlayer",results={@Result(name="success",location="/jsp/player/playerMemo.jsp")})
	public String findPlayerMemoByPlayer() {
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		List<PlayerMemo> list = playerService.findPlayerMemoByPlayer(player);
		List<PlayerMemo> undonePlayerMemoList = new ArrayList<PlayerMemo>();
		List<PlayerMemo> donePlayerMemoList = new ArrayList<PlayerMemo>();

		for (PlayerMemo playerMemo : list) {
			if(playerMemo.getPlayerMemoFlag() == 0) {
				undonePlayerMemoList.add(playerMemo);
			} else {
				donePlayerMemoList.add(playerMemo);
			}
		}
		
		ServletActionContext.getRequest().setAttribute("undonePlayerMemoList",undonePlayerMemoList );
		ServletActionContext.getRequest().setAttribute("donePlayerMemoList",donePlayerMemoList );
		return SUCCESS;
	}

	//����playMemoΪ�����
	@Action(value="setPlayerMemoFlag",results={@Result(name="success",location="findPlayerMemoByPlayer",type="redirectAction")})
	public String setPlayerMemoFlag() {
		String playerMemoId = ServletActionContext.getRequest().getParameter("playerMemoId");
		PlayerMemo playerMemo = playerService.findPlayerMemoByMemoId(playerMemoId);
		playerMemo.setPlayerMemoFlag(1);
		playerService.updatePlayerMemo(playerMemo);
		return SUCCESS;
	}
	
	//ɾ��playerMemo
	@Action(value="deletePlayerMemoById",results={@Result(name="success",location="findPlayerMemoByPlayer",type="redirectAction")})
	public String deletePlayerMemoById() {
		String playerMemoId = ServletActionContext.getRequest().getParameter("playerMemoId");
		playerService.deletePlayerMemoById(playerMemoId);
		return SUCCESS;
	}
	
}












