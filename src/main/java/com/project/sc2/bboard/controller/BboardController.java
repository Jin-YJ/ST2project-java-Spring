package com.project.sc2.bboard.controller;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.sc2.bboard.Bboard;
import com.project.sc2.bboard.service.BboardService;
import com.project.sc2.member.Member;


@Controller
@RequestMapping("/board")
public class BboardController {
	
	
	@Autowired
	BboardService service;
	
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	
	@RequestMapping("/Bboard")
	public String boardMain(HttpSession session, Model model, HttpServletRequest request) {
		Member mem = (Member) session.getAttribute("member");
		int pageNum = 0;
		String page = request.getParameter("pageNum");
		
		if(page == null) {
			if(mem == null) {
				return "/index";

			}else {
				//서비스 빈객체에서 페이지 수 전달받아서 처음에 뿌려줌.
				pageNum = service.bBoardPageNumber();
				ArrayList<Bboard> list = service.bBoardViewAll(1);
				model.addAttribute("list",list);
				model.addAttribute("pageNum",pageNum);
				//model.addAllAttributes((pageNum);
		
				return "/board/Bboard";
			}
		
		
		
		}
		
		else {
			int pages = (Integer.parseInt(page));
			pageNum = service.bBoardPageNumber();
			ArrayList<Bboard> list = service.bBoardViewAll(pages);
			model.addAttribute("list",list);
			model.addAttribute("pageNum",pageNum);
	
			return "/board/Bboard";
		}
	}
		
	
	
	//게시판 글쓰기 폼 이동
	@RequestMapping("/writeForm") 
	public String writeForm(Bboard board, HttpSession session) {
		Member mem = (Member) session.getAttribute("member");
		if(mem == null) {
			return "/index";
			
		}
		else {
				return "/board/writeForm";
		
		}
	}
	
	
	//게시판 글쓰기 등록
	@RequestMapping(value = "/writeBoard" , method = RequestMethod.GET)
	public String writeBoard(Bboard board, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		
		int result = 0;
		result = service.boardWriter(board, member);
		return "/index";
	}
	 
	
	//게시판 수정
	@RequestMapping(value = "/modifyForm")
	public ModelAndView modifyForm(HttpServletRequest request, ModelAndView miv) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		Bboard boards = service.bBoardViewContent(bno);

		miv.setViewName("/board/modifyForm");
		miv.addObject("board", boards);
		return miv;
	}
	
	
	@RequestMapping(value = "/modifyBoard" , method = RequestMethod.GET)
	public String modifyBoard(HttpServletRequest request) {
		
		Bboard board = new Bboard();
		//System.out.println("여긴 컨트롤러 modifyboard");
		
		board.setBno(Integer.parseInt(request.getParameter("bno")));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		
		service.bBoardUpdate(board);
		
		return "/board/modifyOk";
	}
	
	
	
	
	//게시판 상세페이지
	@RequestMapping(value = "/viewContent")
	public ModelAndView viewContent(HttpServletRequest request, ModelAndView miv, HttpSession session) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		//System.out.println("뷰페이지 컨트롤러");
		Member member = (Member) session.getAttribute("member");
		
		//System.out.println("세션값 조회" + member.getmId());
		
		//히트 증가 메소드
		service.bBoardHit(bno);
		Bboard boards = service.bBoardViewContent(bno);
		miv.setViewName("/board/viewBoard");
		miv.addObject("board", boards);
		miv.addObject("mid",member.getmId());
		return miv;
	}
	
	
	//글 삭제
	@RequestMapping(value = "/deleteBoard")
	public String deleteBoard(HttpServletRequest request) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		service.bBoardDelete(bno);
		return "/index";
	}
	
	
	//글 검색
	@RequestMapping(value = "/searchBoard")
	public ModelAndView searchBoard(HttpServletRequest request, ModelAndView miv) {
		ArrayList<Bboard> list = new ArrayList<Bboard>();
		String search = (String) request.getParameter("search");
		String option = (String) request.getParameter("searchOption");
		int pageNum = service.bBoardSearchPage(option, search);
		list = service.bBoardSearch(option, search);
		System.out.println("컨트롤러 페이지넘버 = " + list.size());
		miv.addObject("list", list);
		miv.setViewName("/board/Bboard");
		miv.addObject("pageNum",pageNum);
		
		return miv;
	}
	
	
}
