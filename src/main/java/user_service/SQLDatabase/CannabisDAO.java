package user_service.SQLDatabase;


import user_service.model.Cannabis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CannabisDAO implements ICannabisDAO {

    DBConnector connector = new DBConnector();







    @Override
    public Cannabis getMultipleCannabis(String CannabisName) throws DALException, SQLException {

        Cannabis Cannabis = new Cannabis();
        Connection connection = connector.connectToRemoteDB();
        PreparedStatement getCannabis = connection.prepareStatement("SELECT CannabisName, THCRation, CBGRatio, CBDratio FROM Cannabis WHERE CannabisName = ?");
        getCannabis.setString(1, CannabisName);
        ResultSet rs = getCannabis.executeQuery();
        if (rs.next()) {
            //dont know if proper way to do it
            while(rs.next()) {
                int i = 1;
                Cannabis.setCannabisName(rs.getString(i));
                //Need a join to put side effects in from side effect table
                Cannabis.setTHCRatio(rs.getFloat(i));
                Cannabis.setCBDRatio(rs.getFloat(i));
                Cannabis.setCBGRatio(rs.getFloat(i));
            }
        } else {
            throw new UnknownCannabisException(CannabisName);
        }
        return Cannabis;
    }

    @Override
    public Cannabis getCannabis(String CannabisName) throws DALException, SQLException {

        Cannabis Cannabis = new Cannabis();
        Connection connection = connector.connectToRemoteDB();
        PreparedStatement getCannabis = connection.prepareStatement("SELECT CannabisName, THCRation, CBGRatio, CBDratio FROM Cannabis WHERE CannabisName = ?");
        getCannabis.setString(1, CannabisName);
        ResultSet rs = getCannabis.executeQuery();
        if (rs.next()) {
                Cannabis.setCannabisName(rs.getString(1));
                //Need a join to put side effects in from side effect table
                Cannabis.setTHCRatio(rs.getFloat(1));
                Cannabis.setCBDRatio(rs.getFloat(1));
                Cannabis.setCBGRatio(rs.getFloat(1));
            }
        else {
            throw new UnknownCannabisException(CannabisName);
        }
        return Cannabis;
    }




/*
    @Override
    public void createUser(String email, String username, String password) throws DALException, SQLException {

        //Hash password
        String salt = passwords.createSalt();
        String newPassword = password + salt;
        String hashedPassword = passwords.createHash(newPassword.toCharArray());

        Connection connection = connector.connectToRemoteDB();

        //Check to see if user already exists in database. If so, throw DALException.
        PreparedStatement checkUser = connection.prepareStatement("SELECT username FROM users WHERE username = ?");
        checkUser.setString(1, username);
        ResultSet rs = checkUser.executeQuery();
        if (rs.next()) {
            throw new UserExistsException(username);
        }


        //Add user to database.
        PreparedStatement addUser = connection.prepareStatement("INSERT INTO users (username, email, password, salt)" +
                " VALUES (?, ?, ?, ?);");
        addUser.setString(1, username);
        addUser.setString(2, email);
        addUser.setString(3, hashedPassword);
        addUser.setString(4, salt);
        addUser.execute();
    }

    @Override
    public User getUser(String username) throws DALException, SQLException {

        User user = new User();
        Connection connection = connector.connectToRemoteDB();
        PreparedStatement getUser = connection.prepareStatement("SELECT username, email FROM users WHERE username = ?");
        getUser.setString(1, username);
        ResultSet rs = getUser.executeQuery();
        if (rs.next()) {
            user.setUsername(rs.getString(1));
            user.setEmail(rs.getString(2));
        } else {
            throw new UnknownUserException(username);
        }
        return user;
    }

    @Override
    public Boolean authenticateUser(String username, String inputPass) throws DALException, SQLException {
        Boolean result = false;

        Connection connection = connector.connectToRemoteDB();
        result = authenticate(username, inputPass, connection);
        if (!result) throw new WrongPasswordException(username);
        return true;
    }

    private boolean authenticate(String username, String inputPass, Connection connection) throws DALException, SQLException {
        boolean result = false;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT password, salt FROM users WHERE username = ?");
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        String dbHash;
        char[] psChar;
        if (rs.next()) {
            dbHash = rs.getString(1);
            String dbSalt = rs.getString(2);
            String ps = inputPass + dbSalt;
            psChar = new char[ps.length()];
            for (int i = 0; i < ps.length(); i++) {
                psChar[i] = ps.charAt(i);
            }
        } else {
            throw new WrongPasswordException(username);
        }

        //Verify passwords
        Argon2 argon2 = Argon2Factory.create();
        result = argon2.verify(dbHash, psChar);
        return result;
    }


    @Override
    public Boolean deleteUser(String username, String password) throws DALException, SQLException {
        Connection connection = connector.connectToRemoteDB();
        if (authenticate(username, password, connection)) {
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM users WHERE username = ?");
            deleteUser.setString(1, username);
            deleteUser.execute();
            return true;
        } else {
            throw new WrongPasswordException(username);
        }
    }
    */

}
