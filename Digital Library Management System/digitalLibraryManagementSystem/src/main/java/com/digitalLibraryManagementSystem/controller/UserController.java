package com.digitalLibraryManagementSystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.digitalLibraryManagementSystem.entity.User;
import com.digitalLibraryManagementSystem.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action: " + action);

		if ("showAddUser".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/addUser.jsp");
			dispatcher.forward(request, response);
		} else if ("addUser".equalsIgnoreCase(action)) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNo = request.getParameter("phone");
			String address = request.getParameter("address");

			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPhoneNo(phoneNo);
			user.setAddress(address);
			user.setRole("USER");

			String randomPass = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
			user.setPassword(randomPass);
			user.setCreatedAt(new Date());

			UserService userService = new UserService();
			boolean addFlag = userService.addUser(user);
			if (addFlag) {
				List<User> userList = new ArrayList<>();

				userList = userService.getAllUserList();
				if (userList != null && userList.size() > 0) {
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Something went wrong");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/addUser.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage", "Something went wrong");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/addUser.jsp");
				dispatcher.forward(request, response);
			}
		} else if ("allUserList".equalsIgnoreCase(action)) {
			List<User> userList = new ArrayList<>();

			UserService userService = new UserService();
			userList = userService.getAllUserList();

			if (userList != null && userList.size() > 0) {
				request.setAttribute("userList", userList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userList.jsp");
				dispatcher.forward(request, response);
			}
		} else if ("viewUser".equalsIgnoreCase(action)) {
			String userId = request.getParameter("userId");

			UserService userService = new UserService();
			User user = userService.getUserById(Long.parseLong(userId));

			if (user != null) {
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/editUser.jsp");
				dispatcher.forward(request, response);
			} else {
				List<User> userList = new ArrayList<>();
				userList = userService.getAllUserList();

				if (userList != null && userList.size() > 0) {
					request.setAttribute("errorMessage", "User not found!!");
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userList.jsp");
					dispatcher.forward(request, response);
				}
			}
		} else if ("updateUser".equalsIgnoreCase(action)) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String phoneNo = request.getParameter("phone");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			long userId = Long.parseLong(request.getParameter("userId"));

			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhoneNo(phoneNo);
			user.setAddress(address);
			user.setEmail(email);
			user.setUserId(userId);

			UserService userService = new UserService();
			boolean addFlag = userService.updateUser(user);
			if (addFlag) {
				request.setAttribute("user", user);
				request.setAttribute("successMessage", "User updated successfully!!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/editUser.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("user", user);
				request.setAttribute("errorMessage", "Something went wrong");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/editUser.jsp");
				dispatcher.forward(request, response);
			}

		}

		else if ("deleteUser".equalsIgnoreCase(action)) {

			String userId = request.getParameter("userId");

			System.out.println("DELETE ACTION");
			System.out.println("USER ID = " + userId);

			UserService userService = new UserService();

			boolean deleteFlag = userService.hardDeleteUserById(Long.parseLong(userId));

			System.out.println("DELETE RESULT = " + deleteFlag);

			List<User> userList = userService.getAllUserList();

			request.setAttribute("userList", userList);

			if (deleteFlag) {
				request.setAttribute("successMessage", "User deleted successfully!");
			} else {
				request.setAttribute("errorMessage", "User deletion failed!");
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userList.jsp");

			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
