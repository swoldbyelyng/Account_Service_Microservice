package user_service.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_service.SQLDatabase.ICannabisDAO;
import user_service.SQLDatabase.ICannabisDAO;
import user_service.logic.SearchLogic;
import user_service.logic.ISearchLogic;
import user_service.model.Response;
import user_service.model.Cannabis;

import java.sql.SQLException;

@RestController
//@Order(1)
@RequestMapping("/Cannabis-service")
public class CannabisServiceRestController {

    ISearchLogic SearchLogic = new SearchLogic();
    Response response = new Response();

    @GetMapping("/get-Cannabis")
    public ResponseEntity<String> getCannabis(@RequestBody String jsonString) throws ICannabisDAO.DALException, SQLException {
        JSONObject jsonObject = new JSONObject(jsonString);
        String CannabisName = jsonObject.getString("CannabisName");
        response = SearchLogic.getCannabis(CannabisName);
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    /*
    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody String jsonString) throws ICannabisDAO.DALException, SQLException {
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        response = accountLogic.createUser(username, email, password);
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    @GetMapping("/authenticate-user")
    public ResponseEntity<String> authenticateUser(@RequestBody String jsonString) throws ICannabisDAO.DALException, SQLException {
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        response = accountLogic.authenticateUser(username, password);
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody String jsonString) throws ICannabisDAO.DALException, SQLException {
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        response = accountLogic.deleteUser(username, password);
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }
*/
    @ExceptionHandler(ICannabisDAO.DALException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDALException(ICannabisDAO.DALException dalException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dalException.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleSQLException(SQLException sqlException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sqlException.getMessage());
    }
}
