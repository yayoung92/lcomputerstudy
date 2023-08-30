package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import com.lcomputerstudy.testmvc.datebase.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
import com.lcomputerstudy.testmvc.vo.File;

public class FileDAO {
	private static FileDAO dao = null;
	private FileDAO() {
		
	}
	public static FileDAO getInstance() {
		if(dao == null) {
			dao = new FileDAO();
		}
		return dao;
	}
	public List<File> getFileList() {
		List<File> fileList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = new Board();
    	User user = new User();
    	File file = new File();
    	
    	try {
    		conn = DBConnection.getConnection();
    		String query = "select * from file";
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			 file = new File();
                 file.setF_idx(rs.getInt("f_idx"));
                 file.setF_title(rs.getString("f_title"));
                 file.setF_name(rs.getString("f_name"));
                 file.setF_cate(rs.getString("f_cate"));
                 file.setF_ofile(rs.getString("f_ofile"));
                 file.setF_file(rs.getString("f_file"));
                 file.setF_date(rs.getString("f_date"));
                 fileList.add(file);
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
		return fileList;
	}
	public String getFilename(File file) {
		String content
	}
}