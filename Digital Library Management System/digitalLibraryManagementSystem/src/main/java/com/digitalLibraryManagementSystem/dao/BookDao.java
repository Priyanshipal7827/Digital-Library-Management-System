package com.digitalLibraryManagementSystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.digitalLibraryManagementSystem.entity.Book;
import com.digitalLibraryManagementSystem.entity.BookIssued;
import com.digitalLibraryManagementSystem.entity.User;
import com.digitalLibraryManagementSystem.util.DbUtil;

public class BookDao {

	public boolean addBook(Book book) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "Insert into books(title, author, category, isbn, publisher, total_copies, available_copies, created_at, status)"
					+ " values(?,?,?,?,?,?,?,?,?)";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getCategory());
			preparedStatement.setString(4, book.getIsbn());
			preparedStatement.setString(5, book.getPublisher());
			preparedStatement.setInt(6, book.getTotalCopies());
			preparedStatement.setInt(7, book.getAvailableCopies());

			Date sqlDate = new Date(book.getCreatedAt().getTime());
			preparedStatement.setDate(8, sqlDate);
			preparedStatement.setString(9, book.getStatus());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				return true;
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

		return false;
	}

	public boolean updateBook(Book book) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "update books set title = ?, author = ?, category =? , isbn = ?, publisher =?, total_copies =?"
					+ " where book_id = ?";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getCategory());
			preparedStatement.setString(4, book.getIsbn());
			preparedStatement.setString(5, book.getPublisher());
			preparedStatement.setInt(6, book.getTotalCopies());
			preparedStatement.setLong(7, book.getBookId());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				return true;
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

		return false;
	}

	public List<Book> getAllBookList() {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    List<Book> bookList = new ArrayList<>();

    try {

    	String sql = "SELECT * FROM books WHERE status != 'DELETED' ORDER BY book_id DESC";

        conn = DbUtil.getConnection();
        preparedStatement = conn.prepareStatement(sql);

        rs = preparedStatement.executeQuery();

        while (rs.next()) {

            Book book = new Book();

            book.setBookId(rs.getLong("book_id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            book.setIsbn(rs.getString("isbn"));
            book.setPublisher(rs.getString("publisher"));
            book.setTotalCopies(rs.getInt("total_copies"));
            book.setAvailableCopies(rs.getInt("available_copies"));
            book.setStatus(rs.getString("status"));

            bookList.add(book);
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

    return bookList;
}

	public List<Book> getAllBookByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	public Book getBookById(long bookId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "Select * from books where book_id = ?";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, bookId);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setCategory(rs.getString("category"));
				book.setIsbn(rs.getString("isbn"));
				book.setPublisher(rs.getString("publisher"));
				book.setTotalCopies(rs.getInt("total_copies"));
				book.setAvailableCopies(rs.getInt("available_copies"));
				book.setStatus(rs.getString("status"));

				return book;
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

		return null;
	}

	public List<Book> getAllAvailableBookList() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			String sql = "Select * from books where available_copies > 0";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setCategory(rs.getString("category"));
				book.setIsbn(rs.getString("isbn"));
				book.setPublisher(rs.getString("publisher"));
				book.setTotalCopies(rs.getInt("total_copies"));
				book.setAvailableCopies(rs.getInt("available_copies"));
				book.setStatus(rs.getString("status"));

				bookList.add(book);
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

		return bookList;
	}

	public boolean assignBook(BookIssued bookIssued) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "Insert into book_issued(book_id, user_id, issue_date, due_date, status, assignment_notes)"
					+ " values(?,?,?,?,?,?)";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, bookIssued.getBook().getBookId());
			preparedStatement.setLong(2, bookIssued.getUser().getUserId());

			Date sqlIssuedDate = new Date(bookIssued.getIssueDate().getTime());
			preparedStatement.setDate(3, sqlIssuedDate);

			preparedStatement.setDate(4, Date.valueOf(bookIssued.getDueDate()));
			preparedStatement.setString(5, bookIssued.getStatus());
			preparedStatement.setString(6, bookIssued.getAssignmentNotes());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				return true;
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

		return false;
	}

	public boolean updateAvailableBook(long bookId, int availableCopies) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "update books set available_copies = ? where book_id = ?";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, availableCopies);
			preparedStatement.setLong(2, bookId);

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				return true;
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

		return false;
	}

	public List<BookIssued> getAllIssuedBookList() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<BookIssued> issuedList = new ArrayList<>();

		try {
			String sql = "SELECT bi.issue_id, bi.book_id, bi.user_id, bi.issue_date, bi.due_date, bi.return_date, bi.status, "
					+ "bi.book_condition, bi.assignment_notes, bi.return_notes, bi.fine_amount, bi.fine_paid, "
					+ "b.title, b.author, b.category, b.isbn, b.publisher, b.total_copies, b.available_copies,"
					+ "u.first_name, u.last_name, u.email, u.phone_no " + "FROM book_issued bi "
					+ "JOIN books b ON bi.book_id = b.book_id " + "JOIN users u ON bi.user_id = u.user_id "
					+ "Where bi.status = 'ISSUED'";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				issuedList.add(mapIssuedRecord(rs));
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

		return issuedList;
	}

	public BookIssued getIssuedBookById(long issueId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT bi.issue_id, bi.book_id, bi.user_id, bi.issue_date, bi.due_date, bi.return_date, bi.status, "
					+ "bi.book_condition, bi.assignment_notes, bi.return_notes, bi.fine_amount, bi.fine_paid, "
					+ "b.title, b.author, b.category, b.isbn, b.publisher, b.total_copies, b.available_copies, "
					+ "u.first_name, u.last_name, u.email, u.phone_no " + "FROM book_issued bi "
					+ "JOIN books b ON bi.book_id = b.book_id " + "JOIN users u ON bi.user_id = u.user_id "
					+ "WHERE bi.issue_id = ?";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, issueId);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return mapIssuedRecord(rs);
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

		return null;
	}

	public boolean updateBookReturn(BookIssued bookIssued) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "UPDATE book_issued SET return_date = ?, book_condition = ?, return_notes = ?, status = ?, fine_amount = ?, fine_paid = ? WHERE issue_id = ?";

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setDate(1, Date.valueOf(bookIssued.getReturnDate()));
			preparedStatement.setString(2, bookIssued.getBookCondition());
			preparedStatement.setString(3, bookIssued.getReturnNotes());
			preparedStatement.setString(4, bookIssued.getStatus());
			preparedStatement.setDouble(5, bookIssued.getFineAmount());
			preparedStatement.setBoolean(6, bookIssued.isFinePaid());
			preparedStatement.setInt(7, bookIssued.getIssueId());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				return true;
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

		return false;
	}

	private BookIssued mapIssuedRecord(ResultSet rs) throws SQLException {
		BookIssued bookIssued = new BookIssued();
		bookIssued.setIssueId(rs.getInt("issue_id"));
		bookIssued.setIssueDate(rs.getDate("issue_date"));
		bookIssued.setDueDate(rs.getDate("due_date").toLocalDate());
		bookIssued.setStatus(rs.getString("status"));
		bookIssued.setFineAmount(rs.getDouble("fine_amount"));
		bookIssued.setFinePaid(rs.getBoolean("fine_paid"));
		bookIssued.setBookCondition(rs.getString("book_condition"));
		bookIssued.setAssignmentNotes(rs.getString("assignment_notes"));
		bookIssued.setReturnNotes(rs.getString("return_notes"));


		Book book = new Book();
		book.setBookId(rs.getLong("book_id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setCategory(rs.getString("category"));
		book.setIsbn(rs.getString("isbn"));
		book.setPublisher(rs.getString("publisher"));
		book.setTotalCopies(rs.getInt("total_copies"));
		book.setAvailableCopies(rs.getInt("available_copies"));
		bookIssued.setBook(book);

		User user = new User();
		user.setUserId(rs.getLong("user_id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setPhoneNo(rs.getString("phone_no"));
		bookIssued.setUser(user);

		return bookIssued;
	}

	public List<BookIssued> getIssuedBookListForDashboard() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<BookIssued> issuedList = new ArrayList<>();

		try {
			String sql = "SELECT bi.issue_id, bi.book_id, bi.user_id, bi.issue_date, bi.due_date, bi.return_date, bi.status, "
					+ "bi.book_condition, bi.assignment_notes, bi.return_notes, bi.fine_amount, bi.fine_paid, "
					+ "b.title, b.author, b.category, b.isbn, b.publisher, b.total_copies, b.available_copies,"
					+ "u.first_name, u.last_name, u.email, u.phone_no " + "FROM book_issued bi "
					+ "JOIN books b ON bi.book_id = b.book_id " + "JOIN users u ON bi.user_id = u.user_id "
					+ "Where bi.status = 'ISSUED' " + "LIMIT 10" ;

			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				issuedList.add(mapIssuedRecord(rs));
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

		return issuedList;
	}

	public boolean deletedBookById(long bookId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "UPDATE books SET status='DELETED' WHERE book_id=?";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, bookId);

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

	public boolean hardDeleteBookById(long bookId) {
	    Connection conn = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        String sql = "DELETE FROM books WHERE book_id = ?";
	        conn = DbUtil.getConnection();
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setLong(1, bookId);

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
	// ---------- FINE MANAGEMENT ----------

		public List<BookIssued> getAllFines() {
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			List<BookIssued> fineList = new ArrayList<>();

			try {
				String sql = "SELECT bi.issue_id, bi.book_id, bi.user_id, bi.issue_date, bi.due_date, bi.return_date, bi.status, "
						+ "bi.book_condition, bi.assignment_notes, bi.return_notes, bi.fine_amount, bi.fine_paid, "
						+ "b.title, b.author, b.category, b.isbn, b.publisher, b.total_copies, b.available_copies,"
						+ "u.first_name, u.last_name, u.email, u.phone_no " + "FROM book_issued bi "
						+ "JOIN books b ON bi.book_id = b.book_id " + "JOIN users u ON bi.user_id = u.user_id "
						+ "WHERE bi.fine_amount > 0 ORDER BY bi.issue_id DESC";

				conn = DbUtil.getConnection();
				preparedStatement = conn.prepareStatement(sql);
				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					fineList.add(mapIssuedRecord(rs));
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

			return fineList;
		}

		public boolean markFineAsPaid(long issueId) {
			Connection conn = null;
			PreparedStatement preparedStatement = null;

			try {
				String sql = "UPDATE book_issued SET fine_paid = TRUE WHERE issue_id = ?";
				conn = DbUtil.getConnection();
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setLong(1, issueId);

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

		// ---------- SEARCH & CATEGORY FILTER ----------

		public List<Book> searchBooks(String keyword) {
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			List<Book> bookList = new ArrayList<>();

			try {
				String sql = "SELECT * FROM books WHERE status != 'DELETED' AND (title LIKE ? OR author LIKE ?) ORDER BY book_id DESC";
				conn = DbUtil.getConnection();
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, "%" + keyword + "%");
				preparedStatement.setString(2, "%" + keyword + "%");

				rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getLong("book_id"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setCategory(rs.getString("category"));
					book.setIsbn(rs.getString("isbn"));
					book.setPublisher(rs.getString("publisher"));
					book.setTotalCopies(rs.getInt("total_copies"));
					book.setAvailableCopies(rs.getInt("available_copies"));
					book.setStatus(rs.getString("status"));
					bookList.add(book);
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

			return bookList;
		}

		public List<Book> getBooksByCategory(String category) {
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			List<Book> bookList = new ArrayList<>();

			try {
				String sql = "SELECT * FROM books WHERE status != 'DELETED' AND category = ? ORDER BY book_id DESC";
				conn = DbUtil.getConnection();
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, category);

				rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getLong("book_id"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setCategory(rs.getString("category"));
					book.setIsbn(rs.getString("isbn"));
					book.setPublisher(rs.getString("publisher"));
					book.setTotalCopies(rs.getInt("total_copies"));
					book.setAvailableCopies(rs.getInt("available_copies"));
					book.setStatus(rs.getString("status"));
					bookList.add(book);
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

			return bookList;
		}




}
