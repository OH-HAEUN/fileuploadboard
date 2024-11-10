<%--
  Created by IntelliJ IDEA.
  User: HAEUN
  Date: 2024-04-11
  Time: 오후 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
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
<script>
    $(document).ready(function () {
        $('#username').on("propertychange change keyup paste input", function () {
            document.getElementById('usernamecheck').disabled = false;
        })
    })

    function usernameCheck() {
        var username = document.getElementById('username').value;
        var usernameRegEx = /^[a-zA-z][a-zA-Z0-9]{4,12}$/;
        if (username == "") {
            alert("아이디를 입력하세요.")
            return;
        } else if (!usernameRegEx.test(username)) {
            alert("아이디는 영어와 숫자 조합으로 4~10글자 사이어야 합니다.")
            return;
        } else {
            $.ajax({
                url: '/checkUsername',
                type: 'POST',
                data: {"username": username},
                headers: {
                    "X-CSRF-TOKEN": $("meta[name='_csrf']").attr("content")
                },
                success: function (result) {
                    if (result == 0) {
                        alert("사용 가능한 아이디 입니다.")
                        document.getElementById('usernamecheck').disabled = true;
                    } else if (result > 0) {
                        alert("이미 사용중인 아이디 입니다.")
                    }
                },
                error: function (e) {
                    console.log(e)
                }
            })
        }
    }

    function joinSubmit() {
        var username = document.getElementById('username').value;
        var name = document.getElementById('name').value;
        var password = document.getElementById('password').value;
        var email = document.getElementById('email').value
        var company = document.getElementById('company').value

        var usernamecheck = document.getElementById('usernamecheck').disabled;

        if (username == "") {
            alert("아이디를 입력하세요.")
        } else if (name == "") {
            alert("이름을 입력하세요.")
            return;
        } else if (password == "") {
            alert("비밀번호를 입력하세요.")
            return;
        } else if (email == "") {
            alert("이메일을 입력하세요.")
            return;
        } else if (company == "") {
            alert("회사명을 입력하세요.")
            return;
        } else if (usernamecheck == false) {
            alert("아이디 입력 후 중복확인 버튼을 클릭하세요.")
            return;
        } else {
            document.joinform.submit();
        }
    }
</script>
<body>
<div class="container">
    <h2 class="d-flex justify-content-center">회원가입</h2>
    <form action="/join" method="post" name="joinform">
        <div class="form-group row d-flex justify-content-center">
            <label for="username" class="form-label col-sm-1">아이디</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="username"
                       id="username" placeholder="아이디를 입력하세요.">
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-primary" id="usernamecheck" onclick="usernameCheck()">중복확인</button>
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="name" class="form-label col-sm-1">이름</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="name"
                       id="name" placeholder="이름을 입력하세요.">
            </div>
            <div class="col-sm-2"></div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="password" class="form-label col-sm-1">비밀번호</label>
            <div class="col-sm-3">
                <input type="password" class="form-control" name="password"
                       id="password" placeholder="비밀번호를 입력하세요.">
            </div>
            <div class="col-sm-2"></div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="email" class="form-label col-sm-1">이메일</label>
            <div class="col-sm-3">
                <input type="email" class="form-control" name="email" id="email"
                       placeholder="이메일을 입력하세요.">
            </div>
            <div class="col-sm-2"></div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label for="company" class="form-label col-sm-1">회사명</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="company"
                       id="company" placeholder="회사명을 입력하세요.">
            </div>
            <div class="col-sm-2"></div>
        </div>
        <div class="d-flex justify-content-center">
            <button type="button" id="btn-signup" onclick="joinSubmit()" class="btn btn-primary">회원가입</button>
        </div>
    </form>
</div>
</body>
</html>
