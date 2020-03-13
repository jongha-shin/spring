package bit.com.a.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
		
		String yyyyMmdd=CalendarUtil.yyyymmddhhmm(calparam.getYear(),
				calparam.getMonth(), calparam.getDay(),
				calparam.getHour(),calparam.getMin());	// 시간까지 포함 시켰음.
		
		CalendarDto dto = new CalendarDto(calparam.getId(),
										calparam.getTitle(),
										calparam.getContent(),
										yyyyMmdd);		
		service.writeCalendar(dto);
		
		model.addAttribute("year", calparam.getYear());
		model.addAttribute("month", calparam.getMonth());
		
		return "forward:/calendarlist.do";		
	}
	
	@RequestMapping(value="caldetail.do", method={RequestMethod.GET, RequestMethod.POST})
	public String caldetail(CalendarDto fcal, Model model) throws Exception{		
		model.addAttribute("doc_title", "일정");
		
		CalendarDto dto = service.getDay(fcal);
		String wdate = dto.getWdate();		
		
		String year = wdate.substring(0, 4);	// year
		String month = CalendarUtil.toOne(wdate.substring(5, 7)) + "";	// month
		String urls = String.format("%s?year=%s&month=%s", "calendarlist.do", year, month);		
				
		model.addAttribute("cal", dto);
		model.addAttribute("urls", urls);
		
		return "caldetail.tiles";		
	}
	
	@RequestMapping(value = "callist.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String callist(HttpServletRequest request, CalendarParam calparam, Model model) {		
		model.addAttribute("doc_title", "CALENDAR 일별일정");
		
		System.out.println("calparam:" + calparam.toString());
		
		String id=((MemberDto)request.getSession().getAttribute("login")).getId();
		String yyyyMmdd=CalendarUtil.yyyymmdd(calparam.getYear(),
				calparam.getMonth(), calparam.getDay());
		
		CalendarDto fcal=new CalendarDto(-1, id, null, null, yyyyMmdd, null);
		
		System.out.println("id:" + id);
		System.out.println("yyyyMmdd:" + yyyyMmdd);
		
		List<CalendarDto> flist = service.getDayList(fcal);
				
		model.addAttribute("callist", flist);
		
		model.addAttribute("year", calparam.getYear());
		model.addAttribute("month", calparam.getMonth());
		
		return "callist.tiles";
	}
	
	@RequestMapping(value = "calendarMonth.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String callistmonth(HttpServletRequest request, Model model, int year, int month) {
		model.addAttribute("doc_title", "월별 일정 목록");
		
		MemberDto dto = (MemberDto)request.getSession().getAttribute("login");
		
		String id = dto.getId();
		
		Calendar cal = Calendar.getInstance();
		
		if(year == 0 && month == 0){
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}
			
		String rdate = Integer.toString(year) + CalendarUtil.two(month);
		
		CalendarDto caldto = new CalendarDto();
		
		caldto.setId(id);
		caldto.setRdate(rdate);;
		
		List<CalendarDto> callistmonth = service.getCallistmonth(caldto);
		
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		model.addAttribute("callistmonth", callistmonth);		
		
		return "calendarMonth.tiles";
	}
	
	@RequestMapping(value="caldel.do", method={RequestMethod.GET, RequestMethod.POST})
	public String caldel(int seq,CalParam jcal, HttpSession session) {
		service.deletecal(seq);
		
		return "redirect:/calendarlist.do";
	}
	
	@RequestMapping(value="calupdate.do", method={RequestMethod.GET, RequestMethod.POST})
	public String calupdate(CalendarDto fcal, Model model ) {
		model.addAttribute("doc_title", "일정 수정");
		
		CalendarDto dto = service.getDay(fcal);
		String wdate = dto.getWdate();		
		
		String year = wdate.substring(0, 4);	// year
		String month = CalendarUtil.toOne(wdate.substring(5, 7)) + "";	// month
		String urls = String.format("%s?year=%s&month=%s", "calendarlist.do", year, month);		
				
		model.addAttribute("jcal", dto);
		model.addAttribute("urls", urls);
		
		return "calupdate.tiles";
	}
}








