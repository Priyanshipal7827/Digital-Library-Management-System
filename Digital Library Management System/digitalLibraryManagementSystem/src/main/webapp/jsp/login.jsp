<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Digital Library</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="card shadow-sm" style="width: 100%; max-width: 400px;">
        <div class="card-body p-4">
            <h3 class="text-center mb-4"><i class="bi bi-book-fill"></i> Library Login</h3>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger"><c:out value="${errorMessage}"/></div>
            </c:if>
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success"><c:out value="${successMessage}"/></div>
            </c:if>

            <form action="${pageContext.request.contextPath}/AuthController" method="post">
                <input type="hidden" name="action" value="login">

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <button type="submit" class="btn btn-primary w-100">Login</button>
            </form>

            <p class="text-center mt-3 mb-0">
                Don't have an account? <a href="${pageContext.request.contextPath}/AuthController?action=showRegister">Register here</a>
            </p>
        </div>
    </div>
</div>

</body>
</html>