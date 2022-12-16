package tae.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tae.member.dao.MemberDAO;
import tae.member.dto.MemberDTO;
import tae.wish.control.Controller;
import tae.wish.hander.HandlerAdapter;

public class MemberSelectDetailController implements Controller {
	private static final Log log = LogFactory.getLog(MemberSelectDetailController.class);

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String umail = request.getParameter("umail");
		log.info(umail);
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO = memberDAO.memberSelect(umail);
		log.info(memberDTO);
		request.setAttribute("memberDTO", memberDTO);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		log.info("특정 회원 조회");
		handlerAdapter.setPath("/WEB-INF/view/member/member_select_detail_view.jsp");
		return handlerAdapter;
	}

}
