package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IPlayerDao;
import com.dao.ITouristDao;
import com.domain.CollectionItem;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.TNews;
import com.domain.Tourist;
import com.service.ITouristService;

@Service("touristService")
@Transactional
public class TouristServiceImpl implements ITouristService {
	@Autowired
	@Qualifier("touristDao")
	private ITouristDao touristDao;

	//�ο�ע��
	@Override
	public void touristRegist(Tourist tourist) {
		// TODO Auto-generated method stub
		touristDao.touristRegist(tourist);
	}

	//�ο͵�¼
	@Override
	public Tourist touristLogin(Tourist tourist) {
		// TODO Auto-generated method stub
		return touristDao.touristLogin(tourist);
	}

	//�����µ��ο�����
	@Override
	public void createTNews(TNews tnews) {
		// TODO Auto-generated method stub
		touristDao.createTNews(tnews);
	}

	//��ҳѰ���ο�����
	@Override
	public PageBean<TNews> findOwnTNews(Integer currPage, Tourist tourist) {
		// TODO Auto-generated method stub
		PageBean<TNews> pageBean = new PageBean<TNews>();
		// ���ò�����
		// ���õ�ǰҳ����
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ�ļ�¼��:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = touristDao.findTotalTNews(tourist);
		// ������ҳ��:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		int begin = (currPage - 1) * pageSize;
		List<TNews> list = touristDao.findOwnTNews(tourist,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//��������
	@Override
	public TNews findTNewsArticle(String tId) {
		// TODO Auto-generated method stub
		return touristDao.findTNewsArticle(tId);
	}

	//ɾ������
	@Override
	public void deleteTNewsById(String tId) {
		// TODO Auto-generated method stub
		touristDao.deleteTNewsById(tId);
	}

	//�޸���������
	@Override
	public void updateTNews(TNews tnews) {
		// TODO Auto-generated method stub
		touristDao.updateTNews(tnews);
	}

	//Ѱ���ο�
	@Override
	public Tourist findTourist(String gettId) {
		// TODO Auto-generated method stub
		return touristDao.findTourist(gettId);
	}

	//�޸��ο���Ϣ
	@Override
	public void updateTourist(Tourist tourist) {
		// TODO Auto-generated method stub
		touristDao.updateTourist(tourist);
	}

	//�����û�������ʾ����ҳ��
	@Override
	public List<TNews> findTNewsForInfo(Tourist tourist) {
		// TODO Auto-generated method stub
		return touristDao.findTNewsForInfo(tourist);
	}

	//����ղ�
	@Override
	public void addCollection(CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		touristDao.addCollection(collectionItem);
	}

	//ȡ���ղ�
	@Override
	public void deleteCollection(int i, CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		touristDao.deleteCollection(i,collectionItem);
	}

	@Override
	public CollectionItem findCollection(int i, CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		return touristDao.findCollection(i,collectionItem);
	}

	@Override
	public List<CollectionItem> findAllCollectionByType(int i, Tourist tourist) {
		// TODO Auto-generated method stub
		return touristDao.findAllCollectionByType(i,tourist);
	}

	@Override
	public Tourist findTouristByNumber(String touristEmail) {
		// TODO Auto-generated method stub
		return touristDao.findTouristByNumber(touristEmail);
	}

}
