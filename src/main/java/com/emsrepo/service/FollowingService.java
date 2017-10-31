package com.emsrepo.service;

import java.util.List;

import com.emsrepo.domain.Following;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

public interface FollowingService {

	public boolean registerFollowing(Following following);

	public boolean isExistingFollowing(Following following);

	public Following retrieveFollowing(int followingId);

	public Following retrieveFollowing(User creator, Event event);

	public List<Following> retrieveFollowings(User creator);

	public void cancelFollowing(int fid);

}
