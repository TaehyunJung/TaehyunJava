package Atm;

import java.util.ArrayList;

public class ClientDAO {
	private static ArrayList<Client> clientList = new ArrayList<>();
	private static int clientNum = 1000;

	static ArrayList<Client> getcList() {
		return clientList;
	}

	static String getClientName(int log) {
		return clientList.get(log).name;
	}

	static void dataSetting(String data) {
		String[] dataArr = data.split("\n");
		for (String d : dataArr) {
			String[] temp = d.split("/");
			clientList.add(new Client(++clientNum, temp[0], temp[1], temp[2]));
		}
	}

	static void printClient() {
		int idx = 0;
		System.out.println("[ 회 원 정 보 ]");
		for (Client c : clientList) {
			System.out.println(c);
			AccountDAO.printAccPage(idx);
			idx++;
		}
	}

	static int retIdx(String id) {
		for (int i = 0; i < clientList.size(); i++) {
			if (id.equals(clientList.get(i).id)) {
				return i;
			}
		}
		return -1;
	}

	static boolean isValidPw(String pw, int idx) {
		if (clientList.get(idx).pw.equals(pw))
			return true;
		return false;
	}

	static void join() {
		String id = Util.getVal("ID");
		if (retIdx(id) != -1) {
			System.err.println("중복된 ID");
			return;
		}
		clientList.add(new Client(++clientNum, id, Util.getVal("PW"), Util.getVal("이름")));
	}

	static int quit(int log) {
		String pw = Util.getVal("PW");
		if (!pw.equals(clientList.get(log).pw)) {
			System.err.println("비밀번호 오류");
			return log;
		}
		clientList.remove(log);
		System.out.println("[ 탈 퇴 완 료 ]");
		return -1;
	}

	static int logIn() {
		String id = Util.getVal("ID");
		int idx = retIdx(id);
		if (idx == -1) {
			System.err.println("존재하지 않는 ID");
			return -1;
		}

		String pw = Util.getVal("PW");
		if (!isValidPw(pw, idx)) {
			System.err.println("Pw 불일치");
			return -1;
		}
		System.out.printf("[ 로그인 성공! %s님 반갑습니다 ]\n", clientList.get(idx).name);
		return idx;

	}

}
