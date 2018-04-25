package dev.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import dev.service.CalculService;

import static org.mockito.Mockito.*;

import java.util.Scanner;

public class AppLauncherTest {
	
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void testFin()
	{
		CalculService calculService = mock(CalculService.class);
		systemInMock.provideLines("fin");
		Scanner sc = new Scanner(System.in);
		
		App app = new App(sc, calculService);
		app.demarrer();
		
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
}
