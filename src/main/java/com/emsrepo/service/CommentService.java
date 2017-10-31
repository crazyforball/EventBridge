package com.emsrepo.service;

import java.util.List;

import com.emsrepo.domain.Comment;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

public interface CommentService {

	public boolean registerComment(Comment comment);

	public boolean isExistingComment(Comment comment);

	public Comment retrieveComment(int commentId);

	public Comment retrieveComment(User creator, Event event);

	public List<Comment> retrieveComments(User creator);

	public List<Comment> retrieveComments(Event event);
	
	public List<Comment> retrievePositiveComments(List<Comment> comments);
	
	public List<Comment> retrieveNeutralComments(List<Comment> comments);
	
	public List<Comment> retrieveNegativeComments(List<Comment> comments);

	public List<Comment> retrieveLatestNComments(Event event, int n);

	public void deleteComment(int cid);

}
