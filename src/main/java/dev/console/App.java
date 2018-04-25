package dev.console;

import dev.service.CalculService;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @author Gauthier Puertas
 * Projet ayant pour but de mettre en action la librairie slf4j et ses diff�rents types de loggers
 *
 */
public class App {
	
	private Scanner scanner;
	private CalculService calculatrice;
	
	public App(Scanner sc, CalculService calc)
	{
		this.scanner = sc;
		this.calculatrice = calc;
	}
	
	protected void afficherTitre()
	{
		System.out.println("**** Application Calculatrice ****");
	}
	
	public void demarrer()
	{
		afficherTitre();
		
		String input = this.scanner.next();
		
		while(!StringUtils.equals(input, "fin"))
		{
			System.out.println("Veuillez saisir une expression :");
			input = this.scanner.next();
		}
		
		System.out.println("Aurevoir :-(");
	}
	
	protected void evaluer(String expression)
	{
		int resultat = this.calculatrice.additionner(expression);
		System.out.println(expression + "=" + resultat);
	}
}