package com.project.sc2.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sc2.member.Member;
import com.project.sc2.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	MemberDao dao;
	
	@Override
	public int memberRegister(Member member) {
//		//이미 있는지 확인
//		Member members = dao.memberSelect(member);
//		if(members != null) {
//			return 0;
//		}
		int result = dao.memberInsert(member);
	
		// 주석 처리할 예정 확인구문
		if (result == 0) {
			System.out.println("Join Fail!!");
		} else {
			System.out.println("Join Success!!");
		}
		
		return result;
	}

	@Override
	public Member memberSearch(Member member) {
		
		Member mem = dao.memberSelect(member);
		
		// 주석 처리할 예정 확인구문
		if (mem == null) {
			System.out.println("Login Fail!!");
		} else {
			System.out.println("Login Success!!");
		}
		
		return mem;
	}

	@Override
	public Member memberModify(Member member) {
		
		int result = dao.memberUpdate(member);
		
		// 주석 처리할 예정 확인구문
		if(result == 0 ) {
			System.out.println("Modify Fail!!");
			return null;
		} else {
			System.out.println("Modify Success!!");
		}
		
		return member;
	
	}

	@Override
	public void memberRemove(String mId) {
		System.out.println("리무브 mid");
		dao.memberRemove(mId);

	}
	
}
