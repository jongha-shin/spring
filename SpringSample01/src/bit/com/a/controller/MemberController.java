package bit.com.a.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.MemberDto;
import bit.com.a.service.MemberService;

@Controller
public class MemberController {

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired	// -> 의존성	Dependency Injection(DI) I
	MemberService memberService;
	
	@RequestMapping(value = "test.do", method=RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "login.do", method=RequestMethod.GET)
	public String login(Model model) {
		
		return "login.tiles";
	}
	
	@RequestMapping(value = "regi.do", method=RequestMethod.GET)
	public String regi() {
		return "regi.tiles";
	}
	
	@RequestMapping(value="loginAf.do", method=RequestMethod.POST)
	public String loginAf(MemberDto dto, HttpSession session) {
		MemberDto login = memberService.login(dto);
		if(login != null && !login.getId().equals("")) {
			session.setAttribute("login", login);
			session.setMaxInactiveInterval(60*60*2);
			return "redirect:/bbslist.do";
		}else {
			return "redirect:/login.do";
		}
		
	}
	
		
	
	
	/////////// ajax /////////////
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method=RequestMethod.GET)
	public String checkid(String id) {
		String str = "";
		
		int count = memberService.getId(id);
		if(count > 0) {
			str = "no";
		}else {
			str = "ok";
		}
		
		return str;
	}
	@ResponseBody
	@RequestMapping(value = "regiAf.do", method=RequestMethod.POST)
	public String regiAf(MemberDto member) {
		String str = "";
		
		boolean b = memberService.addmember(member);
		if(b) {
			str = "ok";
		}else {
			str ="no";
		}
		return str;
	}
	
	
}





