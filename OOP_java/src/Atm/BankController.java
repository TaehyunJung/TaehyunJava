package Atm;

public class BankController {
	private static int log = -1;

	public void setSampleData() {
		String clientData = "test01/1111/김철수\n";
		clientData += "test02/2222/이영희\n";
		clientData += "test03/3333/신민수\n";
		clientData += "test04/4444/최상민";

		String accountdata = "test01/1111-1111-1111/8000\n";
		accountdata += "test02/2222-2222-2222/5000\n";
		accountdata += "test01/3333-3333-3333/11000\n";
		accountdata += "test03/4444-4444-4444/9000\n";
		accountdata += "test01/5555-5555-5555/5400\n";
		accountdata += "test02/6666-6666-6666/1000\n";
		accountdata += "test03/7777-7777-7777/1000\n";
		accountdata += "test04/8888-8888-8888/1000";
		// 1) test01 김철수는 계좌를 3개 가지고있다.
		// 2) test02 이영희는 계좌를 2개 가지고있다.
		// 3) test03 신민수는 계좌를 2개 가지고있다.
		// 4) test04 최상민은 계좌를 1개 가지고있다.

		ClientDAO.dataSetting(clientData);
		AccountDAO.dataSetting(accountdata);
	}

	void run() {
		setSampleData();
		while (true) {
			Administrator.mainMenu();
			int sel = Util.getVal("메뉴 입력", 0, 2);

			if (sel == 0) {
				System.out.println("[ ATM 종료 ]");
				break;
			}

			if (sel == 1) {
				Administrator.AdminRun();
			} else if (sel == 2) {
				Administrator.userMenu();
				sel = Util.getVal("메뉴 입력", 0, 2);

				if (sel == 0) {
					continue;
				}

				if (sel == 1) {
					ClientDAO.join();
				} else if (sel == 2) {
					log = ClientDAO.logIn();
					if (log == -1) {
						continue;
					}
					Login.loginRun(log);
					log = -1;
				}
			}
		}
	}
}
