package com.codevicious.NidoConverter;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class EcivisCsvReader {
private final String relativePath;
	
	public EcivisCsvReader(String relativePath) {
		this.relativePath = relativePath;
	}
		
	public Reader getReader() {
		Reader r = null;
		
		try {
			 r = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(relativePath), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		return r;
	}

}
