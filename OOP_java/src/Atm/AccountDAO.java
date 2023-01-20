package Atm;

import java.util.ArrayList;
import java.util.Random;

public class AccountDAO {
	private static ArrayList<Account> accList = new ArrayList<>();

	static ArrayList<Account> getaccList() {
		return accList;
	}

	static int printAccPage(int log) {
		int cnt = 0;
		System.out.println("[ 계 좌 목 록 ]");
		for (Account a : accList) {
			if (a.clientNo == ClientDAO.getcList().get(log).clientNo) {
				cnt++;
				System.out.print(cnt + ") " + a);
			}
		}
		System.out.println("-----------------------------------------");
		return cnt;
	}

	static void myPage(int log) {
		System.out.println(ClientDAO.getcList().get(log));
		printAccPage(log);
		System.out.println("[1] 비밀번호 변경\n[0] 뒤로가기");
		int sel = Util.getVal("메뉴 선택", 0, 1);
		if (sel == 0) {
			return;
		}

		String pw = Util.getVal("PW");
		if (ClientDAO.getcList().get(log).pw.equals(pw)) {
			System.err.println("기존 비밀번호와 같습니다");
			return;
		}
		ClientDAO.getcList().get(log).pw = pw;
		System.out.println("[ 비밀번호 변경 완료 ]");
	}

	private static int[] accArr(int log, int cnt) {
		int[] arr = new int[cnt];
		int idx = 0;
		for (int i = 0; i < accList.size(); i++) {
			if (accList.get(i).clientNo == ClientDAO.getcList().get(log).clientNo) {
				arr[idx] = i;
				idx++;
			}
		}
		return arr;
	}

	static void dataSetting(String data) {
		String[] dataArr = data.split("\n");
		for (String d : dataArr) {
			String[] temp = d.split("/");
			for (Client c : ClientDAO.getcList()) {
				if (temp[0].equals(c.id)) {
					accList.add(new Account(c.clientNo, temp[1], Integer.parseInt(temp[2])));
				}
			}
		}
	}

	private static String addAccNum() {
		Random rd = new Random();
		String accNum = "";
		for (int i = 0; i < 12; i++) {
			accNum += rd.nextInt(10) + "";
			if (i % 4 == 3 && i != 11) {
				accNum += "-";
			}
		}
		return accNum;
	}

	private static int retAccIdx(String accNum) {
		int idx = 0;
		for (Account a : accList) {
			if (accNum.equals(a.accountNumber)) {
				return idx;
			}
			idx++;
		}
		return -1;
	}

	static void addAcc(int log) {
		accList.add(new Account(ClientDAO.getcList().get(log).clientNo, addAccNum(), 0));
		System.out.println("[ 계좌 개설 완료 ]");
	}

	static void delAcc(int log) {
		System.out.println("[ 계좌 삭제 ]");
		printAccPage(log);
		String accNum = Util.getVal("계좌번호 입력");
		int idx = retAccIdx(accNum);
		if (idx == -1) {
			System.err.println("없는 계좌번호 입니다");
			return;
		}
		if (accList.get(idx).money != 0) {
			System.err.println("계좌에 잔액이 남아 삭제할 수 없습니다");
			return;
		}
		accList.remove(log);
		System.out.println("[ 계좌 삭제 완료 ]");
	}

	static void deposit(int log) {
		System.out.println("[ 입   금 ]");
		int cnt = printAccPage(log);
		int[] arr = accArr(log, cnt);
		int sel = Util.getVal("내 계좌 선택", 1, cnt) - 1;

		int money = Util.getVal("금액 입력", 1000, 1000000);
		if (money == -1) {
			return;
		}
		accList.get(arr[sel]).money += money;

	}

	static void withdraw(int log) {
		System.out.println("[ 출   금 ]");
		int cnt = printAccPage(log);
		int[] arr = accArr(log, cnt);
		int sel = Util.getVal("내 계좌 선택", 1, cnt) - 1;

		int money = Util.getVal("금액 입력", 1000, accList.get(arr[sel]).money);
		if (money == -1) {
			return;
		}
		accList.get(arr[sel]).money -= money;
	}

	static void transfer(int log) {
		System.out.println("[ 이   체 ]");
		int cnt = printAccPage(log);
		int[] arr = accArr(log, cnt);
		int sel = Util.getVal("내 계좌 선택", 1, cnt) - 1;

		String accNum = Util.getVal("송금 계좌번호 입력");
		int idx = retAccIdx(accNum);
		if (idx == arr[sel]) {
			System.err.println("같은 계좌로는 송급할 수 없습니다");
			return;
		}
		if (idx == -1) {
			System.err.println("없는 계좌번호 입니다");
			return;
		}

		int money = Util.getVal("금액 입력", 1, accList.get(arr[sel]).money);
		if (money == -1) {
			return;
		}
		accList.get(arr[sel]).money -= money;
		accList.get(idx).money += money;
	}

}
