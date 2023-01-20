package Test6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyUtil {

	static private Scanner sc = new Scanner(System.in);
	
	static String getValue(String msg) {
		System.out.println(msg + ">>");
		String val = null;
		try {
			val = sc.next();
		} catch (Exception e) {
			System.out.println("입력 오류 ");
		}
		sc.nextLine();
		return val;
	}
	
	static int getValue(String msg, int min, int max) {
		System.out.printf("[ %d ~ %d ] 중에서 ", min, max);
		System.out.println(msg + ">> ");
		int num = 100;
		try {
			num = sc.nextInt();
			if (num < min || num > max) {
				throw new Exception();
			}
		} catch (InputMismatchException e) {
			System.err.println("문자열은 입력될수 없습니다.");
		} catch (Exception e) {
			num = -1;
			System.err.println("입력범위 에러.");
		}
		sc.nextLine();
		return num;
	}
	
}
