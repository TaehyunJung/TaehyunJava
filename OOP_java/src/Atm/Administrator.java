package Atm;

import java.util.ArrayList;

public class Administrator {
	static String bankName = "우리은행";

	static void mainMenu() {
		System.out.println("[ " + bankName + " ]");
		System.out.println("[1] 관리자");
		System.out.println("[2] 사용자");
		System.out.println("[0] 종 료");
	}

	static void adminMenu() {
		System.out.println("[ 관리자 ]");
		System.out.println("[1] 회원목록");
		System.out.println("[2] 회원정보 수정");
		System.out.println("[3] 회원정보 삭제");
		System.out.println("[4] 회원정보 저장");
		System.out.println("[5] 회원정보 불러오기");
		System.out.println("[0] 뒤로가기");
	}

	static void userMenu() {
		System.out.println("[ 사용자 ]");
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 뒤로가기");
	}

	static void setClientMenu() {

	}

	static int checkClient(String str, String val, ArrayList<Client> cList) {
		if (str.equals("이름")) {
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).name.equals(val)) {
					return i;
				}
			}
		} else if (str.equals("ID")) {
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).id.equals(val)) {
					return i;
				}
			}
		} else if (str.equals("PW")) {
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).pw.equals(val)) {
					return i;
				}
			}
		}

		return -1;
	}

	static void setClient() {
		ClientDAO.printClient();
		String name = Util.getVal("수정할 회원 이름");
		ArrayList<Client> cList = ClientDAO.getcList();
		int idx = checkClient("이름", name, cList);
		if (idx == -1) {
			System.err.println("없는 회원입니다");
			return;
		}
		String id = Util.getVal("수정할 회원 ID 입력");
		int idIdx = checkClient("ID", name, cList);
		if (idIdx != -1) {
			System.err.println("이미 존재하는 ID");
			return;
		}
		String pw = Util.getVal("수정할 회원 PW 입력");
		String newName = Util.getVal("수정할 이름 입력");
		cList.set(idx, new Client(cList.get(idx).clientNo, id, pw, newName));
	}

	static void delClient() {
		ClientDAO.printClient();
		String name = Util.getVal("삭제할 회원 이름");
		ArrayList<Client> cList = ClientDAO.getcList();
		int idx = checkClient("이름", name, cList);
		if (idx == -1) {
			System.err.println("없는 회원입니다");
			return;
		}
		cList.remove(idx);
		System.out.printf("[%s] 데이터 삭제 완료\n", name);
	}

	static void AdminRun() {
		while (true) {
			Administrator.adminMenu();
			int sel = Util.getVal("메뉴 입력", 0, 5);

			if (sel == 0) {
				break;
			}
			if (sel == 1) {
				ClientDAO.printClient();
			} else if (sel == 2) {
				setClient();
			} else if (sel == 3) {
				delClient();
			} else if (sel == 4) {
				File.saveData("Client", File.clientFileName);
				File.saveData("Account", File.accountFileName);
			} else if (sel == 5) {
				File.loadData("Client", File.clientFileName);
				File.loadData("Account", File.accountFileName);

			}
		}
	}

}
