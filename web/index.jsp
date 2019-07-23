<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<%--<a href="${pageContext.request.contextPath}/addAdmin?name=abcd&password=123456">点击一下添加一个Admin对象</a>--%>
<%--<br>--%>
<%--<a href="${pageContext.request.contextPath}/login?name=admin&password=admin">点击测试登陆</a>--%>
<%--<jsp:forward page="WEB-INF/page/login.jsp"/>--%>
<a href="${pageContext.request.contextPath}/page/login">登录</a>
<br>
<a href="${pageContext.request.contextPath}/page/font">直接进入登录成功<b>前台</b>页面</a>
<br>
<a href="${pageContext.request.contextPath}/page/font">直接进入登录成功<b>后台</b>页面</a>
<br>
<a href="${pageContext.request.contextPath}/page/register">直接进入注册页面</a>
<br>
<a href="${pageContext.request.contextPath}/page/testWebSocket">WebSocket测试页面</a>

</body>
</html>
