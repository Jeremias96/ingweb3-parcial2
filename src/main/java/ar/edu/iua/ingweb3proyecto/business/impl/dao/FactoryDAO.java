package ar.edu.iua.ingweb3proyecto.business.impl.dao;

import org.springframework.context.annotation.Bean;

public class FactoryDAO {

	private static FactoryDAO instance = null;
	
	private FactoryDAO() {
		
	}
	
	@Bean
	public static FactoryDAO getInstance() {
		if (instance == null)
			instance = new FactoryDAO();
		
		return instance;
	}
	
	private static String dataBaseActive = "MYSQL";	//Esto es parte del Factory
	
	@Bean
	public static IGenericDAO getTareasDAO() {
		if(dataBaseActive == "MYSQL") {
			return TareasDAO.getInstance();
		}
		/*
		else{
			return TareasOracleDAO.getInstance();	//Esto es parte del Factory
		}
		 */
		return null;
	}
	
}
