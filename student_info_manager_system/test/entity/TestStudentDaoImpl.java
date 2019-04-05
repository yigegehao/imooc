package entity;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import service.StudentDao;
import service.impl.StudentsDaoImpl;

public class TestStudentDaoImpl {
	
	@Test
	public void testQueryAllStudents() {
		StudentDao sdi = new StudentsDaoImpl();
		List<Students> list = sdi.queryAllStudents();
		for(Students s:list) {
			System.out.println(s);
		}
	}
	
	@Test
	public void testGetNewSid() {
		StudentsDaoImpl sdi = new StudentsDaoImpl();
		//System.out.println(sdi.getNewSid());
	}
	
	@Test
	public void testAddStudents() {
		StudentDao sd = new StudentsDaoImpl();
		Students s = new Students();
		s.setAddress("武夷山");
		s.setBirthday(new Date());
		s.setGender("男");
		s.setSname("张二丰");
		boolean b = sd.addStudents(s);
		Assert.assertEquals(true, b);
	}
}
