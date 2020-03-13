<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1" class="list_table" style="width: 85%">
		<colgroup>
			<col style="width: 150">
			<col style="width: 600">
		</colgroup>

		<tr>
			<th>작성자</th>
			<td>${bbs.id}</td>
		</tr>

		<tr>
			<th>제목</th>
			<td>${bbs.title}</td>
		</tr>

		<tr>
			<th>작성일</th>
			<td>${bbs.wdate}</td>
		</tr>

		<tr>
			<th>조회수</th>
			<td>${bbs.readcount}</td>
		</tr>

		<tr>
			<th>정보</th>
			<td>${bbs.ref}-${bbs.step}-${bbs.depth}</td>
		</tr>

		<tr>
			<th>내용</th>
			<td align="center"><textarea rows="10" cols="90"
					readonly="readonly">${bbs.content}</textarea></td>
		</tr>
	</table>
<br>
<hr>
<br>
	<table border="1" class="list_table" style="width: 85%">
	<colgroup>
		<col style="width: 150">
		<col style="width: 600">
	</colgroup>
		<tr>
			<th>ID</th>
			<td style="float: left;"><input type="text" id="id" name="id"
				value=${login.id } readonly></td>
		</tr>
		<tr>
			<th>Title</th>
			<td style="float: left;"><input type="text" id="title"
				name="title" size="80"></td>
		</tr>
	
		<tr>
			<th colspan="2">Content</th>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="5" cols="97" id="content"
					name="content"></textarea></td>
		</tr>
	
		<tr height="5">
		</tr>
		<tr>
			<td colspan="2">
				<input type="hidden" id="seq" value="${bbs.seq} ">
				<button type="button" id="answerBtn">답글등록</button>
				<button type="button" onclick="location.href='bbsPagingList.do'">목록으로</button>
			</td>
		</tr>
	</table>
<script type="text/javascript">

	$("#answerBtn").click(function(){
		
		var bbs = {
				id : $("#id").val(),
				title : $("#title").val(),
				content : $("#content").val(),
				seq: $("#seq").val()
			};
			$.ajax({
				url : "answerAf.do",
				type : "get",
				data : bbs,
				success : function(msg) {
					if (msg == "ok") {
						alert("글쓰기 성공. 목록으로 이동합니다");
						location.href = "bbslist.do";
					} else {
						alert("글쓰기 실패");
						location.href = "bbslist.do";
					}
				},
				error : function() {
					alert('err');
				}
			});
	});
</script>

</body>
</html>