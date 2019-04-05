package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyHibernateSessionFactory {

	private static SessionFactory sessionFactory; //会话工厂属性
	
	//保证单例
	private MyHibernateSessionFactory() {
		
	}
	
	//获取会话工厂
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
			return sessionFactory;
		}
		return sessionFactory;
	}
}
