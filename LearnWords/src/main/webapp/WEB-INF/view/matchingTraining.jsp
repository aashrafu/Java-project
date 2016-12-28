<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="templates/header.jsp" />
<body>
<jsp:include page="templates/menu.jsp" />

<div class="container">
	<div class="row row-offcanvas row-offcanvas-right">
	 	<div class="col-xs-12 col-sm-9">
	 		<center><h2>Original word</h2>
	 		<br>
			<div class="list-group">
			<%! int i = 0; %> 
			  <c:if test="${!empty translationList}">
	 			<c:forEach items="${translationList}" var="translation">
			       <a href="#" class="list-group-item" style="width:400px;">${translation}</a>
			    </c:forEach>
	 		</c:if>
			</div>
			</center>
		</div>
		<jsp:include page="templates/sidebar.jsp" />
	</div>
</div>
</body>
</html>