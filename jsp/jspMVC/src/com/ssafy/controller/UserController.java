package com.ssafy.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.dto.User;
import com.ssafy.service.UserService;
import com.ssafy.service.UserServiceImpl;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService = UserServiceImpl.getInstance();

    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		
		if(action.equals("join")) {
			System.out.println("join");
			doJoin(request,response);
		}else if(action.equals("list")) {
			System.out.println("list");
			doList(request,response);
		}else if(action.equals("detail")) {
			System.out.println("user의 detail 정보 클릭됨");
			doDetail(request,response);
		}else if(action.equals("login")) {
			System.out.println("login 시도");
			doLogin(request,response);
		}else if(action.equals("logout")) {
			doLogout(request,response);
		}else if(action.equals("modify")) {
			System.out.println("수정");
			doModify(request,response);
		}else if(action.equals("remove")) {
			System.out.println("삭제");
			doRemove(request,response);
		}
		
	}



	private void doRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("id");
		boolean isSuccess = userService.deleteUserById(userId);
		System.out.println(isSuccess);
		HttpSession session = request.getSession();
		session.setAttribute("hasMsg", true);
		session.setAttribute("msg", "유저 삭제 성공");
		response.sendRedirect("/board6_final/user?action=list");
	}
	private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("user_id");
		System.out.println(userId);
		String userPassword = request.getParameter("user_password");
		String userName = request.getParameter("user_name");
		String userEmail = request.getParameter("user_email");
		
		
		String changePassword = request.getParameter("change_password");
		String changePassword2 = request.getParameter("change_password2");
		
		User userBeforeMod = userService.findUserById(userId);
		User userAfterMod = new User(userId,userName,userPassword,userEmail);
		HttpSession session = request.getSession();
		
		
		if(userPassword.equals(userBeforeMod.getPw())) {
			if(!changePassword.equals(changePassword2)) {
				session.setAttribute("hasMsg", true);
				session.setAttribute("msg","변경 비밀번호 불일치");
				response.sendRedirect(request.getRequestURL().toString()+"?action=list");
				return;
			}
			if(!changePassword.equals("") && changePassword.equals(changePassword2)) {
				userAfterMod.setPw(changePassword2);
			}
			userService.modifyUserById(userId, userAfterMod);
			
			
			session.setAttribute("hasMsg", true);
			session.setAttribute("msg","회원정보 수정 성공");
			response.sendRedirect(request.getRequestURL().toString()+"?action=list");	
		}else {
			session.setAttribute("hasMsg", true);
			session.setAttribute("msg","비밀번호가 틀립니다");
			response.sendRedirect(request.getRequestURL().toString()+"?action=list");
		}
	}


	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/board6_final/index.jsp");
	}


	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPassword");
		System.out.printf("userId=%s, userPw=%s\n", userId,userPw);
		User user = userService.findUserById(userId);
		if(user.getPw().equals(userPw)) {
			System.out.println("zz");
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",user);
			response.sendRedirect("/board6_final/index.jsp");
		}
				
	}


	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("id");
		User user = userService.findUserById(userId);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/userDetail.jsp");
		dispatcher.forward(request, response);
		
	}


	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userList = userService.getUserList();
		request.setAttribute("userList", userList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/userList.jsp");
		dispatcher.forward(request, response);
	}


	private void doJoin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("user_id");
		String userName = request.getParameter("user_name");
		String userPw = request.getParameter("user_pw");
		String userEmail = request.getParameter("user_email");
		User user = new User(userId,userName,userPw,userEmail);
		userService.joinUser(user);
		response.sendRedirect("/board6_final/index.jsp");

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
