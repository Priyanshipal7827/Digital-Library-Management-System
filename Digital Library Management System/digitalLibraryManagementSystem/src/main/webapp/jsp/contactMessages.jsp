<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="includes/header.jsp" %>

<div class="container-fluid">
<div class="row">
     <%@ include file="includes/sidebar.jsp" %>

    <main class="col-md-9 ms-sm-auto col-lg-10">
        <div class="page-header">
            <h1 class="h2"><i class="bi bi-chat-dots-fill me-2"></i>Contact Messages</h1>
        </div>

        <div class="table-container">
            <div class="table-responsive">
                <c:choose>
                    <c:when test="${not empty messages}">
                        <table class="table table-hover align-middle">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Message</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="m" items="${messages}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td><c:out value="${m.name}" /></td>
                                    <td><c:out value="${m.email}" /></td>
                                    <td><c:out value="${m.message}" /></td>
                                    <td><c:out value="${m.createdAt}" /></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div>No messages yet.</div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>