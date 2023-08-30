package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.testmvc.dao.FileDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.File;

public class FileService {
	private static FileService service = null;
	private static FileDAO dao = null;
	
	private FileService() {
		
	}
	public static FileService getInstance() {
		if(service == null) {
			service = new FileService();
			dao = FileDAO.getInstance();
		}
		return service;
	}
	public List<File> getFileList() {
		return dao.getFileList();
	}
}
