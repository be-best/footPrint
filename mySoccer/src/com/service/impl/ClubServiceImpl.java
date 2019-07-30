package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IClubDao;
import com.domain.Announcement;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.PageBean;
import com.domain.Player;
import com.service.IClubService;

@Service("clubService")
@Transactional
public class ClubServiceImpl implements IClubService{
	@Autowired
	@Qualifier("clubDao")
	private IClubDao clubDao;

	//���ֲ�ע��
	@Override
	public void clubRegist(Club club) {
		// TODO Auto-generated method stub
		clubDao.clubRegist(club);
	}

	//���ֲ���¼
	@Override
	public Club clubLogin(Club club) {
		// TODO Auto-generated method stub
		return clubDao.clubLogin(club);
	}

	//�����¹���
	@Override
	public void newAnnouncement(Announcement annoucement) {
		// TODO Auto-generated method stub
		clubDao.newAnnouncement(annoucement);
	}

	//��ѯ���й���
	@Override
	public PageBean<Announcement> findAllAnnouncement(
			Integer currPage, Club club) {
		PageBean<Announcement> pageBean = new PageBean<Announcement>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 6;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = clubDao.findTotalByCid(club);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Announcement> list = clubDao.findPageByCid(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//��ѯ��������
	@Override
	public Announcement findAnnouncementContent(String aId) {
		// TODO Auto-generated method stub
		return clubDao.findAnnouncementContent(aId);
	}

	//ɾ������
	@Override
	public void deleteAnnouncement(String aId) {
		// TODO Auto-generated method stub
		clubDao.deleteAnnouncement(aId);
	}
	
	//��ӽ���
	@Override
	public void addNewCoach(Coach coach) {
		// TODO Auto-generated method stub
		clubDao.addNewCoach(coach);
		
	}

	//��ѯ���н���
	@Override
	public PageBean<Coach> findAllCoach(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<Coach> pageBean = new PageBean<Coach>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 6;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = clubDao.findTotalCoachByCid(club);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Coach> list = clubDao.findAllCoach(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//����������ֲ������
	@Override
	public void deleteCoachFromClub(String coachId) {
		// TODO Auto-generated method stub
		clubDao.deleteCoachFromClub(coachId);
	}

	//����������
	@Override
	public void writeCNews(CNews cnews) {
		// TODO Auto-generated method stub
		clubDao.writeCNews(cnews);
	}
	//��ʾ��������
	@Override
	public PageBean<CNews> showAllCNews(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<CNews> pageBean = new PageBean<CNews>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = clubDao.findTotalCNewsByCid(club);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<CNews> list = clubDao.showAllCNews(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	//���Ҿ��ֲ�����
	@Override
	public Club findClubNameByClubId(String clubId) {
		// TODO Auto-generated method stub
		return clubDao.findClubNameByClubId(clubId);
	}

	//��ʾ��������
	@Override
	public CNews findCNewsArtical(String newsId) {
		// TODO Auto-generated method stub
		return clubDao.findCNewsArtical(newsId);
	}

	//ɾ������
	@Override
	public void deletCNews(String newsId) {
		// TODO Auto-generated method stub
		clubDao.deleteCNews(newsId);
	}

	//�޸���������
	@Override
	public void updateCNewsArtical(CNews cnews) {
		// TODO Auto-generated method stub
		clubDao.updateCNewsArtical(cnews);
	}

	//���ֲ�������¼
	@Override
	public Coach coachLogin(Coach coach) {
		// TODO Auto-generated method stub
		return clubDao.coachLogin(coach);
	}

	//Ѱ����Ա
	@Override
	public Player findPlayerByPid(String pid) {
		// TODO Auto-generated method stub
		return clubDao.findPlayerByPid(pid);
	}

	//������Ա
	@Override
	public void updatePlayer(Player rePlayer) {
		// TODO Auto-generated method stub
		clubDao.updatePlayer(rePlayer);
	}

	//��ʾδ�н�����Ա
	@Override
	public PageBean<Player> findPlayerWithoutCoach(Integer currPage,
			Club club) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 9;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = clubDao.findTotalPlayerWithoutCoach(club);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = clubDao.findPlayerWithoutCoach(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//Ѱ�ҽ���
	@Override
	public Coach findCoachByCoachId(String coachId) {
		// TODO Auto-generated method stub
		return clubDao.findCoachByCoachId(coachId);
	}

	//Ѱ��δ������Ա
	@Override
	public PageBean<Player> findUnreceivePlayer(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 9;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = clubDao.findTotaUnReceivelPlayer(club);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = clubDao.findUnreceivePlayer(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//Ѱ�����н�����Ա
	@Override
	public PageBean<Player> findPlayerWithCoach(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 9;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = clubDao.findTotalWithCoachPlayer(club);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = clubDao.findPlayerWithCoach(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//Ѱ�����н���
	@Override
	public List<Coach> findAllCoachByClub(Club club) {
		// TODO Auto-generated method stub
		return clubDao.findAllCoachByClub(club);
	}

	//Ѱ��δ���յĽ���
	@Override
	public PageBean<Coach> findUnReceiveCoach(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<Coach> pageBean = new PageBean<Coach>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 9;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = clubDao.findTotaUnReceivelCoach(club);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Coach> list = clubDao.findUnreceiveCoach(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//���½���
	@Override
	public void updateCoach(Coach reCoach) {
		// TODO Auto-generated method stub
		clubDao.updateCoach(reCoach);
	}

	//�޸ľ��ֲ���Ϣ
	@Override
	public void updateClub(Club club) {
		// TODO Auto-generated method stub
		clubDao.updateClub(club);
	}

	//��ȡ���ֲ�������ʾ����ҳ��
	@Override
	public List<Announcement> findAnnouncementForInfo(Club club) {
		// TODO Auto-generated method stub
		return clubDao.findAnnouncementForInfo(club);
	}

	//���Ҿ��ֲ���̬
	@Override
	public List<CNews> findCNewsForInfo(Club club) {
		// TODO Auto-generated method stub
		return clubDao.findCNewsForInfo(club);
	}

	//���Ҿ��ֲ�
	@Override
	public Club findClubByName(String clubName) {
		// TODO Auto-generated method stub
		return clubDao.findClubByName(clubName);
	}

	//���Һͱ�������ҳ�ľ��ֲ�
	@Override
	public List<Club> findMainPageClub() {
		// TODO Auto-generated method stub
		return clubDao.findMainPageClub();
	}

	
	
	
	
}
