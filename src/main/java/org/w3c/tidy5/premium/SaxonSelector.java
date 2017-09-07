package org.w3c.tidy5.premium;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.transform.dom.DOMSource;

import org.w3c.tidy5.Tidy;

import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;

public class SaxonSelector {
	static final net.sf.saxon.s9api.Processor PROCESSOR = new net.sf.saxon.s9api.Processor(false);
	static final net.sf.saxon.s9api.XPathCompiler XPATH = PROCESSOR.newXPathCompiler();
	static final DocumentBuilder BUILDER = PROCESSOR.newDocumentBuilder();
	DOMSource source;
	XdmNode xn;

	SaxonSelector(DOMSource source) throws Exception {
		this.source = source;
		xn = BUILDER.build(source);
	}
	public static SaxonSelector selector(InputStream in) throws Exception {
		Tidy me =Tidy. me();
		DOMSource domSource = me.parseDOMSource(in);

		return new SaxonSelector(domSource);
	}

	public static SaxonSelector selector(Reader in) throws Exception {
		Tidy me = Tidy.me();
		DOMSource domSource = me.parseDOMSource(in);

		return new SaxonSelector(domSource);
	}
	public String evaluate(String xpath) throws Exception {
		XPathExecutable compile = XPATH.compile(xpath);
		XPathSelector selector = compile.load();
		selector.setContextItem(xn);
		StringBuilder sbr = new StringBuilder();
		for (XdmItem xdmItem : selector)
			sbr.append(xdmItem.toString());

		return sbr.toString();
	}
	public Map<String,String> evaluate(Map<String,String> xpaths) throws Exception {
		Set<Entry<String, String>> entrySet = xpaths.entrySet();
		HashMap<String,String> res=new HashMap<String,String>();
		for (Entry<String, String> entry : entrySet) {
			String xpath = entry.getValue();
			String key = entry.getKey();
			XPathExecutable compile = XPATH.compile(xpath);
			XPathSelector selector = compile.load();
			selector.setContextItem(xn);
			StringBuilder sbr = new StringBuilder();
			for (XdmItem xdmItem : selector)
				sbr.append(xdmItem.toString());
			res.put(key, sbr.toString());
		}
		
		return res;
	}
	public List<String> evaluate(List<String> xpaths) throws Exception {
		
		 List<String> res=new ArrayList<String>();
		for (String xpath : xpaths) {
		
			XPathExecutable compile = XPATH.compile(xpath);
			XPathSelector selector = compile.load();
			selector.setContextItem(xn);
			StringBuilder sbr = new StringBuilder();
			for (XdmItem xdmItem : selector)
				sbr.append(xdmItem.toString());
			res.add(sbr.toString());
		}
		
		return res;
	}
}
