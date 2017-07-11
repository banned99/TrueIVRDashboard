package com.truecorp.dashboard.service;

import java.sql.SQLException;
import java.util.List;

import com.truecorp.dashboard.criteria.ACCriteria;
import com.truecorp.dashboard.dao.AccessChannelDao;
import com.truecorp.dashboard.model.AccessChannel;

public class AccessChannelService {
	public List<AccessChannel> getAll(int perPage, int page) throws SQLException{
		return AccessChannelDao.getAllAC(perPage, page);
	}
	
	public List<AccessChannel> searchAC(ACCriteria cri, int perPage, int page) throws SQLException{
		return AccessChannelDao.searchAC(cri, perPage, page);
	}

	public int getTotal() throws SQLException {
		return AccessChannelDao.getTotalAC();
	}
}
