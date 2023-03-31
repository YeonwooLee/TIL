package com.ssafy.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.dto.AttractionInfo;
import com.ssafy.dto.Gugun;
import com.ssafy.dto.Sido;

public interface TripInfoService {
	
	List<Sido> getSido() throws SQLException;
	List<Gugun> getGugun(int sidoCode) throws SQLException;
	List<Gugun> getAllGugun() throws SQLException;
	List<AttractionInfo> findTripInfo(int sidoCode, int gugunCode, int contentType, String keyword) throws SQLException;
	AttractionInfo getAttractionInfo(int contentId) throws SQLException;
	boolean registHotplace(AttractionInfo info) throws SQLException;
	
	
}
