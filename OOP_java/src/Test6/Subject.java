package Test6;

public class Subject {
	private int studentNum; // 학생 학번
	private String subject; // 과목이름 
	private int score; // 학생 점수
	
	public Subject(int studentNum, String subject, int score) {
		super();
		this.studentNum = studentNum;
		this.subject = subject;
		this.score = score;
	}
	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "\t"+ subject +"\t" + score + "점";
	}
	
	
	
}
