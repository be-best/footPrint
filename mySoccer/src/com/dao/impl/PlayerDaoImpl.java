package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dao.IPlayerDao;
import com.domain.Announcement;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Diary;
import com.domain.Player;
import com.domain.PlayerDeeds;
import com.domain.PlayerMemo;
import com.domain.Report;
import com.domain.Share;
import com.service.IPlayerService;

@Repository("playerDao")
public class PlayerDaoImpl extends HibernateDaoSupport implements IPlayerDao {
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	//����������
	@Override
	public void writeNewPlayerMemo(PlayerMemo playerMemo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(playerMemo);
	}

	//����player����memo
	@Override
	public List<PlayerMemo> findPlayerMemoByPlayer(Player player) {
		// TODO Auto-generated method stub
		return (List<PlayerMemo>) this.getHibernateTemplate().find("from PlayerMemo p where p.memoPlayer = ? order by playerMemoTime desc", player);
	}

	//����playerMemoId����memo
	@Override
	public PlayerMemo findPlayerMemoByMemoId(String playerMemoId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(PlayerMemo.class, playerMemoId);
	}

	//����playerMemo
	@Override
	public void updatePlayerMemo(PlayerMemo playerMemo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(playerMemo);
	}

	//ɾ��playerMemo
	@Override
	public void deletePlayerMemoById(String playerMemoId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(PlayerMemo.class, playerMemoId));
	}

	//����ʱ�����¼�
	@Override
	public void createPlayerDeeds(PlayerDeeds playerDeeds) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(playerDeeds);
	}

	//ɾ��ʱ�����¼�
	@Override
	public void deletePlayerDeedsById(String playerDeedsId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(PlayerDeeds.class, playerDeedsId));
	}

	//�������е���Ա�¼�
	@Override
	public List<PlayerDeeds> findAllPlayerDeedsByPlayer(Player player) {
		// TODO Auto-generated method stub
		return (List<PlayerDeeds>) this.getHibernateTemplate().find("from PlayerDeeds pd where pd.deedsPlayer = ? order by pd.playerDeedsTitleTime desc", player);
	}

	//������Ա
	@Override
	public Player findPlayerById(String pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Player.class, pid);
	}

	//�޸���Ա
	@Override
	public void updatePlayer(Player player) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(player);
	}

	//д�ռ�
	@Override
	public void createDiary(Diary diary) {
		// TODO Auto-generated method stub
		diary.setViewNumber(1);
		this.getHibernateTemplate().save(diary);
	}

	//Ѱ���ռ�����
	@Override
	public Integer findTotalDiaryByPlayer(Player player) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from diary where d_player = ?");
		sqlQuery.setParameter(0, player.getPid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//Ѱ�����е��ռ�
	@Override
	public List<Diary> findAllDiary(Player player, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary where d_player = :playerId order by diaryDate desc");
		query.setParameter("playerId", player.getPid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//�����ռ�
	@Override
	public Diary findDiaryById(String diaryId) {
		// TODO Auto-generated method stub
		Diary diary = this.getHibernateTemplate().get(Diary.class, diaryId);
		Integer number = diary.getViewNumber();
		number++;
		diary.setViewNumber(number);
		this.getHibernateTemplate().update(diary);
		return diary;
	}

	//ɾ���ռ�
	@Override
	public void deleteDiaryById(String diaryId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Diary.class, diaryId));
	}

	//�������������ռ���Ŀ
	@Override
	public Integer findTotalDiary(int i, Player player) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = null;
		if(i == 1) { // ���Լ��ɼ�
			sqlQuery = session.createSQLQuery("select count(*) from diary where d_player = ? and diaryPermission = ?");
		} else { //�������ѿɼ�
			sqlQuery = session.createSQLQuery("select count(*) from diary where d_player = ? and diaryPermission = ?");
		}                                  //select count(*) from diary where d_player in (select pid from player where p_coach = ?) and diaryPermission != ?;
		sqlQuery.setParameter(0, player.getPid());
		sqlQuery.setParameter(1, i);
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//�������������ռ�
	@Override
	public List<Diary> findPageDiary(int i, Player player, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = null;
		if(i == 1) {
			query = session.createQuery("from Diary where d_player = :playerId and diaryPermission = :permissionId order by diaryDate desc");
			
		} else {
			query = session.createQuery("from Diary where d_player = :playerId and diaryPermission = :permissionId order by diaryDate desc");
		}
		query.setParameter("playerId", player.getPid());
		query.setParameter("permissionId", i);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//Ѱ�Ҷ����ռ�����
	@Override
	public Integer findTotalTeammatesDiary(Player player) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from diary where d_player in (select pid from player where p_coach = ? and pid != ?) and diaryPermission != ?;");
		sqlQuery.setParameter(0, player.getpCoach());
		sqlQuery.setParameter(1, player.getPid());
		sqlQuery.setParameter(2, 1); //ȥ�����Լ��ɼ�����
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		//System.out.println(totalCount);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//���Ҷ����ռ�
	@Override
	public List<Diary> findTeammatesDiary(Player player, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		//(��from Customer c where 1>(select count(o) from c.orders o)��)
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary where d_player in (select pid from Player where p_coach = :coachId and pid != :playerId) and diaryPermission != :permissionId order by diaryDate desc");
		query.setParameter("coachId", player.getpCoach());
		query.setParameter("playerId", player.getPid());
		query.setParameter("permissionId", 1);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//ѡ����Ա�ռ���ʾ����ҳ
	@Override
	public List<Diary> findDiaryForInfo(Player infoPlayer) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary where d_player = :playerId and diaryPermission = :permissionId order by diaryDate desc");
		query.setParameter("playerId", infoPlayer.getPid());
		query.setParameter("permissionId", 0);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//ģ����ѯ���ֲ�
	@Override
	public List<Club> fuzzyFindClub(String clubFuzzyName) {
		// TODO Auto-generated method stub
		//this.getHibernateTemplate().find("from bean.User u where u.name like ?", "%test%");
		//return (List<Club>) this.getHibernateTemplate().find("from Club c where c.clubName like ?",clubFuzzyName);
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Club where clubName like :clubName and clubCode = :clubCode order by cid desc");
		query.setParameter("clubName", clubFuzzyName);
		query.setParameter("clubCode", 1);
		List<Club> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//�����˺Ų�����Ա
	@Override
	public Player findPlayerByNumber(String playerNumber) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from player where playerNumber = ?");
		sqlQuery.setParameter(0, playerNumber);
		sqlQuery.addEntity(Club.class);
		Player player = (Player) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return player;
	}

	//������Ա����
	@Override
	public void createPlayerReport(Report report) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(report);
	}

	//������Ա����
	@Override
	public Report findPlayerReportByPid(String pid) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from report where r_player = ?");
		sqlQuery.setParameter(0, pid);
		sqlQuery.addEntity(Report.class);
		Report report = (Report) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return report;
	}

	//������Ա����
	@Override
	public void updatePlayerReport(Report oldReport) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(oldReport);
	}

	@Override
	public void updateDiary(Diary diary) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(diary);
	}

	//��������ҳ��Ա
	@Override
	public List<Player> findMainPagePlayer() {
		// TODO Auto-generated method stub
		return (List<Player>) this.getHibernateTemplate().find("from Player p where p.playerMainPage = ?", 1);
	}
}







