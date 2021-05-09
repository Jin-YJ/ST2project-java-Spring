package com.project.sc2.bboard.dao;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.project.sc2.bboard.Bboard;
import com.project.sc2.bboard.BoardPages;
import com.project.sc2.member.Member;

@Repository
public class BboardDao implements IBboardDao{

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1522:xe";
	private String userid = "scott";
	private String userpw = "tiger";
	
	private ComboPooledDataSource dataSource;
	
	private JdbcTemplate template;
	
	
	
	public BboardDao() {
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
	//게시판 입력
	public int bBoardInsert(final Bboard board , final Member member) {
		int result = 0;
		final String sql = "INSERT INTO build_board ( bno , title , content , writer, regdate, hit) values (build_board_seq.nextval,? ,? ,? ,sysdate ,0)";
		result = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setString(3, member.getmId());
			
				
			}
		});
		
		return result;
	}

	@Override
	//게시판 검색
	public ArrayList<Bboard> bBoardSearchTitle( String search) {
		
		ArrayList<Bboard> list = new ArrayList<Bboard>();
		final String sql = "select * from build_board where title like ? order by bno desc";
		final String keyword = "%" + search + "%";
		list = (ArrayList<Bboard>) template.query(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, keyword);
		}
	}, new RowMapper<Bboard>() {

		@Override
		public Bboard mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Bboard board = new Bboard();
			board.setBno(rs.getInt("bno"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setDate(rs.getDate("regdate"));
			board.setHit(rs.getInt("hit"));
			return board;

		}
	});
		
		System.out.println("검색 글 갯수(dao) = " + list.size());
		return list;
	}
	
	@Override
	public ArrayList<Bboard> bBoardSearchContent(final String search) {
		ArrayList<Bboard> list = new ArrayList<Bboard>();
		final String sql = "select * from build_board where content like ?";
		list = (ArrayList<Bboard>) template.query(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, search);
		}
	}, new RowMapper<Bboard>() {

		@Override
		public Bboard mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Bboard board = new Bboard();
			board.setBno(rs.getInt("bno"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setDate(rs.getDate("regdate"));
			board.setHit(rs.getInt("hit"));
			return board;

		}
	});
		
		System.out.println("검색 글 갯수(dao) = " + list.size());
		return list;
	}


	@Override
	public ArrayList<Bboard> bBoardSearchWriter(final String search) {
		ArrayList<Bboard> list = new ArrayList<Bboard>();
		final String sql = "select * from build_board where Writer like ?";
		list = (ArrayList<Bboard>) template.query(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, search);
		}
	}, new RowMapper<Bboard>() {

		@Override
		public Bboard mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Bboard board = new Bboard();
			board.setBno(rs.getInt("bno"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setDate(rs.getDate("regdate"));
			board.setHit(rs.getInt("hit"));
			return board;

		}
	});
		
		System.out.println("검색 글 갯수(dao) = " + list.size());
		return list;
	}


	@Override
	//게시판 수정
	public void bBoardModify(Bboard board) {
		final String sql = "update build_board set title = ? , content = ? where bno = ?";
		
		final int bno = board.getBno();
		final String title = board.getTitle();
		final String content = board.getContent();
		
		
//		System.out.println("게시물 번호 = " + bno);
//		System.out.println("게시물 제목 = " + title);
//		System.out.println("게시물 내용 = " + content);
		
		template.update(sql, new PreparedStatementSetter() {
		
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setInt(3, bno);
		
			}
		});


	}

	@Override
	//게시판 삭제
	public void bBoardDelete(final int bno) {
		final String sql = "delete from build_board where bno = ?";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bno);
		
			}
		});
		
	}

	@Override
	//게시글 상세보기
	public Bboard bBoardContentView(final int bno) {
		ArrayList<Bboard> list = new ArrayList<Bboard>();
		final String sql = "select regdate, hit, writer , title, content from build_board where bno = ?";
		list = (ArrayList<Bboard>) template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bno);
			}
		}, new RowMapper<Bboard>() {

			@Override
			public Bboard mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				//작성일 , 조회수 ,작성자, 제목, 컨텐츠, 글번호
				Bboard board = new Bboard();
				board.setBno(bno);
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setDate(rs.getDate("regdate"));
				board.setHit(rs.getInt("hit"));
				board.setContent(rs.getString("content"));
				return board;

			}
		});
		Bboard boards = list.get(list.size()-1);
		
		
		return boards;
	}

	@Override
	//게시판 입장 시 10개 데이터 주는 코드
	public ArrayList<Bboard> bBoardViewAll(int page) {
		ArrayList<Bboard> list = new ArrayList<Bboard>();
		//System.out.println("Dao Page : " + page);
		final int ends = 10 * page;
		final int start = ends-9;
		
		//page가 1일 경우 1~10
		//page가 2일 경우 11~20
		//page가 3일 경우 21~30
		
		final String sql = "select * from (select rownum numrow, aa.* from(select bno , title, content, writer , regdate, hit from build_board order by bno desc)aa) where numrow between ? and ?";
		list = (ArrayList<Bboard>) template.query(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement pstmt) throws SQLException {
			pstmt.setInt(1, start);
			pstmt.setInt(2, ends);
		}
	}, new RowMapper<Bboard>() {

		@Override
		public Bboard mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Bboard board = new Bboard();
			board.setBno(rs.getInt("bno"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setDate(rs.getDate("regdate"));
			board.setHit(rs.getInt("hit"));
			return board;

		}
	});
		return list;
	}

	@Override
	//페이지 수 조회
	public int bBoardPage() {
		int result = 0;
		
		final String sql = "select count (*) from build_board";
		result = template.queryForObject(sql, Integer.class);
		
		result = (result / 10) +1;
		return result;
	}

		
	
	
	@Override
	public void bBoardHit (final int bno) {
		int result = 0;
		final String sql = "update build_board set hit = hit +1 where bno = ?";
		result = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bno);
		
			}
		});
	
	
	}



}
