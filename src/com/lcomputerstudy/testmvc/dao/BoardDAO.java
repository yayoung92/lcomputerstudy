package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.testmvc.datebase.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
import com.lcomputerstudy.testmvc.vo.File;

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

	public List<Board> getBoards(String keyWord, String search, Pagination pagaination) {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		Board board = new Board();
    	User user = new User();
    	int pageNum = pagaination.getPageNum();
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from board join user on board.u_idx = user.u_idx ";
				if(keyWord == null || keyWord.equals("none")) {
					query += "";
				}
				else if(keyWord.equals("title")) {
					query += "where b_title like ? ";
				} else if(keyWord.equals("tandc")) {
					query += "where b_title like ? or b_content like ? ";
				} else if(keyWord.equals("content")) {
					query += "where b_content like ? ";
				} else if(keyWord.equals("writer")) {
					query += "where user.u_id like ? ";
				} else {
					query += "";
				}
				
			query += "order by b_group desc, b_order asc limit ?, ?";
			pstmt = conn.prepareStatement(query);
				if(keyWord == null || keyWord.equals("none")) {
					pstmt.setInt(1, pageNum);
					pstmt.setInt(2, Pagination.perPage);
				} else if(keyWord.equals("title") || keyWord.equals("content") || keyWord.equals("writer")) {
					pstmt.setString(1, "%" + search + "%");
					pstmt.setInt(2, pageNum);
					pstmt.setInt(3, Pagination.perPage);
				} else if(keyWord.equals("tandc")) {
					pstmt.setString(1, "%" + search + "%");
					pstmt.setString(2, "%" + search + "%");
					pstmt.setInt(3, pageNum);
					pstmt.setInt(4, Pagination.perPage);
				} else {
					pstmt.setInt(1, pageNum);
					pstmt.setInt(2, Pagination.perPage);
				}
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
	        while(rs.next()){
	        	board = new Board();
	        	user = new User();
       	       	board.setB_idx(rs.getInt("b_idx"));
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_view(rs.getString("b_view"));
       	       	user.setU_id(rs.getString("user.u_id"));
       	       	board.setUser(user);
       	       	board.setB_date(rs.getString("b_date"));
       	       	board.setB_order(rs.getInt("b_order"));
       	       	board.setB_depth(rs.getInt("b_depth"));
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
	public int getBoardsCount(String keyWord, String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM board JOIN user ON board.u_idx = user.u_idx ";
				if(keyWord == null || keyWord.equals("none")) {
					query += "";
				}
				else if(keyWord.equals("title")) {
					query += "where b_title like ? ";
				} else if(keyWord.equals("tandc")) {
					query += "where b_title like ? or b_content like ? ";
				} else if(keyWord.equals("content")) {
					query += "where b_content like ? ";
				} else if(keyWord.equals("writer")) {
					query += "where user.u_id like ? ";
				} else {
					query += "";
				}
			pstmt = conn.prepareStatement(query);
			if(keyWord == null || keyWord.equals("none")) {
				
			} else if(keyWord.equals("tandc")) {
				pstmt.setString(1, "%" + search + "%");
				pstmt.setString(2, "%" + search + "%");
			} else if(keyWord.equals("title") || keyWord.equals("content") || keyWord.equals("writer")) {
				pstmt.setString(1, "%" + search + "%");
			} else {
				
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch(Exception e) {
			
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
		
	}
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			
			String query = new StringBuilder()
						.append("insert into board (b_title,b_content,b_view,u_idx,b_date,b_order, b_depth) ")
						.append("value (?, ?, 0, ?, now(), 1, 0) ")
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
	    		board.setB_group(rs.getInt("b_group"));
	    		board.setB_order(rs.getInt("b_order"));
	    		board.setB_depth(rs.getInt("b_depth"));
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
	public void reBoard(Board board) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
            String query = "update board set b_order=b_order+1 where b_group=? and b_order >?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, board.getB_group());
            pstmt.setInt(2, board.getB_order());
            pstmt.executeUpdate();
            pstmt.close();
            
            query = new StringBuilder()
					.append("insert into board (b_title,b_content,b_view,u_idx,b_date,b_group,b_order, b_depth) ")
					.append("value (?, ?, 0, ?, now(), ?,?, ?) ")
					.toString();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, board.getB_title());
            pstmt.setString(2, board.getB_content());
            pstmt.setInt(3, board.getU_idx());
            pstmt.setInt(4, board.getB_group());
            pstmt.setInt(5, board.getB_order()+1);
            pstmt.setInt(6, board.getB_depth()+1);
            pstmt.executeUpdate();
			
		} catch(Exception e) {
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
	public List<Comment> getComments(int bIdx) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		ArrayList<Comment> list = null;
		Comment comment = null;
		Board board = null;
		User user = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from `comment` left join board on board.b_idx = `comment`.b_idx left join user on user.u_idx = `comment`.u_idx where `comment`.b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bIdx);
			rs = pstmt.executeQuery();
			list = new ArrayList<Comment>();

	        while(rs.next()){
	        	board = new Board();
	        	user = new User();
	        	comment = new Comment();
	        	comment.setC_idx(rs.getInt("c_idx"));
	        	comment.setC_content(rs.getString("c_content"));
       	       	comment.setC_date(rs.getString("c_date"));
       	       	board.setB_idx(rs.getInt("board.b_idx"));
       	       	comment.setBoard(board);
       	       	user.setU_id(rs.getString("user.u_id"));
       	       	comment.setUser(user);
       	       	comment.setC_group(rs.getInt("c_group"));
       	       	comment.setC_order(rs.getInt("c_order"));
       	       	comment.setC_depth(rs.getInt("c_depth"));
       	       	list.add(comment);
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
	public Board getCom(int bIdx) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Comment comment = null;
		Board board = null;
		User user = null;
		List<Comment> commentList = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					 .append("SELECT      * ")
				     .append("FROM        board ta ")
				     .append("LEFT JOIN   user tb ON ta.u_idx = tb.u_idx ")
			         .append("LEFT JOIN   `comment` tc ON ta.b_idx = tc.b_idx ")
			         .append("LEFT JOIN   user td ON tc.u_idx = td.u_idx ")
		  	         .append("WHERE       ta.b_idx = ?")
		 	         .toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bIdx);
			rs = pstmt.executeQuery();

			int tmpBIdx = 0;
			commentList = new ArrayList<>();
			
	        while(rs.next()){

	        	if (board == null || board.getB_idx() != tmpBIdx) {

	        		board = new Board();
	        		user = new User();
	        		tmpBIdx = rs.getInt("b_idx");
	        		
		        	board.setB_idx(tmpBIdx);
		        	board.setB_title(rs.getString("b_title"));
			    	board.setB_content(rs.getString("b_content"));
			    	board.setB_date(rs.getString("b_date"));
			    		
		        	user.setU_id(rs.getString("tb.u_id"));
			    	board.setUser(user);
			    	
	        	}
	        	
	        	comment = new Comment();
	        	user = new User();
	        	comment.setC_idx(rs.getInt("c_idx"));
	        	comment.setB_idx(rs.getInt("b_idx"));
	    		comment.setC_content(rs.getString("c_content"));
	    		
	    		user.setU_id(rs.getString("td.u_id"));
	    		comment.setUser(user);
	    		
	    		comment.setC_date(rs.getString("c_date"));
	    		comment.setC_group(rs.getInt("c_group"));
	    		comment.setC_order(rs.getInt("c_order"));
	    		comment.setC_depth(rs.getInt("c_depth"));
       	       	
       	       	commentList.add(comment);
	        }
	        
	        board.setCommentList(commentList);

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
		return board;
	}
	public void insertComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
						.append("insert into `comment` (c_content,c_date,b_idx,u_idx,c_order,c_depth) ")
						.append("value (?, now(),?,?,1,0) ")
						.toString();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getB_idx());
			pstmt.setInt(3, comment.getU_idx());
			pstmt.executeUpdate();
			pstmt.close();
			
			query = new StringBuilder()
					.append("update `comment` set c_group = last_insert_id() where c_idx = last_insert_id()")
					.toString();
			pstmt = conn.prepareStatement(query);
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
	public void deleteComment(int cIdx) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "delete from `comment` where c_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cIdx);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void reComment(Comment comment) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "update `comment` set c_order=c_order+1 where c_group=? and c_order >?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment.getC_group());
            pstmt.setInt(2, comment.getC_order());
			pstmt.executeUpdate();
			pstmt.close();
			
			query = new StringBuilder()
					.append("insert into `comment` (c_content,c_date,b_idx,u_idx,c_group,c_order, c_depth) ")
					.append("value (?, now(), ?, ?, ?, ?, ?) ")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getB_idx());
			pstmt.setInt(3, comment.getU_idx());
			pstmt.setInt(4, comment.getC_group());
			pstmt.setInt(5, comment.getC_order()+1);
			pstmt.setInt(6, comment.getC_depth()+1);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
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
	public Comment getComment(int cIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		Comment comment = null;
		
		try {
			conn = DBConnection.getConnection();
	        String query = "select * from `comment` left join user on user.u_idx = `comment`.u_idx left join board on board.b_idx = `comment`.b_idx where c_idx=?";
	  	 	pstmt = conn.prepareStatement(query);
		   	pstmt.setInt(1, cIdx);
	    	rs = pstmt.executeQuery();
	
	    	while(rs.next()){
	    		user = new User();
	    		comment = new Comment();

	    		comment.setB_idx(rs.getInt("b_idx"));
	    		comment.setC_content(rs.getString("c_content"));
	    		
	    		user.setU_id(rs.getString("user.u_id"));
	    		comment.setUser(user);
	    		
	    		comment.setC_group(rs.getInt("c_group"));
	    		comment.setC_order(rs.getInt("c_order"));
	    		comment.setC_depth(rs.getInt("c_depth"));
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		return comment;
	}
	public void ajeditComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "update `comment` set c_content = ?, c_date = now() where c_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getC_idx());
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


	
