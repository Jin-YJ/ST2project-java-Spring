package com.project.sc2.member.dao;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.project.sc2.member.Member;

@Repository
public class MemberDao implements IMemberDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1522:xe";
	private String userid = "scott";
	private String userpw = "tiger";
	
	private ComboPooledDataSource dataSource;
	
	private JdbcTemplate template;

	public MemberDao() {

		dataSource = new ComboPooledDataSource();
		try {
			
			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(userid);
			dataSource.setPassword(userpw);
			
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		template = new JdbcTemplate();
		template.setDataSource(dataSource);

	}
	
	@Override
	//유저 등록 Dao
	public int memberInsert(final Member member) {
		int result = 0;
		
		final String sql = "INSERT INTO member (mid , nickname, password, email) values (?,?,?,?)";
		
		result = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getmId());
				pstmt.setString(2, member.getNickname());
				pstmt.setString(3, member.getPassword());
				pstmt.setString(4, member.getEmail());
				
			}
		});
		
		return result;
		
		
	}

	

	@Override
	//유저 검색 Dao
	public Member memberSelect(final Member member) {
 		
		List<Member> members = null;
	
		final String sql = "SELECT * FROM member WHERE mId = ? AND password = ?";
		
		members = template.query(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getPassword());
		}
	}, new RowMapper<Member>() {

		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member mem = new Member();
			mem.setmId(rs.getString("mId"));
			mem.setPassword(rs.getString("password"));
			mem.setEmail(rs.getString("email"));
			mem.setNickname(rs.getString("nickname"));
			return mem;
		}
	});
		
		if(members.isEmpty()) 
			return null;
		
		return members.get(0);
		

	}

	@Override
	//유저 수정 Dao
	public int memberUpdate(final Member member) {
		int result = 0;
		
		final String sql = "UPDATE member SET nickname = ? , password = ? WHERE mId = ?";
		
		result = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getNickname());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getmId());
			}
		});
		return result;
		
	}

	@Override
	//유저 삭제 Dao
	public void memberRemove(final String mId) {
		int result = 0;
		final String sql = "delete from member where mid = ?";
	
		System.out.println("dao mId");
		result = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, mId);
		
			}
		});
		
	}

}
