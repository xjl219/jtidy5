package org.w3c.tidy5.premium;
import java.util.ArrayList;
import java.util.List;

public class StringList extends ArrayList<String> implements List<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5125890519503782259L;
	
	public String toString(){
		
	    StringBuffer b = new StringBuffer();
        for (String s:this) {
            b.append(s);
        }
        return b.toString();
	}
}
