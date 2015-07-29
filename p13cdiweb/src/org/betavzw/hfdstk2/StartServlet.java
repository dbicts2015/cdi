package org.betavzw.hfdstk2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartServlet
 */
@WebServlet("/StartServlet")
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject DummyDatabank databank;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> lijst = databank.getCursussen();
		
		if (request.getParameter("filter") != null) {
			// Met streams
			lijst = lijst.stream().filter(s -> s.contains((String) request.getParameter("filter"))).collect(Collectors.toList());
		
			// Zonder streams
//			List<String> temp = new ArrayList<>();
//			for (String string : lijst) {
//				if (string.contains((String) request.getParameter("filter"))) {
//					temp.add(string);
//				}
//			}
//			lijst = temp;
		}
		request.setAttribute("lijst", lijst);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
