package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	public int additionner(String expression)
	{
		LOG.debug("Evaluation de l'expression " + expression);
		
		String[] nombres = expression.split("[+]");
		int somme = 0;
		
		for (int i = 0; i < nombres.length; i++) 
		{
			somme+=Integer.parseInt(nombres[i]);
		}
		
		return somme;
	}
}