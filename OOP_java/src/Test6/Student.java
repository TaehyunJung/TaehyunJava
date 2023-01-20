package Test6;

public class Student {
	
		int studentNum;
		String studentId;
		
		public Student(int studentNum, String studentId) {
			super();
			this.studentNum = studentNum;
			this.studentId = studentId;
		}

		@Override
		public String toString() {
			return studentNum +"\t" + studentId;
		}
	
}
