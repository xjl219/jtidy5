package org.w3c.tidy5.premium;

import java.io.StringWriter;


import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;

public class ExecXpath {

	public static ExecXpath me() {
		return new ExecXpath();
	}
	XPathSelector compile(String xpath) {
		try {
			XPathCompiler xPathCompiler =Executor. PROCESSOR.newXPathCompiler();
			XPathExecutable executable = xPathCompiler.compile(xpath);
			return executable.load();
		} catch (Throwable e) {
			// e.printStackTrace();
			throw new IllegalArgumentException("compile error for " + xpath);
		}
	}
	public String runIt(XdmNode xn, ClearRule rule) throws Exception {
		XPathSelector selector = compile( rule.expression);
		selector.setContextItem(xn);
		StringWriter res = new StringWriter();

		for (XdmItem xdmItem : selector) {

			res.write(xdmItem.toString());
		}
		return res.toString();
	}

	public String runIt(String domString, String rulestring) throws Exception {

		XPathCompiler xPathCompiler = Executor.PROCESSOR.newXPathCompiler();
		XPathExecutable executable = xPathCompiler.compile(rulestring);
		DocumentBuilder builder = Executor.PROCESSOR.newDocumentBuilder();
		XdmNode xdmNode = builder.build(new StringSource(domString));
		XPathSelector selector = executable.load();
		selector.setContextItem(xdmNode);
		StringWriter res = new StringWriter();

		for (XdmItem xdmItem : selector) {

			res.write(xdmItem.toString());
		}
		return res.toString();
	}

}
