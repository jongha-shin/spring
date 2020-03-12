<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table class="list_table" style="width: 85%">
	<colgroup>
		<col style="width: 70px">
		<col style="width: auto">
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
		<td colspan="2"><textarea rows="20" cols="97" id="content"
				name="content"></textarea></td>
	</tr>

	<tr height="5">
	</tr>
	<tr align="center">
		<td colspan="2"><input type="button" id="addBbsBtn" value="작성">
			<input type="button" id="cancle" onclick="cancleWrite()" value="취소">
		</td>
	</tr>

</table>
<script type="text/javascript">
	$("#addBbsBtn").click(function() {
		var bbs = {
			id : $("#id").val(),
			title : $("#title").val(),
			content : $("#content").val()
		};
		$.ajax({
			url : "bbswriteAf.do",
			type : "get",
			data : bbs,
			success : function(msg) {
				if (msg == "ok") {
					alert("글쓰기 성공. 목록으로 이동합니다");
					location.href = "bbsPagingList.do";
				} else {
					alert("글쓰기 실패");
					location.href = "bbsPagingList.do";
				}
			},
			error : function() {
				alert('err');
			}
		});
	});
</script>

