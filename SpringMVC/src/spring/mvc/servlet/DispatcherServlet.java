package spring.mvc.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spring.mvc.util.ControllerMap;

public class DispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ModelAndView mdv = new ModelAndView();
		Enumeration<String> pNames = request.getParameterNames();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		while(pNames.hasMoreElements()){
			String pName = (String) pNames.nextElement();
			paraMap.put(pName,request.getParameter(pName));
		}
		mdv.setParamMap(paraMap);
		String uri = request.getRequestURI();
		ControllerMap cm = ControllerMap.getControllerMap();
		ModelAndView mav = cm.invokeMethod(uri, mdv);
		if(mav == null){
			System.out.println("mav为空");
			request.getRequestDispatcher(uri).forward(request, response);
			return;
		}
		Iterator<String> it = mav.getObjectNames().iterator();
		while(it.hasNext()){
			String name = it.next();
			request.setAttribute(name, mav.getObject(name));
		}
		System.out.println("转发到" + mav.getViewName());
		request.getRequestDispatcher(mav.getViewName()).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
