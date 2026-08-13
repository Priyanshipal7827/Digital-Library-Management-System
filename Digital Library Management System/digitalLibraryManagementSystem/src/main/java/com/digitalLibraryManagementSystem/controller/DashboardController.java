package com.digitalLibraryManagementSystem.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.digitalLibraryManagementSystem.entity.BookIssued;
import com.digitalLibraryManagementSystem.entity.DashboardStats;
import com.digitalLibraryManagementSystem.service.BookService;
import com.digitalLibraryManagementSystem.service.DashboardService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/DashboardController")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DashboardService dashboardService = new DashboardService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("viewDashboard".equalsIgnoreCase(action)) {
			DashboardService dashboardService = new DashboardService();
			DashboardStats dashboardStats = dashboardService.getDashboardStats();

			BookService bookService = new BookService();
			List<BookIssued> issuedList = bookService.getIssuedBookListForDashboard();

			if (issuedList != null && issuedList.size() > 0) {
				LocalDate today = LocalDate.now();

				for (BookIssued bookIssued : issuedList) {
					LocalDate dueDate = bookIssued.getDueDate();

					if (dueDate.isBefore(today)) {
						bookIssued.setDueDayStatus("Overdue");
					} else if (dueDate.isEqual(today)) {
						bookIssued.setDueDayStatus("Due Today");
					} else {
						bookIssued.setDueDayStatus("Active");
					}
				}
			}
			request.setAttribute("issuedList", issuedList);
			request.setAttribute("dashboardStats", dashboardStats);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dashboard.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/error.jsp");
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
		doGet(request, response);
	}
}
