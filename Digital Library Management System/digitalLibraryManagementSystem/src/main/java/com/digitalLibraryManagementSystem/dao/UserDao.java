package com.digitalLibraryManagementSystem.dao;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.digitalLibraryManagementSystem.entity.User;
import com.digitalLibraryManagementSystem.util.DbUtil;



public class  UserDao
{

	public User checkLogin(String username, String password) {
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Connection conn = null;

		try {
			String sql = "Select * from users where email = ? and password = ?";

			conn =  DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setPhoneNo(rs.getString("phone_no"));
				user.setRole(rs.getString("role"));

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	// Used during registration to prevent duplicate email sign-ups
	public boolean emailExists(String email) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "Select user_id from users where email = ?";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);

			rs = preparedStatement.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean addUser(User user) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "Insert into users(first_name, last_name, email, password, role, phone_no, address, created_at)"
					+ " values(?,?,?,?,?,?,?,?)";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setString(6, user.getPhoneNo());
			preparedStatement.setString(7, user.getAddress());

			Date sqlDate = new Date(user.getCreatedAt().getTime());
			preparedStatement.setDate(8, sqlDate);

			int i = preparedStatement.executeUpdate();
			if(i > 0) {
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}


	public List<User> getAllUserList() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<User> userList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM users WHERE role = 'USER' AND status = 'ACTIVE' ORDER BY user_id DESC";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);

			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				 User user = new User();
				 user.setFirstName(rs.getString("first_name"));
				 user.setLastName(rs.getString("last_name"));
				 user.setUserId(rs.getLong("user_id"));
				 user.setEmail(rs.getString("email"));

				 userList.add(user);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userList;
	}


	public User getUserById(long userId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "Select * from users where role = 'USER' and user_id = ?";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, userId);

			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				 User user = new User();
				 user.setFirstName(rs.getString("first_name"));
				 user.setLastName(rs.getString("last_name"));
				 user.setUserId(rs.getLong("user_id"));
				 user.setEmail(rs.getString("email"));
				 user.setAddress(rs.getString("address"));
				 user.setPhoneNo(rs.getString("phone_no"));

				 return user;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}


	public boolean updateUser(User user) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "Update users set first_name = ?, last_name = ?, phone_no = ?, address = ?"
					+ " where user_id = ?";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getPhoneNo());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setLong(5, user.getUserId());

			int i = preparedStatement.executeUpdate();
			if(i > 0) {
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean deletedUserById(long userId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "UPDATE users SET status='DELETED' WHERE user_id=?";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, userId);

			int i = preparedStatement.executeUpdate();
			if(i != 0) {
				return true;
			}
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	public boolean hardDeleteUserById(long userId) {
	    Connection conn = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        String sql = "DELETE FROM users WHERE user_id = ?";
	        conn = DbUtil.getConnection();
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setLong(1, userId);

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

}

