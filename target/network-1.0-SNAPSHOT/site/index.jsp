<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vovantam
  Date: 21/11/2020
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/common/head.jsp"/>
<body>
<jsp:include page="/common/nav.jsp"/>
<div class="container">
    <table class="table table-borderless">
        <thead>
        <tr>
            <th scope="col">Tên</th>
            <th scope="col">Chi tiết</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="todo" items="${todos}">
            <tr>
                <th>${todo.name}</th>
                <th>${todo.content}</th>
                <c:choose>
                    <c:when test='${todo.status == "DONE"}'>
                        <td><span class="badge badge-success">Hoàn thành</span></td>
                    </c:when>
                    <c:when test='${todo.status == "IN_PROCESS"}'>
                        <td><span class="badge badge-primary">Đang làm</span></td>
                    </c:when>
                    <c:otherwise>
                        <td><span class="badge badge-secondary">Chưa bắt đầu</span></td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <a role="button" class="btn-primary btn" href="/update?id=${todo.id}">Cập nhật</a>
                    <a role="button" class="btn btn-danger" onclick="return confirm('Xác nhận xóa?')" href="/delete?id=${todo.id}" id="delete-todo">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
