package com.emsrepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emsrepo.dao.FollowingDao;
import com.emsrepo.domain.Following;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;
import com.emsrepo.service.FollowingService;

@Service("followingService")
public class FollowingServiceImpl implements FollowingService {

	@Autowired
	private FollowingDao followingDao;

	@Override
	public boolean registerFollowing(Following following) {
		if (!isExistingFollowing(following)) {
			followingDao.saveFollowing(following);
			return true;
		}
		return false;
	}

	@Override
	public boolean isExistingFollowing(Following following) {
		return followingDao.getFollowing(following.getCreator(), following.getEvent()) != null;
	}

	@Override
	public Following retrieveFollowing(int bid) {
		return followingDao.getFollowing(bid);
	}

	@Override
	public Following retrieveFollowing(User creator, Event event) {
		return followingDao.getFollowing(creator, event);
	}

	@Override
	public List<Following> retrieveFollowings(User creator) {
		return followingDao.getFollowings(creator);
	}

	@Override
	public void cancelFollowing(int fid) {
		followingDao.deleteFollowing(followingDao.getFollowing(fid));
	}
}
