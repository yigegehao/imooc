package service;

import java.util.List;

import entity.Students;

public interface StudentDao {
	//查询所有学生信息
	public List<Students> queryAllStudents();
	//根据sid查询学生信息
	public Students queryStudentsBySid(String sid);
	//添加学生信息
	public boolean addStudents(Students s);
	//根据sid删除学生信息
	public boolean deleteStudents(String sid);
	//修改学生信息
	public void updateStudent(Students s);
}
