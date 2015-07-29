package org.betavzw.hfdstk3.web;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.betavzw.hfdstk3.data.Cursus;
import org.betavzw.hfdstk3.data.CursusDAO;

/**
 * Servlet implementation class CursusServlet
 */
@WebServlet("/CursusServlet")
public class CursusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/__default")
    private DataSource ds;
	private CursusDAO dao;
	@Inject
	private CursusInputBean cursusInput;
	@Inject
	private Validator validator;
	@Override
	public void init() throws ServletException {
		super.init();
		dao = new CursusDAO(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (cursusInput.isDelete()){
			String code = cursusInput.getDelete();
			try{
				dao.DeleteCursus(code);
			}catch(IllegalArgumentException e){
				request.setAttribute("codenietgevondenfout", "code bestaat niet.");
			}
		}
		List<Cursus> cursussen = dao.getCursussen();
		request.setAttribute("cursussen", cursussen);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<ConstraintViolation<CursusInputBean>> codeViolations = validator.validateProperty(cursusInput, "code"); 
		Set<ConstraintViolation<CursusInputBean>> naamViolations = validator.validateProperty(cursusInput, "naam");
		Set<ConstraintViolation<CursusInputBean>> duurViolations = validator.validateProperty(cursusInput, "duur");
		if (codeViolations.size() == 0 && naamViolations.size() == 0 && duurViolations.size() == 0){
		    String code = cursusInput.getCode();
		    String naam = cursusInput.getNaam();
		    int duur = Integer.valueOf(cursusInput.getDuur());
		    try {
			    dao.InsertCursus(code, naam, duur);
			    cursusInput.verbergParameterWaarden();
		    } catch (SQLIntegrityConstraintViolationException e) {
			    request.setAttribute("codefout", "code bestaat al");
		    }
		}else {
			for (ConstraintViolation<CursusInputBean> codeViolation : codeViolations) {
				request.setAttribute("codefout", codeViolation.getMessage());
			}
			for (ConstraintViolation<CursusInputBean> naamViolation : naamViolations) {
				request.setAttribute("naamfout", naamViolation.getMessage());
			}
			for (ConstraintViolation<CursusInputBean> duurViolation : duurViolations) {
				request.setAttribute("duurfout", duurViolation.getMessage());
			}
		}
		List<Cursus> cursussen = dao.getCursussen();
		request.setAttribute("cursussen", cursussen);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
