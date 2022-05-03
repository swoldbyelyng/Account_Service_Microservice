package user_service.SQLDatabase;

import user_service.model.Response;
import user_service.model.User;

import java.sql.SQLException;

public interface IUserDAO {

    void createUser(String email, String username, String password) throws DALException, SQLException;

    User getUser(String username) throws DALException, SQLException;

    Boolean authenticateUser(String username, String password) throws DALException, SQLException;

    Boolean deleteUser(String username, String password) throws DALException, SQLException;


    // ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    // Exceptions

    class DALException extends Exception {
        private Response response;

        public DALException(String msg, Response response) {
            super(msg);
            this.response = response;
        }

        public Response getResponse(){
            return  response;
        }
    }

    class WrongPasswordException extends DALException {

        public WrongPasswordException(String username) {
            super("Wrong password for user '" + username + "'", new Response(404, true, "Wrong password for user '" + username + "'"));
        }
    }


    class UserExistsException extends DALException {

        public UserExistsException(String username) {
            super("User: '" + username + "' already exists", new Response(404, true, "User: '" + username + "' already exists."));
        }
    }


    class UnknownUserException extends DALException {

        public UnknownUserException(String username) {
            super("Unknown user '" + username + "'", new Response(404, true, "Unknown user '" + username + "'"));
        }
    }
}
