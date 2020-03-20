<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="_youtube_">
	<iframe id="_youtube" width="640" height="360" src="http://www.youtube.com/embed/" frameborder="0" allowfullscreen>
	
	</iframe>
</div>

<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width: 70px"><col style="width: auto"><col style="width: 100px"><col style="width: 40px">
</colgroup>
<thead>
	<tr>
		<th>번호</th><th>제목</th><th>유투브 고유번호</th><th>삭제</th>
	</tr>
</thead>
<tbody>
<c:if test="${empty mylist }">
<tr>
	<td colspan="4">
		작성된 목록이 없습니다
	</td>
</tr>
</c:if>
<c:forEach items="${mylist }" var="my" varStatus="vs">
<tr class="_hover_tr">
	<td>${vs.count }</td>
	<td style="text-align: left;" id="_v${my.vname}ed2">
		<div class="c_vname" vname="${my.vname }">${my.title }</div>
	</td>
	<td>${my.vname }</td>
	<td >
		<button type="button" class="ck_seq" value=${my.seq }>삭제</button>
	</td>
</tr>

</c:forEach>
</tbody>

</table>

<script type="text/javascript">
$(document).ready(function(){
	$("#_youtube_").hide();
	$("._hober_tr").mouseover(function(){
		$(this).children().css("backgroundcolr","#f0f5ff")
	}).mouseout(function(){
		$(this).children().css("backgroundcolr","#ffffff")
	});
});

$(".c_vname").click(function(){
	$("#_youtube_").show();
	$("#_youtube").attr("src","http://www.youtube.com/embed/"+$(this).attr("vname"));
});

$(".ck_seq").click(function(){
	var seq = $(this).val();
	//alert(seq);
	$.ajax({
		type:'post',
		url:'deletetube.do',
		data: { "seq":seq },
		success: function(msg){
			if(msg == "ok"){
				alert('삭제 성공');
				location.reload();
			}else{
				alert("삭제 실패");
			}
		},
		error:function(){
			alert("err");
		}
	});
});
</script>