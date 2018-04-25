package dev.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculServiceTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	private CalculService calc;
	private int somme;
	
	@Before
	public void setUp()
	{
		calc = new CalculService();
		somme = 0;
	}
	
	@Test
	public void testAdditionner() throws Exception
	{
		LOG.info("Etant donné, une instance de la classe CalculService");
		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		somme = calc.additionner("1+3+4");
		
		LOG.info("Alors j'obtiens le résultat 8");
		assertThat(somme).isEqualTo(8);
	}
	
	@Test
	public void testInvalidAdditionner()
	{
		LOG.info("Etant donné, une instance de la classe CalculService");
		LOG.info("Lorsque j'évalue l'addition de l'expression invalide 1+3+a");
		
		assertThatThrownBy(() -> { 
			somme = calc.additionner("1+3+a"); 
		}).isInstanceOf(CalculException.class);
		
		LOG.info("Alors j'obtiens une exception CalculException");
	}
}