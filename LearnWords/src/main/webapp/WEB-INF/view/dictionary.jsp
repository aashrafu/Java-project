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
	 	<div id="message" class="alert alert-success hide"></div>
	 	<a href="/addWord" class="btn btn-primary">Add word</a><br><br>
	 	 <table class="table table-hover">
						<tr align="center">
						  <td class="active">Original word</td>
						  <td class="success">Translation</td>
						  <td class="warning">Action</td>
						</tr>
			<c:if test="${!empty dictionaryList}">
				<c:forEach items="${dictionaryList}" var="word">	
						<tr>
						  <td class="active">${word.original}</td>
						  <td class="success">${word.translation}</td>
						  <td class="warning"><a class="btn" href="javascript:removeWord(${word.id})">Remove</a></td>
						</tr>
					
			    </c:forEach>
			</c:if>
			</table>
		</div>
		<jsp:include page="templates/sidebar.jsp" />
	</div>
</div>
<script type="text/javascript">
function removeWord(id)
{
	$.ajax({
		type : "POST",
		cache : false,
		url : '/removeWord',
		data : {
			'id' : id
		},
		success : function(response) {
			location.reload();
			//$("#message").html("The word was successfully removed.");
			//$("#message").removeClass("hide");
		}
	});
}
</script>
</body>
</html>