package bit.com.a.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.model.CalParam;
import bit.com.a.model.CalendarDto;
import bit.com.a.model.CalendarParam;
import bit.com.a.model.MemberDto;
import bit.com.a.service.BitCalendarService;
import bit.com.a.util.CalendarUtil;

@Controller
public class BitCalendarController {

	@Autowired
	BitCalendarService service;
	
	@RequestMapping(value = "calendarlist.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String calendarlist(Model model, CalParam jcal, HttpSession session) {
		model.addAttribute("doc_title", "일정");
		
		jcal.calculate();
		
		// id 취득
		String id = ((MemberDto)session.getAttribute("login")).getId();
		// 날짜 취득
		String yyyymm = CalendarUtil.yyyymm(jcal.getYear(), jcal.getMonth());
		
		CalendarDto fcal = new CalendarDto();
		fcal.setId(id);
		fcal.setRdate(yyyymm);
		
		List<CalendarDto> list = service.getCalendarList(fcal);
		
		model.addAttribute("flist", list);
		model.addAttribute("jcal", jcal);
		
		return "calendar.tiles";
	}
	
	@RequestMapping(value="calwrite.do", method={RequestMethod.GET, RequestMethod.POST})
	public String calwrite(Model model, CalParam jcal) {		
		model.addAttribute("doc_title", "일정쓰기");
		
		jcal.calculate();
		model.addAttribute("jcal", jcal);
		
		return "calwrite.tiles";		
	}
	
	@RequestMapping(value="calwriteAf.do", method={RequestMethod.GET, RequestMethod.POST})
	public String calwriteAf(Model model, CalendarParam calparam) {			
		String yyyymmdd = "" + calparam.getYear() + CalendarUtil.two(calparam.getMonth()) +
				CalendarUtil.two(calparam.getDay());
		
		CalendarDto dto = new CalendarDto(calparam.getId(), 
										calparam.getTitle(), 
										calparam.getContent(), 
										yyyymmdd);		
		service.writeCalendar(dto);
		
		model.addAttribute("year", calparam.getYear());
		model.addAttribute("month", calparam.getMonth());
		
		return "forward:/calendarlist.do";		
	}
	
	@RequestMapping(value="caldetail.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String caldetail(Model model, int seq) {
		model.addAttribute("doc_title", "일정 상세보기");
		
		CalendarDto dto = service.caldetail(seq);
		
		model.addAttribute("dto", dto);
		return "caldetail.tiles";
	}
	
}







