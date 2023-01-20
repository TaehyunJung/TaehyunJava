package Atm;

public class Account {
	int clientNo;
	String accountNumber;
	int money;

	public Account(int clientNo, String accountNumber, int money) {
		super();
		this.clientNo = clientNo;
		this.accountNumber = accountNumber;
		this.money = money;
	}

	@Override
	public String toString() {
		return "계좌번호 = " + accountNumber + ", 잔액 = " + money + "원\n";
	}

}
