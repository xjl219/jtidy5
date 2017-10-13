package org.w3c.tidy5.premium;

public class ExecDelStr {
	public static ExecDelStr me() {
		return new ExecDelStr();
	}
	public String runIt(String domString, String rulestring) throws Exception {
		return domString.replaceAll(rulestring, "");
	}
}
