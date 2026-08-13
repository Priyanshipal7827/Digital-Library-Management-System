package com.digitalLibraryManagementSystem.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.digitalLibraryManagementSystem.entity.ContactMessage;
import com.digitalLibraryManagementSystem.service.ContactService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ContactController")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("showContactForm".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/contact.jsp");
			dispatcher.forward(request, response);
		}

		else if ("submitContact".equalsIgnoreCase(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String message = request.getParameter("message");

			ContactMessage msg = new ContactMessage();
			msg.setName(name);
			msg.setEmail(email);
			msg.setMessage(message);
			msg.setCreatedAt(new Date());

			ContactService contactService = new ContactService();
			boolean flag = contactService.addMessage(msg);

			if (flag) {
				request.setAttribute("successMessage", "Your message has been sent!");
			} else {
				request.setAttribute("errorMessage", "Something went wrong. Please try again.");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/contact.jsp");
			dispatcher.forward(request, response);
		}

		else if ("viewMessages".equalsIgnoreCase(action)) {
			ContactService contactService = new ContactService();
			List<ContactMessage> messages = contactService.getAllMessages();
			request.setAttribute("messages", messages);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/contactMessages.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}