package org.w3c.tidy5.premium;

import java.util.ArrayList;

class Rules extends ArrayList<ClearRule>{
		private static final long serialVersionUID = 1L;

		public boolean isUpdate() {
			for(ClearRule rule:this)
				if(rule.isUpdate()) return true;
			return false;
		}
	}