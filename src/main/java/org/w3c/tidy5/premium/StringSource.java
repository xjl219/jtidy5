package org.w3c.tidy5.premium;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

public class StringSource extends StreamSource implements Source {
	StringSource(String domString){
		
		super(new StringReader(domString));
	}
	StringSource(Reader reader){
		
		super(reader);
	}
	
	public static StringSource newMe(String domString){
		return new StringSource(domString);
	}
}
