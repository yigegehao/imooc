package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Users;
import service.UserDao;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean userLogin(Users user) {
		Transaction tx = null;  //事务对象
		String hql = "";
		
		try {
			//获取session对象并查询
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			hql = "from Users where username=? and password=?";
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter(0, user.getUsername());
			query.setParameter(1, user.getPassword());
			List list = query.list();
			tx.commit();
			if(list != null && list.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(tx != null) {
				tx = null;
			}
		}
	}

}
