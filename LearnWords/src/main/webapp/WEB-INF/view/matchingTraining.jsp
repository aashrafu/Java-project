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
	 	
	 		<center><div id="original"><h2>${word.original}</h2></div>
	 		<br>
			<div class="list-group">
			<%! int i = 0; %> 
			  <c:if test="${!empty translationList}">
	 			<c:forEach items="${translationList}" var="translation">			
			       <a id="<c:if test='${word.translation == translation}'>correct</c:if>" href="javascript:saveResult()" class="list-group-item" style="width:400px;font-size:18px;cursor:pointer;">${translation}</a>
			    </c:forEach>
	 		</c:if>
			</div>
			<a href="javascript:location.reload()" class="list-group-item" style="width:300px;font-size:18px;cursor:pointer;">Next</a>
			</center>
		</div>
		<jsp:include page="templates/sidebar.jsp" />
	</div>
</div>
<script type="text/javascript">
function saveResult() {
	if($("#correct").hasClass("active")) return;
	
	var q = document.querySelectorAll(":hover");
	var selectedTranslation = q[q.length - 1];
	var correctTranslation = $("#correct")[0];
	correctTranslation.className += " active";

	$.ajax({
		type : "POST",
		cache : false,
		url : '/saveResult',
		data : {
			'word' : original.textContent,
			'isCorrect' : selectedTranslation.innerHTML == correctTranslation.innerHTML
		},
		success : function(response) {
			
		}
	});

};
</script>
</body>
</html>