package com.ssafy.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
create database index_test;

use index_test;

create table board (
	no int auto_increment,
    subject varchar(100) not null,
    content varchar(4000) not null,
    name varchar(20),
    hit int default 0,
    test int,
    logtime timestamp default current_timestamp,
    constraint board_no_pk primary key (no)
);

create table reboard (
	no int
);

alter table board add index board_test_idx (test);
*/

public class IndexTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/index_test?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8", "ssafy", "ssafy");
			StringBuilder sql = new StringBuilder("insert into board (subject, content, name, test) values ");
			String names[] = {"홍길동","이순신","강감찬"};
			for(int i=0;i<80000;i++) {
				sql.append("('제목").append(i).append("','내용").append(i).append("', '").append(names[i%3]).append("', ").append(i+1).append(")");
//				sql += "('제목" + i + "','내용" + i + "', '" + names[i%3] + "', " + (i+1) + ")";
				if(i != 79999)
					sql.append(",");
			}
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql.setLength(0);
			sql.append("insert into reboard (no) values ");
			for(int i=0;i<10000;i++) {
				sql.append("(" + i + ")");
				if(i != 9999)
					sql.append(",");
			}
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
