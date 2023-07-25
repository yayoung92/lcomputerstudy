package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.datebase.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;

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
		try {
			conn = DBConnection.getConnection();
			String query = "select * from board";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();

	        while(rs.next()){     
	        	Board board = new Board();
       	       	board.setB_idx(rs.getInt("b_idx"));
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_view(rs.getString("b_view"));
       	       	board.setB_writer(rs.getString("b_writer"));
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
			String sql = "insert into board(b_title,b_content,b_view,b_writer,b_date) values(?,?,0,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_view());
			pstmt.setString(3, board.getB_writer());
			pstmt.setString(4, board.getB_date());
			pstmt.executeUpdate();
		} catch( Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
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
		
		try {
			conn = DBConnection.getConnection();
		    String query = "select * from board where b_idx=?";
		   	pstmt = conn.prepareStatement(query);
		   	pstmt.setInt(1, bIdx);
		    rs = pstmt.executeQuery();
		
		    while(rs.next()){  
		    	board = new Board();
		    	board.setB_idx(rs.getInt("b_idx"));
		    	board.setB_writer(rs.getString("b_writer"));
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
		    String query = "UPDATE board SET b_view = b_view +1 where b_idx=" + boardNo;
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
		
		try {
			conn = DBConnection.getConnection();
	  	    String query = "select * from board where b_idx=?";
	  	 	pstmt = conn.prepareStatement(query);
		   	pstmt.setInt(1, bIdx);
		   	
	    	rs = pstmt.executeQuery();
	
	    	while(rs.next()){
	    		board = new Board();
	    		board.setB_idx(rs.getInt("b_idx"));
	    		board.setB_title(rs.getString("b_title"));
	    		board.setB_content(rs.getString("b_content"));
	    		board.setB_view(rs.getString("b_view"));
	    		board.setB_writer(rs.getString("b_writer"));
	    		board.setB_date(rs.getString("b_date"));
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
			String sql = "UPDATE board SET b_title = ?, b_content = ?, b_writer =?, b_date = ? where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_writer());
			pstmt.setString(4, board.getB_date());
			pstmt.setInt(5, board.getB_idx());
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
}


	