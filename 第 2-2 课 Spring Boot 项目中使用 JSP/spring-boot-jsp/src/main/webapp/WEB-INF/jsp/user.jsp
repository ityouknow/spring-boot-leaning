<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html lang="en">
<body>
<h3>一行 Java 代码</h3>
<p>
	今天的日期是: <%=(new java.util.Date())%>
</p>

<h3>多行 Java 代码</h3>
<p>
	你的 IP 地址是：
<%
	out.println("Your IP address is " + request.getRemoteAddr()+"</br>");
	out.println("一段代码 ");
%>
</p>

<h3>标签 c:if</h3>
<c:if test="${username !=null}">
<p>用户名为：username<p>
</c:if>

<h3>标签 c:choose</h3>
<c:choose>
	<c:when test="${salary <= 0}">
	太惨了。
	</c:when>
	<c:when test="${salary > 1000}">
	不错的薪水，还能生活。
	</c:when>
	<c:otherwise>
	什么都没有。
	</c:otherwise>
</c:choose>

<h3>For 循环实例</h3>
<%
	int count = (int)session.getAttribute("count");
	for ( int fontSize = 1; fontSize <=count; fontSize++){
 %>
          纯洁的微笑
<br />
<%}%>


<h3>布局</h3>

<%@include file="footer.jsp"%>

</body>
</html>
