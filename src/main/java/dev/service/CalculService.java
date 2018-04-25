package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	public int additionner(String expression)
	{
		LOG.debug("Evaluation de l'expression " + expression);
		
		String[] nombres = expression.split("[+]");
		int somme = 0;
		
		for (int i = 0; i < nombres.length; i++) 
		{
			try
			{
				somme+=Integer.parseInt(nombres[i]);
			}
			catch(NumberFormatException ex)
			{
				throw new CalculException(ex.getMessage());
			}
		}
		
		return somme;
	}
}
