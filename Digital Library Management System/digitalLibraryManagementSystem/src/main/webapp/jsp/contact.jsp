<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="includes/header.jsp" %>

<div class="container-fluid">
<div class="row">
     <%@ include file="includes/sidebar.jsp" %>

    <main class="col-md-9 ms-sm-auto col-lg-10">
        <div class="page-header">
            <h1 class="h2"><i class="bi bi-envelope-fill me-2"></i>Contact / Query</h1>
        </div>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success"><c:out value="${successMessage}"/></div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger"><c:out value="${errorMessage}"/></div>
        </c:if>

        <div class="card" style="max-width: 600px;">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/ContactController" method="post">
                    <input type="hidden" name="action" value="submitContact">

                    <div class="mb-3">
                        <label class="form-label">Your Name</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Your Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Message / Query</label>
                        <textarea name="message" class="form-control" rows="4" required></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Send Message</button>
                </form>
            </div>
        </div>
    </main>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>