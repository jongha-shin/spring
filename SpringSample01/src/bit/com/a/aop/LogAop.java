package bit.com.a.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAop {
	
	@Around("within(bit.com.a.controller.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		
		// session check
		
		Object obj = joinpoint.proceed(); // 기능 실행
		System.out.println("loggerAOP: " + signatureStr +" 메소드호출");
		
		return obj;
	}
}
