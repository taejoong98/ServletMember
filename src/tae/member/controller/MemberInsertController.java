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

public class MemberInsertController implements Controller {
	 private static final Log log = LogFactory.getLog(MemberInsertController.class);

	@Override
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String umail = request.getParameter("umail");
		log.info(umail);
		String upw = request.getParameter("upw");
		log.info(upw);
		String uname = request.getParameter("uname");
		log.info(uname);
		String joinday = request.getParameter("joinday");
		log.info(joinday);
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		arrayList = memberDAO.memberSelectAll();
		log.info(arrayList);
		request.setAttribute("arrayList", arrayList);
		
		memberDTO.setUmail(umail);
		memberDTO.setUpw(upw);
		memberDTO.setUname(uname);
		memberDTO.setJoinday(joinday);
		log.info(memberDTO);
		memberDTO = memberDAO.memberInsert(memberDTO);
		request.setAttribute("umail", memberDTO.getUmail());
		request.setAttribute("uname", memberDTO.getUname());
		HandlerAdapter handlerAdapter = new HandlerAdapter();
		handlerAdapter.setPath("/WEB-INF/view/member/member_insert_view.jsp");
		return handlerAdapter;
	}

}
