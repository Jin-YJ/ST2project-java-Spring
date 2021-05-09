package com.project.sc2.member.dao;

import com.project.sc2.member.Member;

public interface IMemberDao {
	int memberInsert(Member member);
	Member memberSelect(Member member);
	int memberUpdate(Member member);
	void memberRemove(String mId);

}
