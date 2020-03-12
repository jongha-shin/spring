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


			<tr>
				<td colspan="2">

				<c:if test="${login.id == bbs.id }">
					<a href="#none" id="_update" title="수정"> <img alt=""
						src="./image/bupdate.png">
					</a>
					 <a href="#none" id="_delete" title="삭제"> <img alt=""
						src="./image/del.png">
					</a>
				</c:if>
					<button type="button" onclick="location.href='bbsAnswer.do?seq=${bbs.seq }'">답글달기</button>
					<button type="button" onclick="location.href='bbsPagingList.do'">목록으로</button>
				</td>
			</tr>

	</table>


	<script type="text/javascript">
		$('#_update').click(function() {
			location.href = "bbsupdate.do?seq=" + ${bbs.seq};
		});

		$('#_delete').click(function() {
			var conf = confirm("삭제하시겠습니까?");
			if(conf == true){
				$.ajax({
					url:"bbsdelete.do",
					data:"seq="+${bbs.seq},
					type:"get",
					success:function(data){
						alert("s");
					},
					error:function(){
						alert("err");
					}
				});
			}
		});
	</script>



</body>
</html>