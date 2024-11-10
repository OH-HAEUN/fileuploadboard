<%--
  Created by IntelliJ IDEA.
  User: HAEUN
  Date: 2024-04-17
  Time: 오전 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
</head>
<body>

<script>
    var message = '${message}';
    alert(message);

    if ('${url}' != null) {
        location.replace('${url}');
    } else {
        history.back();
    }

</script>

</body>
</html>
