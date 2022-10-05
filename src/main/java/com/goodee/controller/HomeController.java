package com.goodee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//이 클래스를 서블릿으로 사용할 예정 

//MVC에서는 @Component 어노테이션을 대신할 애들이 많은데 그중에 하나
@Controller  //이것만 쓰면 controller완성 & spring에서 bean객체로 짜잔 //controller어노테이션을 호출하는 servlet이 따로 있어서 기존에 쓰던 기능을 다 쓸 수 있다
public class HomeController {
	
	@RequestMapping(value="/") // servlet에 가장 위에 있는 @WebServlet("/Quiz1Controller")이 역활
	public String home() {
		return "index";
	}
}
