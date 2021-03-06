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
	 	<a href="/newArticle" class="btn btn-primary">Add new article</a>
	 	
			<c:if test="${!empty articleList}">
				<div class="row">
				<c:forEach items="${articleList}" var="article">
			        <div class="col-lg-4">
			          <h2>${article.title}</h2>
			          <p class="text-danger">${article.description}</p>
			          <p><a class="btn btn-primary" href="/viewArticle?id=${article.id}" role="button">View details »</a></p>
			        </div>
			    </c:forEach>
			    </div>
			</c:if>
			
		</div>
		<jsp:include page="templates/sidebar.jsp" />
	</div>
</div>
</body>
</html>