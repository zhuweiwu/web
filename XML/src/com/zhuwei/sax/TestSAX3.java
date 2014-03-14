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
		//1.������������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//2.�õ�������
		SAXParser sp = factory.newSAXParser();
		
		//3.�õ���ȡ��
		XMLReader reader = sp.getXMLReader();
		
		//4.�������ݴ�����
		BeanListHandler handler = new BeanListHandler();
		reader.setContentHandler(handler);
		
		handler.getList();
		
		//5.��ȡxml�ĵ�����
		reader.parse("src/exam.xml");
		
		List<Student> list = handler.getList();
		System.out.println(list);
	}
}

//��װÿһ������ �������з�װ�������list��
class BeanListHandler extends DefaultHandler{
	
	private List<Student> list = new ArrayList<Student>();
	String currentTag = null;
	Student student = null;
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		currentTag = name;
		if ("student".equals(currentTag)){//currentTag ���ܷ�ǰ�棬����ֿ�ָ�����
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
		currentTag = ""; //������ղ�Ȼ����ֿ�ָ�������Ϊxml�ļ������ǩ֮��Ŀո�Ҳ�ᱻ��ȡ
		if("student".equals(name)){
			list.add(student);
			student = null;
		}
	}

	public List<Student> getList() {
		return list;
	}


}