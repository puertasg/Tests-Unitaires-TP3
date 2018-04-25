package dev.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import dev.exception.CalculException;
import dev.service.CalculService;

import static org.mockito.Mockito.*;

import java.util.Scanner;

public class AppLauncherTest {
	
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	private App app;
	private CalculService calculService;
		
	@Before
	public void setUp() throws Exception
	{
		Scanner sc = new Scanner(System.in);
		this.calculService = mock(CalculService.class);
		this.app = new App(sc, calculService);
	}
	
	@Test
	public void testFin()
	{
		systemInMock.provideLines("fin");
		
		app.demarrer();
		
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
	
	/*@Test
	public void testDeuxExpressionsFin()
	{
		String expression1 = "1+2";
		String expression2 = "30+2";
		systemInMock.provideLines(expression1,"fin");
		
		when(calculService.additionner(expression1)).thenReturn(3);
		when(calculService.additionner(expression2)).thenReturn(32);
		
		app.demarrer();
		
		this.app.evaluer(expression1);
		verify(calculService).additionner(expression1);
		
		assertThat(systemOutRule.getLog()).contains("1+2=3");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}*/
	
	@Test
	public void testBoucleFin()
	{
		String expression = "1+2";
		systemInMock.provideLines(expression,"fin");
		when(calculService.additionner(expression)).thenReturn(3);
		
		app.demarrer();
		
		this.app.evaluer(expression);
		verify(calculService).additionner(expression);
		
		assertThat(systemOutRule.getLog()).contains("1+2=3");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
	
	@Test
	public void testExpInvalide()
	{
		String expression = "AAAA";
		systemInMock.provideLines(expression,"fin");
		when(calculService.additionner(expression)).thenThrow(new CalculException("L'expression " + expression + " est invalide."));
		
		app.demarrer();
		
		try
		{
			this.app.evaluer(expression);
		}
		catch(CalculException ex)
		{
			String msg = ex.getMessage();
			System.out.println(msg);
		}
		
		verify(calculService).additionner(expression);
		
		assertThat(systemOutRule.getLog()).contains("L'expression " + expression + " est invalide.");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
}
