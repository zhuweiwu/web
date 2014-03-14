package com.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.domain.one2one.IdCard;
import com.hibernate.domain.one2one.Person;
import com.hibernate.utils.HibernateUtils;

/**
 * �ı�IdCard.hbm.xml�µ�one-to-one�е�lazy����ֵ����Hibernate��ѯ�������ʲô��ͬ
 * lazy=false ---- ���������أ������������ֱ�Ӳ�ѯ����
 * lazy=proxy ---- �����أ�����Ҫ�õ�ʱ������������
 * lazy=no-proxy ----
 * 
 * fetch = select ---- ��select��ʽ���в�ѯ
 * fetch = join ---- ��join��ʽ�����в�ѯ�������ַ�����ѯlazy��ûʲô���ˣ�joinֱ�Ӷ���ѯ������
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
