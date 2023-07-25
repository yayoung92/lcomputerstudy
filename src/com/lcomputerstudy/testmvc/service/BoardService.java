package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.vo.Board;

public class BoardService {
	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	private BoardService() {
		
	}
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	public ArrayList<Board> getBoards() {
		return dao.getBoards();
	}
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	public Board detailBoard(int bIdx) {
		return dao.detailBoard(bIdx);
	}
	public void boardViews(int bIdx) {
		dao.boardViews(bIdx);
	}
	public void editBoard(Board board) {
		 dao.editBoard(board);
	}
	public Board getBoard(int bIdx) {
		return dao.getBoard(bIdx);
	}
}


