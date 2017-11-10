package com.emsrepo.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.CommentDao;
import com.emsrepo.domain.Comment;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

@SuppressWarnings("deprecation")
@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveComment(Comment comment) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(comment);
		
	}

	@Override
	public Comment getComment(User creator, Event event) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Comment.class);
		criteria.add(Expression.like("creator", creator)).add(Expression.like("event", event));
		return (Comment) criteria.uniqueResult();

	}

	@Override
	public Comment getComment(int commentId) {
		// TODO Auto-generated method stub
		return (Comment) sessionFactory.getCurrentSession().get(Comment.class, commentId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getComments(User creator) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Comment.class);
		criteria.add(Expression.like("creator", creator)).addOrder(Order.desc("commentDate"));
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getComments(Event event) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Comment.class);
		criteria.add(Expression.like("event", event)).addOrder(Order.desc("commentDate"));
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getLatestNComments(Event event, int n) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Comment.class);
		criteria.add(Expression.like("event", event)).addOrder(Order.desc("commentDate")).setMaxResults(n);
		return (List<Comment>) criteria.list();
	}

	@Override
	public void deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete((Object) comment);
	}

	/*
	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	@Override
	public void saveComment(Comment comment) {
		Session session = null;
		try {
			session = getSession();
			session.save(comment);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public Comment getComment(User creator, Event event) {
		Session session = null;
		Comment comment = null;

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Comment.class);
			criteria.add(Expression.like("creator", creator)).add(Expression.like("event", event));
			comment = (Comment) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return comment;

	}

	@Override
	public Comment getComment(int cid) {
		Session session = null;
		Comment comment = null;

		try {
			session = getSession();
			comment = (Comment) session.get(Comment.class, cid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return comment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getComments(User creator) {
		Session session = null;
		List<Comment> list = new ArrayList<>();

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Comment.class);
			criteria.add(Expression.like("creator", creator)).addOrder(Order.desc("commentDate"));
			list = (List<Comment>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getComments(Event event) {
		Session session = null;
		List<Comment> list = new ArrayList<>();

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Comment.class);
			criteria.add(Expression.like("event", event)).addOrder(Order.desc("commentDate"));
			list = (List<Comment>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getLatestNComments(Event event, int n) {
		Session session = null;
		List<Comment> list = new ArrayList<>();

		try {
			session = getSession();
			list = (List<Comment>) session.createCriteria(Comment.class).add(Expression.like("event", event))
					.addOrder(Order.desc("createDate")).setMaxResults(n).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public void deleteComment(Comment comment) {
		Session session = null;
		try {
			session = getSession();
			session.delete(comment);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	*/

}
