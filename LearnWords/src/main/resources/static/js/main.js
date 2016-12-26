function translate() {
	var q = document.querySelectorAll(":hover");
	var word = q[q.length - 1].innerHTML;
	$.ajax({
		type : "POST",
		cache : false,
		url : '/translate',
		data : {
			'word' : word,
			'article' : location.search.split('id=')[1]
		},
		success : function(response) {
			$('#translation').html(response.translation);
		}
	});
}