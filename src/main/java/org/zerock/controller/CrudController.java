package org.zerock.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.CrudVO;
import org.zerock.domain.BoardAttachVO;
import org.zerock.service.CrudService;

import jdk.internal.org.jline.utils.Log;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
public class CrudController {
	
	public CrudService service;
	UploadController upload;
	
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("list", service.list());
	}
	
	@GetMapping("create")
	public void create() {
	}
	@PostMapping("create")
	public String create(CrudVO vo, RedirectAttributes rttr, Model model) {
		service.create(vo);
		if(vo.getTitle().length()==0 && vo.getText().length()==0) {
			return "create";
		}
		log.info(vo);
		rttr.addAttribute("bno", vo.getBno());
		return "redirect:/read";
	}
	@GetMapping("read")
	public void read(int bno, Model model,BoardAttachVO vo) {
		model.addAttribute("read", service.read(bno));
	}
	@GetMapping("update")
	public void update(int bno, Model model) {
		model.addAttribute("update", service.read(bno));
	}
	@PostMapping("update")
	public String modifyPostNo(CrudVO vo,RedirectAttributes rttr, Model model) {
		model.addAttribute("update", service.update(vo));
		rttr.addAttribute("bno", vo.getBno());
		return "redirect:/read";
	}
	@GetMapping("delete")
	public String delete(int bno) {
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		if(service.delete(bno)==1) {
			deleteFile(attachList);
		}
		return "redirect:/list";
	}

	//CRUD
//================================================================
	//Upload
	@GetMapping(value="getAttachList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList (int bno,@RequestParam(value = "in", required=false) List<Integer> in){
		return new ResponseEntity<>(service.getAttachList(bno),HttpStatus.OK);
	}
	

	@PostMapping("/deleteFilee1")
	@ResponseBody
	public void deleteFile(List<BoardAttachVO> attachList) {
		
		if(attachList == null || attachList.size()==0) {
			return;
		}

		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\kym\\eclipse\\workspace\\modify\\src\\main\\webapp\\resources"+attach.getUploadpath()+"\\"+
			attach.getUuid()+"\\"+"_"+attach.getFilename());
				Files.deleteIfExists(file);
				
				
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\kym\\eclipse\\workspace\\modify\\src\\main\\webapp\\resources"+attach.getUploadpath()+"\\"+
			attach.getUuid()+"\\"+"_"+attach.getFilename());
					
					Files.delete(thumbNail);}
			} catch(Exception e) {
				log.error("delete"+e.getMessage());
			}

	});
}

}
