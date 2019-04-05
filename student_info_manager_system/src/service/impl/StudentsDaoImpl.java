package service.impl;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Students;
import service.StudentDao;

public class StudentsDaoImpl implements StudentDao{

	@SuppressWarnings("deprecation")
	@Override
	public List<Students> queryAllStudents() {
		Transaction tx = null;
		Session session = null;
		List<Students> list = null;
		String hql = "from Students";
		try {
			session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			tx = null;
		}
		return list;
	}

	@Override
	public Students queryStudentsBySid(String sid) {
		Transaction tx = null;
		Session session = null;
		Students s = null;
		
		try {
			session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = session.get(Students.class, sid);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			tx = null;
		}
		return s;
	}


	@Override
	public boolean addStudents(Students s) {
		Transaction tx = null;
		s.setSid(getNewSid());

		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx = null;
			return false;
		} finally {
			tx = null;
		}
		return true;
	}

	@Override
	public boolean deleteStudents(String sid) {
		Transaction tx=null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Students s = session.get(Students.class, sid);
			session.delete(s);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			tx=null;
		}
		return false;
	}

	@Override
	public void updateStudent(Students s) {
		Transaction tx = null;
		
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(s);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx = null;
		} finally {
			tx = null;
		}
	}
	
	private String getNewSid() {
		Transaction tx = null;
		String sql = "";
		String tempStr = "S";
		
		try {
			sql = "select max(sid) from Students";
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			String sid = (String)query.uniqueResult();
			if(sid == null || "".equals(sid)) {
				tempStr = "S001";
			}else {
				String temp = sid.substring(1);
				int tempInt = Integer.parseInt(temp)+1;
				int len = (""+tempInt).length();
				int zerolen = 3 - len;
				for(int i=0;i<zerolen;i++) {
					tempStr+=0;
				}
				tempStr = tempStr+tempInt;
			}
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			tx = null;
		}
		return tempStr;
	}

}
