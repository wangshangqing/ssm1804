<#import "spring.ftl" as s/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<a href="<@s.url '/student/toAdd.do'/>">添加</a>
<table border="1">
    <tr>
        <td>
            编号
        </td>
        <td>
            姓名
        </td>
        <td>
            性别
        </td>
        <td>
            操作
        </td>
    </tr>

    <#list liststu as stu>
        <tr>
            <td>
                ${stu_index+1}
            </td>
            <td>
                ${stu.stuName}
            </td>
            <td>
                ${(stu.sex=='1')?string('男','女')}
            </td>
            <td>
                <a href="/student/toUpdate.do?id=${stu.stuNo}">修改</a>|
                <a href="">删除</a>
            </td>
        </tr>
    </#list>

</table>
</body>
</html>