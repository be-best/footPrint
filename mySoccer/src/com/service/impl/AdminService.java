package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IAdminDao;
import com.domain.ANews;
import com.domain.AdPhoto;
import com.domain.Admin;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Comments;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Share;
import com.domain.TNews;
import com.domain.Tourist;
import com.service.IAdminService;

@Service("adminService")
@Transactional
public class AdminService implements IAdminService {
	@Autowired
	@Qualifier("adminDao")
	private IAdminDao adminDao;

	@Override
	public Admin adminLogin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.adminLogin(admin);
	}

	

	@Override
	public PageBean<Player> findAllPlayer(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(1);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = adminDao.findAllPlayer(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean<Coach> findAllCoach(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Coach> pageBean = new PageBean<Coach>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(2);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Coach> list = adminDao.findAllCoach(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//ɾ����Ա
	@Override
	public void deletePlayerByAdmin(String pid) {
		// TODO Auto-generated method stub
		adminDao.deletePlayerByAdmin(pid);
	}


	//ɾ������
	@Override
	public void deleteCoachByAdmin(String coachId) {
		// TODO Auto-generated method stub
		adminDao.deleteCoachByAdmin(coachId);
	}


	//Ѱ�Ҿ��ֲ�
	@Override
	public PageBean<Club> findClubByAdmin(int i, Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Club> pageBean = new PageBean<Club>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		Integer totalCount = null;
		// �����ܼ�¼��:
		if(i == 0) {
			totalCount = adminDao.findTotalNumber(3);
		}
		if(i == 1) {
			totalCount = adminDao.findTotalNumber(4);
		}
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Club> list = null;
		System.out.println(begin+"---"+pageSize);
		System.out.println("----------------------");
		if(i == 0) {
			list = adminDao.findClubByAdmin(0,begin,pageSize);			
		}
		if(i == 1) {
			list = adminDao.findClubByAdmin(1,begin,pageSize);
		}
		pageBean.setList(list);
		return pageBean;
	}


	//���δͨ����ɾ�����ֲ�
	@Override
	public void deleteClubByAdmin(String cid) {
		// TODO Auto-generated method stub
		adminDao.deleteClubByAdmin(cid);
	}


	//���Ҿ��ֲ����½���
	@Override
	public List<Coach> findAllCoachByClub(Club club) {
		// TODO Auto-generated method stub
		return adminDao.findAllCoachByClub(club);
	}


	//��ѯ���еľ��ֲ�����
	@Override
	public PageBean<CNews> findAllCNewsByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<CNews> pageBean = new PageBean<CNews>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(5);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<CNews> list = adminDao.findAllCNewsByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//���ҽ�������
	@Override
	public PageBean<Share> findAllShareByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Share> pageBean = new PageBean<Share>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(6);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Share> list = adminDao.findAllShareByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//������Ա�ռ�
	@Override
	public PageBean<Diary> findAllDiaryByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(7);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = adminDao.findAllDiaryByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//����Աд�µ�����
	@Override
	public void createANews(ANews anews) {
		// TODO Auto-generated method stub
		adminDao.createANews(anews);
	}


	//��ѯһ������Ա������
	@Override
	public PageBean<ANews> findANews(Integer currPage, Admin admin) {
		// TODO Auto-generated method stub
		PageBean<ANews> pageBean = new PageBean<ANews>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalANews(admin);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<ANews> list = adminDao.findANews(admin,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//����IDѰ��һƪANews
	@Override
	public ANews findANewsById(String anewsId) {
		// TODO Auto-generated method stub
		return adminDao.findANewsById(anewsId);
	}

	//���¹���Ա����
	@Override
	public void updateANews(ANews anews) {
		// TODO Auto-generated method stub
		adminDao.updateANews(anews);
	}

	//ɾ������Ա����
	@Override
	public void deleteANewsById(String anewsId) {
		// TODO Auto-generated method stub
		adminDao.deleteANewsById(anewsId);
	}

	//����µĹ���Ա
	@Override
	public void addNewAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.addNewAdmin(admin);
	}

	//�������й���Ա
	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		return adminDao.findAllAdmin();
	}

	//ɾ������Ա
	@Override
	public void deleteAdmin(String adminId) {
		// TODO Auto-generated method stub
		adminDao.deleteAdmin(adminId);
	}

	//����ϵͳ����Ա
	@Override
	public Admin findAdminById(String adminId) {
		// TODO Auto-generated method stub
		return adminDao.findAdminById(adminId);
	}

	//�޸Ĺ���Ա��Ϣ
	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.updateAdmin(admin);
	}

	//Ѱ�����еĹ���Ա����
	@Override
	public PageBean<ANews> findAllANews(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<ANews> pageBean = new PageBean<ANews>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(8);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<ANews> list = adminDao.findAllANews(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//Ѱ�ҿɼ����ռǵ���ҳ
	@Override
	public PageBean<Diary> findDiaryShowByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(9);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = adminDao.findDiaryShowByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//Ѱ�������ο�
	@Override
	public PageBean<Tourist> findTouristByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Tourist> pageBean = new PageBean<Tourist>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(10);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<Tourist> list = adminDao.findTouristByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//ɾ���ο�
	@Override
	public void deleteTourist(String tId) {
		// TODO Auto-generated method stub
		adminDao.deleteTourist(tId);
	}


	//Ѱ���ο���Ѷ
	@Override
	public PageBean<TNews> findTNews(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<TNews> pageBean = new PageBean<TNews>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = adminDao.findTotalNumber(11);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<TNews> list = adminDao.findTNews(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//����ֲ�ͼ
	@Override
	public void addAdPhoto(AdPhoto adPhoto) {
		// TODO Auto-generated method stub
		adminDao.addAdPhoto(adPhoto);
	}

	//�����ֲ�ͼ
	@Override
	public List<AdPhoto> findAllAdPhoto() {
		// TODO Auto-generated method stub
		return adminDao.findAllAdPhoto();
	}

	//ɾ���ֲ�ͼ
	@Override
	public void deleteAdPhotoById(String adId) {
		// TODO Auto-generated method stub
		adminDao.deleteAdPhotoById(adId);
	}

	//����ID�����ֲ�ͼ
	@Override
	public AdPhoto findAdPhotoById(String adId) {
		// TODO Auto-generated method stub
		return adminDao.findAdPhotoById(adId);
	}

	//�����ֲ�ͼ
	@Override
	public void updateAdPhoto(AdPhoto adPhoto) {
		// TODO Auto-generated method stub
		adminDao.updateAdPhoto(adPhoto);
	}



	@Override
	public Admin findAdminByName(String adminName) {
		// TODO Auto-generated method stub
		return adminDao.findAdminByName(adminName);
	}


	//�������
	@Override
	public void addComments(Comments comments) {
		// TODO Auto-generated method stub
		adminDao.addComments(comments);
	}
	
	//�������µ�����
	@Override
	public List<Comments> findCommentsByArticleId(int i, String id) {
		// TODO Auto-generated method stub
		return adminDao.findCommentsByArticleId(i,id);
	}

	//ɾ����������
	@Override
	public void deleteCommentsById(String commentsId) {
		// TODO Auto-generated method stub
		adminDao.deleteCommentsById(commentsId);
	}


	//����������ߵ�����
	@Override
	public List<CNews> findCNewsByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findCNewsByViewNumber();
	}



	@Override
	public List<ANews> findANewsByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findANewsByViewNumber();
	}



	@Override
	public List<Diary> findDiaryByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findDiaryByViewNumber();
	}



	@Override
	public List<Share> findShareByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findShareByViewNumber();
	}



	@Override
	public List<TNews> findTNewsByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findTNewsByViewNumber();
	}
}

















