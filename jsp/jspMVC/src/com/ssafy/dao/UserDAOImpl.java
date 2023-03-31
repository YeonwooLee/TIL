package com.ssafy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ssafy.dto.User;
import com.ssafy.util.DBUtil;

public class UserDAOImpl implements UserDAO {
	DBUtil dbUtil = DBUtil.getInstance();
	// singleton
	private static UserDAOImpl instance = new UserDAOImpl();

	private UserDAOImpl() {
		super();
	}

	public static UserDAOImpl getInstance() {
		try {
			instance.crtUserTbl();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return instance;
	}


	// user 테이블이 없으면 만든다
	public void crtUserTbl(){
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "create table if not exists `user`\r\n" + 
					"(\r\n" + 
					"`user_id` varchar(200) not null comment '사용자 아이디',\r\n" + 
					"`user_name` varchar(200) not null comment '사용자 이름',\r\n" + 
					"`user_pw` varchar(200) not null comment '사용자 비밀번호',\r\n" + 
					"`user_email` varchar(200) not null comment '사용자 이메일',\r\n" + 
					"primary key(`user_id`)\r\n" + 
					")";
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtil.close(stmt,conn);
		}
		
	}

	// 유저목록 가져오기
	public List<User> selectUserList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<>();
		try {
			conn = dbUtil.getConnection();
			String sql = "select * from user";
	
			pstmt = conn.prepareStatement(sql);
	//		pstmt.setString(1, "파람");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User curUser = new User(rs.getString("user_id"), rs.getString("user_name"), rs.getString("user_pw"),
						rs.getString("user_email"));
//				System.out.println("cur = "+curUser);
				userList.add(curUser);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtil.close(rs, pstmt, conn);
		}
//		System.out.println("user"+userList);
		return userList;
	}

	
	// 유저id로 조회
	@Override
	public User selectUserById(String userId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "select * from user where user_id=?";
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString("user_id"), rs.getString("user_name"), rs.getString("user_pw"),
						rs.getString("user_email"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtil.close(rs, pstmt, conn);
		}
//		System.out.println("user"+userList);
		return user;
	}
	// 유저id로 조회
	@Override
	public boolean updateUserById(String userId, User user){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rst = 0;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "update user set `user_name`=?,`user_pw`=?,`user_email`=? where `user_id`=?;";
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, userId);
			System.out.println("sql="+user);
			rst = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtil.close(pstmt, conn);
		}
//		System.out.println("user"+userList);
		return rst==1?true:false;
	}


	@Override
	public boolean deleteUserById(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		User user = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "delete from user where user_id=?";
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtil.close(pstmt, conn);
		}
//		System.out.println("user"+userList);
		return rs==1?true:false;
	}

	@Override
	public boolean insertUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;

		try {
			conn = dbUtil.getConnection();
			String sql = "insert into user(`user_id`,`user_name`,`user_pw`,`user_email`) values(?,?,?,?);";
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPw());
			pstmt.setString(4, user.getEmail());
			rs = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			dbUtil.close(pstmt, conn);
		}
//		System.out.println("user"+userList);
		return rs==1?true:false;
	}
//	public static void main(String[] args) {
//		instance.deleteUserById("33");
//	}
}