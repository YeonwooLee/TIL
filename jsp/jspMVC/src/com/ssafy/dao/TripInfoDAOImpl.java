package com.ssafy.dao;

import java.sql.*;
import java.util.*;

import com.ssafy.dto.AttractionInfo;
import com.ssafy.dto.Gugun;
import com.ssafy.dto.Sido;
import com.ssafy.util.DBUtil;

public class TripInfoDAOImpl implements TripInfoDAO {

	private static TripInfoDAO tripInfoDAO = new TripInfoDAOImpl();
	private DBUtil dbUtil;
	
	public TripInfoDAOImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static TripInfoDAO getTripInfoDAO() {
		return tripInfoDAO;
	}
	
	@Override
	public List<Sido> getSido() throws SQLException {
		List<Sido> sidoList = new ArrayList<Sido>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select sido_code, sido_name from sido;");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int sidoCode = rs.getInt("sido_code");
				String sidoName= rs.getString("sido_name");
				sidoList.add(new Sido(sidoCode, sidoName));
			}
		}finally {
			dbUtil.close(conn, pstmt);
		}
		return sidoList;
	}

	@Override
	public List<Gugun> getGugun(int sidoCode) throws SQLException {
		List<Gugun> gugunList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select gugun_code, gugun_name, sido_code from gugun \n");
			sql.append("where sido_code = ? ; ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sidoCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gugunCode = rs.getInt("gugun_code");
				String gugunName= rs.getString("gugun_name");
				gugunList.add(new Gugun(gugunCode, gugunName, sidoCode));
			}
		}finally {
			dbUtil.close(conn, pstmt);
		}
		return gugunList;
	}
	
	@Override
	public List<Gugun> getAllGugun() throws SQLException {
		List<Gugun> gugunList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select gugun_code, gugun_name, sido_code from gugun ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gugunCode = rs.getInt("gugun_code");
				String gugunName= rs.getString("gugun_name");
				int sidoCode = rs.getInt("sido_code");
				gugunList.add(new Gugun(gugunCode, gugunName, sidoCode));
			}
		}finally {
			dbUtil.close(conn, pstmt);
		}
		return gugunList;
	}


	@Override
	public List<AttractionInfo> getTripInfo(int sidoCode, int gugunCode, int contentTypeId, String keyword) throws SQLException {
		List<AttractionInfo> attractionInfoList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			String basic = ("select "
								+ "content_id, content_type_id, title, "
								+ "addr1, addr2, zipcode, tel, "
								+ "first_image, first_image2, readcount, "
								+ "sido_code, gugun_code, latitude, longitude "
							+ "from attraction_info " );
			String optionSido = (" sido_code= ? " );
			String optionGugun = (" gugun_code= ? " );
			String optionContentTypeId = (" content_type_id= ? " );
			String optionKeyword= (" title like concat('%', ?, '%') ");
			
			sql.append(basic);
			boolean hasOption = false;
			boolean checkOption[] = new boolean[5];
			if(sidoCode!=0) {
				if(!hasOption) {
					hasOption = true;
					sql.append(" where ");
				}
				sql.append(optionSido);
				checkOption[1] = true;
			} 
			if(gugunCode!=0) {
				if(!hasOption) {
					hasOption = true;
					sql.append(" where ");
				}
				if(checkOption[1]) sql.append(" and ");
				sql.append(optionGugun);
				checkOption[2] = true;
			} 
			
			if(contentTypeId!=0) {
				if(!hasOption) {
					hasOption = true;
					sql.append(" where ");
				}
				if(checkOption[2]) sql.append(" and ");
				sql.append(optionContentTypeId);
				checkOption[3] = true;			
			}
			
			if(!keyword.equals("")) {
				if(!hasOption) {
					hasOption = true;
					sql.append(" where ");
				}
				if(checkOption[3]) sql.append(" and ");
				sql.append(optionKeyword);
				checkOption[4] = true;
			}
			sql.append(";");
			
			pstmt = conn.prepareStatement(sql.toString());
			int cnt=1;
			if(checkOption[1]) pstmt.setInt(cnt++, sidoCode);
			if(checkOption[2]) pstmt.setInt(cnt++, gugunCode);
			if(checkOption[3]) pstmt.setInt(cnt++, contentTypeId);
			if(checkOption[4]) pstmt.setString(cnt++, keyword);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				contentTypeId = rs.getInt("content_type_id");
				int contentId = rs.getInt("content_id");
				String title = rs.getString("title");
				String addr1 = rs.getString("addr1");
				String addr2 = rs.getString("addr2");
				String zipcode = rs.getString("zipcode");
				String tel = rs.getString("tel");
				String firstImage = rs.getString("first_image");
				String firstImage2 = rs.getString("first_image2");
				int readCount = rs.getInt("readcount");
				sidoCode = rs.getInt("sido_code");
				gugunCode = rs.getInt("gugun_code");
				double latitude = rs.getDouble("latitude");
				double longitude = rs.getDouble("longitude");
				attractionInfoList.add(new AttractionInfo(contentTypeId, contentId, title, addr1, addr2, zipcode,
						tel, firstImage, firstImage2, readCount, sidoCode, gugunCode,
						latitude, longitude));
			}
		}finally {
			dbUtil.close(conn, pstmt);
		}
		return attractionInfoList;
	}

	@Override
	public AttractionInfo getAttractionInfo(int contentId) throws SQLException {
		AttractionInfo info = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select "
					+ "content_id, content_type_id, title, "
					+ "addr1, addr2, zipcode, tel, "
					+ "first_image, first_image2, readcount, "
					+ "sido_code, gugun_code, latitude, longitude "
				+ "from attraction_info " );
			sql.append("where content_id = ? ;");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int contentTypeId = rs.getInt("content_type_id");
				String title = rs.getString("title");
				String addr1 = rs.getString("addr1");
				String addr2 = rs.getString("addr2");
				String zipcode = rs.getString("zipcode");
				String tel = rs.getString("tel");
				String firstImage = rs.getString("first_image");
				String firstImage2 = rs.getString("first_image2");
				int readCount = rs.getInt("readcount");
				int sidoCode = rs.getInt("sido_code");
				int gugunCode = rs.getInt("gugun_code");
				double latitude = rs.getDouble("latitude");
				double longitude = rs.getDouble("longitude");
				info = new AttractionInfo(contentTypeId, contentId, title, addr1, addr2, zipcode,
						tel, firstImage, firstImage2, readCount, sidoCode, gugunCode,
						latitude, longitude);
			}
			
		}finally {
			dbUtil.close(conn, pstmt);
		}
		return info;
	}

	@Override
	public boolean registHotplace(AttractionInfo info) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int rs = -1;
//		try {
//			conn = dbUtil.getConnection();
////			String sql = "insert into hotplace(`user_id`,`user_name`,`user_pw`,`user_email`) values(?,?,?,?);";
////			String sql = "insert into hotplace(`content_id`,`user_id`) values(?,?);";
//	
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, user.getId());
//			pstmt.setString(2, user.getName());
//			pstmt.setString(3, user.getPw());
//			pstmt.setString(4, user.getEmail());
//			rs = pstmt.executeUpdate();
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		finally {
//			dbUtil.close(pstmt, conn);
//		}
////		System.out.println("user"+userList);
//		return rs==1?true:false;
		return true;
	}

}
