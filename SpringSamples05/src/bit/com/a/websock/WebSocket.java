package bit.com.a.websock;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

// 서버부분
public class WebSocket extends TextWebSocketHandler {
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	public WebSocket() {
		System.out.println("------Echo Handler 생성됨 "+ new Date());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 연결된 후 실행									//  소켓정보
		System.out.println("연결됨 " + new Date());
		users.put(session.getId(), session);	// 소켓정보 담음
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 연결 종료
		System.out.println("연결 종료 " + new Date());
		users.remove(session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메세지 수신
		// 방만든경우 특정사람들에게만 보낼결우 이부분 수정해야함
		System.out.println("메세지 수신 " + message + new Date());
		
		//전체
		for(WebSocketSession s : users.values()) {
			s.sendMessage(message);
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// 예외 발생
		System.out.println("예외 발생 : "  + session.getId() +" / "+ new Date());
	}
	
	
	
	
	
}
