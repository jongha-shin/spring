<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<td><input type="text" value="${bbs.title}" id="title">
			</td>
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
			<td align="center"><textarea rows="10" cols="90" id="content">${bbs.content}</textarea></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<a href="#none" id="_btnLogin" title="수정하기">
					<img alt="" src="./image/bupdate.png">
				</a>
				
			</td>
		</tr>
</table>

<script type="text/javascript">
$("#_btnLogin").click(function(){
	var ubbs = {
			seq : ${bbs.seq},
			title : $("#title").val(),
			content : $("#content").val()
				}
	
	$.ajax({
		url:"updateAf.do",
		data:ubbs,
		type:"get",
		success : function(msg) {
			if (msg == "ok") {
				alert("글수정 성공. 글세부정보로 이동합니다");
				location.href = "bbsdetail.do?seq="+${bbs.seq};
			} else {
				alert("글수정 실패");
				location.href = "bbsdetail.do?seq="+${bbs.seq};
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