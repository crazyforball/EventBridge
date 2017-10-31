package com.emsrepo.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emsrepo.dao.CommentDao;
import com.emsrepo.domain.Comment;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;
import com.emsrepo.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public boolean registerComment(Comment comment) {
		if (!isExistingComment(comment)) {
			commentDao.saveComment(comment);
			return true;
		}
		return false;
	}

	@Override
	public boolean isExistingComment(Comment comment) {
		return commentDao.getComment(comment.getCreator(), comment.getEvent()) != null;
	}

	@Override
	public Comment retrieveComment(int cid) {
		return commentDao.getComment(cid);
	}

	@Override
	public Comment retrieveComment(User creator, Event event) {
		return commentDao.getComment(creator, event);
	}

	@Override
	public List<Comment> retrieveComments(User creator) {
		return commentDao.getComments(creator);
	}

	@Override
	public List<Comment> retrieveComments(Event event) {
		return commentDao.getComments(event);
	}

	@Override
	public List<Comment> retrievePositiveComments(List<Comment> comments) {
		List<Comment> positiveComments = new ArrayList<Comment>();
		for (Iterator<Comment> iterator=comments.iterator(); iterator.hasNext();) {
			Comment comment = iterator.next();
			if (comment.getRating() == 1) {
				positiveComments.add(comment);
			}
		}
		return positiveComments;
	}
	
	@Override
	public List<Comment> retrieveNeutralComments(List<Comment> comments) {
		List<Comment> neutralComments = new ArrayList<Comment>();
		for (Iterator<Comment> iterator=comments.iterator(); iterator.hasNext();) {
			Comment comment = iterator.next();
			if (comment.getRating() == 0) {
				neutralComments.add(comment);
			}
		}
		return neutralComments;
	}
	
	@Override
	public List<Comment> retrieveNegativeComments(List<Comment> comments) {
		List<Comment> negativeComments = new ArrayList<Comment>();
		for (Iterator<Comment> iterator=comments.iterator(); iterator.hasNext();) {
			Comment comment = iterator.next();
			if (comment.getRating() == -1) {
				negativeComments.add(comment);
			}
		}
		return negativeComments;
	}
	
	@Override
	public List<Comment> retrieveLatestNComments(Event event, int n) {
		return commentDao.getLatestNComments(event, n);
	}

	@Override
	public void deleteComment(int cid) {
		commentDao.deleteComment(commentDao.getComment(cid));
	}
}
