package robert.reversi_v5web.api;

public interface HibDBUserDAO {
	public boolean addUser(UserDAO userDao);

	public UserDAO getUser(int userID);

	public UserDAO getUser(String email);

	public UserDAO searchUser(String email);
}
