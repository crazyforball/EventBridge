package com.emsrepo.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.CategoryDao;
import com.emsrepo.domain.Category;

@SuppressWarnings("deprecation")
@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	@Override
	public void saveCategory(Category category) {
		Session session = null;
		try {
			session = getSession();
			session.save(category);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public Category getCategory(String categoryName) {
		Session session = null;
		Category category = null;

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Expression.like("categoryName", categoryName));
			category = (Category) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return category;
	}
	
	@Override
	public Category getCategory(int cid) {
		Session session = null;
		Category category = null;

		try {
			session = getSession();
			category = (Category) session.get(Category.class, cid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCategoryNames() {
		Session session = null;
		List<Category> cList = new ArrayList<>();
		List<String> list = new ArrayList<>();

		try {
			session = getSession();
			Criteria c = session.createCriteria(Category.class);
			cList = c.list();

			for (Iterator<Category> iterator = cList.iterator(); iterator.hasNext();) {
				list.add(iterator.next().getCategoryName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoryList() {

		Session session = null;
		List<Category> list = new ArrayList<>();
		try {
			session = getSession();
			Criteria c = session.createCriteria(Category.class);
			list = c.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println(list.toString());
		return list;
	}

	@Override
	public void deleteCategory(Category category) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = getSession();
			session.delete(category);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
