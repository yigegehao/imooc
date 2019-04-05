package entity;

import java.util.Date;
import java.util.EnumSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Test;

import db.MyHibernateSessionFactory;

public class TestStudents {    
	
	  @Test 
	  public void testSchemaExport() { 
		  //创建配置对象 
		  Configuration cfg = new Configuration().configure(); 
		  //创建工厂 
		  SessionFactory sessionFactory = cfg.buildSessionFactory(); 
		  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build(); 
		  Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata(); 
		  SchemaExport export = new SchemaExport(); export.create(EnumSet.of(TargetType.DATABASE), metadata); 
	  }
	  
	  @Test
	  public void TestSaveStudent() {
		  Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		  
		  Transaction tx = session.beginTransaction();
		  Students s1 = new Students("s001","张三丰","男",new Date(),"武当山");
		  Students s2 = new Students("s002","郭靖","男",new Date(),"桃花岛");
		  Students s3 = new Students("s003","黄蓉","女",new Date(),"桃花岛");
		  session.save(s1);
		  session.save(s2);
		  session.save(s3);
		  tx.commit();
	  }
}