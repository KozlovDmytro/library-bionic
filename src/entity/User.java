package entity;

public class User {
	private int id;
	private String login;
	private String pass;
	private int access;
	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}

	public int getAccess() {
		return access;
	}

	public void setId(int userId) {
		id = userId;
	}

	public void setLogin(String userLogin) {
		login = userLogin;
	}

	public void setPass(String userPass) {
		pass = userPass;
	}

	public void setAccess(int UserAccess) {
		access = UserAccess;
	}

}
