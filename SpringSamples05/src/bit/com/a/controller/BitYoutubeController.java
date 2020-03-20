package bit.com.a.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.MemberDto;
import bit.com.a.model.Youtube;
import bit.com.a.model.YoutubeSave;
import bit.com.a.service.BitYoutubeService;
import bit.com.a.util.YoutubeParser;

@Controller
public class BitYoutubeController {

	@Autowired
	BitYoutubeService youtubeservice;
	
	@Autowired
	private YoutubeParser youtubeParser;
	
	@RequestMapping(value = "yutube.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String yutube(String s_keyword, Model model) {
		model.addAttribute("doc_title","Youtube");
		
		if(s_keyword != null && !s_keyword.equals("")) {
			ArrayList<Youtube> getTitles = youtubeParser.getTitles(s_keyword);
			
			model.addAttribute("yulist", getTitles);
			model.addAttribute("s_keyword",s_keyword);
		}
		return "yutube.tiles";
	}
	@ResponseBody
	@RequestMapping(value = "youtubesave.do", method= {RequestMethod.GET, RequestMethod.POST})
	public YoutubeSave youtubesave(YoutubeSave y) {
		
		youtubeservice.writeYoutube(y);
		YoutubeSave ysave = youtubeservice.getYoutube(y);
		
		return ysave;
	}
	
	@RequestMapping(value = "youtubelist.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String mytube(Model model, YoutubeSave y, HttpServletRequest req) {
		model.addAttribute("doc_title","내 유투브");
		String id = ((MemberDto)req.getSession().getAttribute("login")).getId();
		
		y.setId(id);
		List<YoutubeSave> list = youtubeservice.getMytube(y);
		model.addAttribute("mylist", list);
		
		return "mytube.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "deletetube.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String deletetube(int seq) {
		
		String str="";
		
		boolean b = youtubeservice.deletetube(seq);
		if(b) {
			str = "ok";
		}else {
			str = "fail";
		}
		
		return str;
	}
}
