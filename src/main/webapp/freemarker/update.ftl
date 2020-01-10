<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <form action="/student/add.do" method="post">
        姓名：<input type="text" name="stuName" value="${stu.stuName}">
        性别：<input type="text" name="sex" value="${stu.sex}">
        <input type="submit" value="提交">
    </form>



</body>
</html>