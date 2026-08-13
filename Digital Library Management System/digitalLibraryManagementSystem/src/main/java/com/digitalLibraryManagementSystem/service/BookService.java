package com.digitalLibraryManagementSystem.service;

import java.util.Date;
import java.util.List;

import com.digitalLibraryManagementSystem.dao.BookDao;
import com.digitalLibraryManagementSystem.entity.Book;
import com.digitalLibraryManagementSystem.entity.BookIssued;

public class BookService {

	BookDao bookDao = new BookDao();

	public boolean addBook(Book book) {
		return bookDao.addBook(book);
	}

	public boolean updateBook(Book book) {

		return bookDao.updateBook(book);
	}

	public List<Book> getAllBookList() {

		return bookDao.getAllBookList();
	}

	public List<Book> getAllBookByStatus(String status) {

		return null;
	}

	public Book getBookById(long bookId) {

		return bookDao.getBookById(bookId);
	}

	public List<Book> getAllAvailableBookList() {
		return bookDao.getAllAvailableBookList();
	}

	public boolean assignBook(BookIssued bookIssued) {
		bookIssued.setIssueDate(new Date());
		bookIssued.setStatus("ISSUED");

		boolean assignFlag = false;
		Book book = bookDao.getBookById(bookIssued.getBook().getBookId());
		if (book != null) {
			int availableCopies = book.getAvailableCopies() - 1;

			boolean updateflag = bookDao.updateAvailableBook(bookIssued.getBook().getBookId(), availableCopies);

			if (updateflag) {
				assignFlag = bookDao.assignBook(bookIssued);

				if (!assignFlag) {
					Book book1 = bookDao.getBookById(bookIssued.getBook().getBookId());
					int newAvailableCopies = book1.getAvailableCopies() + 1;
					bookDao.updateAvailableBook(bookIssued.getBook().getBookId(), newAvailableCopies);
				}
			}
		}

		return assignFlag;
	}

	public List<BookIssued> getAllIssuedBookList() {
		return bookDao.getAllIssuedBookList();
	}

	public BookIssued getIssuedBookById(long issueId) {
		return bookDao.getIssuedBookById(issueId);
	}

//	public boolean updateBookReturn(BookIssued bookIssued) {
//		bookIssued.setStatus("RETURN");
//
//		BookIssued bookIssued2 = bookDao.getIssuedBookById(bookIssued.getIssueId());
//		boolean returnflag = false;
//
//		if (bookIssued2 != null) {
//			int availableCopies = bookIssued2.getBook().getAvailableCopies() + 1;
//
//			boolean updateflag = bookDao.updateAvailableBook(bookIssued2.getBook().getBookId(), availableCopies);
//			if (updateflag) {
//				returnflag = bookDao.updateBookReturn(bookIssued);
//
//				if (!returnflag) {
//					BookIssued bookIssued3 = bookDao.getIssuedBookById(bookIssued.getIssueId());
//					int newAvailableCopies = bookIssued3.getBook().getAvailableCopies() - 1;
//					bookDao.updateAvailableBook(bookIssued3.getBook().getBookId(), newAvailableCopies);
//				}
//			}
//		}
//
//		return returnflag;
//	}

	public List<BookIssued> getIssuedBookListForDashboard() {
		return bookDao.getIssuedBookListForDashboard();
	}

	public boolean deletedBookById(long bookId) {
		return bookDao.deletedBookById(bookId);
	}
	public boolean hardDeleteBookById(long bookId) {
	    return bookDao.hardDeleteBookById(bookId);
	}

	public boolean updateBookReturn(BookIssued bookIssued) {
		bookIssued.setStatus("RETURN");

		BookIssued existing = bookDao.getIssuedBookById(bookIssued.getIssueId());
		boolean returnflag = false;

		if (existing != null) {
			int availableCopies = existing.getBook().getAvailableCopies() + 1;

			long daysLate = java.time.temporal.ChronoUnit.DAYS.between(
					existing.getDueDate(), bookIssued.getReturnDate());

			double fine = 0;
			if (daysLate > 0) {
				fine = daysLate * 5;
			}
			bookIssued.setFineAmount(fine);
			bookIssued.setFinePaid(fine == 0);

			boolean updateflag = bookDao.updateAvailableBook(existing.getBook().getBookId(), availableCopies);
			if (updateflag) {
				returnflag = bookDao.updateBookReturn(bookIssued);

				if (!returnflag) {
					BookIssued bookIssued3 = bookDao.getIssuedBookById(bookIssued.getIssueId());
					int newAvailableCopies = bookIssued3.getBook().getAvailableCopies() - 1;
					bookDao.updateAvailableBook(bookIssued3.getBook().getBookId(), newAvailableCopies);
				}
			}
		}

		return returnflag;
	}

	public List<BookIssued> getAllFines() {
		return bookDao.getAllFines();
	}

	public boolean markFineAsPaid(long issueId) {
		return bookDao.markFineAsPaid(issueId);
	}

	public List<Book> searchBooks(String keyword) {
		return bookDao.searchBooks(keyword);
	}

	public List<Book> getBooksByCategory(String category) {
		return bookDao.getBooksByCategory(category);
	}
}
