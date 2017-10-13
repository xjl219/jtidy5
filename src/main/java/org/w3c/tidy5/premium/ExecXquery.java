package org.w3c.tidy5.premium;


import net.sf.saxon.om.TreeModel;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmNode;

public class ExecXquery {
	public static ExecXquery me() {
		return new ExecXquery();
	}
	XQueryEvaluator compileOmit(String xpath) {
			try {
				
				XQueryCompiler xQueryCompiler =Executor. PROCESSOR.newXQueryCompiler();
				xQueryCompiler.setUpdatingEnabled(true);
				XQueryExecutable queryExecutable = xQueryCompiler.compile("delete nodes " + xpath);
				return queryExecutable.load();
			} catch (Throwable e) {
				// e.printStackTrace();
				throw new IllegalArgumentException("compile error for " + xpath);
			}
		}
	
	
	XQueryEvaluator compileXQuery(String xquery) {
		try {
			XQueryCompiler xQueryCompiler = Executor.PROCESSOR.newXQueryCompiler();
			xQueryCompiler.setUpdatingEnabled(true);
			XQueryExecutable queryExecutable = xQueryCompiler.compile(xquery);
			return queryExecutable.load();
		} catch (Throwable e) {
			// e.printStackTrace();
			throw new IllegalArgumentException("compile error for " + xquery);
		}
	}
	public String runIt(XdmNode xdmNode, ClearRule rule) throws Exception {
		XQueryEvaluator evaluator = null;
		if(rule.getRule() ==RULE.MINUS){
			evaluator=compileOmit(rule.expression);
		}else if(rule.getRule() ==RULE.XQ){
			evaluator=compileXQuery(rule.expression);
		}

		evaluator.setContextItem(xdmNode);
		evaluator.run();
		return xdmNode.toString();
	}

	public String runIt(String domString, String rulestring) throws Exception {
		DocumentBuilder builder = Executor.PROCESSOR.newDocumentBuilder();
		builder.setTreeModel(TreeModel.LINKED_TREE);
		XdmNode xdmNode = builder.build(StringSource.newMe(domString));
		XQueryCompiler queryCompiler = Executor.PROCESSOR.newXQueryCompiler();
		queryCompiler.setUpdatingEnabled(true);
		XQueryExecutable executable = queryCompiler.compile(rulestring);
		XQueryEvaluator evaluator = executable.load();

		evaluator.setContextItem(xdmNode);

		evaluator.run();
		return xdmNode.toString();
	}
}
