<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% System.out.println("bbslist.jsp 실행"); %>
<!-- arrow class 생성 (클래서에서 setAttribute를 안해도 접근 가능)-->
<jsp:useBean id="ubbs" class="bit.com.a.util.BbsArrow" />

<table class="list_table" style="width: 85%">
	<colgroup>
		<col style="width: 70px">
		<col style="width: auto">
		<col style="width: 100px">
	</colgroup>

	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
	</thead>

	<tbody>
		<c:if test="${empty bbsPagingList }">
			<tr>
				<td colspan="3">작성된 글이 없습니다</td>
			</tr>
		</c:if>

		<c:forEach items="${bbsPagingList }" var="bbs" varStatus="vs">
			<c:if test="${bbs.del eq 1 }">
				<tr>
					<td>${bbs.rnum }</td>
					<td colspan="2" style="color: red">
						작성자가 삭제한 게시글입니다.
					</td>
				</tr>
			</c:if>
			
			<c:if test="${bbs.del eq 0 }">
			<!-- arrow setting -->
			<jsp:setProperty property="depth" name="ubbs" value="${bbs.depth }" />


			<tr class="_hover_tr">
				<td>${bbs.rnum }</td>
				<td style="text-align: left;">
					<jsp:getProperty property="arrow" name="ubbs" />
					<a href="bbsdetail.do?seq=${bbs.seq }">${bbs.title }</a></td>
				<td>${bbs.id }</td>
			</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>
<br>

<div id="paging_wrap">
	<jsp:include page="/WEB-INF/views/bbs/paging.jsp" flush="false">
		<jsp:param name="totalRecordCount" value="${totalRecordCount }"/>
		<jsp:param name="pageNumber" value="${pageNumber }"/>
		<jsp:param name="pageCountPerScreen" value="${pageCountPerScreen }"/>
		<jsp:param name="recordCountPerPage" value="${recordCountPerPage }"/>
	</jsp:include>

</div>
<!-- 
<div>
	<c:forEach begin="0" end="${totalPage-1}" step="1" varStatus="page">
		<c:if test="${pageNumber eq page.index }">
			<c:out value="${page.index + 1 }"></c:out>
		</c:if>
		<c:if test="${pageNumber ne page.index }">
			<a href="#none" onclick="goPage(${page.index})"><c:out value="${page.index +1 }"></c:out></a>
		</c:if>			
	</c:forEach>
</div>
 -->
<br>
<div>
	<select id="choice">
		<option value="sel" selected="selected">선택</option>
		<option value="title">제목</option>
		<option value="writer">작성자</option>
		<option value="content">내용</option>
	</select>

	<input type="text" id="search" value="">

	<span class="button blue"><button onclick="searchBbs()">검색</button></span>
</div>
<div>

<br>
</div>

<script type="text/javascript">
$(document).ready(function () {
	var _choice = '${choice }';
	var _searchWord = '${searchWord }';
	if(_choice != '' && _choice != 'sel'){		
		if(_searchWord != ""){			
			
			$("#choice").val(_choice);
			$("#search").val(_searchWord);
		}
	}
});

function searchBbs() {
	var choice = $("#choice").val();
	var word = $("#search").val();
	alert(choice);
	alert(word);

	if(word == ""){
		$("#choice").val("sel");
	}
	
	location.href = "bbsPagingList.do?searchWord=" + word + "&choice=" + choice;
	
}
function goPage( pageNum ) {
	var choice = $("#choice").val();
	var word = $("#search").val();
	//alert(pageNum);
	if(word == ""){
		document.getElementById("choice").value = 'sel';
	}
	
	location.href = "bbsPagingList.do?searchWord=" + word + "&choice=" + choice + "&spageNumber=" + pageNum;
}
</script>
