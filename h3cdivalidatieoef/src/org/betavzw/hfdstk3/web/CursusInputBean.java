package org.betavzw.hfdstk3.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class CursusInputBean {
	private boolean parameterWaardenVerbergen;
	@Inject
	private HttpServletRequest request;
	
	/**
	 * Geeft de waarde van parameter code terug of een lege String 
	 * (waneer verbergParameterWaarden() opgeroepen is
	 * @return Een String met de waarde van de Code parameter
	 */
	@Size(min=1, max=4, message="Code moet ingevuld zijn en maximaal 4 karakters.")
	public String getCode(){
		if (parameterWaardenVerbergen) return "";
		return request.getParameter("code");
	}
	
	/**
	 * Geeft de waarde van parameter Naam terug of een lege String 
	 * (waneer verbergParameterWaarden() opgeroepen is
	 * @return Een String met de waarde van de Naam parameter
	 */
	@Size(min=1, max=50, message="Naam moet ingevuld zijn en maximaal 50 karakters.")
	public String getNaam(){
		if (parameterWaardenVerbergen) return "";
		return request.getParameter("naam");
	}
	
	//@Size(min, message="De duur moet ingevuld zijn en tussen 1 en 4 liggen")
	@Pattern(regexp="[1234]", message="De duur moet ingevuld zijn en tussen 1 en 4 liggen")
	public String getDuur(){
		if (parameterWaardenVerbergen) return "";
		return request.getParameter("duur");
	}
	
	/**
	 * Zorgt ervoor dat getCode en getNaam een lege string teruggeven
	 */
	public void verbergParameterWaarden() {
		parameterWaardenVerbergen = true;
	}
	
	/**
	 * Controleert of de parameter Delete is ingevuld
	 * @return true wanneer de parameter Delete is ingevuld.
	 */
	public boolean isDelete(){
		return request.getParameter("delete") != null;
	}
	
	/**
	 * Geeft de waarde van de parameter Delete terug
	 * @return Een String met de waarde van de Delete parameter
	 */
	public String getDelete(){
		return request.getParameter("delete");
	}
}
