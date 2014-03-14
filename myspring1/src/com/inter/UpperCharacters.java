package com.inter;

public class UpperCharacters implements ChangeCharacters {
	private String str;


	public String getStr() {
		return str;
	}



	public void setStr(String str) {
		this.str = str;
	}
	
	@Override
	public String change() {
		return str.toUpperCase();
	}


}
