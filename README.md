# Digital Library Management System

A web-based Library Management System built using **Core Java, JDBC, Servlets, and JSP**, designed to manage books, users, book issue/return operations, and fine tracking for a library.

## Features

### Authentication
- **User Registration & Login** — secure sign-up and login with session-based authentication
- **Role-based access** — Admin and User roles
- **Logout** — clears session and redirects to login

### Book Management
- Add, update, view, and delete books from the library catalog
- **Search books** by title or author
- **Filter books** by category

### User Management
- Add, update, view, and delete library users

### Issue & Return
- **Book Issue** — assign available books to users, with automatic tracking of available copies
- **Book Return** — return issued books, update book condition and return notes, and automatically restore available copies
- Overdue status tracking (Overdue / Due Today / Active)

### Fine Management
- **Automatic fine calculation** — ₹5/day charged for overdue returns
- **Fine tracking dashboard** — view all pending and paid fines
- **Mark fines as paid** — admin can update fine payment status

### Contact / Query
- Contact form for users to submit queries
- Admin panel to view all submitted messages

### Dashboard
- Real-time stats: total books, books assigned, books returned, total users
- Currently issued books with due-date status

## Tech Stack

- **Language:** Java
- **Backend:** Servlets (Jakarta EE)
- **Frontend:** JSP, JSTL, HTML, CSS, Bootstrap 5
- **Database:** MySQL (via JDBC)
- **Server:** Apache Tomcat 10.x
- **Architecture:** DAO – Service – Controller (layered architecture)

## Project Structure

```
src/main/java/com/digitalLibraryManagementSystem/
├── controller/     # Servlets handling HTTP requests
│   ├── AuthController.java        # Login, Register, Logout
│   ├── BookController.java        # Book CRUD, Issue/Return, Fines, Search
│   ├── UserController.java        # User CRUD
│   ├── ContactController.java     # Contact form
│   └── DashboardController.java   # Dashboard stats
├── service/        # Business logic layer
├── dao/            # Database access layer using JDBC
├── entity/         # POJO classes (Book, User, BookIssued, ContactMessage, DashboardStats)
├── filter/         # AuthFilter — protects routes, enforces login
└── util/           # DbUtil for DB connection

src/main/webapp/
├── jsp/            # JSP pages (login, register, bookList, addBook, editBook,
│                   #   assignBook, returnBook, finesList, contact, contactMessages, etc.)
└── includes/       # Reusable JSP fragments (header, sidebar)
```

## Setup Instructions

1. Clone the repository:
   ```
   git clone <your-repo-url>
   ```
2. Import the project into Eclipse as a **Dynamic Web Project**.
3. Create the MySQL database and required tables (`books`, `users`, `book_issued`, `contact_messages`).
4. Update database credentials in `DbUtil.java` (URL, username, password).
5. Deploy the project on **Apache Tomcat** (10.x recommended, as it uses `jakarta.servlet.*`).
6. Access the application at:
   ```
   http://localhost:8082/<project-name>/AuthController?action=showLogin
   ```

## Database Tables (Overview)

- **books** — book_id, title, author, category, isbn, publisher, total_copies, available_copies, status, created_at
- **users** — user_id, first_name, last_name, email, password, role, phone_no, address, created_at
- **book_issued** — issue_id, book_id, user_id, issue_date, due_date, return_date, status, book_condition, assignment_notes, return_notes, fine_amount, fine_paid
- **contact_messages** — message_id, name, email, message, created_at

## Future Enhancements

- Advance booking (reserve a book currently issued to another user) — planned but not implemented due to time constraints
- Password hashing (currently stored in plain text for simplicity)
- Email/SMS notifications for due dates
- Export reports (PDF/CSV)


