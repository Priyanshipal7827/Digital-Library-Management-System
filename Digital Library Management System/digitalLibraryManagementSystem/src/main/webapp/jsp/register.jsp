<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register - Digital Library</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="card shadow-sm" style="width: 100%; max-width: 480px;">
        <div class="card-body p-4">
            <h3 class="text-center mb-4">Create Account</h3>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger"><c:out value="${errorMessage}"/></div>
            </c:if>

           <form action="${pageContext.request.contextPath}/AuthController" method="post">
                <input type="hidden" name="action" value="register">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">First Name</label>
                        <input type="text" name="firstName" class="form-control" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Last Name</label>
                        <input type="text" name="lastName" class="form-control" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required minlength="4">
                </div>

                <div class="mb-3">
                    <label class="form-label">Phone No</label>
                    <input type="text" name="phone" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Address</label>
                    <input type="text" name="address" class="form-control">
                </div>

                <button type="submit" class="btn btn-primary w-100">Register</button>
            </form>

            <p class="text-center mt-3 mb-0">
                Already have an account? <a href="${pageContext.request.contextPath}/AuthController?action=showLogin">Login here</a>
            </p>
        </div>
    </div>
</div>

</body>
</html>