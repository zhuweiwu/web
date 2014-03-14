package com.zhuwei.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class TestSAX3 {
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException{
		//1.创建解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//2.得到解析器
		SAXParser sp = factory.newSAXParser();
		
		//3.得到读取器
		XMLReader reader = sp.getXMLReader();
		
		//4.设置内容处理器
		BeanListHandler handler = new BeanListHandler();
		reader.setContentHandler(handler);
		
		handler.getList();
		
		//5.读取xml文档内容
		reader.parse("src/exam.xml");
		
		List<Student> list = handler.getList();
		System.out.println(list);
	}
}

//封装每一个对象， 并将所有封装对象放在list中
class BeanListHandler extends DefaultHandler{
	
	private List<Student> list = new ArrayList<Student>();
	String currentTag = null;
	Student student = null;
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		currentTag = name;
		if ("student".equals(currentTag)){//currentTag 不能放前面，会出现空指针错误
			student = new Student();
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if("id".equals(currentTag)){
			int id = Integer.parseInt(new String(ch, start, length));
			student.setId(id);
		}
		
		if("name".equals(currentTag)){
			String name = new String(ch, start, length);
			student.setName(name);
		}
		
		if("location".equals(currentTag)){
			String location = new String(ch, start, length);
			student.setLocation(location);
		}
		
		if("grade".equals(currentTag)){
			double grade = Double.parseDouble(new String(ch, start, length));
			student.setGrade(grade);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		currentTag = ""; //必须清空不然会出现空指针错误，因为xml文件里面标签之间的空格也会被读取
		if("student".equals(name)){
			list.add(student);
			student = null;
		}
	}

	public List<Student> getList() {
		return list;
	}


}