package ejagruti.finsys.config;

import org.ejagruti.finsys.generic.PropertiesFileReader;

import ejagruti.finsys.BusinessLogic.Opreations;
public class Config {
	public static Opreations obj =new Opreations();
	public static PropertiesFileReader pfr= new PropertiesFileReader("parameters");
	public static PropertiesFileReader or= new PropertiesFileReader("or");
	


}
