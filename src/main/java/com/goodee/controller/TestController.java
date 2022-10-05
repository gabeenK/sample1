package com.goodee.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.vo.MediaVO;

@Controller
public class TestController {
	
	// Spring file upload 정책
	/* - 스프링에서는 파일을 받기 위한 스펙을 제공하고 있으며 그중 하나가 MultipartFile 클래스에 바로
	 *   바이너리 파일을 넣는 형태를 제공한다.
	 * - 형식 : @RequestParam("type="file" input 이름") MultipartFile file
	 */
	
	@PostMapping("/test1")
	public String singleFileUpload(@RequestParam("mediaFile") MultipartFile file) throws IllegalStateException, IOException {
		
		if(!file.getOriginalFilename().isEmpty()) {
			Path path = Paths.get("D:/sample/"+file.getOriginalFilename()); //이 위치에 파일 저장을 하겠다
			file.transferTo(path);
			System.out.println("매우 잘 저장되었습니다");
		}else {
			System.out.println("에러가 발생했습니다.");
		}
		return "test1_result";
	}
	
	@PostMapping("/test2")
	public String multiFileUpload(@RequestParam("mediaFile") MultipartFile[] files) throws IllegalStateException, IOException {
		
		for (MultipartFile file : files) {
			if(!file.getOriginalFilename().isEmpty()) {
				//Path path = Paths.get("D:/sample/"+file.getOriginalFilename()); //이 위치에 파일 저장을 하겠다
				//file.transferTo(path);
				file.transferTo(Paths.get("D:/sample/"+file.getOriginalFilename()));
				System.out.println(file.getOriginalFilename()+"저장완료.");
			}else {
				System.out.println("에러가 발생했습니다.");
			}
		}
		
		return "test2_result";
	}
	
	@PostMapping("/test3")
	public String multiFileUpload(@RequestParam("mediaFile") MultipartFile[] files,
			@RequestParam String user, @RequestParam String url, Model model) throws IllegalStateException, IOException {
		
		for (MultipartFile file : files) {
			if(!file.getOriginalFilename().isEmpty()) {
				//Path path = Paths.get("D:/sample/"+file.getOriginalFilename()); //이 위치에 파일 저장을 하겠다
				//file.transferTo(path);
				file.transferTo(Paths.get("D:/sample/"+file.getOriginalFilename()));
				System.out.println(file.getOriginalFilename()+"저장완료.");
			}else {
				System.out.println("에러가 발생했습니다.");
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("url", url);
		return "test3_result";
	}

	@PostMapping("/test4")
	public String multiFileUpload(MediaVO vo) throws IllegalStateException, IOException {
		
		MultipartFile[] files = vo.getMediaFile();
		
		for (MultipartFile file : files) {
			if(!file.getOriginalFilename().isEmpty()) {
				file.transferTo(Paths.get("D:/sample/"+file.getOriginalFilename()));
				System.out.println(file.getOriginalFilename()+"저장완료.");
			}else {
				System.out.println("에러가 발생했습니다.");
			}
		}
		return "test4_result";
	}
	
	
	@PostMapping("/test5")
	@ResponseBody
	public String multiFileUploadWithAjax(MultipartFile[] uploadFile) throws IllegalStateException, IOException {
		
		for (MultipartFile file : uploadFile) {
			if(!file.getOriginalFilename().isEmpty()) {
				//Path path = Paths.get("D:/sample/"+file.getOriginalFilename()); //이 위치에 파일 저장을 하겠다
				//file.transferTo(path);
				file.transferTo(Paths.get("D:/sample/"+file.getOriginalFilename()));
				System.out.println(file.getOriginalFilename()+"저장완료.");
			}else {
				System.out.println("에러가 발생했습니다.");
			}
		}
		
		return "test5 received";
	}
	
	// 데이터 전송시에는 반드시 response 객체를 통해 전송을 해야한다.
	@GetMapping("/download1")
	public void download(HttpServletResponse response) throws Exception {
		String path = "D:/sample/ABC.txt";
		
		// 다운로드 받고자 하는 파일에 대한 Path 지정
		Path file = Paths.get(path);
		
		// 파일 이름 utf-8로 인코딩		// 파일 안깨지도록
		String filename = URLEncoder.encode(file.getFileName().toString(), "UTF-8");
		
		// response 객체의 헤더 세팅		Content-Disposition: attachment;filename=ABC.txt 개발자도구 네트워크에 headers확인
		response.setHeader("Content-Disposition","attachment;filename="+filename);
		
		// 파일 channel 설정		// 단순히 가져오는 용도의 channal  .Read
		FileChannel fc = FileChannel.open(file, StandardOpenOption.READ);
						//FileChannel.open( Path , OpenOption)
		
		// response에서 output스트림 추출   채널 추출 전단계  단방향 통로
		// 내가 볼땐 원래 file to file이면 양방향이지만 우리는 response라는 객체를 통해서 file을 보내야하기 때문에 
		// response에서는 channel을 못불러내고 Stream만 뽑아낼 수 있음 따라서 아래랑 같이 사용
		OutputStream out = response.getOutputStream();
		
		// outputStream에서 channel 추출	 그통로에 체널을 불러옴  양방향 통로
		// 데이터를 쓸 수 있도록 인터페이스 제공 
		WritableByteChannel outputChannel = Channels.newChannel(out);
		
		// response 객체로 파일 전송
		fc.transferTo(0, fc.size(), outputChannel);
		
	}
}


