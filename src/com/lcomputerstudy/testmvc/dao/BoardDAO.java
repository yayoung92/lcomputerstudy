package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.datebase.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.User;

public class BoardDAO {
	private static BoardDAO dao = null;
	private BoardDAO() {
		
	}
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	public ArrayList<Board> getBoards() {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		Board board = new Board();
    	User user = new User();
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("select * from board join user on board.u_idx = user.u_idx")
					.toString();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();

	        while(rs.next()){
	        	board = new Board();
	        	user = new User();
       	       	board.setB_idx(rs.getInt("b_idx"));
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_groub(board.getB_idx());
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_view(rs.getString("b_view"));
       	       	user.setU_id(rs.getString("user.u_id"));
       	       	board.setUser(user);
       	       	board.setB_date(rs.getString("b_date"));
       	       	list.add(board);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			
			String query = new StringBuilder()
						.append("insert into board (b_title,b_content,b_view,u_idx,b_date) ")
						.append("value (?, ?, 0, ?, now()) ")
						.toString();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setInt(3, board.getU_idx());
			pstmt.executeUpdate();
			pstmt.close();
			
			query = new StringBuilder()
					.append("update board set b_group = last_insert_id() where b_idx = last_insert_id()")
					.toString();
			pstmt = conn.prepareStatement(query);
		//	pstmt.setInt(0, board.getB_groub());
			pstmt.executeUpdate();
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	public Board detailBoard(int bIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		User user = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from board join user on board.u_idx = user.u_idx where b_idx=?";
		   	pstmt = conn.prepareStatement(query);
		   	pstmt.setInt(1, bIdx);
		    rs = pstmt.executeQuery();
		
		    while(rs.next()){  
		    	board = new Board();
		    	user = new User();
		    	board.setB_idx(rs.getInt("b_idx"));
		    	user.setU_id(rs.getString("user.u_id"));
		    	board.setUser(user);
		    	board.setB_title(rs.getString("b_title"));
		    	board.setB_content(rs.getString("b_content"));
		    	board.setB_date(rs.getString("b_date"));
		    }
		
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
		
		return board;
	}
	public void boardViews(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
		    String query = "update board set b_view = b_view +1 where b_idx=" + boardNo;
		   	pstmt = conn.prepareStatement(query);
		   	pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Board getBoard(int bIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		User user = null;
		
		try {
			conn = DBConnection.getConnection();
	        String query = "select * from board join user on user.u_idx = board.u_idx where b_idx=?";
	  	 	pstmt = conn.prepareStatement(query);
		   	pstmt.setInt(1, bIdx);
	    	rs = pstmt.executeQuery();
	
	    	while(rs.next()){
	    		board = new Board();
	    		user = new User();
	    		board.setB_idx(rs.getInt("b_idx"));
	    		board.setB_title(rs.getString("b_title"));
	    		board.setB_content(rs.getString("b_content"));
	    		user.setU_id(rs.getString("user.u_id"));
	    		board.setUser(user);
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		return board;
	}
	public void editBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "update board set b_title = ?, b_content = ?, b_date = now() where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setInt(3, board.getB_idx());
			pstmt.executeUpdate();
		} catch( Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	public void deleteBoard(int bIdx) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "delete from board where b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bIdx);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Board replyBoard(Board board) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "update board set re_level = re_level+1 where ref=? and re_level> ?";
			pstmt = conn.prepareCall(query);
            pstmt.setInt(1, board.getB_groub());
            pstmt.setInt(2, board.getB_order());
            pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return board;
	}
}


	
