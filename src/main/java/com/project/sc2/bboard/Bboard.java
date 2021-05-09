package com.project.sc2.bboard;

import java.util.Date;

public class Bboard {
	private int bno; //글 번호
	private String title; //글제목
	private String content; //글 내용
	private String writer; //작성자
	private String writerCode; //작성자 코드
	private Date date; //작성일
	private int hit; // 조회 수
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriterCode() {
		return writerCode;
	}
	public void setWriterCode(String writerCode) {
		this.writerCode = writerCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
}
