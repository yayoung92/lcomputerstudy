package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;

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
	public void deleteBoard(int bIdx) {
		dao.deleteBoard(bIdx);
	}
	public void reBoard(Board board) {
		dao.reBoard(board);
	}
	public List<Comment> getComments(int bIdx) {
		return dao.getComments(bIdx);
	}
	public void insertComment(Comment comment) {
		dao.insertComment(comment);
	}
	public Board getCom(int bIdx) {
		return dao.getCom(bIdx);
	}
	public void deleteComment(int cIdx) {
		dao.deleteComment(cIdx);
	}
	public void reComment(Comment comment) {
		dao.reComment(comment);
	}
	public Comment getComment(int cIdx) {
		return dao.getComment(cIdx);
	}
	public void ajeditComment(Comment comment) {
		dao.ajeditComment(comment);
	}
}


