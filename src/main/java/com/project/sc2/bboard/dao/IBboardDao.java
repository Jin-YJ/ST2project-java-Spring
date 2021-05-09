package com.project.sc2.bboard.dao;

import java.util.ArrayList;

import com.project.sc2.bboard.Bboard;
import com.project.sc2.member.Member;

public interface IBboardDao {
	int bBoardInsert(Bboard board, Member member);
	Bboard bBoardContentView(int bno);
	ArrayList<Bboard> bBoardViewAll(int page);
	ArrayList<Bboard> bBoardSearchTitle(String search);
	ArrayList<Bboard> bBoardSearchContent(String search);
	ArrayList<Bboard> bBoardSearchWriter( String search);
	void bBoardModify(Bboard board);
	void bBoardDelete(int bno);
	int bBoardPage();
	void bBoardHit(int bno);
}
