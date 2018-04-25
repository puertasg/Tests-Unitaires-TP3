package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	public int additionner(String expression)
	{
		LOG.debug("Evaluation de l'expression " + expression);
		return 0;
	}
}
