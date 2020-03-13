package bit.com.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.service.BbsService;

@Controller
public class BitBbsController {

	@Autowired
	BbsService bbsService;
	
	@RequestMapping(value = "bbslist.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String bbslist(Model model, BbsParam param) {						
		model.addAttribute("doc_title", "글목록");
		
		// paging 처리
		int sn = param.getPageNumber();	// 0 1 2	현재 페이지
		int start = sn * param.getRecordCountPerPage() + 1; // 1, 11, 21
		int end = (sn + 1) * param.getRecordCountPerPage();	// 10, 20, 30
		
		param.setStart(start);
		param.setEnd(end);
				
		List<BbsDto> bbslist = bbsService.getBbsList(param);
		
		// 글의 총수
		int totalRecordCount = bbsService.getBbsCount(param);
		
		model.addAttribute("bbslist", bbslist);
		
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
				
		model.addAttribute("s_category", param.getS_category());
		model.addAttribute("s_keyword", param.getS_keyword());
		
		return "bbslist.tiles";
	}	
	
	@RequestMapping(value = "bbswrite.do", method = {RequestMethod.GET,	RequestMethod.POST})
	public String bbswrite(Model model) {		
		model.addAttribute("doc_title", "글쓰기");
		
		return "bbswrite.tiles";
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.POST)
	public String bbswriteAf(BbsDto bbs, Model model) throws Exception {
		if(bbs.getContent().equals("") || bbs.getTitle().equals("")){
			return "bbswrite.tiles";
		}
		bbsService.writeBbs(bbs);
		return "redirect:/bbslist.do";
	}
	
	@RequestMapping(value = "bbsdetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String bbsdetail(int seq, Model model) {		
		model.addAttribute("doc_title", "상세글 보기");
		
		BbsDto bbs=bbsService.getBbs(seq);
		model.addAttribute("bbs", bbs);
		return "bbsdetail.tiles";
	}
	
	@RequestMapping(value = "bbsupdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String bbsupdate(int seq, Model model){		
		model.addAttribute("doc_title", "글 수정");
		
		BbsDto bbs=bbsService.getBbs(seq);		
		model.addAttribute("bbs", bbs);		
		return "bbsupdate.tiles";
	}
	
	@RequestMapping(value = "bbsupdateAf.do", 
			method = RequestMethod.POST)
	public String bbsupdateAf(BbsDto bbs,Model model) throws Exception {		
		bbsService.updateBbs(bbs);
		return "redirect:/bbslist.do";
	}
	
	@RequestMapping(value = "bbsdelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteBbs(int seq, Model model) {		
		bbsService.deleteBbs(seq);
		return "redirect:/bbslist.do";		
	}
	
	@RequestMapping(value = "answer.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String answer(int seq, Model model) throws Exception {		
		BbsDto bbs=null;		
		bbs=bbsService.getBbs(seq);
		model.addAttribute("bbs", bbs);
		return "answer.tiles";
	}
	
	@RequestMapping(value = "answerAf.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String answerAf(BbsDto bbs, Model model) throws Exception {		
		bbsService.reply(bbs);		
		return "redirect:/bbslist.do";
	}
}












