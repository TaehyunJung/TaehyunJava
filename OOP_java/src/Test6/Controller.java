package Test6;

import java.util.Scanner;

public class Controller {

	StudentDAO studentDAO;
	SubjectDAO subjectDAO;
	MyFile file;
	Controller() {
		studentDAO = new StudentDAO();
		subjectDAO = new SubjectDAO();
		file = new MyFile();
	}

	void run() {
		while (true) {
			System.out.println("1)학생추가"); // 아이디 중복금지
			System.out.println("2)학생삭제"); // 학번으로 삭제
			System.out.println("3)과목추가"); // 과목이름 중복 금지
			System.out.println("4)과목삭제"); // 과목 이름
			System.out.println("5)전체출력"); // 기본: 출력 , 심화: 평균이 높은 순으로 출력 혹은
											// 심화: id 값 이름 오름차순으로 저장
			System.out.println("6)파일저장"); // 각각 studentList.txt , subjectList.txt 로저장
			System.out.println("7)파일로드"); // 기본: 출력 ,\
			System.out.println("8)성적 정렬 "); // 기본: 출력 ,
			System.out.println("0)종료");

			int sel = -1;
			while(sel == -1) {
				sel = MyUtil.getValue("메뉴선택", 0, 8);
			}
			
			if (sel == 0) {
				break;
			} else if (sel == 1) {
				studentDAO.join();
			} else if (sel == 2) {
				studentDAO.deleteStudent();
			} else if (sel == 3) {
				subjectDAO.insertSubject();
			} else if (sel == 4) {
				subjectDAO.deleteSubject();
			} else if (sel == 5) {
				studentDAO.printStudentList();
			} else if (sel == 6) {
				file.saveStudentList();
			} else if (sel == 7) {
				file.loadStudentList();
			} else if (sel == 8) {
				studentDAO.sortSudentByScore();
			}
		}
	}
}