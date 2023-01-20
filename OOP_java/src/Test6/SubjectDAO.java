package Test6;

import java.util.ArrayList;
import java.util.Scanner;

public class SubjectDAO {
	private static ArrayList<Subject> subjectList = new ArrayList<Subject>();
    static ArrayList<Subject> getSubjectList(){
    	return subjectList;
    }
	
	static ArrayList<Subject> getAStuSubjests(Student stu) {
		ArrayList<Subject> subList = new ArrayList<Subject>();
		for (Subject sub : subjectList) {
	
			if (sub.getStudentNum() == stu.studentNum) {
				subList.add(sub);
			}
		}
		return subList;
	}

	static void deleteSubjects(int hakbun) {

		for(int i =0; i < subjectList.size();i++) {
			if(subjectList.get(i).getStudentNum() == hakbun) {
				subjectList.remove(i);
			}
		}

	}
	
	int checkSubjectName(int hakbun, String name) {
		int idx =0;
		for (Subject sub : subjectList) {
			if (sub.getStudentNum() == hakbun && name.equals(sub.getSubject())) {
				return idx;
			}
			idx++;
		}
		return -1;
	}
	
	int existSubject() {
		if (StudentDAO.studentList.size() == 0) {
			System.out.println("[ 학생 데이터가 존재하지 않습니다 ]");
			return -1;
		}
		int hakbun = MyUtil.getValue("[과목 추가 : 학번]", StudentDAO.MINMUM+1, StudentDAO.getNum());
		if (hakbun == -1) {
			System.out.println("없는 학생 번호");
			return -1;
		}

		return hakbun;
	}
	
	public void insertSubject() {
	
		int hakbun = existSubject();
		if( hakbun == -1) {
			return;
		}
		
		String name = "";

		while (true) {
			name = MyUtil.getValue("[과목 추가 : 과목 이름] ");
			int check = checkSubjectName(hakbun,name);
			if (check != -1) {
				System.out.println("이미 존재하는 과목 이름입니다");
				continue;
			}
			break;
		}

		int score = -1;
		while (score == -1) {
			score = MyUtil.getValue("[과목 추가 : 과목 점수]", 0, 100);
		}
		Subject subData = new Subject(hakbun, name, score);
		subjectList.add(subData);

	}
	
	int getSubjects(int hakbun) {
		int cnt =0;
		for(Subject s: subjectList) {
			if(s.getStudentNum() == hakbun) {
				cnt++;
			}
		}
		return cnt;
	}

	public void deleteSubject() {
		int hakbun = existSubject();
		if( hakbun == -1) {
			return;
		}
		int cnt = getSubjects(hakbun);
		if(cnt == 0) {
			System.out.println("[ 과목 데이터가 존재하지 않습니다 ]");
			return;
		}
		
		String name = "";

		name = MyUtil.getValue("[과목 삭제 : 과목 이름] ");
		int idx = checkSubjectName(hakbun,name);
		if (idx == -1) {
			System.out.println("없는 과목 이름입니다");
			return;
		}
		subjectList.remove(idx);
		System.out.printf("%s 의 과목 %s 삭제 완료 \n" , hakbun, name);

	}
}
