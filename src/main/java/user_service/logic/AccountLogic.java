package user_service.logic;

import user_service.SQLDatabase.IUserDAO;
import user_service.SQLDatabase.UserDAO;
import user_service.model.Response;
import user_service.model.User;

import java.sql.SQLException;

public class AccountLogic implements IAccountLogic {

    IUserDAO dao = new UserDAO();

    public Response getUser(String username) throws IUserDAO.DALException, SQLException {
        //Make a call to the DAO, to get the user from the database
        User responseUser = dao.getUser(username);
        //Make a call to the (static) Response class, to create a response.
        return Response.setResult(200, "User Gotten.", responseUser.toJSONObject());
    }

    public Response createUser(String username, String email, String password) throws IUserDAO.DALException, SQLException {
        //Make a call to the DAO, to create the user.
        dao.createUser(email, username, password);
        User responseUser = new User(username, email);
        return Response.setResult(200, "User Created.", responseUser.toJSONObject());
    }

    public Response authenticateUser(String username, String password) throws IUserDAO.DALException, SQLException {
        //Make a call to the DAO, to authenticate the user.
        dao.authenticateUser(username, password);
        return Response.setResult(200, "User Authenticated.");
    }

    public Response deleteUser(String username, String password) throws IUserDAO.DALException, SQLException {
        //Make a call to the DAO, to delete the user.
        dao.deleteUser(username, password);
        return Response.setResult(200, "User Deleted");
    }
}
