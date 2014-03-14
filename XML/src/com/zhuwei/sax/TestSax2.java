package com.zhuwei.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class TestSax2 {
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException{
		//1.������������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//2.�õ�������
		SAXParser sp = factory.newSAXParser();
		
		//3.�õ���ȡ��
		XMLReader reader = sp.getXMLReader();
		
		//4.�������ݴ�����
		reader.setContentHandler(new TagValueHandler());
		
		//5.��ȡxml�ĵ�����
		reader.parse("src/exam.xml");
	}
}

class TagValueHandler extends DefaultHandler{
	
	private String currentTag;
	private int needNum = 2;
	private int currentNum;
	

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		currentTag = name;
		
		if(currentTag.equals("name")){
			currentNum++;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		currentTag = "";
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(currentTag.equals("name") && currentNum == needNum){
			System.out.println(new String(ch,start, length));
		}
	}
	
}


