package dev.console;

import dev.service.CalculService;
import java.util.Scanner;
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
	}
	
	protected void evaluer(String expression)
	{
		
	}
}
