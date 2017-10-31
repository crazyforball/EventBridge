package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.domain.Comment;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

public interface CommentDao {
	public void saveComment(Comment comment);

	public Comment getComment(User creator, Event event);

	public Comment getComment(int commentId);

	public List<Comment> getComments(User creator);

	public List<Comment> getComments(Event event);

	public List<Comment> getLatestNComments(Event event, int n);

	public void deleteComment(Comment comment);
}
