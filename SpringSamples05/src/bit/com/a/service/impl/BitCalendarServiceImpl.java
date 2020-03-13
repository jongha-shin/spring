package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitCalendarDao;
import bit.com.a.model.CalendarDto;
import bit.com.a.service.BitCalendarService;

@Service
public class BitCalendarServiceImpl implements BitCalendarService {

	@Autowired
	BitCalendarDao calendarDao;

	@Override
	public List<CalendarDto> getCalendarList(CalendarDto cal) {		
		return calendarDao.getCalendarList(cal);		
	}

	@Override
	public boolean writeCalendar(CalendarDto cal) {		
		return calendarDao.writeCalendar(cal);		
	}

	@Override
	public CalendarDto getDay(CalendarDto cal) {		
		return calendarDao.getDay(cal);
	}

	@Override
	public List<CalendarDto> getDayList(CalendarDto cal) {		
		return calendarDao.getDayList(cal);		
	}

	@Override
	public List<CalendarDto> getCallistmonth(CalendarDto caldto) {		
		return calendarDao.getCallistmonth(caldto);		
	}

	@Override
	public void deletecal(int seq) {
		calendarDao.deletecal(seq);
	}
	
	
	
}






