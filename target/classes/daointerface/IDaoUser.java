package daointerface;
import model.User;

public interface IDaoUser {
	public boolean insert( User user) throws Exception;
	public User  getUserByNick(String nickInput) throws Exception;
	public User  getUserByEmail(String correoElectronico) throws Exception;
	public boolean update(User user) throws Exception;
}
