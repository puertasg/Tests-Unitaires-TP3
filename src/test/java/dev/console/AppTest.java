package dev.console;

import dev.exception.CalculException;
import dev.service.CalculService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.*;

public class AppTest {

	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	private App app;
	private CalculService calculService;

	@Before
	public void setUp() throws Exception {
		Scanner sc = new Scanner(System.in);
		this.calculService = mock(CalculService.class);
		this.app = new App(sc, calculService);
	}

	@Test
	public void testEvaluer() throws Exception {
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+35");
		String expression = "1+34";
		when(calculService.additionner(expression)).thenReturn(35);

		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);

		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);

		LOG.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("1+34=35");
	}

	@Test
	public void testInvalidExpression() {
		String expression = "1+a";
		when(calculService.additionner(expression))
				.thenThrow(new CalculException("L'expression " + expression + " est invalide."));

		try {
			this.app.evaluer(expression);
		} catch (CalculException ex) {
			String msg = ex.getMessage();
			LOG.debug(msg);
		}
		verify(calculService).additionner(expression);

		assertThat(systemOutRule.getLog().contains("L'expression " + expression + " est invalide."));
	}

	@Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();

		String logConsole = systemOutRule.getLog();

		assertThat(logConsole).contains("**** Application Calculatrice ****");
	}

	@Test
	public void testFin() {
		systemInMock.provideLines("fin");

		app.demarrer();

		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}

	@Test
	public void testBoucleFin() {
		String expression = "1+2";
		systemInMock.provideLines(expression, "fin");
		when(calculService.additionner(expression)).thenReturn(3);

		app.demarrer();

		verify(calculService).additionner(expression);

		assertThat(systemOutRule.getLog()).contains("1+2=3");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}

	@Test
	public void testExpInvalide() {
		String expression = "AAAA";
		systemInMock.provideLines(expression, "fin");
		when(calculService.additionner(expression))
				.thenThrow(new CalculException("L'expression " + expression + " est invalide."));

		try {
			app.demarrer();
		} catch (CalculException ex) {
			String msg = ex.getMessage();
			LOG.debug(msg);
		}

		verify(calculService).additionner(expression);

		assertThat(systemOutRule.getLog()).contains("L'expression " + expression + " est invalide.");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}

	@Test
	public void testDeuxExp() {
		String expression1 = "1+2";
		String expression2 = "30+2";
		systemInMock.provideLines(expression1, expression2, "fin");

		when(calculService.additionner(expression1)).thenReturn(3);
		when(calculService.additionner(expression2)).thenReturn(32);

		app.demarrer();

		verify(calculService, times(3)).additionner(anyString());

		assertThat(systemOutRule.getLog()).contains("1+2=3");
		assertThat(systemOutRule.getLog()).contains("30+2=32");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
}
