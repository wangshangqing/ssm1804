<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <fmt:formatDate value="${s}" pattern="yyyy-MM-dd"></fmt:formatDate>
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
        <c:forEach items="${liststu}" var="liststu" varStatus="stat">
            <tr>
                <td>
                    ${stat.index+1}
                </td>
                <td>
                    ${liststu.stuName}
                </td>
                <td>
                    ${liststu.sex eq '1'?'男':'女'}
                </td>
                <td>
                    <a>修改</a>|<a href="/student/delete.do?id=${liststu.stuNo}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>



</body>
</html>