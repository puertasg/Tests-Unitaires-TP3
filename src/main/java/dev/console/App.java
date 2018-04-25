package dev.console;

import dev.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.service.AppService;

/**
 * 
 * @author Gauthier Puertas
 * Projet ayant pour but de mettre en action la librairie slf4j et ses différents types de loggers
 *
 */
public class App {
	
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		
		LOG.info("Bonjour Slf4J ! ");
		LOG.info("Implémentation Logback");
		new AppService().exectuer("valeur 1");
	}

}
