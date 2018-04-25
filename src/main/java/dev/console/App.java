package dev.console;

import dev.service.CalculService;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author Gauthier Puertas
 * Projet ayant pour but de mettre en action la librairie slf4j et ses diff�rents types de loggers
 *
 */
public class App {
	
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	private Scanner scanner;
	private CalculService calculatrice;
	
	public App(Scanner sc, CalculService calc)
	{
		this.scanner = sc;
		this.calculatrice = calc;
	}
	
	protected void afficherTitre()
	{
		LOG.info("**** Application Calculatrice ****");
	}
	
	public void demarrer()
	{
		afficherTitre();
		
		String input = "";
		
		while(!StringUtils.equals(input, "fin"))
		{
			LOG.info("Veuillez saisir une expression :");
			input = this.scanner.next();
			
			this.evaluer(input);
		}
		
		LOG.info("Aurevoir :-(");
	}
	
	protected void evaluer(String expression)
	{
		int resultat = this.calculatrice.additionner(expression);
		if(!StringUtils.equals(expression, "fin"))
		{
			LOG.info(expression + "=" + resultat);
		}
		
	}
}