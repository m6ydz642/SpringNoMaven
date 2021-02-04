package project.rasp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.rasp.model.Comment;

public interface Controller {
	// usrid check 메소드
	// public String checkUserid();  // 매개변수때문에 잠시 보류 
	/*****************************************************************/
	// 보드 CRUD
	public String getBoardlist(Model model,HttpSession session) throws Exception; // 게시글 리스트 조회
	public String witeBoard(HttpServletRequest request, 
			HttpSession session, HttpServletResponse response) 
					throws IOException; // 보드게시글 작성
	public String deleteBoard(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Model model) throws IOException; // 게시글삭제
	public ModelAndView getmodifyBoard(HttpServletRequest request); // 게시글 수정을 위해 게시글 읽기
	public String modifywriteBoard(HttpServletRequest request, Model model, 
			HttpSession session, HttpServletResponse response) throws IOException; // 게시글 수정페이지 로드
	public String completemodifyBoard(HttpServletRequest request, HttpSession session); // 게시글 수정 완료
	public String getBoardContent_getCommentContent(HttpServletRequest request, 
			Model model) throws Exception; // 보드 게시글 내용 조회
											// 내용조회시 댓글 같이 읽음
	public String completewriteBoard(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception; // 게시글 작성완료
	/****************************************************************************/
	// 댓글 CRUD
	public String writeComment(Model model, HttpServletRequest request) 
			throws UnsupportedEncodingException; // 댓글작성완료
	public String getCommentEdit(HttpServletRequest request, HttpSession session, 
			@RequestParam int comment_number, Comment comment); //  댓글 수정을 위해		
																//	글을 가져옴
	public String completeEditComment(HttpServletRequest request, HttpSession session, 
			@RequestParam int comment_number, @RequestParam String testarea); // 댓글수정완료
	public String deleteComment(HttpServletRequest request, HttpSession session, 
			@RequestParam int comment_number); // 댓글삭제
}
