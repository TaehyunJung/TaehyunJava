package Atm;

public class Login {
	static void loginMenu(int log) {
		System.out.printf("[ %s ]\n", ClientDAO.getClientName(log));
		System.out.println("[1] 계좌 추가");
		System.out.println("[2] 계좌 삭제");
		System.out.println("[3] 입    금");
		System.out.println("[4] 출    금");
		System.out.println("[5] 이    체");
		System.out.println("[6] 탈    퇴");
		System.out.println("[7] 마이페이지");
		System.out.println("[0] 로그아웃");
	}

	static void loginRun(int log) {
		while (true) {
			loginMenu(log);
			int sel = Util.getVal("메뉴 입력", 0, 7);

			if (sel == 0) {
				break;
			}

			if (sel == 1) {
				AccountDAO.addAcc(log);
			} else if (sel == 2) {
				AccountDAO.delAcc(log);
			} else if (sel == 3) {
				AccountDAO.deposit(log);
			} else if (sel == 4) {
				AccountDAO.withdraw(log);
			} else if (sel == 5) {
				AccountDAO.transfer(log);
			} else if (sel == 6) {
				log = ClientDAO.quit(log);
				break;
			} else if (sel == 7) {
				AccountDAO.myPage(log);
			}
		}
	}
}
