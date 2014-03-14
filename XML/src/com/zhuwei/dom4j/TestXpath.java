package com.zhuwei.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestXpath {
	
	
	public static void main(String args[]) throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read("src/exam.xml");
		
		Element name = (Element) document.selectNodes("//name").get(0);
		System.out.println(name.getText());
	}

}
