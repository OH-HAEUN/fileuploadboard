<%--
  Created by IntelliJ IDEA.
  User: HAEUN
  Date: 2024-04-22
  Time: 오후 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<style>
    .layout {
        width: 60%;
        margin: 0 auto;
    }

    a {
        color: black;
        text-decoration: none;
    }

    a:hover {
        color: lightcoral;
    }

    .right {
        float: right;
    }

    ul {
        list-style: none;
    }

    .right > ul > li {
        float: left;
        margin-right: 12px;
    }

</style>

<body>
<div class="layout">
    <c:if test="${not empty principal}">
        <div class="right">
            <ul>
                <li>${currentCompany} '<a href="/mypage">${currentName}</a>'님 안녕하세요.</li>
                <li><a href="/logout">로그아웃</a></li>
            </ul>
        </div>
    </c:if>
    <table class="table table-hover">
        <thead class="text-center">
        <tr>
            <th class="col-sm-1">글번호</th>
            <th class="col-sm-3">제목</th>
            <th class="col-sm-2">작성자</th>
            <th class="col-sm-2">날짜</th>
            <%--            <th class="col-sm-3">파일이름</th>--%>
            <%--            <th class="col-sm-1">파일다운</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="list">
            <tr>
                <td class="text-center">${list.id}</td>
                <td class="text-left"><a href="view/${list.id}">${list.title}</a></td>
                <td class="text-center">${list.writer}</td>
                <td class="text-center">${list.createddate}</td>
                    <%--                <td class="text-center">${list.filename}</td>--%>
                    <%--                <td class="col-sm-1"><a href="${list.filepath}">다운</a></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button type="button" class="btn btn-outline-primary" id="boardlist" onclick="window.location.reload()">글목록</button>
    <button type="button" class="btn btn-primary right" id="writebutton" onclick="location.href='/write'">글쓰기</button>

    <br>
    <nav aria-label="Page">
        <ul class="pagination justify-content-center">
            <c:if test="${paging.startPage != 1 }">
                <li class="page-item"><a class="page-link" href="/list?page=${paging.startPage - 1 }">&laquo;</a></li>
            </c:if>
            <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
                <c:choose>
                    <c:when test="${p == paging.nowPage }">
                        <li class="page-item active"><span class="page-link">${p }</span></li>
                    </c:when>
                    <c:when test="${p != paging.nowPage }">
                        <li class="page-item"><a class="page-link" href="/list?page=${p }">${p }</a></li>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${paging.endPage != paging.lastPage}">
                <li class="page-item"><a class="page-link" href="/list?page=${paging.endPage+1 }">&raquo;</a></li>
            </c:if>
        </ul>
    </nav>

</div>
</body>
</html>
