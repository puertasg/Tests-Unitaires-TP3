package dev.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculServiceTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	private CalculService calc;
	
	@Before
	public void setUp()
	{
		calc = new CalculService();
	}
	
	@Test
	public void testAdditionner() throws Exception
	{
		LOG.debug("Etant donné, une instance de la classe CalculService");
		LOG.debug("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = calc.additionner("1+3+4");
		
		LOG.debug("Alors j'obtiens le résultat 8");
		assertEquals(8, somme);
	}
	
	@Test(expected=CalculException.class)
	public void testInvalidAdditionner()
	{
		LOG.debug("Etant donné, une instance de la classe CalculService");
		LOG.debug("Lorsque j'évalue l'addition de l'expression invalide 1+3+a");
		int somme = calc.additionner("1+3+a");
		
		LOG.debug("Alors j'obtiens une exception CalculException");
	}
}