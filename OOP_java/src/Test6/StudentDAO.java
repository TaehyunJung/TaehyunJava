package Test6;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentDAO {
	static final int MINMUM = 1000;
	static final ArrayList<Student> studentList = new ArrayList<Student>();
	private static int num = MINMUM;
	public static int getNum() {
		return num;
	}
    static void setNum(int num) {
    	StudentDAO.num = num;
    }
	
	Student checkId(String id) {
		for(Student s :studentList) {
			if(s.studentId.equals(id)) {
				return s;
			}
		}
		return null;
	}
	
	public void join() {
		System.out.println("[ 회원가입 ]");
		String id = MyUtil.getValue("ID 입력>>");
		Student stu = checkId(id);
		if(stu == null) {
			stu = new Student(++num,id);
			System.out.println(stu +" 학생 등록 성공 ");
			studentList.add(stu);
		}else {
			System.out.println("이미 존재하는 ID 입니다");
		}
		
	}
	int checkValidNum(int num) {
		int idx =0;
		for(Student stu : studentList) {
			if(num == stu.studentNum) {
				return idx;
			}
			idx++;
		}
		return -1;
	}
	public void deleteStudent() {
		if(studentList.isEmpty()) {
			System.out.println("[no data]");
			return;
		}
		int hakbun = MyUtil.getValue("[학생 삭제 : 학번]", MINMUM+1, num);
		int idx = checkValidNum(num);
		if(idx == -1) {
			System.err.println("[해당 학번은 존재하지 않는 학번]");
			return;
		}
		//SubjectDAO.subjectList = null;
		SubjectDAO.deleteSubjects(hakbun);
		studentList.remove(idx);
		System.out.println("[학생 삭제 성공]");
				
	}
	public void printStudentList() {
		System.out.println("[ 학생 목록 ]");
		for(Student stu : studentList) {
			System.out.print(stu);
			ArrayList<Subject> subList = SubjectDAO.getAStuSubjests(stu);
			for(Subject sub : subList) {
				System.out.print(sub);
			}
			System.out.println();
		}
	}
	
	void sortSudentByScore() {
		ArrayList<Double> scores = new ArrayList<Double>();
		
		for(Student stu : studentList) {
			int sum =0;
			ArrayList<Subject> subList = SubjectDAO.getAStuSubjests(stu);
			for(Subject sub : subList) {
				sum+= sub.getScore();
			}
			if(sum == 0) {
				scores.add(0.0);
			}else {
				scores.add(sum *1.0/subList.size());
			}
		}
		
		System.out.println(scores);
		
		System.out.println(" 성적 순으로 정렬 ");
		for (int i = 0; i < scores.size(); i++) {
			int maxIdx = i;
			for (int k = i; k < scores.size(); k++) {
				if (scores.get(i) < scores.get(k)) {
					maxIdx = k;
				}
			}
			
			double temp = scores.get(i);
			scores.set(i, scores.get(maxIdx));
			scores.set(maxIdx, temp);

			Student temp2 = studentList.get(i);
			studentList.set(i, studentList.get(maxIdx));
			studentList.set(maxIdx, temp2);

		}
		
		int idx =0;
		for(Student stu : studentList) {
			System.out.printf("%d 평균 %.2f점 \n" , stu.studentNum , scores.get(idx));
			idx++;
		}
		System.out.println();
		
	}
	
	

}
