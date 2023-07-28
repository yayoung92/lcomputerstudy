package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

@WebServlet("*.do")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		String pw = null;
		String idx = null;
		User user = null;
		Board board = null;
		int uIdx = 0;
		int bIdx = 0;
		int usercount = 0;
		int page = 1;
		HttpSession session = null;
		command = checkSession(request, response, command);
		
		switch(command) {
			case "/user-list.do":
				String reqPage = request.getParameter("page");
				if(reqPage != null) 
					page = Integer.parseInt(reqPage);
				//	page = (page-1)*3;
				UserService userService = UserService.getInstance();
				usercount = userService.getUsersCount();
				
				Pagination pagination = new Pagination();
				pagination.setPage(page);
				pagination.setCount(usercount);
				pagination.init();
				
				List<User> list = userService.getUsers(pagination);

				request.setAttribute("list", list);
				request.setAttribute("pagination", pagination);
				view = "user/list";
				break;
			case "/user-insert.do":
				view = "user/insert";
				break;
			case "/user-insert-process.do":
				user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user);
						
				view = "user/insert-result";
				break;
			case "/user-detail.do":
				uIdx = Integer.parseInt(request.getParameter("u_idx"));
				userService = UserService.getInstance();
				user = userService.detailUser(uIdx);
				request.setAttribute("user", user);
				
				view = "user/detail";
				break;
			case "/user-edit.do": //회원 수정 창 - 회원상세정보 받아오기
				uIdx = Integer.parseInt(request.getParameter("u_idx"));
				userService = UserService.getInstance();
				user = userService.getUser(uIdx);
				request.setAttribute("user", user);
				view = "user/edit";

				break;
			case "/user-edit-process.do":  //회원 수정 값 보내기
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				user.setU_id(request.getParameter("edit_id"));
				user.setU_pw(request.getParameter("edit_password"));
				user.setU_name(request.getParameter("edit_name"));
				user.setU_tel(request.getParameter("edit_tel1") + "-" + request.getParameter("edit_tel2") + "-" + request.getParameter("edit_tel3"));
				user.setU_age(request.getParameter("edit_age"));
				
				userService = UserService.getInstance();
				userService.editUser(user);
				
				view = "user/edit-result";
				break;
			case "/user-delete-process.do":
				uIdx = Integer.parseInt(request.getParameter("u_idx"));
				userService = UserService.getInstance();
				userService.deleteUser(uIdx);
				request.setAttribute("user", user);
				
				view = "user/delete";
				break;
			case "/user-login.do":
				view="user/login";
				break;
			case "/user-login-process.do":
				idx = request.getParameter("login_id");
				pw = request.getParameter("login_password");
				
				userService = UserService.getInstance();
				user = userService.loginUser(idx,pw);
				
				if(user != null) {
					session = request.getSession();
					session.setAttribute("user", user);
					
					view="user/login-result";
				} else {
					view="user/login-fail";
				}
				break;
			case "/logout.do":
				session = request.getSession();
				session.invalidate();
				view="user/login";
				break;
				
			case "/access-denied.do":
				view = "user/access-denied";
				break;
				
			case "/board-b_list.do":
				BoardService boardService = BoardService.getInstance();
				ArrayList<Board> blist = boardService.getBoards();

				request.setAttribute("b_list", blist);
				view = "board/b_list";
				break;
			case "/board-b_insert.do":
				view = "board/b_insert";
				break;
			case "/board-b_insert-process.do":
				session = request.getSession();
				
				board = new Board();
				board.setB_title(request.getParameter("title"));
				board.setB_content(request.getParameter("content"));
				user = (User)session.getAttribute("user");
		//		user.setU_idx((User)session.getAttribute(request.getParameter("user")));
				board.setUser(user);
				
				boardService = BoardService.getInstance();
				boardService.insertBoard(board);
						
				view = "board/b_insert-result";
				break;
			case "/board-b_detail.do":
				bIdx = Integer.parseInt(request.getParameter("b_idx"));
				boardService = BoardService.getInstance();
				board = boardService.detailBoard(bIdx);
				
				request.setAttribute("board", board);
				boardService.boardViews(bIdx);  //클릭할수록 조회수 증가
				
				view = "board/b_detail";
				break;
			case "/board-b_edit.do": //게시글 수정 창 - 게시글정보 받아오기
				bIdx = Integer.parseInt(request.getParameter("b_idx"));
				boardService = BoardService.getInstance();
				board = boardService.getBoard(bIdx);
				request.setAttribute("board", board);
				view = "board/b_edit";

				break;
			case "/board-b_edit-process.do":  //게시글 수정 값 보내기
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board.setB_title(request.getParameter("edit_b_title"));
				board.setB_content(request.getParameter("edit_b_content"));
				board.setB_date(request.getParameter("edit_b_date"));
				
				boardService = BoardService.getInstance();
				boardService.editBoard(board);
				
				view = "board/b_edit-result";
				break;
			case "/board-b_delete-process.do":
				bIdx = Integer.parseInt(request.getParameter("b_idx"));
				boardService = BoardService.getInstance();
				boardService.deleteBoard(bIdx);
				request.setAttribute("board", board);
				
				view = "board/b_delete";
				break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view + ".jsp");
		rd.forward(request, response);
	}
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
			};
		
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}
}