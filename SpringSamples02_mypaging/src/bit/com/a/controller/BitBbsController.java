package bit.com.a.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.service.BbsService;

@Controller
public class BitBbsController {

	private static final Logger logger = LoggerFactory.getLogger(BitBbsController.class);
	
	@Autowired
	BbsService bbsService;
	
	@RequestMapping(value = "bbslist.do", method=RequestMethod.GET)
	public String bbslist(Model model) {						
		model.addAttribute("doc_title", "글목록");
		
		List<BbsDto> bbslist = bbsService.getBbsList();			
		model.addAttribute("bbslist", bbslist);
		
		return "bbslist.tiles";
	}	
	
	@RequestMapping(value = "bbswrite.do", method=RequestMethod.GET)
	public String writeBbs(Model model) {
		model.addAttribute("doc_title", "글쓰기");
		return "bbswrite.tiles";
	}

	@RequestMapping(value = "bbsdetail.do", method = RequestMethod.GET)
	public String getBbsDetail(Model model, int seq) {
		bbsService.readcount(seq);
		BbsDto bbs = bbsService.getBbs(seq);
		model.addAttribute("bbs", bbs);
		model.addAttribute("doc_title", "글세부사항");
		return "bbsdetail.tiles";
	}
	
	@RequestMapping(value = "bbsupdate.do", method = RequestMethod.GET)
	public String updateBbs(Model model, int seq) {
		BbsDto bbs = bbsService.getBbs(seq);
		model.addAttribute("bbs", bbs);
		model.addAttribute("doc_title", "글수정하기");
		return "bbsupdate.tiles";
	}
	
	@RequestMapping(value = "bbsAnswer.do", method = RequestMethod.GET)
	public String answerBbs(Model model, int seq) {
		BbsDto bbs = bbsService.getBbs(seq);
		model.addAttribute("bbs", bbs);
		model.addAttribute("doc_title", "답글달기");
		return "bbsanswer.tiles";
	}
	
	
	@RequestMapping(value="bbsPagingList.do", method=RequestMethod.GET)
	public String bbsPagingList(Model model, String choice, String searchWord, String spageNumber ){
	
		int pageNumber = 0;
		if(spageNumber != null && !spageNumber.equals("")) {
			pageNumber = Integer.parseInt(spageNumber);
		}
		if(choice == null || choice.equals("")){
			choice = "sel";
		}
		// 검색어를 지정하지 않고 choice가 넘어 왔을 때
		if(choice.equals("sel")){
			searchWord = "";
		}
		if(searchWord == null || searchWord.equals("")){
			choice = "sel";
		}
		logger.info("choice: "+choice+" /searchWord: "+searchWord+" /pageNumber: "+pageNumber);

		
		BbsParam bbsParam = new BbsParam(searchWord, choice, pageNumber);
		bbsParam.setStart(1+ bbsParam.getRecordCountPerPage()*pageNumber);
		bbsParam.setEnd(10+ bbsParam.getRecordCountPerPage()*pageNumber);
		logger.info("bbsParam: "+bbsParam.toString());
		
		List<BbsDto> list = bbsService.bbsPagingList(bbsParam);
		
		int len = bbsService.getAllBbs(bbsParam);
		logger.info("리스트 전체  len: "+len);
		
		int totalPage = len/10;
		if(len%10>0) {
			totalPage = totalPage+1;
		}
		
		model.addAttribute("choice", choice);
		model.addAttribute("searchWord", searchWord);
		
		model.addAttribute("bbsPagingList", list);
		
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", bbsParam.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalPage);
		return "bbslist.tiles";
	}
	
	
	
	///////////// ajax /////////////
	
	@ResponseBody
	@RequestMapping(value = "bbswriteAf.do", method=RequestMethod.GET)
	public String writeBbsAf(BbsDto bbs) {
		String str ="";
		int count = bbsService.writeBbs(bbs);
		if(count == 1 ) {
			str ="ok";
		}else {
			str="no";
		}
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value="bbsdelete.do", method = RequestMethod.GET)
	public String deleteBbs(int seq) {
		String str="";
		int count = bbsService.deleteBbs(seq);
		if(count == 1 ) {
			str ="ok";
		}else {
			str="no";
		}
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value="updateAf.do", method = RequestMethod.GET)
	public String updateBbs(BbsDto bbs) {
		String str="";
		int count = bbsService.updateBbs(bbs);
		if(count == 1 ) {
			str ="ok";
		}else {
			str="no";
		}
		return str;
	}
	@ResponseBody
	@RequestMapping(value = "answerAf.do", method = RequestMethod.GET)
	public String answerBbs(BbsDto bbs) {
		String str="";
		
		int count = bbsService.answerBbs(bbs);
		if(count == 1 ) {
			str ="ok";
		}else {
			str="no";
		}
		return str;
	}
}
