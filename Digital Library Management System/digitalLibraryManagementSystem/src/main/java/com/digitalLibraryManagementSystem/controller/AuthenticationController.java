package com.digitalLibraryManagementSystem.controller;

import java.io.IOException;
import java.util.Date;

import com.digitalLibraryManagementSystem.entity.User;
import com.digitalLibraryManagementSystem.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthController
 * Handles Login, Register, and Logout for the Library Management System
 */
@WebServlet("/AuthController")
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthenticationController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("auth action: " + action);

		if ("showLogin".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
		}

		else if ("showRegister".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
			dispatcher.forward(request, response);
		}

		else if ("login".equalsIgnoreCase(action)) {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (email != null) {
		email = email.trim();
	}
    if (password != null) {
		password = password.trim();
	}

    System.out.println("LOGIN ATTEMPT -> email: [" + email + "] password: [" + password + "]");

    UserService userService = new UserService();
    User user = userService.checkLogin(email, password);

    System.out.println("LOGIN RESULT -> user found: " + (user != null));

    if (user != null) {
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", user);
        session.setAttribute("userRole", user.getRole());
        response.sendRedirect("BookController?action=allBookList");
    } else {
        request.setAttribute("errorMessage", "Invalid email or password");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(request, response);
    }
}

		else if ("register".equalsIgnoreCase(action)) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phoneNo = request.getParameter("phone");
			String address = request.getParameter("address");

			UserService userService = new UserService();

			if (userService.emailExists(email)) {
				request.setAttribute("errorMessage", "An account with this email already exists.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
				dispatcher.forward(request, response);
				return;
			}

			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhoneNo(phoneNo);
			user.setAddress(address);
			user.setRole("USER");
			user.setCreatedAt(new Date());

			boolean registerFlag = userService.addUser(user);

			if (registerFlag) {
				request.setAttribute("successMessage", "Registration successful! Please log in.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Registration failed. Please try again.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
				dispatcher.forward(request, response);
			}
		}

		else if ("logout".equalsIgnoreCase(action)) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("AuthController?action=showLogin");
		}

		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}