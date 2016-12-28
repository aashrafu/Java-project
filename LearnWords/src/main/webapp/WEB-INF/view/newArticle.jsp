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
			<div class="jumbotron">
				<div id="result"></div>
				<form:form method="post" action="add" commandName="article">
		            <h3>New article</h3>
		           	 <div>Title: </div>
		          	 <form:input type="text" path="title" class="form-control"/>
		          	 <div>Content: </div>
		          	 <form:textarea path="content" class="form-control" rows="7"/>
		          	 <br></br>
		          	 <input type="submit" value="Save" />		
	          	 </form:form>
	         </div>	
		</div>
		<jsp:include page="templates/sidebar.jsp" />
	</div>
</div>
</body>
</html>