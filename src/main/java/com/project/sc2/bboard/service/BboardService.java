package com.project.sc2.bboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sc2.bboard.Bboard;
import com.project.sc2.bboard.dao.BboardDao;
import com.project.sc2.member.Member;

@Service
public class BboardService implements IBboardService{

	@Autowired
	BboardDao dao;

	@Override
	public int boardWriter(Bboard board, Member member) {
		int result = dao.bBoardInsert(board, member);
		
		// �ּ� ó���� ���� Ȯ�α���
//		if (result == 0) {
//			System.out.println("boardWrite Fail!!");
//		} else {
//			System.out.println("boardWrite Success!!");
//		}
//		
		return result;
		
	}

	@Override
	public ArrayList<Bboard> bBoardSearch(String option, String search) {
		ArrayList<Bboard> list = new ArrayList<Bboard>();
		if(option.equals("title")) {list = dao.bBoardSearchTitle(search);}
		else if(option.equals("content")) {list = dao.bBoardSearchContent(search);}
		else {list = dao.bBoardSearchWriter(search);}
		for(int i = 7 ; i < list.size(); i++) {
			list.remove(i);
		}
		

		return list;
	}
	@Override
	public int bBoardSearchPage(String option, String search) {
		int result = 0;
		ArrayList<Bboard> list = bBoardSearch(option, search);
		result = (list.size()/10)+1;
		return result;
	}

	@Override
	public void bBoardUpdate(Bboard board) {
//		System.out.println("����");
//		System.out.println(board.getBno());
//		System.out.println(board.getTitle());
//		System.out.println(board.getContent());
		dao.bBoardModify(board);
		
	}

	@Override
	public void bBoardDelete(int bno) {
		dao.bBoardDelete(bno);
		
	}

	@Override
	public ArrayList<Bboard> bBoardViewAll(int page) {
		ArrayList<Bboard> list = new ArrayList<Bboard>();
		
		System.out.println("service page : " +page);
		list = dao.bBoardViewAll(page);
		
		// �ּ� ó���� ���� Ȯ�α���
//		if (list == null) {
//			System.out.println("boardview Fail!!");
//		} else {
//			System.out.println("boardview Success!!");
//		}
		return list;
	}

	@Override
	public int bBoardPageNumber() {
		int result = 0;
		result = dao.bBoardPage();
		//������ �� ��ȸ ���� �ּ� ����
		//System.out.println("�������� = " + result);
		return result;
	}

	@Override
	public Bboard bBoardViewContent(int bno) {

		Bboard board = null;
		
		board = dao.bBoardContentView(bno);
		//for�� ������ ��ġ�ϴ� �� 

// �ּ� ó���� ���� Ȯ�α���
//				if (board == null) {
//					System.out.println("boardContentview Fail!!");
//				} else {
//					System.out.println("boardContentview Success!!");
//				}
		
		return board;
	}

	@Override
	public void bBoardHit(int bno) {
		
		dao.bBoardHit(bno);
		
	}
	
	
}
