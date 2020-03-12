package bit.com.a.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bit.com.a.model.MemberDto;

@Aspect
public class LogAop {

	@Around("within(bit.com.a.controller.*) or within(bit.com.a.dao.*.*)")	
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();

		// session check (singleton방식으로 request얻어옴)
		
		HttpServletRequest request =
		( (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes() ).getRequest();
		if(request != null) {	// 로그인한 이후
			HttpSession session = request.getSession();
			MemberDto login = (MemberDto)session.getAttribute("login");
			if(login == null) {	// 세션에들어간 로그인정보가 소멸되었을때 
				return "redirect:/login.do";
			}
		}
		
		Object obj = joinpoint.proceed();	// 기능 실행		
		System.out.println("loggerAOP:" + signatureStr + " 메소드호출");
		
		return obj;			
	}
}