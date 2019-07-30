package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ICoachDao;
import com.dao.IPlayerDao;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.PlayerDeeds;
import com.domain.PlayerMemo;
import com.domain.Report;
import com.domain.Share;
import com.service.IPlayerService;
@Service("playerService")
@Transactional
public class PlayerServiceImpl implements IPlayerService {
	@Autowired
	@Qualifier("playerDao")
	private IPlayerDao playerDao;
	
	//����playerMemo
	@Override
	public void writeNewPlayerMemo(PlayerMemo playerMemo) {
		// TODO Auto-generated method stub
		playerDao.writeNewPlayerMemo(playerMemo);
	}
	
	//����player����memo
	@Override
	public List<PlayerMemo> findPlayerMemoByPlayer(Player player) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerMemoByPlayer(player);
	}

	//����playerMemoΪ�����
	@Override
	public PlayerMemo findPlayerMemoByMemoId(String playerMemoId) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerMemoByMemoId(playerMemoId);
	}

	//����playerMemo
	@Override
	public void updatePlayerMemo(PlayerMemo playerMemo) {
		// TODO Auto-generated method stub
		playerDao.updatePlayerMemo(playerMemo);
	}

	//ɾ��playerMemo
	@Override
	public void deletePlayerMemoById(String playerMemoId) {
		// TODO Auto-generated method stub
		playerDao.deletePlayerMemoById(playerMemoId);
	}

	//����ʱ�����¼�
	@Override
	public void createPlayerDeeds(PlayerDeeds playerDeeds) {
		// TODO Auto-generated method stub
		playerDao.createPlayerDeeds(playerDeeds);
	}

	//ɾ��ʱ�����¼�
	@Override
	public void deletePlayerDeedsById(String playerDeedsId) {
		// TODO Auto-generated method stub
		playerDao.deletePlayerDeedsById(playerDeedsId);
	}

	//������Ա��ʱ�����¼�
	@Override
	public List<PlayerDeeds> findAllPlayerDeedsByPlayer(Player player) {
		// TODO Auto-generated method stub
		return playerDao.findAllPlayerDeedsByPlayer(player);
	}

	//Ѱ����Ա
	@Override
	public Player findPlayerById(String pid) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerById(pid);
	}

	//������Ա
	@Override
	public void updatePlayer(Player player) {
		// TODO Auto-generated method stub
		playerDao.updatePlayer(player);
	}

	//��Աд�ռ�
	@Override
	public void createDiary(Diary diary) {
		// TODO Auto-generated method stub
		playerDao.createDiary(diary);
	}

	//Ѱ�����е��ռ�
	@Override
	public PageBean<Diary> findAllDiary(Integer currPage, Player player) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = playerDao.findTotalDiaryByPlayer(player);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = playerDao.findAllDiary(player,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//�����ռ�
	@Override
	public Diary findDiaryById(String diaryId) {
		// TODO Auto-generated method stub
		return playerDao.findDiaryById(diaryId);
	}

	//ɾ���ռ�
	@Override
	public void deleteDiaryById(String diaryId) {
		// TODO Auto-generated method stub
		playerDao.deleteDiaryById(diaryId);
	}

	//����Ȩ�޲����ռ�
	@Override
	public PageBean<Diary> findPageDiary(int i, Integer currPage,
			Player player) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = null;
		if(i == 1) {
			//���Լ��ɼ�
			totalCount = playerDao.findTotalDiary(1,player);
		}
		else  {
			//�������ѿɼ�(i==2)
			totalCount = playerDao.findTotalDiary(2,player);
		}
		
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = null;
		if(i == 1) {
			//���Լ��ɼ�
			 list = playerDao.findPageDiary(1,player,begin,pageSize);
		}
		else  { 
			//�������ѿɼ�(i==2)
			 list = playerDao.findPageDiary(2,player,begin,pageSize);
		}
		
		pageBean.setList(list);
		return pageBean;
	}

	//���Ҷ����ռ�
	@Override
	public PageBean<Diary> findTeammatesDiary(Integer currPage, Player player) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = playerDao.findTotalTeammatesDiary(player);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = playerDao.findTeammatesDiary(player,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//Ѱ����Ա�ռ���ʾ����ҳ
	@Override
	public List<Diary> findDiaryForInfo(Player infoPlayer) {
		// TODO Auto-generated method stub
		return playerDao.findDiaryForInfo(infoPlayer);
	}

	//ģ����ѯ���ֲ�
	@Override
	public List<Club> fuzzyFindClub(String clubFuzzyName) {
		// TODO Auto-generated method stub
		return playerDao.fuzzyFindClub(clubFuzzyName);
	}

	//������Ա
	@Override
	public Player findPlayerByNumber(String playerNumber) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerByNumber(playerNumber);
	}

	//�½���Ա����
	@Override
	public void createPlayerReport(Report report) {
		// TODO Auto-generated method stub
		playerDao.createPlayerReport(report);
	}

	//������Ա����
	@Override
	public Report findPlayerReportByPid(String pid) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerReportByPid(pid);
	}

	//������Ա����
	@Override
	public void updatePlayerReport(Report oldReport) {
		// TODO Auto-generated method stub
		playerDao.updatePlayerReport(oldReport);
	}

	@Override
	public void updateDiary(Diary diary) {
		// TODO Auto-generated method stub
		playerDao.updateDiary(diary);
	}

	@Override
	public List<Player> findMainPagePlayer() {
		// TODO Auto-generated method stub
		return playerDao.findMainPagePlayer();
	}
	
	

}
