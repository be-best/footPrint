package com.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dao.IAdminDao;
import com.domain.ANews;
import com.domain.AdPhoto;
import com.domain.Admin;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.CoachPlan;
import com.domain.Comments;
import com.domain.Diary;
import com.domain.Player;
import com.domain.Share;
import com.domain.TNews;
import com.domain.Tourist;

@Repository("adminDao")
public class AdminDaoImpl extends HibernateDaoSupport implements IAdminDao {
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	//����Ա��¼
	@Override
	public Admin adminLogin(Admin admin) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from admin where adminName = ? and adminPassword = ?");
		sqlQuery.setParameter(0,admin.getAdminName());
		sqlQuery.setParameter(1, admin.getAdminPassword());
		sqlQuery.addEntity(Admin.class);
		Admin existAdmin = (Admin) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return existAdmin;
	}

	//��ѯ����
	@Override
	public Integer findTotalNumber(int i) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = null;
		if(i == 1) {
			//��ѯ��Ա����
			sqlQuery = session.createSQLQuery("select count(*) from player");
		}
		if(i == 2) {
			//��ѯ��������
			sqlQuery = session.createSQLQuery("select count(*) from coach");
		}
		if(i == 3) {
			//��ѯδ��˾��ֲ�����
			sqlQuery = session.createSQLQuery("select count(*) from club where clubCode = ?");
			sqlQuery.setParameter(0, 0);
		}
		if(i == 4) {
			//��ѯ��ͨ�����ֲ�����
			sqlQuery = session.createSQLQuery("select count(*) from club where clubCode = ?");
			sqlQuery.setParameter(0, 1);
		}
		if(i == 5) {
			//��ѯ���ֲ���������
			sqlQuery = session.createSQLQuery("select count(*) from cnews");
		}
		if(i == 6) {
			//��ѯ��������
			sqlQuery = session.createSQLQuery("select count(*) from share");
		}
		if(i == 7) {
			//��ѯ��������
			sqlQuery = session.createSQLQuery("select count(*) from diary");
		}
		if(i == 8) {
			//��ѯ����Ա����
			sqlQuery = session.createSQLQuery("select count(*) from anews");
		}
		if(i == 9) {
			//��ѯ����ʾ����Ա�ռ�
			sqlQuery = session.createSQLQuery("select count(*) from diary where diaryPermission = ? ");
			sqlQuery.setParameter(0, 0);
		}
		if(i == 10) {
			//��ѯ�ο�����
			sqlQuery = session.createSQLQuery("select count(*) from tourist");
		}
		if(i == 11) {
			//��ѯ�ο���������
			sqlQuery = session.createSQLQuery("select count(*) from tnews");
		}
		
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//��ҳ��ѯ��Ա
	@Override
	public List<Player> findAllPlayer(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player order by pid desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//��ҳ��ѯ����
	@Override
	public List<Coach> findAllCoach(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Coach order by coachId desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Coach> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//ɾ����Ա
	@Override
	public void deletePlayerByAdmin(String pid) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Player.class, pid));
	}

	//ɾ������
	@Override
	public void deleteCoachByAdmin(String coachId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Coach.class, coachId));
	}

	//Ѱ�Ҿ��ֲ�
	@Override
	public List<Club> findClubByAdmin(Integer i,int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = null;
		if(i == 0) { //����δ���
			query = session.createQuery("from Club where clubCode= :code order by cid desc");
			query.setParameter("code", 0);			
		} 
		if(i == 1) { //���������
			query = session.createQuery("from Club where clubCode= :code order by cid desc");
			query.setParameter("code", 1);
		}
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Club> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//ɾ�����ֲ�
	@Override
	public void deleteClubByAdmin(String cid) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Club.class, cid));
	}

	//���Ҿ��ֲ����µ����н���
	@Override
	public List<Coach> findAllCoachByClub(Club club) {
		// TODO Auto-generated method stub
		return (List<Coach>) this.getHibernateTemplate().find("from Coach c where c.coachClub = ?", club);
	}

	//��ѯ���ֲ���������
	@Override
	public List<CNews> findAllCNewsByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CNews order by newsDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<CNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//Ѱ�ҽ�������
	@Override
	public List<Share> findAllShareByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Share order by shareDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Share> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//������Ա�ռ�
	@Override
	public List<Diary> findAllDiaryByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary order by diaryDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//�����µ�����
	@Override
	public void createANews(ANews anews) {
		// TODO Auto-generated method stub
		anews.setViewNumber(1);
		this.getHibernateTemplate().save(anews);
	}

	//��ѯ��������
	@Override
	public Integer findTotalANews(Admin admin) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from anews where anews_admin = ?");
		sqlQuery.setParameter(0, admin.getAdminId());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//��ѯһ������Ա�û�������
	@Override
	public List<ANews> findANews(Admin admin, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ANews where anews_admin = :adminId order by anewsDate desc");
		query.setParameter("adminId", admin.getAdminId());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<ANews> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//����ID����ANews
	@Override
	public ANews findANewsById(String anewsId) {
		// TODO Auto-generated method stub
		ANews aNews = this.getHibernateTemplate().get(ANews.class, anewsId);
		Integer number = aNews.getViewNumber();
		number++;
		aNews.setViewNumber(number);
		this.getHibernateTemplate().update(aNews);
		return this.getHibernateTemplate().get(ANews.class, anewsId);
	}

	//��������
	@Override
	public void updateANews(ANews anews) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(anews);
	}

	//ɾ������Ա����
	@Override
	public void deleteANewsById(String anewsId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(ANews.class, anewsId));
	}

	//��ӹ���Ա
	@Override
	public void addNewAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(admin);
	}

	//�������й���Ա
	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		return (List<Admin>) this.getHibernateTemplate().find("from Admin a order by a.adminId desc");
	}

	//ɾ������Ա
	@Override
	public void deleteAdmin(String adminId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Admin.class, adminId));
	}

	//����IDѰ�ҹ���Ա
	@Override
	public Admin findAdminById(String adminId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Admin.class, adminId);
	}

	//���¹���Ա
	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(admin);
	}

	//��ѯ���й���Ա�û�������
	@Override
	public List<ANews> findAllANews(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ANews order by anewsDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<ANews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;	
	}

	//Ѱ�ҿɼ����ռ�
	@Override
	public List<Diary> findDiaryShowByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary where diaryPermission= :permission order by diaryDate desc");
		query.setParameter("permission", 0);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//�����ο�
	@Override
	public List<Tourist> findTouristByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Tourist order by tId desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Tourist> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//ɾ���ο�
	@Override
	public void deleteTourist(String tId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Tourist.class, tId));
	}

	//�������е��ο���Ѷ
	@Override
	public List<TNews> findTNews(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TNews order by newsDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<TNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//����ֲ�ͼ
	@Override
	public void addAdPhoto(AdPhoto adPhoto) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(adPhoto);
	}

	//�����ֲ�ͼ
	@Override
	public List<AdPhoto> findAllAdPhoto() {
		// TODO Auto-generated method stub
		return (List<AdPhoto>) this.getHibernateTemplate().find("from AdPhoto order by adId desc");
	}

	//ɾ���ֲ�ͼ
	@Override
	public void deleteAdPhotoById(String adId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(AdPhoto.class, adId));
	}

	//����ID�����ֲ�ͼ
	@Override
	public AdPhoto findAdPhotoById(String adId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(AdPhoto.class, adId);
	}

	//�����ֲ�ͼ
	@Override
	public void updateAdPhoto(AdPhoto adPhoto) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(adPhoto);
	}

	@Override
	public Admin findAdminByName(String adminName) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from admin where adminName = ?");
		sqlQuery.setParameter(0, adminName);
		sqlQuery.addEntity(Club.class);
		Admin admin = (Admin) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return admin;
	}

	//������� 
	@Override
	public void addComments(Comments comments) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(comments);
	}

	//������������
	@Override
	public List<Comments> findCommentsByArticleId(int i, String id) {
		// TODO Auto-generated method stub
		if(i == 1) {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.cnewsId = ? order by c.commentsTime desc", id);
			
		} else if(i == 2) {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.shareId = ? order by c.commentsTime desc", id);

		} else if(i == 3) {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.diaryId = ? order by c.commentsTime desc", id);

		} else if(i == 4) {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.tnewsId = ? order by c.commentsTime desc", id);

		} else {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.anewsId = ? order by c.commentsTime desc", id);

		}
	}

	//ɾ������
	@Override
	public void deleteCommentsById(String commentsId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Comments.class, commentsId));
	}

	//Ѱ��������ߵ�����
	@Override
	public List<CNews> findCNewsByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//��������ǰ����
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CNews c where c.newsDate >= :limitTime order by c.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<CNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<ANews> findANewsByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//��������ǰ����
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ANews a where a.anewsDate >= :limitTime order by a.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<ANews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Diary> findDiaryByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//��������ǰ����
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary d where d.diaryDate >= :limitTime and d.diaryPermission = :permission order by d.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setParameter("permission",0);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Share> findShareByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//��������ǰ����
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Share s where s.shareDate >= :limitTime order by s.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<Share> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<TNews> findTNewsByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//��������ǰ����
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TNews t where t.newsDate >= :limitTime order by t.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<TNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
}















