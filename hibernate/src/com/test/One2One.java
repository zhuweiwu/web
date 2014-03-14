package com.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.domain.one2one.IdCard;
import com.hibernate.domain.one2one.Person;
import com.hibernate.utils.HibernateUtils;

/**
 * 改变IdCard.hbm.xml下的one-to-one中的lazy属性值来看Hibernate查询的语句有什么不同
 * lazy=false ---- 不是懒加载，相关联的属性直接查询加载
 * lazy=proxy ---- 懒加载，在需要用的时候加载相关属性
 * lazy=no-proxy ----
 * 
 * fetch = select ---- 用select方式进行查询
 * fetch = join ---- 用join方式来进行查询，用这种方法查询lazy就没什么用了，join直接都查询出来了
 * @author AD
 *
 */

public class One2One {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		add();
		query(1);
	}
	
	
	static void add(){
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			IdCard idCard = new IdCard();
			idCard.setLifetime(new Date());
			
			Person person = new Person();
			person.setName("aaa");
			
			idCard.setPerson(person);
			
			t = s.beginTransaction();
			s.save(person);
			s.save(idCard);
			
			t.commit();
			
		} finally{
			if(s!=null){
				s.close();
			}
		}
		
	}
	
	static Person query(int id){
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			IdCard idCard = (IdCard) s.get(IdCard.class, id);
			
			return null;
		}finally{
			if(s!=null) s.close();
		}
	}

}
