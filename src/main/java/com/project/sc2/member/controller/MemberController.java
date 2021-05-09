package com.project.sc2.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.sc2.member.Member;
import com.project.sc2.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;
	
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	
	
	// Join
	@RequestMapping("/joinForm")
	public String joinForm(Member member) {

		return "/member/joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinReg(Member member) {
		int result = 0;
		
		result = service.memberRegister(member);
		//result로 jsp페이지에서 메시지박스를 띄워주기 위함...
		//리턴 jsp의 경우 한번 더 생각해보고 페이지 지정해보기...
		return "/index";
	}
		
	// Login
	@RequestMapping("/loginForm")
	public String loginForm(Member member) {
		return "/member/loginForm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(Member member, HttpSession session) {
		
		Member mem = service.memberSearch(member);
		if(mem == null) 
			return "/member/loginForm";
		
		session.setAttribute("member", mem);
		
		return "/index";
	}
	
	// Logout
	@RequestMapping("/logout")
	public String memLogout(Member member, HttpSession session) {
		session.invalidate();
		
		return "/member/logoutOk";
	}
	
	
	// Member Modify
	
	// Member ModifyForm
		@RequestMapping("/modifyForm")
		public ModelAndView memModifyForm(Member member, ModelAndView miv, HttpSession session) {
			
			miv.setViewName("/member/modifyForm");
			miv.addObject("member", session.getAttribute("member"));
			return miv;
		}
		
		
		
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public String memModify(Member member) {
		
		service.memberModify(member);
		
		return "/member/modifyOk";
	}
	
	@RequestMapping(value = "/removeForm")
	public String removeForm(HttpSession session) {
		//Member member = new Member();
		//member = (Member) session.getAttribute("member");
		//System.out.println(member.getmId());
		return "/member/removeForm";
	}
	
	@RequestMapping(value = "/memRemove")
	public String memRemove(HttpSession session) {
		Member member = new Member();
		member = (Member) session.getAttribute("member");
		
		service.memberRemove(member.getmId());
//		System.out.println(member.getmId());
		session.invalidate();
		return "/member/removeOk";
	}
	

}
