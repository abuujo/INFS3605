package util;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;


public class GetAbsolutePath {
	public String getAbsolutePath(String path){
		
		URL url = this.getClass().getClassLoader().getResource(path);
		String returnPath = url.toString();
		return returnPath;
				
		
		
	}
}
