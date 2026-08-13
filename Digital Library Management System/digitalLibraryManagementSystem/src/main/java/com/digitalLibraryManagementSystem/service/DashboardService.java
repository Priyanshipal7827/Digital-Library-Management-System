package com.digitalLibraryManagementSystem.service;

import com.digitalLibraryManagementSystem.dao.DashboardDao;
import com.digitalLibraryManagementSystem.entity.DashboardStats;

public class DashboardService {

	private DashboardDao dashboardDao = new DashboardDao();

	public DashboardStats getDashboardStats() {
		DashboardStats stats = dashboardDao.fetchDashboardStats();
		return stats != null ? stats : new DashboardStats();
	}
}
