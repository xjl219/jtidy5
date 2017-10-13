package org.w3c.tidy5.premium;

import java.util.ArrayList;


import net.sf.saxon.s9api.XdmItem;

public class ClearRule {
	RULE rule;
	public RULE getRule() {
		return rule;
	}

	public String getXpath() {
		return expression;
	}
	public boolean isUpdate() {
		return rule == RULE.MINUS || RULE.XQ == rule;
	}

	String expression;
	Iterable<XdmItem> iterable;

	ClearRule(String xrule) throws InvalidFormatException{
		
		char sign = xrule.charAt(0);
		switch (sign) {
		case '&':
			this.rule=RULE.XQ;
			this.expression=xrule.substring(1);
			break;
		case '-':
			this.rule=RULE.MINUS;
			this.expression=xrule.substring(1);
			break;
		case '$':
			this.rule=RULE.EXTRACT;
			this.expression=xrule.substring(1);
			break;
		case '_':
			this.rule=RULE.DELSTR;
			this.expression=xrule.substring(1);
			break;
		default:
			throw new InvalidFormatException(xrule,sign);
			
		}
	}
	
	

	static ClearRule compile(String ruleXpath) throws InvalidFormatException{
		return new ClearRule(ruleXpath);
	}
	static Rules compiles(String ...ruleXpath) throws InvalidFormatException{
		Rules rules = new Rules();
		for (String clearRule : ruleXpath) {
			rules.add(new ClearRule(clearRule));
		}
		return rules;
	}
}
