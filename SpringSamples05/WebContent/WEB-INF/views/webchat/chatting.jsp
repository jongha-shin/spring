<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width: 200px"><col style="width: 500px">
</colgroup>

<tbody>
<tr>
	<th>채팅명</th>
	<td style="text-align: left;">
		<input type="text" id="name">
		<input type="button" id="enterBtn" value="입장" onclick="connect()">
		<input type="button" id="exitBtn" value="나가기" onclick="disconnect()">
	</td>
</tr>

<tr>
	<th>아이디</th>
	<td style="text-align: left;">
		<input type="text" id="nickname" value="${login.id }" readonly="readonly">
	</td>
</tr>
<tr>
	<td colspan="2">
		<textarea rows="10" cols="70" id="charMessageArea"></textarea>
	</td>
</tr>
<tr>
	<td colspan="2">
		<input type="text" id="message" size="20">
		<input type="button" id="sendBtn" value="전송" onclick="send()">
	</td>
</tr>


</tbody>
</table>

<script>
var wsocket;
// 입장 클릭시 호출
function connect(){
	if(wsocket != undefined && wsocket.readyState != WebSocket.CLOSED){
		// 이미 소캣이 생성된 경우
		alert("이미 입장 하셨습니다");
		return;
	}

	// web Socket 생성
	   if($("#name").val() == "aabbcc"){
	      wsocket = new WebSocket("ws://localhost:8090/SpringSamples/echo.do");
	   } else {
	      wsocket = new WebSocket("ws://192.168.2.67:8090/SpringSamples/echo.do");
	   }


	// Web Socket 생성
	//wsocket = new WebSocket("ws://192.168.2.67:8090/SpringSamples/echo.do");
	//alert("wsocket: " + wsocket);

	wsocket.onopen = onOpen;		// onopen 상태일때 onOpen함수를 호출
	wsocket.onmessage = onMessage;
	wsocket.close = onClose;
}

function disconnect(){
	wsocket.close();
	location.href='chatting.do';
}


function onOpen(evt){	// 연결
	appendMessage("연결되었습니다." + "\n");

}
function onMessage(evt){	// 메시지 수신시 
	var data = evt.data;
	if(data.substring(0,4) == "msg:"){
		appendMessage(data.substring(4));
	}
}
function onClose(evt){	// 끊겼을떄
	appendMessage("연결이 끊겼습니다.");
}

function send(){
	var nickname = $("#nickname").val();
	var msg = $("#message").val();

	// 전송
	wsocket.send("msg:"+nickname + " : " + msg);
	$("#message").val("");
}

function appendMessage(msg){	
	// 메시지 추가하고 개행
	$("#charMessageArea").append(msg + "\n");
	
	// 스크롤구현 맨위로  - 자동 스크롤 구현
	const top = $("#charMessageArea").prop('scrollHeight');
	$("#charMessageArea").scrollTop(top);
	
}


</script>