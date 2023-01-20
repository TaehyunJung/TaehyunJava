package Test6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyFile {
	private FileReader fr;
	private FileWriter fw;
	private String studentFilePath = "src/시험6/studentList.txt";
	private String subjectFilePath = "src/시험6/subjectList.txt";
	
	void saveStudentList() {
		saveData(studentFilePath,"Student");
		saveData(subjectFilePath,"Subject");
	}

     void loadStudentList() {
    	 loadData(studentFilePath,"Student");
    	 loadData(subjectFilePath,"Subject");
	}
	// 어레이리스트 객체 -> String data로 변경 
	// data -> fileWriter -> 텍스트파일 생성 
	// 파일 -> fileReader -> 다시 어레이리스트 
	private String getData(String cName) {
		String data="";
		if(cName.equals("Student")) {
			ArrayList<Student> stuList =StudentDAO.studentList;
			if(stuList.size()==0) {
				return data;
			}
			for(Student s : stuList) {
				data+= s.studentNum+","+s.studentId+"\n";
			}
		
		}
		if(cName.equals("Subject")) {
			ArrayList<Subject> subList =SubjectDAO.getSubjectList();
			if(subList.size()==0) {
				return data;
			}
			for(Subject s : subList) {
				data+= s.getStudentNum()+","+s.getSubject()+","+s.getScore()+"\n";
			}
		}
		data=data.substring(0,data.length()-1);
		
		return data;
	}
	
	private void saveData(String path, String cName) {
		String data = getData(cName);
		if(data.equals("")) {
			System.out.println("저장할 데이터가 없습니다");
			return;
		}
		try {
			fw = new FileWriter(path);
			fw.write(data);
			System.out.println(data);
			System.out.println("["+ path+" 저장완료 ]");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		
	}
	
	void updateData(String data, String cName) {
		String[] temp = data.split("\n");
		if(cName.equals("Student")) {
			ArrayList<Student> stuList = StudentDAO.studentList;
			for(int i =0; i <temp.length;i++) {
				String[] info = temp[i].split(",");
				//Student stu = new Student(Integer.parseInt(info[0]), info[1]);
				//stuList.add(stu);
				stuList.add(new Student(Integer.parseInt(info[0]), info[1]));
			}
			StudentDAO.setNum(StudentDAO.MINMUM+temp.length);
		}
		if(cName.equals("Subject")) {
			ArrayList<Subject> subList = SubjectDAO.getSubjectList();
			for(int i =0; i <temp.length;i++) {
				String[] info = temp[i].split(",");
				subList.add(new Subject(Integer.parseInt(info[0]), info[1],
						Integer.parseInt(info[2])));
			}
		}
		
	}

	void loadData(String path,String cName) {
		try {
			fr = new FileReader(path);
			String data="";
			while(true) {
				int read = fr.read();
				if(read != -1) {
					data += (char)read;
				}else {
					break;
				}
			}
			System.out.println(data);
			updateData(data, cName);
			
			System.out.println(path + "로드 성공");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
}
