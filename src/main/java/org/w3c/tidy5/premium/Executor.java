package org.w3c.tidy5.premium;

import org.w3c.tidy5.Tidy;

import net.sf.saxon.om.TreeModel;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XdmNode;

public class Executor {
	static Processor PROCESSOR = XQPCleaner.PROCESSOR;
	Executor(){
		
	}
	public static Executor me() {
		return new Executor();
	}
	public  String run(XdmNode xn,ClearRule rule) {
		try {
			
		if(rule.rule == RULE.DELSTR){
			return run(xn.toString(), rule);
		}else if(rule.isUpdate()){
			return ExecXquery.me().runIt(xn,rule);
		}else{
			return ExecXpath.me().runIt(xn,rule);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public  String run(String source,ClearRule rule) {
		try {
			DocumentBuilder builder=null;
			XdmNode xdmNode =null;
			switch (rule.rule) {
			case DELSTR:
				return ExecDelStr.me().runIt(source,rule.getXpath());
			case MINUS:
			case XQ:
				 builder = PROCESSOR.newDocumentBuilder();
				
				builder.setTreeModel(TreeModel.LINKED_TREE);
				 xdmNode = builder.build(Tidy.toDOMSource(source));
				return run(xdmNode,rule);
			case EXTRACT:
				 builder = PROCESSOR.newDocumentBuilder();
				 xdmNode = builder.build(Tidy.toDOMSource(source));
				return run(xdmNode,rule);
			default:
				break;
			}
			
		} catch (Exception e) {
			System.err.println(source);
			e.printStackTrace();
		}
		return "";
	}
	
	
	

}
