package tae.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tae.member.dao.MemberDAO;
import tae.member.dto.MemberDTO;
import tae.wish.control.Controller;
import tae.wish.hander.HandlerAdapter;

public class MemberSelectController implements Controller {
 private static final Log log = LogFactory.getLog(MemberSelectController.class);
 
	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO memberDAO = new MemberDAO();
		
//		MemberDTO memberDTO = new MemberDTO();
//		log.info(memberDTO);
		
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		arrayList = memberDAO.memberSelectAll();
		log.info(arrayList);
		request.setAttribute("arrayList", arrayList);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("WEB-INF/view/member/member_select_view.jsp");
		return handlerAdapter;
	}

}
