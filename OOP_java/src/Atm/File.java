package Atm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class File {

	static String clientFileName = "src/ATM/clientData.txt";
	// clientNo/id/pw/name
	// clientNo/id/pw/name
	// ...

	static String accountFileName = "src/ATM/accountData.txt";
	// clientNo/accountNumber/money
	// clientNo/accountNumber/money
	// ...

	static void saveData(String Val, String path) {
		FileWriter fw = null;
		String data = "";
		try {
			fw = new FileWriter(path);
			if (Val.equals("Client")) {
				for (Client c : ClientDAO.getcList()) {
					data += c.clientNo + "/" + c.id + "/" + c.pw + "/" + c.name + "\n";
				}
			}
			if (Val.equals("Account")) {
				for (Account a : AccountDAO.getaccList()) {
					data += a.clientNo + "/" + a.accountNumber + "/" + a.money + "\n";
				}
			}
			data = data.substring(0, data.length() - 1);
			fw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[ " + path + " ] 저장 완료");
	}

	static void loadData(String Val, String path) {
		FileReader fr = null;
		String data = "";
		int read = 0;
		try {
			fr = new FileReader(path);
			while (true) {
				read = fr.read();
				if (read != -1) {
					data += (char) read;
				} else {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (Val.equals("Client")) {
			ArrayList<Client> cList = ClientDAO.getcList();
			String[] temp1 = data.split("\n");
			for (String t : temp1) {
				String[] temp2 = t.split("/");
				cList.add(new Client(Integer.parseInt(temp2[0]), temp2[1], temp2[2], temp2[3]));
			}
		}
		if (Val.equals("Account")) {
			ArrayList<Account> accList = AccountDAO.getaccList();
			String[] temp1 = data.split("\n");
			for (String t : temp1) {
				String[] temp2 = t.split("/");
				accList.add(new Account(Integer.parseInt(temp2[0]), temp2[1], Integer.parseInt(temp2[2])));

			}
		}
		System.out.println("[ " + path + " ] 로드 완료");
	}

}
