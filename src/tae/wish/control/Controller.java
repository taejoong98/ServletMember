package tae.wish.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tae.wish.hander.HandlerAdapter;

public interface Controller {
	
	public HandlerAdapter execute(HttpServletRequest request, HttpServletResponse response);
}
