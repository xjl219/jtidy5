package org.w3c.tidy5.premium;
public  enum RULE{
	/*
	 * use $
	 *  extract each element of  this source that  matches the given xpath expression
	 */
		EXTRACT,
		/*
		 *
		 * unsuport
		 */
		PLUS,
		/*
		 *   use -
		 * delete  each element of  this source that  matches the given xpath expression
		 */
		MINUS,
		/*
		 * use &
		 * execute Xquery expression update for this source 
		 */
		XQ,
		/*
		 *  use _
		 * delete   each substring of this string that matches the given regular expression
		 */
		DELSTR
		
	}