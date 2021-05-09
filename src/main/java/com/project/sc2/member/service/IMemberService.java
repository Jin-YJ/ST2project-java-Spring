package com.project.sc2.member.service;

import com.project.sc2.member.Member;

public interface IMemberService {

	int memberRegister(Member member);
	Member memberSearch(Member member);
	Member memberModify(Member member);
	void memberRemove(String mId);

}
