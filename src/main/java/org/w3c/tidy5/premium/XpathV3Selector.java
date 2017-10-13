package org.w3c.tidy5.premium;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.w3c.tidy5.Tidy;

import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;

public class XpathV3Selector {
	static final net.sf.saxon.s9api.Processor PROCESSOR = new net.sf.saxon.s9api.Processor(false);
	static final net.sf.saxon.s9api.XPathCompiler XPATH = PROCESSOR.newXPathCompiler();
	static final DocumentBuilder BUILDER = PROCESSOR.newDocumentBuilder();
	Source source;
	XdmNode xn;

	XpathV3Selector(Source source) throws Exception {
		this.source = source;
		xn = BUILDER.build(source);
	}

	public static XpathV3Selector selector(InputStream in) throws Exception {
		Tidy me = Tidy.me();
		DOMSource domSource = me.parseDOMSource(in);

		return new XpathV3Selector(domSource);
	}

	public static XpathV3Selector selector(Reader in) throws Exception {
		Tidy me = Tidy.me();
		DOMSource domSource = me.parseDOMSource(in);
//		System.err.println(me.asString());
		return new XpathV3Selector(domSource);
	}

	public static XpathV3Selector selector(Source domSource) throws Exception {

		return new XpathV3Selector(domSource);
	}
	/**
	 * An XPathSelector represents a compiled and loaded XPath expression ready
	 * for execution. The XPathSelector holds details of the dynamic evaluation
	 * context for the XPath expression.
	 * 
	 * @param xpaths
	 *            XPath expression 
	 * @return evaluated
	 * @throws Exception
	 */
	public String evaluate(String xpath) throws Exception {
		XPathExecutable compile = XPATH.compile(xpath);
		XPathSelector selector = compile.load();
		selector.setContextItem(xn);
		StringBuilder sbr = new StringBuilder();
		for (XdmItem xdmItem : selector)
			sbr.append(xdmItem.toString());

		return sbr.toString();
	}
	/**
	 * An XPathSelector represents a compiled and loaded XPath expression ready
	 * for execution. The XPathSelector holds details of the dynamic evaluation
	 * context for the XPath expression.
	 * 
	 * @param xpaths
	 *            XPath expression 
	 * @return evaluated
	 * @throws Exception
	 */
	public  List<String> eval4List(String xpath) throws Exception {
		XPathExecutable compile = XPATH.compile(xpath);
		XPathSelector selector = compile.load();
		selector.setContextItem(xn);
		StringList sres = new StringList();
		for (XdmItem xdmItem : selector)
			sres.add(xdmItem.toString());

		return sres;
	}
	/**
	 * An XPathSelector represents a compiled and loaded XPath expression ready
	 * for execution. The XPathSelector holds details of the dynamic evaluation
	 * context for the XPath expression.
	 * 
	 * @param xpaths
	 *          key map to name ,Value XPath expression of Map
	 * @return evaluated
	 * @throws Exception
	 */
	public Map<String, String> evaluate(Map<String, String> xpaths) throws Exception {
		Set<Entry<String, String>> entrySet = xpaths.entrySet();
		HashMap<String, String> res = new HashMap<String, String>();
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
	/**
	 * An XPathSelector represents a compiled and loaded XPath expression ready
	 * for execution. The XPathSelector holds details of the dynamic evaluation
	 * context for the XPath expression.
	 * 
	 * @param xpaths
	 *             key map to name ,Value XPath expression of Map
	 * @return evaluated
	 * @throws Exception
	 */
	public Map<String,  List<String>> eval4List(Map<String, String> xpaths) throws Exception {
		Set<Entry<String, String>> entrySet = xpaths.entrySet();
		HashMap<String,  List<String>> res = new HashMap<String,  List<String>>();
		for (Entry<String, String> entry : entrySet) {
			String xpath = entry.getValue();
			String key = entry.getKey();
			XPathExecutable compile = XPATH.compile(xpath);
			XPathSelector selector = compile.load();
			selector.setContextItem(xn);
			StringList sres = new StringList();
			for (XdmItem xdmItem : selector)
				sres.add(xdmItem.toString());
			res.put(key, sres);
		}

		return res;
	}
	/**
	 * An XPathSelector represents a compiled and loaded XPath expression ready
	 * for execution. The XPathSelector holds details of the dynamic evaluation
	 * context for the XPath expression.
	 * 
	 * @param xpaths
	 *            XPath expression of list
	 * @return evaluation
	 * @throws Exception
	 */
	public List<String> evaluate(List<String> xpaths) throws Exception {

		List<String> res = new ArrayList<String>();
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

	/**
	 * An XPathSelector represents a compiled and loaded XPath expression ready
	 * for execution. The XPathSelector holds details of the dynamic evaluation
	 * context for the XPath expression.
	 * 
	 * @param xpaths
	 *            XPath expression of list
	 * @return evaluation
	 * @throws Exception
	 */
	public List< List<String>> eval4List(List<String> xpaths) throws Exception {

		List< List<String>> res = new ArrayList< List<String>>();
		for (String xpath : xpaths) {

			XPathExecutable compile = XPATH.compile(xpath);
			XPathSelector selector = compile.load();
			selector.setContextItem(xn);
			StringList sres = new StringList();
			for (XdmItem xdmItem : selector)
				sres.add(xdmItem.toString());
			res.add(sres);
		}

		return res;
	}

	/**
	 * Returns a string containing the string representation of each of
	 * {@code list}, using the "" separator between each.
	 * 
	 * @param list
	 * @return
	 */
	public static String asString(List<String> list) {

		StringBuffer b = new StringBuffer();
		for (String s : list) {
			b.append(s);
		}
		return b.toString();
	}

	/**
	 * Returns a string containing the string representation of each of
	 * {@code list}, using the "" separator between each.
	 * 
	 * @param list
	 * @return
	 */
	public static String asStr(List<StringList> list) {

		StringBuffer b = new StringBuffer();
		for (StringList s : list) {
			b.append(s.toString());
		}
		return b.toString();
	}
}
