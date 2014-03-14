package com.inter;

public class LowerCharacters implements ChangeCharacters {
	private String str;
	
	
	@Override
	public String change() {
		return str.toLowerCase();
	}


	public String getStr() {
		return str;
	}


	public void setStr(String str) {
		this.str = str;
	}

}
