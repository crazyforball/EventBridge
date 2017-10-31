package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.domain.Following;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

public interface FollowingDao {
	public void saveFollowing(Following following);

	public Following getFollowing(User creator, Event event);

	public Following getFollowing(int followingId);

	public List<Following> getFollowings(User creator);

	public void deleteFollowing(Following following);
}
