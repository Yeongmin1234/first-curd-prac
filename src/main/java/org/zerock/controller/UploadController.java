package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	
	
	@GetMapping("/uploadForm")  
	public void uploadForm() {

		log.info("upload form");
	}

	
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		for (MultipartFile multipartFile : uploadFile) {

			log.info("-------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			log.info("saveFile : "+saveFile);
			
			try { 
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}  //end catch
		}  //end for

	}

	
	private boolean checkImageType(File file) {//

		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	
	
//첨부파일 업로드
	
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("uploadFile : " +uploadFile[0].getOriginalFilename());
		List<AttachFileDTO> list = new ArrayList<>();

		String uploadFolder = "C:\\kym\\eclipse\\workspace\\modify\\src\\main\\webapp\\resources";
		String uploadFolderPath = "\\img";
							
		log.info("getFolder : "+uploadFolder+uploadFolderPath);
		// make folder --------
		File uploadPath = new File(uploadFolder, uploadFolderPath);
//		File uploadPaths = new File(uploadFolder, uploadFolderPaths);
		
		if (uploadPath.exists() == false) {  
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();

			log.info("only file name: " + uploadFileName);
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			log.info("only file name: " + uploadFileName);
			
			log.info("only file name: " + uploadFileName.lastIndexOf("\\") + 1);
			
			attachDTO.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {
				File saveFile = new File(uploadPath, uploadFileName);

				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());

				attachDTO.setUploadPath(uploadFolderPath);

				if (checkImageType(saveFile)) {

					attachDTO.setImage(true);
				}
				list.add(attachDTO);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} // end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	
//섬네일 업로드	
	
	@PostMapping(value = "/uploadAjaxActions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPostt(MultipartFile[] tuploadFile) {
		log.info("uploadFile : " +tuploadFile[0].getOriginalFilename());
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\kym\\eclipse\\workspace\\modify\\src\\main\\webapp\\resources";
		String uploadFolderPaths = "\\thumb";
							
		File uploadPaths = new File(uploadFolder, uploadFolderPaths);
for (MultipartFile multipartFile : tuploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();

			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			
			attachDTO.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {
					attachDTO.setUuid(uuid.toString());
					
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPaths, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 200, 200);
					
					thumbnail.close();
				
				list.add(attachDTO);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} // end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	
//	
//	@GetMapping("/display")
//	@ResponseBody
//	public ResponseEntity<byte[]> getFile(String fileName) {
//
//		log.info("fileName: " + fileName);
//		
//		File file = new File("C:\\upload\\" + fileName);
//		
//		log.info("file: " + file);
//
//		ResponseEntity<byte[]> result = null;//???
//
//		try {
//			HttpHeaders header = new HttpHeaders();
//			
//			header.add("Content-Type", Files.probeContentType(file.toPath()));
//			
//			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody			
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) throws UnsupportedEncodingException {
		
		Resource resource = new FileSystemResource("C:\\kym\\eclipse\\workspace\\modify\\src\\main\\webapp\\resources\\" + fileName);

		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		String resourceName = resource.getFilename();

		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		HttpHeaders headers = new HttpHeaders();
		try {

			boolean checkIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);
			
			String downloadName = null;

			if (checkIE) { 
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF8").replaceAll("\\+", " ");
			} else {
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}

			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {

		log.info("deleteFile: " + fileName);
		log.info("deleteFile type: " + type);

		File file;

		try {
			file = new File("C:\\kym\\eclipse\\workspace\\modify\\src\\main\\webapp\\resources" + URLDecoder.decode(fileName, "UTF-8"));
			log.info("00000 " +  URLDecoder.decode(fileName, "UTF-8"));
			file.delete();

	
			if (type.equals("image")) {

				String largeFileName = file.getAbsolutePath().replace("s_", "");

				log.info("largeFileName: " + largeFileName);

				file = new File(largeFileName);

				file.delete();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>("deleted", HttpStatus.OK);

	}

}