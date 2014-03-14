package com.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hibernate.dao.UserDao;
import com.hibernate.domain.many2one.User;
import com.hibernate.utils.HibernateUtils;

public class UserDaoHiberateImpl implements UserDao {

	@Override
	public User findUserById(int id) {
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			User user = (User) s.get(User.class, id);
			return user;
		}finally{
			if(s!=null){
				s.close();
			}
		}
	}

	@Override
	public User findUserByName(String username) {
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			Criteria c = s.createCriteria(User.class);
			c.add(Restrictions.eq("username", username));
			User user = (User) c.uniqueResult();
			return user;
		} finally{
			if(s!=null) s.close();
		}
	}
	
	//和上面的方法一样，看个人喜好使用
	public User findUserByName1(String username){
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			String hql = "from User as user where user.username=:username";
			Query q = s.createQuery(hql);
			q.setString("username", username);
			User user = (User) q.uniqueResult();
			return user;
		} finally{
			if(s!=null) s.close();
		}
	}

	@Override
	public void remove(User user) {
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.delete(user);
			t.commit();
		} finally{
			if(s!=null) s.close();
		}
	}
	
	@Override
	public void saveUser(User user) {
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.save(user);
			t.commit();
		} finally{
			if(s!=null) s.close();
		}
	}
	
	@Override
	public void updateUser(User user) {
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtils.getSession();
			t = s.beginTransaction();
			s.update(user);
			t.commit();
		} finally{
			if(s!=null) s.close();
		}
	}

}
