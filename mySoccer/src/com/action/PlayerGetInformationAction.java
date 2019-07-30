package com.action;

import java.io.IOException;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.domain.Club;
import com.domain.Diary;
import com.domain.Player;
import com.domain.PlayerDeeds;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IClubService;
import com.service.IPlayerService;

/**
 * ��ȡ��Ա��ҳ��Ϣ
 * @author 45��ը
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerGetInformationAction extends ActionSupport {
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	@Action(value="getplayerInformation",results={@Result(name="success",location="/jsp/player/playerFirstPage.jsp")})
	public String getplayerInformation() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		if(pid.length() == 1) {
			//���û��ȡ��pid��˵����Ա�ǲ鿴�Լ�����ҳ(�鿴�Լ���ҳʱ���ô�������pidΪ0)
			Player player = (Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
			
			pid = player.getPid();
		}
		
		//������Ա����ֲ�û������������õ��Ǿ��ֲ�ID��ǰ̨��ʾ��ʱ����Ҫ��ʾ���ֲ�����
		Player infoPlayer = playerService.findPlayerById(pid);
		String clubId = infoPlayer.getpClubId();
		if(clubId != null) {
			Club club = clubService.findClubNameByClubId(clubId);
			infoPlayer.setpClubName(club.getClubName());
			
		}
		//System.out.println(infoPlayer.getPid()+infoPlayer.getpRealName());
		ServletActionContext.getRequest().getSession().setAttribute("infoPlayer", infoPlayer);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
	}
	
	//Ѱ��ʱ�����¼�д����ҳ������
	@Action(value="findPlayerDeedsForInfo")
	public void findPlayerDeedsForInfo() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		if(pid.length() == 1) {
			Player player = (Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
			pid = player.getPid();
		}
		Player infoPlayer = playerService.findPlayerById(pid);
		List<PlayerDeeds> infoPlayerDeedList = playerService.findAllPlayerDeedsByPlayer(infoPlayer);
		PropertyFilter filter = new PropertyFilter() {

			@Override
			public boolean apply(Object arg0, String filedName, Object arg2) {
				if("deedsPlayer".equalsIgnoreCase(filedName)) {
					return false; //����һ�Զ࣬���һʱҪ��ʵ����Ϣ���˵���������deedsPlayer���˵���
				}
				return true;
			}
			
		};
		String json = JSONArray.toJSONString(infoPlayerDeedList,filter,SerializerFeature.DisableCircularReferenceDetect);
		//String json = JSONArray.toJSONString(infoPlayerDeedList);
		try {
			//��������Ӧ��ȥ
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");//�ı���
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Ѱ�������ʾ����ҳ������
	@Action(value="findDiaryForInfo")
	public void findDiaryForInfo() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		if(pid.length() == 1) {
			Player player = (Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
			pid = player.getPid();
		}
		Player infoPlayer = playerService.findPlayerById(pid);
		List<Diary> infoList = playerService.findDiaryForInfo(infoPlayer);
		PropertyFilter filter = new PropertyFilter() {

			@Override
			public boolean apply(Object arg0, String filedName, Object arg2) {
				if("diaryPlayer".equalsIgnoreCase(filedName)) {
					return false; //����һ�Զ࣬���һʱҪ��ʵ����Ϣ���˵���������diaryPlayer���˵���
				}
				return true;
			}
			
		};
		String json = JSONArray.toJSONString(infoList,filter,SerializerFeature.DisableCircularReferenceDetect);
		try {
			//��������Ӧ��ȥ
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");//�ı���
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}















