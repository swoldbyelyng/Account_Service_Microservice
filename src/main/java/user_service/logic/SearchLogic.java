package user_service.logic;

import user_service.SQLDatabase.CannabisDAO;
import user_service.SQLDatabase.ICannabisDAO;
import user_service.model.Cannabis;
import user_service.model.Response;

import java.sql.SQLException;

public class SearchLogic implements ISearchLogic {

    ICannabisDAO dao = new CannabisDAO();

    public Response getCannabis(String CannabisName) throws ICannabisDAO.DALException, SQLException {
        //Make a call to the DAO, to get the user from the database
        Cannabis responseCannabis = dao.getCannabis(CannabisName);
        //Make a call to the (static) Response class, to create a response.
        return Response.setResult(200, "Cannabis Gotten.", responseCannabis.toJSONObject());
    }


}




