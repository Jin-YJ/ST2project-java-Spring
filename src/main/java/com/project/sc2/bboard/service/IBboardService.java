package com.project.sc2.bboard.service;

import java.util.ArrayList;

import com.project.sc2.bboard.Bboard;
import com.project.sc2.member.Member;

public interface IBboardService{

	int boardWriter(Bboard board , Member member);
	ArrayList<Bboard> bBoardSearch(String option, String search);
	Bboard bBoardViewContent(int bno);
	void bBoardUpdate(Bboard board);
	void bBoardDelete(int bno);
	ArrayList<Bboard> bBoardViewAll(int page);
	int bBoardPageNumber();
	void bBoardHit(int bno);
	int bBoardSearchPage(String option, String search);
}
