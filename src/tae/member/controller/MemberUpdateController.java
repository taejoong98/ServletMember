package tae.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tae.member.dao.MemberDAO;
import tae.member.dto.MemberDTO;
import tae.wish.control.Controller;
import tae.wish.hander.HandlerAdapter;

public class MemberUpdateController implements Controller {
 private static final Log log = LogFactory.getLog(MemberUpdateController.class);
 
	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String umail = request.getParameter("umail");
		log.info(umail);
		String upw = request.getParameter("upw");
		log.info(upw);
		String uname = request.getParameter("uname");
		log.info(uname);
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUmail(umail);
		memberDTO.setUpw(upw);
		memberDTO.setUname(uname);
		memberDTO = memberDAO.memberUpdate(memberDTO);
		log.info(memberDTO);
		request.setAttribute("memberDTO", memberDTO);
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/member/member_update_view.jsp");
		return handlerAdapter;
	}

}
