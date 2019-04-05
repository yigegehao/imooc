package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entity.Students;
import service.StudentDao;
import service.impl.StudentsDaoImpl;

public class StudentsAction extends SuperAction {

	// 查询所有学生信息
	public String query() {
		StudentDao sd = new StudentsDaoImpl();
		List<Students> list = sd.queryAllStudents();
		if (list != null && list.size() > 0) {
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}

	// 删除学生信息
	public String delete() {
		StudentDao sd = new StudentsDaoImpl();
		String sid = request.getParameter("sid");
		System.out.println("------" + sid);
		sd.deleteStudents(sid);
		return "delete_success";
	}

	// 添加学生信息
	public String add() throws ParseException {
		Students s = new Students();
		StudentDao sd = new StudentsDaoImpl();

		s.setAddress(request.getParameter("address"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setGender(request.getParameter("gender"));
		s.setSname(request.getParameter("sname"));
		boolean isTrue = sd.addStudents(s);

		return "add_success";
	}

	// 修改学生信息
	public String modify() throws ParseException {
		StudentDao sd = new StudentsDaoImpl();
		String sid = request.getParameter("sid");
		Students student = sd.queryStudentsBySid(sid);

		session.setAttribute("modify_students", student);
		return "modify_success";
	}

	// 保存学生信息
	public String save() throws ParseException {
		Students s = new Students();
		StudentDao sd = new StudentsDaoImpl();
		String sid = request.getParameter("sid");
		
		s.setSid(sid);
		s.setAddress(request.getParameter("address"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setGender(request.getParameter("gender"));
		s.setSname(request.getParameter("sname"));

		sd.updateStudent(s);
		return "save_success";
	}
}
