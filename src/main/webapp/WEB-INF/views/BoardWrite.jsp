<%--
  Created by IntelliJ IDEA.
  User: HAEUN
  Date: 2024-04-22
  Time: 오후 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 작성</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>

<style>
    .layout {
        width: 70%;
        margin: 0 auto;
    }

    #title {
        margin-bottom: 20px;
    }

    .form-control {
        margin-bottom: 10px;
    }

    .btn {
        margin-bottom: 10px;
    }

    ul {
        list-style: none;
        padding-left: 0px;
    }

    #file {
        margin-top: 10px;
    }

</style>

<script>
    $(document).ready(function () {
        const dataTransfer = new DataTransfer();

        const offset = 1000 * 60 * 60 * 9
        const krNow = new Date((new Date()).getTime() + offset)

        $("#file_expiry_date").prop("min", krNow.toISOString().substring(0, 10));
        $("#file_expiry_date").val(new Date(krNow.setDate(krNow.getDate() + 15)).toISOString().substring(0, 10));

        $("#file").change(function () {
            let fileArr = document.getElementById('file').files

            if (fileArr != null && fileArr.length > 0) {
                for (var i = 0; i < fileArr.length; i++) {
                    dataTransfer.items.add(fileArr[i])
                    $('#fileList').append(
                        '<li>' + fileArr[i].name + ' <b class="remove_button">−</b></li>'
                    )
                }
                document.getElementById('file').files = dataTransfer.files;
            }
        })

        $(document).on('click', '#fileList > li', function (event) {
            if (event.target.className == 'remove_button') {
                var targetFile = $('li').index(this);

                for (var i = 0; i < dataTransfer.files.length; i++) {
                    if (dataTransfer.files[i] == dataTransfer.files[targetFile]) {
                        dataTransfer.items.remove(i)
                        break
                    }
                }
                document.getElementById("file").files = dataTransfer.files;

                const removeTarget = $('li:eq(' + targetFile + ')');
                removeTarget.remove();
            }
        })
    })

    function writing() {

        var title = document.getElementById('title').value;
        var file = document.getElementById('file').value;

        console.log(title)
        console.log(file)

        if (title == "") {
            console.log("?")
            alert("제목은 비워둘 수 없습니다.")
            return;
        } else if (!file) {
            alert("파일을 첨부해 주세요.")
            return;
        } else {
            document.writeform.submit();
        }
    }
</script>

<body>
<div class="layout">
    <form action="/writepro" method="post" enctype="multipart/form-data" name="writeform">
        <input id="title" name="title" type="text" class="form-control" placeholder="제목">
        <textarea id="memo" name="memo" class="form-control" rows="3" placeholder="메모"></textarea>
        <div class="row row-cols-auto">
            <div class="col align-content-center">파일 다운로드 기한 선택</div>
            <div class="col align-content-center"><input type="date" name="file_expiry_date" id="file_expiry_date" class="form-control">
            </div>
        </div>
        <div class="row row-cols-auto">
            <div class="col align-content-center">다운로드 가능 횟수 설정</div>
            <div class="col align-content-center">
                <select id="download_limit" name="download_limit" class="form-select">
                    <option value="1">1회</option>
                    <option value="2">2회</option>
                    <option value="3">3회</option>
                    <option value="4">4회</option>
                    <option value="5" selected>5회</option>
                    <option value="6">6회</option>
                    <option value="7">7회</option>
                    <option value="8">8회</option>
                    <option value="9">9회</option>
                    <option value="10">10회</option>
                </select>
            </div>
        </div>
        <input type="file" name="file" id="file" class="form-control" multiple>
        <ul id="fileList"></ul>
        <button type="button" class="btn btn-primary" onclick="writing()">작성</button>
    </form>
</div>
</body>
</html>
