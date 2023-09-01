package com.lcomputerstudy.testmvc.vo;

public class File {
	private int f_idx;
	private String f_file;
	private String f_date;
	private int b_idx;
	private Board board;
	
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public int getF_idx() {
		return f_idx;
	}
	public void setF_idx(int f_idx) {
		this.f_idx = f_idx;
	}
	public String getF_file() {
		return f_file;
	}
	public void setF_file(String f_file) {
		this.f_file = f_file;
	}
	public String getF_date() {
		return f_date;
	}
	public void setF_date(String f_date) {
		this.f_date = f_date;
	}
	
}
