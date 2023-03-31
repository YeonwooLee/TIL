package com.ssafy.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.dao.TripInfoDAO;
import com.ssafy.dao.TripInfoDAOImpl;
import com.ssafy.dto.AttractionInfo;
import com.ssafy.dto.Gugun;
import com.ssafy.dto.Sido;

public class TripInfoServiceImpl implements TripInfoService {

	private static TripInfoService tripInfoService = new TripInfoServiceImpl();
	private static TripInfoDAO tripInfoDAO = TripInfoDAOImpl.getTripInfoDAO();
	
	public TripInfoServiceImpl() {
	}
	
	public static TripInfoService getTripInfoService() {
		return tripInfoService;
	}
	
	@Override
	public List<Sido> getSido() throws SQLException {
		return tripInfoDAO.getSido();

	}

	@Override
	public List<Gugun> getGugun(int sidoCode) throws SQLException {
		return tripInfoDAO.getGugun(sidoCode);

	}

	@Override
	public List<Gugun> getAllGugun() throws SQLException {
		return tripInfoDAO.getAllGugun();

	}
	
	@Override
	public List<AttractionInfo> findTripInfo(int sidoCode, int gugunCode, int contentType, String keyword) throws SQLException {
		return tripInfoDAO.getTripInfo(sidoCode, gugunCode, contentType, keyword);
	}

	@Override
	public AttractionInfo getAttractionInfo(int contentId) throws SQLException {
		return tripInfoDAO.getAttractionInfo(contentId);
	}

	@Override
	public boolean registHotplace(AttractionInfo info) throws SQLException {
		return tripInfoDAO.registHotplace(info);
	}

}
