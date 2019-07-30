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

import com.dao.IClubDao;
import com.domain.Announcement;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Player;
import com.domain.Share;

@Repository("clubDao")
public class ClubDaoImpl extends HibernateDaoSupport implements IClubDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}


	//���ֲ�ע��
	@Override
	public void clubRegist(Club club) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(club);
	}

	//���ֲ���¼
	@Override
	public Club clubLogin(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from club where clubName = ? and clubPassword = ?");
		sqlQuery.setParameter(0, club.getClubName());
		sqlQuery.setParameter(1, club.getClubPassword());
		sqlQuery.addEntity(Club.class);
		Club newClub = (Club) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return newClub;
	}


	//�����¹���
	@Override
	public void newAnnouncement(Announcement annoucement) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(annoucement);
	}

	//��ѯ��������
	@Override
	public Integer findTotalByCid(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from announcement where a_club = ?");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
		
	}

	//��ҳ��ѯ����
	@Override
	public List<Announcement> findPageByCid(Club club, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Announcement where a_club = :clubId order by aTime desc");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Announcement> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//�鿴��������
	@Override
	public Announcement findAnnouncementContent(String aId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Announcement.class, aId);
	}

	//ɾ������
	@Override
	public void deleteAnnouncement(String aId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Announcement.class,aId));
	}

	//��ӽ���
	@Override
	public void addNewCoach(Coach coach) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(coach);
	}

	//��ѯ��������
	@Override
	public Integer findTotalCoachByCid(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from coach where c_club = ?");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//��ҳ��ѯ����
	@Override
	public List<Coach> findAllCoach(Club club, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Coach where c_club = :clubId order by coachId desc");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Coach> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
		
	}

	//����������ֲ������
	@Override
	public void deleteCoachFromClub(String coachId) {
		// TODO Auto-generated method stub
		Coach coach = this.getHibernateTemplate().get(Coach.class, coachId);
		coach.setCoachClub(null);
		this.getHibernateTemplate().update(coach);
	}

	//д������
	@Override
	public void writeCNews(CNews cnews) {
		// TODO Auto-generated method stub
		cnews.setViewNumber(1);
		this.getHibernateTemplate().save(cnews);
	}

	//��ѯ��������
	@Override
	public Integer findTotalCNewsByCid(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from cnews where n_club = ?");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//��ҳ��ʾ��������
	@Override
	public List<CNews> showAllCNews(Club club, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CNews where n_club = :clubId order by newsDate desc");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<CNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//��ȡ���ֲ�����
	@Override
	public Club findClubNameByClubId(String clubId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Club.class, clubId);
	}

	//��ȡ��������
	@Override
	public CNews findCNewsArtical(String newsId) {
		// TODO Auto-generated method stub
		CNews cNews = this.getHibernateTemplate().get(CNews.class, newsId);
		Integer number = cNews.getViewNumber();
		number++;
		cNews.setViewNumber(number);
		this.getHibernateTemplate().update(cNews);
		return cNews;
	}

	//ɾ������
	@Override
	public void deleteCNews(String newsId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(CNews.class, newsId));
	}

	//�޸���������
	@Override
	public void updateCNewsArtical(CNews cnews) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(cnews);
	}

	//���ֲ�������¼
	@Override
	public Coach coachLogin(Coach coach) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from coach where coachNumber = ? and coachPassword = ?");
		sqlQuery.setParameter(0, coach.getCoachNumber());
		sqlQuery.setParameter(1, coach.getCoachPassword());
		sqlQuery.addEntity(Coach.class);
		Coach newCoach = (Coach) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return newCoach;
	}

	//Ѱ����Ա
	@Override
	public Player findPlayerByPid(String pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Player.class, pid);
	}

	//������Ա
	@Override
	public void updatePlayer(Player rePlayer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(rePlayer);
	}

	//����δ�н�����Ա����
	@Override
	public Integer findTotalPlayerWithoutCoach(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from player where pClubId = ? and p_coach is null");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//����δ�н�����Ա
	@Override
	public List<Player> findPlayerWithoutCoach(Club club, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player where pClubId = :clubId and p_coach is null order by pid desc");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//Ѱ��δ������Ա
	@Override
	public List<Player> findUnreceivePlayer(Club club, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player where pApplyId = :clubId  order by pid desc");
		//System.out.println("δ������111-------------------------");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//Ѱ�����н�����Ա
	@Override
	public List<Player> findPlayerWithCoach(Club club, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player where pClubId = :clubId and p_coach is not null order by pid desc");
		//System.out.println("�ѱ�����------------------------");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//Ѱ��coach
	@Override
	public Coach findCoachByCoachId(String coachId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Coach.class, coachId);
	}

	//Ѱ���н�������Ա����
	@Override
	public Integer findTotalWithCoachPlayer(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from player where pClubId = ? and p_coach is not null");
		//System.out.println("���н���------------------------");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//Ѱ��δ�����յ���Ա����
	@Override
	public Integer findTotaUnReceivelPlayer(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from player where pApplyId = ?");
		//System.out.println("δ������-------------------------------");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//Ѱ�����еĽ���
	@Override
	public List<Coach> findAllCoachByClub(Club club) {
		// TODO Auto-generated method stub
		//System.out.println(club.getCid()+"---------------------------------");
		return (List<Coach>) this.getHibernateTemplate().find("from Coach c where c.coachClub = ?", club);
	}

	//Ѱ��δ���յĽ�������
	@Override
	public Integer findTotaUnReceivelCoach(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from coach where coachApplyId = ?");
		//System.out.println("δ������-------------------------------");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//��ʾδ���յĽ���
	@Override
	public List<Coach> findUnreceiveCoach(Club club, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Coach where coachApplyId = :clubId  order by coachId desc");
		//System.out.println("�ѱ�����------------------------");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Coach> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//���½���
	@Override
	public void updateCoach(Coach reCoach) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(reCoach);
	}

	//�޸ľ��ֲ���Ϣ
	@Override
	public void updateClub(Club club) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(club);
	}

	//��ȡ���ֲ�������ʾ����ҳ��
	@Override
	public List<Announcement> findAnnouncementForInfo(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Announcement where a_club = :clubId order by aTime desc");
		query.setParameter("clubId", club.getCid());
		List<Announcement> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//���Ҿ��ֲ�������ʾ����ҳ��
	@Override
	public List<CNews> findCNewsForInfo(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CNews where n_club = :clubId order by newsDate desc");
		query.setParameter("clubId", club.getCid());
		List<CNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
		
	}

	
	//�������ֲ��Ҿ��ֲ�
	@Override
	public Club findClubByName(String clubName) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from club where clubName = ?");
		sqlQuery.setParameter(0, clubName);
		sqlQuery.addEntity(Club.class);
		Club newClub = (Club) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return newClub;
	}

	//���ұ�������ҳ�ľ��ֲ�
	@Override
	public List<Club> findMainPageClub() {
		// TODO Auto-generated method stub
		return (List<Club>) this.getHibernateTemplate().find("from Club c where c.clubMainPage = ?", 1);
	}


	


	
	

}










