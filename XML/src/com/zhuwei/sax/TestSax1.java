package com.zhuwei.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class TestSax1 {
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException{
		//1.创建解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//2.得到解析器
		SAXParser sp = factory.newSAXParser();
		
		//3.得到读取器
		XMLReader reader = sp.getXMLReader();
		
		//4.设置内容处理器
		reader.setContentHandler(new ListHandler());
		
		//5.读取xml文档内容
		reader.parse("src/exam.xml");
	}
}

class ListHandler implements ContentHandler{
	
	@Override
	public void startElement(String uri, String localName, String name,//解析标签
			Attributes atts) throws SAXException {
		System.out.println("<"+name+">");
		
		for (int i=0; atts!=null && i<atts.getLength(); i++){
			String attrName = atts.getQName(i);
			String attrValue = atts.getValue(i);
			System.out.println(attrName +"=" + attrValue);
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length)//解析内容
			throws SAXException {
		System.out.println(new String(ch, start, length));
		
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		System.out.println("</" + name + ">");
		
	}
	
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException { 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
}
