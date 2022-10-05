package com.goodee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVC프로젝트에 관련된 설정을 하는 클래스
@Configuration
// Controller 어노테이션이 세팅되어있는 클래스를 등록하는 어노테이션 
// componentScan이랑 같이 움직여야함 (스캔이 읽어드린 클래스 중에 @controller가 있으면 @enableWebMvc가 알아서 controller를 만들어준다)
@EnableWebMvc
// 스캔할 패키지 지정
@ComponentScan("com.goodee.controller")     //컨트롤러 스펙 정보가 들어있는 interface (Spring core에서의 설정이 아닌 springWebMVC에 특화된 설정을 사용할 경우)
public class ServletAppContext implements WebMvcConfigurer{
	
	//configureviewResolerver
	//Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙여주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureViewResolvers(registry);
		
		//여기서 부터 추가하는 것 앞뒤로 넣을 경로
		registry.jsp("/WEB-INF/views/", ".jsp");
		
	}
	
	// resoursesHandler
	// 정적 파일의 경로 세팅
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		//여기서 부터 내가 추가             전체경로 아래의 모든 경로(어디경로든 resources찾음)
//		registry.addResourceHandler("/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/resource/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///D:/sample/");
	}
	
	// 파일 업로드 세팅
	private final int MAX_SIZE = 10 * 1024 * 1024;
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		// 디폴트 인코딩 타입 설정
		multipartResolver.setDefaultEncoding("utf-8");
		// 전체 올릴 수 있는 파일의 총 용량 최대치
		multipartResolver.setMaxUploadSize(MAX_SIZE*10);
		// 파일 한개당 올릴 수 있는 용량 최대치
		multipartResolver.setMaxUploadSizePerFile(MAX_SIZE);
		
		return multipartResolver;
	}
}
