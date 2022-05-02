package user_service.logic;

import user_service.SQLDatabase.ICannabisDAO;
import user_service.SQLDatabase.ICannabisDAO;
import user_service.model.Response;
import user_service.model.Cannabis;

import java.sql.SQLException;

public interface ISearchLogic {

    public Response getCannabis(String username) throws ICannabisDAO.DALException, SQLException;
}
