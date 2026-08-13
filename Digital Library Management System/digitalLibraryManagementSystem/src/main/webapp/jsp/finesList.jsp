<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="includes/header.jsp" %>

<div class="container-fluid">
<div class="row">
     <%@ include file="includes/sidebar.jsp" %>

    <main class="col-md-9 ms-sm-auto col-lg-10">
        <div class="page-header header-with-btn">
            <h1 class="h2"><i class="bi bi-cash-coin me-2"></i>Fine Management</h1>
        </div>

        <div class="table-container">
            <div class="table-responsive">
                <c:choose>
                    <c:when test="${not empty fineList}">
                        <table class="table table-hover align-middle">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Book</th>
                                    <th>Member</th>
                                    <th>Due Date</th>
                                    <th>Return Date</th>
                                    <th>Fine Amount</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="f" items="${fineList}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td><c:out value="${f.book.title}" /></td>
                                    <td><c:out value="${f.user.firstName} ${f.user.lastName}" /></td>
                                    <td><c:out value="${f.dueDate}" /></td>
                                    <td><c:out value="${f.returnDate}" /></td>
                                    <td>&#8377; <c:out value="${f.fineAmount}" /></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${f.finePaid}">
                                                <span class="badge bg-success">Paid</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Unpaid</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${!f.finePaid}">
                                            <a href="${pageContext.request.contextPath}/BookController?action=markFinePaid&issueId=${f.issueId}"
                                               class="btn btn-sm btn-success"
                                               onclick="return confirm('Mark this fine as paid?');">
                                                Mark as Paid
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div>No fines recorded.</div>
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