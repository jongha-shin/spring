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
public class BbsController {

	@Autowired
	BbsService bbsService;
	
	@RequestMapping(value="bbslist.do", method=RequestMethod.GET)
	public String bbslist(Model model) {
		List<BbsDto> bbslist = bbsService.getBbsList();
		model.addAttribute("bbslist", bbslist);
		
		return "bbslist.tiles";
	}
	
	
	/*
	@RequestMapping(value = "bbslist.do", method = RequestMethod.GET)
	public String bbsList(Model model, BbsParam bp) {
		
		List<BbsDto> list = bbsService.getBbsList(bp);
		model.addAttribute("bbsList", list);

		return "bbslist_css";
	}

	@RequestMapping(value = "bbsWrite.do")
	public String bbsWrite() {
		//logger.info("BbsController bbsWrite " + new Date());

		return "bbswrite";
	}

	@ResponseBody
	@RequestMapping(value = "bbsWriteAf.do", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public String bbsWriteAf(BbsDto bbs) {
		//logger.info("BbsController bbsWriteAf " + new Date());
		//logger.info(bbs.toString());
		String str = "";

		int count = bbsService.writeBbs(bbs);
		if (count == 1) {
			str = "ok";
		} else {
			str = "no";
		}

		return str;
	}

	@RequestMapping(value = "bbsDetail.do", method = RequestMethod.GET)
	public String bbsDetail(int seq, Model model) {
		// logger.info("BbsController bbsDetail " + new Date());
		// logger.info(seq+"");
		bbsService.readcount(seq);
		BbsDto bbs = bbsService.getBbs(seq);
		model.addAttribute("bbsDetail", bbs);

		return "bbsdetail";
	}

	@ResponseBody
	@RequestMapping(value = "bbsDelAf.do", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public String bbsDelAf(int seq) {
		//logger.info("BbsController bbsDelAf " + new Date());
		String str = "";

		int count = bbsService.delBbs(seq);
		if (count == 1) {
			str = "ok";
		} else {
			str = "no";
		}

		return str;
	}

	@RequestMapping(value = "bbsUpdate.do", method = RequestMethod.GET)
	public String bbsUpdate(int seq, Model model) {
		// logger.info("BbsController bbsDetail " + new Date());
		// logger.info(seq+"");
		BbsDto bbs = bbsService.getBbs(seq);
		model.addAttribute("bbsDetail", bbs);

		return "bbsupdate";
	}

	@ResponseBody
	@RequestMapping(value = "bbsUpdateAf.do", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public String bbsUpdateAf(BbsDto bbs) {
		//logger.info("BbsController bbsUpdateAf " + new Date());
		//logger.info(bbs.toString());
		String str = "";
		int count = bbsService.updateBbs(bbs);
		if (count == 1) {
			str = "ok";
		} else {
			str = "no";
		}

		return str;
	}

	@RequestMapping(value = "bbsAnswer.do", method = RequestMethod.GET)
	public String bbsAnswer(int seq, Model model) {
		// logger.info("BbsController bbsDetail " + new Date());
		// logger.info(seq+"");
		BbsDto bbs = bbsService.getBbs(seq);
		model.addAttribute("bbsDetail", bbs);

		return "answer";
	}

	@ResponseBody
	@RequestMapping(value = "bbsAnswerAf.do", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public String bbsAnswerAf(BbsDto bbs) {
		//logger.info("BbsController bbsUpdateAf " + new Date());
		String str = "";
		// int count = bbsService.updateBbs(bbs);
		int count = bbsService.bbsStepUp(bbs.getSeq());

		int result = bbsService.bbsAnswer(bbs);

		if (result == 1) {
			//logger.info("answer ok");
			str = "ok";
		} else {
			str = "no";
			//logger.info("answer no");
		}

		return str;
	}
*/
}
