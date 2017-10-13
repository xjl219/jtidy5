package org.w3c.tidy5.premium;

import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.w3c.tidy5.Tidy;

import net.sf.saxon.dom.DOMSender;
import net.sf.saxon.om.TreeModel;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.serialize.ReconfigurableSerializer;

public class XQPCleaner {
	
	XQPCleaner(String source){
		this.source=source;
	}
	static{
		 DOMSender.ignoreNoBindingNamespaces();
	}
	public static void ignoreNoBindingNamespaces(boolean ignore){
		 DOMSender.ignoreNoBindingNamespaces(ignore);
	}
	/**
	 * 
	 * @param source
	 * @return
	 */
	public static XQPCleaner cleaner(String source){
		return new XQPCleaner( source);
		
	}
	static Processor PROCESSOR = new Processor(true);
	String source;
	public static String clearHTML(String html,boolean execTity,String...ruleXpath) throws InvalidFormatException, Exception{
		Rules rules = ClearRule.compiles(ruleXpath);
		DocumentBuilder builder = PROCESSOR.newDocumentBuilder();
		if(rules.isUpdate())
			builder.setTreeModel(TreeModel.LINKED_TREE);
		XdmNode xdmNode =null;
		if(execTity) {
			Source source = Tidy.me().parseDOMSource(new StringReader(html));
			xdmNode = builder.build(source);
		}else{
			Source source=StringSource.newMe(html);
			xdmNode = builder.build(source);
		}
		String res=html;
		for (ClearRule rule : rules) {
			res=Executor.me().run(xdmNode, rule);
//			xdmNode = builder.build(new StringSource(res));
			xdmNode = builder.build(Tidy.toDOMSource(new StringReader(res)));
		}
		
        return res;
	}
	public static String clearDomSource(Source source,String...ruleXpath) throws InvalidFormatException, Exception{
		Rules rules = ClearRule.compiles(ruleXpath);
		DocumentBuilder builder = PROCESSOR.newDocumentBuilder();
		if(rules.isUpdate())
			builder.setTreeModel(TreeModel.LINKED_TREE);
		XdmNode xdmNode =null;
			xdmNode = builder.build(source);
		String res="";
		
		for (ClearRule rule : rules) {
			res=Executor.me().run(xdmNode, rule);
			xdmNode = builder.build(Tidy.toDOMSource(new StringReader(res)));
//			xdmNode = builder.build(new StringSource(res));
		}
		
		return res;
	}
	public  String clearDomSource(String...ruleXpath) throws InvalidFormatException, Exception{
		Rules rules = ClearRule.compiles(ruleXpath);

		String res=source;
		
		for (ClearRule rule : rules) {
			res=Executor.me().run(res, rule);
		}
		
		return res;
	}
	
	public static String clear(String dom,String...ruleXpath) throws InvalidFormatException, Exception{
		
		
		return clearHTML(dom,false,ruleXpath);
	}
	
	
	
	
	 public static void main(String[] args) throws InvalidFormatException {
		 try {
			 FileReader in = new FileReader("/Users/xujl-mac/IdeaProjects/tt/src/main/resources/simple.xml");
			System.out.println(clearDomSource(Tidy.toDOMSource(in),  "$//title"));
//			System.out.println(clearDomSource(new StreamSource(in),  "$//title"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }


}
