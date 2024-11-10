<%--
  Created by IntelliJ IDEA.
  User: HAEUN
  Date: 2024-04-18
  Time: 오전 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
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

    .btn-login {
        margin-bottom: 10px;
    }
</style>

<script>
    $(document).ready(function () {
        var message = ''
        console.log(message);
        if (message != '') {
            alert(message);
        }
    })

    function login() {
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;

        if (username == "") {
            alert("아이디를 입력하세요.")
            return;
        } else if (password == "") {
            alert("비밀번호를 입력하세요.")
            return;
        } else {
            // document.loginform.submit();

            $.ajax({
                type: 'POST',
                url: '/login',
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded',
                data: {
                    username: username,
                    password: password
                },
                success: function(res) {
                    alert("로그인 되었습니다.");
                    location.href = "/"
                },
                error: function(error) {
                    alert(JSON.stringify(error));
                }
            })
        }
    }
</script>

<body>
<div class="container">
    <h2>로그인</h2>
    <form action="/loginPro" method="post" name="loginform">
        <div class="form-group row d-flex justify-content-center">
            <label for="username" class="form-label col-sm-1">아이디</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="username" id="username" placeholder="아이디를 입력하세요.">
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="password" class="form-label col-sm-1">비밀번호</label>
            <div class="col-sm-3">
                <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호를 입력하세요.">
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <div class="col-sm-1"></div>
            <div class="col-sm-3 btn-login">
                <button type="submit" class="btn btn-primary col-sm-12">로그인</button>
<%--                <button type="button" class="btn btn-primary col-sm-12" onclick="login()">로그인</button>--%>
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <div class="col-sm-1"></div>
            <div class="col-sm-3">
                <button type="button" id="btn-signup" class="btn btn-primary col-sm-12" onclick="location.href='/join'">
                    회원가입
                </button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
