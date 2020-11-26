<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vovantam
  Date: 22/11/2020
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/common/head.jsp"/>
<body>
<jsp:include page="/common/nav.jsp"/>
<div class="container">
    <form action="/create" method="post" class="col-6">
        <div class="form-group">
            <label for="name">Tên:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="content">Nội dung:</label>
            <textarea rows="5" type="text" class="form-control" id="content" name="content" required></textarea>
        </div>
        <div class="form-group">
            <label for="status">Trạng thái:</label>
            <select class="form-control" id="status" name="status">
                <option value="NOT_START" selected>Chưa bắt đầu</option>
                <option value="IN_PROCESS">Đang làm</option>
                <option value="DONE">Hoàn thành</option>
            </select>
        </div>
        <button class="btn btn-primary" type="submit">Tạo mới</button>
    </form>
</div>
</body>
</html>
