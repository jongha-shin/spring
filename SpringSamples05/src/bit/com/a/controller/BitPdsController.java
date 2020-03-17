package bit.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.model.PdsDto;
import bit.com.a.service.PdsService;
import bit.com.a.util.FUpUtil;

@Controller
public class BitPdsController {

		@Autowired
		PdsService service;
		
		@RequestMapping(value = "pdslist.do", method= {RequestMethod.GET, RequestMethod.POST})
		public String pdslist(Model model) {
			model.addAttribute("doc_title", "자료실목록");
			
			List<PdsDto> list = service.getPdsList();
			model.addAttribute("pdslist",list);
						
			return "pdslist.tiles";
		}
		
		@RequestMapping(value = "pdswrite.do", method= {RequestMethod.GET, RequestMethod.POST})
		public String pdswrite(Model model) {
			model.addAttribute("doc_title", "자료 올리기");
			return "pdswrite.tiles";
		}
		
		@RequestMapping(value = "pdsupload.do", method= RequestMethod.POST)
		public String pdsupload(PdsDto pdsdto,
								@RequestParam(value="fileload", required = false)MultipartFile fileload,
								HttpServletRequest req) {
			// filename 취득
			String filename = fileload.getOriginalFilename();
			pdsdto.setFilename(filename);	// original 파일이름으로 저장
			
			// upload 경로 설정
				// tomcat
			String fupload = req.getServletContext().getRealPath("/upload");
				// 폴더
			//String fupload = "d:\\tmp";
			
			System.out.println("fupload: "+fupload);
			
			// 파일명 변경
			String f = pdsdto.getFilename();
			String newfilename = FUpUtil.getNewFileName(f);	// 파일이름 -> date이름으로 변경
			pdsdto.setDbfilename(newfilename);	// newfile 이름으로 저장
			
			System.out.println("dbfilename: "+pdsdto.getDbfilename());
			
			File file = new File(fupload + "/" + newfilename);
			
			// 실제 파일 업로드 코드
			try {
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				
				// db 저장
				service.uploadPds(pdsdto);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:/pdslist.do";
		}
		
		@RequestMapping(value = "pdsdetail.do", method= {RequestMethod.GET, RequestMethod.POST})
		public String pdsdetail(int seq, Model model) {
			model.addAttribute("doc_title", "자료 보기");
			
			// readcount
			
			// dto취득
			PdsDto dto = service.getPds(seq);
			model.addAttribute("pds", dto);
			return "pdsdetail.tiles";
		}
		
		@RequestMapping(value = "fileDownLoad.do", method= {RequestMethod.GET, RequestMethod.POST})
		public String pdsdown(String filename, int seq, HttpServletRequest req, Model model) {
			
			// download 경로
				// tomcat
			String fupload = req.getServletContext().getRealPath("/upload");
				// 폴더
			//String fupload = "d:\\tmp";
			
			File downloadFile = new File(fupload+"/"+filename);
			model.addAttribute("downloadFile", downloadFile);
			model.addAttribute("seq", seq);
			
			return "downloadView";
			
		}
		
		@RequestMapping(value="pdsupdate.do", method={RequestMethod.GET, RequestMethod.POST})
		public String pdsupdate(int seq, Model model) {		
			model.addAttribute("doc_title", "자료 수정");
			
			PdsDto pdsdto = service.getPds(seq);
			model.addAttribute("pds", pdsdto);
			
			return "pdsupdate.tiles";
		}
		
		@RequestMapping(value="pdsupdateAf.do", method={RequestMethod.GET, RequestMethod.POST})
		public String pdsupdateAf(PdsDto pdsdto,
								String namefile,	// 기존의 파일명
								HttpServletRequest req,
								@RequestParam(value = "fileload", required=false)
								MultipartFile fileload) {
			
			System.out.println(pdsdto.toString());
			System.out.println(namefile);		
			System.out.println(fileload.getOriginalFilename());
			
			pdsdto.setFilename(fileload.getOriginalFilename());
			
			// 파일 경로 취득
			String fupload = req.getServletContext().getRealPath("/upload");
			
			// 수정된 파일 있음
			if(pdsdto.getFilename() != null && !pdsdto.getFilename().equals("")) {
				
				String f = pdsdto.getFilename();
				pdsdto.setFilename(f);
				
				String newfile = FUpUtil.getNewFileName(f);
				pdsdto.setDbfilename(newfile);
				
				File file = new File(fupload + "/" + newfile);
				
				// 실제 업로드
				try {
					FileUtils.writeByteArrayToFile(file, fileload.getBytes());
					
					// db 갱신
					service.updatePds(pdsdto);
					
				} catch (IOException e) {				
					e.printStackTrace();
				}			
			}
			// 수정된 파일 없음
			else {						
				// 기존의 파일 명으로 설정
				pdsdto.setFilename(namefile);
				
				// DB
				service.updatePds(pdsdto);
			}		
			
			return "redirect:/pdslist.do";
		}
}
