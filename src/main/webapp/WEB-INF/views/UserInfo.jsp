<%--
  Created by IntelliJ IDEA.
  User: HAEUN
  Date: 2024-10-10
  Time: 오전 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원정보</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<style>
    .container > h2 {
        text-align: center;
        padding: 20px;
    }

    label {
        display: inline-block;
        text-align: right;
        height: 40px;
        line-height: 40px;
    }

    form {
        margin: 20px;
    }
</style>
<body>
<div class="container">
    <form action="/join" method="post" name="joinform">
        <div class="form-group row d-flex justify-content-center">
            <label for="username" class="form-label col-sm-1">아이디</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="username"
                       id="username" value="${userInfo.username}" readonly>
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="name" class="form-label col-sm-1">이름</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="name"
                       id="name" value="${userInfo.name}">
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="password" class="form-label col-sm-1">비밀번호</label>
            <div class="col-sm-3">
                <input type="password" class="form-control" name="password"
                       id="password">
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="email" class="form-label col-sm-1">이메일</label>
            <div class="col-sm-3">
                <input type="email" class="form-control" name="email" id="email"
                       value="${userInfo.email}">
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="company" class="form-label col-sm-1">회사명</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="company"
                       id="company" value="${userInfo.company}">
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <button type="button" id="btn-signup" onclick="" class="btn btn-primary">저장</button>
        </div>
    </form>
</div>
</body>
</html>
