package com.goodee.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 전체 환경 설정을 해주는 엔트리 클래스
public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
	//컨트롤+1 add unimpled 어쩌고
	
	// 프로젝트에서 사용할 Bean들을 정의하기 위한 클래스를 지정한다.
	// 임시적으로 bean들을 설정해주기 위한 클래스의 위치를 지정함()
	@Override	//? : 와일드카드 (어떤 타입도 다 입력 받을 수 있도록)
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootAppContext.class};	//우리가 만든거
	}

	// Spring MVC 프로젝트를 구성하는데 필요한 모든 설정릏 담은 클래스를 지정하는 메소드.
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletAppContext.class};	//우리가 만든거
	}

	// DispatcherServlet에 매핑할 요청 주소를 세팅한다.	 xml에서 value=("/")이거랑 같은 듯?
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		// "/" : "localhost:8080/프로젝트 주소"라는 뜻
		return new String[] {"/"};
	}
	
	//파라미터 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}
	
}
