package com.digitalLibraryManagementSystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.digitalLibraryManagementSystem.entity.ContactMessage;
import com.digitalLibraryManagementSystem.util.DbUtil;

public class ContactDao {

	public boolean addMessage(ContactMessage msg) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			String sql = "Insert into contact_messages(name, email, message, created_at) values(?,?,?,?)";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, msg.getName());
			preparedStatement.setString(2, msg.getEmail());
			preparedStatement.setString(3, msg.getMessage());
			preparedStatement.setDate(4, new Date(msg.getCreatedAt().getTime()));

			int i = preparedStatement.executeUpdate();
			return i > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<ContactMessage> getAllMessages() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<ContactMessage> list = new ArrayList<>();

		try {
			String sql = "SELECT * FROM contact_messages ORDER BY message_id DESC";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ContactMessage msg = new ContactMessage();
				msg.setMessageId(rs.getLong("message_id"));
				msg.setName(rs.getString("name"));
				msg.setEmail(rs.getString("email"));
				msg.setMessage(rs.getString("message"));
				msg.setCreatedAt(rs.getDate("created_at"));
				list.add(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
}