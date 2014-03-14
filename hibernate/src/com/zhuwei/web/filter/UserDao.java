package com.zhuwei.web.filter;

import com.hibernate.domain.many2one.User;
import com.hibernate.utils.HibernateUtils;

public class UserDao {
	
	public void addUser(User user){
		HibernateUtils.getThreadLocalSession().save(user);
	}
}
