package Atm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	static Scanner sc = new Scanner(System.in);

	static String getVal(String msg) {
		System.out.print(msg + " : ");
		return sc.next();
	}

	static int getVal(String msg, int start, int end) {
		int num = -1;
		while (true) {
			try {
				System.out.printf(msg + "[%d-%d] : ", start, end);
				num = sc.nextInt();
				if (num < start || num > end) {
					throw new Exception(start + "-" + end + "사이값 입력");
				}
				if (msg.equals("금액 입력") && num % 1000 != 0) {
					throw new Exception("1000단위 로만 입력");
				}
			} catch (InputMismatchException e) {
				System.err.println("숫자만 입력하세요");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			if (num >= start && num <= end) {
				if (msg.equals("금액 입력")) {
					if (num % 1000 == 0) {
						break;
					}
				} else {
					break;
				}
			}
			sc.nextLine();
		}
		return num;
	}

}
