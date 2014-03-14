package com.zhuwei.dom4j;

import java.io.*;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;



public class TestDom4j1 {
	
	//读取xml文档第二个人的location
	@Test
	public void read() throws DocumentException{
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/exam.xml"));
        
        Element root = document.getRootElement();
        
        Element studentElement = (Element) root.elements("student").get(1);
        
        String location = studentElement.element("location").getText();
        
        System.out.println(location);
	}
	
	
	//读取xml文档第二个人的location的属性
	@Test
	public void readAttr() throws DocumentException{
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/exam.xml"));
        
        Element root = document.getRootElement();
        
        Element studentElement = (Element) root.elements("student").get(1);
        
        //String attr = studentElement.attribute("id").getValue();
        String attr = studentElement.attributeValue("id");
        
        System.out.println(attr);
	}
	
	//add attribute: height
	@Test
	public void add() throws DocumentException, IOException{
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/exam.xml"));
        
        
        Element exam = document.getRootElement();
        Element student =  (Element) exam.elements("student").get(1);
        
        student.addElement("height").setText("170cm");
        
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        
        //XMLWriter writer = new XMLWriter(new FileWriter( "src/exam.xml" )); //会出现乱码
        //XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream("src/exam.xml"), "UTF-8")); //这个可以
        //XMLWriter writer = new XMLWriter(new FileWriter("src/exam.xml"), format);
        XMLWriter writer = new XMLWriter(new FileOutputStream("src/exam.xml"), format);
        writer.write( document );
        writer.close();
	}
	
	//add attribute: height
	@Test
	public void addBefore() throws DocumentException, IOException{
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/exam.xml"));
        
        
        Element exam = document.getRootElement();
        
        Element student = (Element) exam.elements("student").get(0);
        
        List<Element> list = student.elements();
        Element height =DocumentHelper.createElement("height");
        height.setText("170cm");
        list.add(2, height);
        
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream("src/exam.xml"), format);
        writer.write( document );
        writer.close();
	}
	
	@Test
	public void delete() throws Exception{
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/exam.xml"));
        
        Element height = document.getRootElement().element("student").element("height");
        
        height.getParent().remove(height);
        
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream("src/exam.xml"), format);
        writer.write( document );
        writer.close();
        
	}
	
	@Test
	public void update() throws Exception{
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/exam.xml"));
        
        Element student = (Element) document.getRootElement().elements("student").get(1);
        
        student.element("grade").setText("95.0");
        
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream("src/exam.xml"), format);
        writer.write( document );
        writer.close();
        
	}
}
