package fys.fis;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;


public class Parser extends Index implements InstanceCreator<Communication> {

	private static final long serialVersionUID = 1L;
	
	private String function;
	
	Gson gson2 = new GsonBuilder().create();

	@Override
	public Communication createInstance(Type type) {

		switch(function) {
		
		case "login":
			return new Login();

		case "register":
			return new Register();
			
		default:
			return null;
		}
	}
	
	
	
}
