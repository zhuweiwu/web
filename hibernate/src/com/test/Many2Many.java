package com.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.domain.many2many.Student;
import com.hibernate.domain.many2many.Teacher;
import com.hibernate.utils.HibernateUtils;

public class Many2Many {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		add();
		query(1);
	}
	
	static void add(){
		Session s = null;
		Transaction t = null;
		try {
			Set<Teacher> teachers = new HashSet<Teacher>();
			Teacher teacher1 = new Teacher();
			teacher1.setName("t1");
			teachers.add(teacher1);
			Teacher teacher2 = new Teacher();
			teachers.add(teacher2);
			teacher2.setName("t2");
			
			Set<Student> students = new HashSet<Student>();
			Student student1 = new Student();
			student1.setName("s1");
			students.add(student1);
			Student student2 = new Student();
			student2.setName("s2");
			students.add(student2);
			
			teacher1.setStudents(students);
			teacher2.setStudents(students);
			
			//如果再加上学生的会出现什么错误,会发生重复
			/*
			student1.setTeachers(teachers);
			student2.setTeachers(teachers);
			*/
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			
			s.save(teacher1);
			s.save(teacher2);
			s.save(student1);
			s.save(student2);
			
			t.commit();
		} finally{
			if(s!=null) s.close();
		}
	}

	static void query(int id){
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			Teacher teacher = (Teacher) s.get(Teacher.class, id);
			System.out.println("students:" + teacher.getStudents().size());
			t.commit();
		} catch (Exception e) {
			if(s!=null){
				s.close();
			}
		}
	}
}
