package user_service.logic;

import user_service.SQLDatabase.IUserDAO;
import user_service.model.Response;
import user_service.model.User;

import java.sql.SQLException;

public interface IAccountLogic {

    public Response getUser(String username) throws IUserDAO.DALException, SQLException;

    public Response createUser(String username, String email, String password) throws IUserDAO.DALException, SQLException;

    public Response authenticateUser(String username, String password) throws IUserDAO.DALException, SQLException;

    public Response deleteUser(String username, String password) throws IUserDAO.DALException, SQLException;
}
