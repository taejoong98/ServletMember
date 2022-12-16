package tae.wish.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tae.member.controller.MemberDeleteController;
import tae.member.controller.MemberInsertController;
import tae.member.controller.MemberSelectController;
import tae.member.controller.MemberSelectDetailController;
import tae.member.controller.MemberUpdateController;
import tae.wish.control.Controller;
import tae.wish.hander.HandlerAdapter;

public class WishDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private static final Log log = LogFactory.getLog(WishDispatcherServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		log.info(requestURI);
		String contextPath = request.getContextPath();
		log.info(contextPath);
		String pathURI = requestURI.substring(contextPath.length());
		log.info(pathURI);
		HandlerAdapter handlerAdapter = null;
		Controller controller = null;
		
		if (pathURI.equals("/MemberSelect.me")) {
			controller = new MemberSelectController();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 전체 조회 실패 - " + e);
			}
		}
		
		else if (pathURI.equals("/MemberSelectDetail.me")) {
			controller = new MemberSelectDetailController();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("특정 회원 조회 실패 - " + e);
			}
		}
		
		else if (pathURI.equals("/MemberInsert.me")) {
			controller = new MemberInsertController();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 가입 실패 - " + e);
			}
		}
		else if(pathURI.equals("/MemberInsertView.me")) {
			handlerAdapter = new HandlerAdapter();
		    handlerAdapter.setPath("/WEB-INF/view/member/member_insert.jsp");
		}
		
		else if (pathURI.equals("/MemberUpdate.me")) {
			controller = new MemberUpdateController();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 수정 실패 - " + e);
			}
		}
		else if (pathURI.equals("/MemberUpdateView.me")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/member/member_update.jsp");
		}
		
		else if (pathURI.equals("/MemberDelete.me")) {
			controller = new MemberDeleteController();
			try {
				handlerAdapter = controller.execute(request, response);
			} catch (Exception e) {
				log.info("회원 삭제 실패 - " + e);
			}
		}
		else if (pathURI.equals("/MemberDeleteView.me")) {
			handlerAdapter = new HandlerAdapter();
			handlerAdapter.setPath("/WEB-INF/view/member/member_delete.jsp");
		}
		
		if (handlerAdapter != null) {
			if (handlerAdapter.isRedirect()) {
				response.sendRedirect(handlerAdapter.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(handlerAdapter.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}