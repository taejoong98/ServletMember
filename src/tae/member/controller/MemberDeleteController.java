package tae.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tae.member.dao.MemberDAO;
import tae.member.dto.MemberDTO;
import tae.wish.control.Controller;
import tae.wish.hander.HandlerAdapter;

public class MemberDeleteController implements Controller {
	 private static final Log log = LogFactory.getLog(MemberDeleteController.class);

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String umail = request.getParameter("umail");
		log.info(umail);
		String upw = request.getParameter("upw");
		log.info(upw);
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();


		request.setAttribute("memberDTO", memberDTO);
		memberDTO = memberDAO.memberDelete(umail, upw);
		log.info(memberDTO);
		
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/member/member_delete_view.jsp");
		return handlerAdapter;
	}
}