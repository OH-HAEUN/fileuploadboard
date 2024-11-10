<%--
  Created by IntelliJ IDEA.
  User: HAEUN
  Date: 2024-04-23
  Time: 오후 4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>상세 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>

<style>

    .layout {
        width: 60%;
        margin: 0 auto;
    }

    .layout > h2 {
        margin-bottom: 30px;
    }

    .layout > p {
        margin: 20px 0;
    }

    .button {
        margin-top: 30px;
    }

    ul, li {
        list-style: none;
        margin: 0px;
        padding: 0px;
    }

</style>

<script>
    $(document).ready(function () {
        var now = new Date();
        var file_expire_date = new Date('${board.file_expiry_date}')

        if(now.getTime() > file_expire_date.getTime() || 0 >= ${board.download_limit - board.download_click}) {
            $("#download_btn").attr("disabled", true);
            $("#download_btn").attr("class", 'btn btn-secondary');
        }
    })

    function del() {
        if (confirm("삭제하시겠습니까?")) {
            location.href = "/delete/${board.id}"
        }
    }

</script>

<body>

<div class="layout">
    <h3>${board.title}</h3>
    작성날짜 : ${board.createddate}
    <br>
    <c:if test="${not empty board.updatedate}">
        최종수정날짜 : ${board.updatedate}
    </c:if>
    <p>${board.memo}</p>
    <p>다운로드기한 : ~ ${board.file_expiry_date}</p>
    <ul>
    <c:forEach items="${file}" var="file">
        <li>${file.fileorigname}</li>
    </c:forEach>
    </ul>
    <p><button type="button" class="btn btn-primary" id="download_btn" onclick="location.href='/downloadZip/${board.id}'">다운로드</button></p>
    <fmt:parseNumber value="${board.download_limit}" var="limit"/>
    <fmt:parseNumber value="${board.download_click}" var="click"/>
    다운로드가능횟수 : ${board.download_limit}회
    <br>
    남은다운로드가능횟수 : ${board.download_limit - board.download_click}회
    <div class="button">
        <c:if test="${principal == board.writerid}">
            <button type="button" class="btn btn-success" onclick="location.href='/modify/${board.id}'">수정</button>
            <button type="button" class="btn btn-danger" onclick="del()">삭제</button>
        </c:if>
    </div>
    <div class="button">
        <button type="button" class="btn btn-outline-primary" id="boardlist" onclick="location.href='/list'">글목록</button>
    </div>
</div>

</body>
</html>
