package user_service.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_service.SQLDatabase.IUserDAO;
import user_service.logic.AccountLogic;
import user_service.logic.IAccountLogic;
import user_service.model.Response;
import user_service.model.User;

import java.sql.SQLException;

@RestController
//@Order(1)
@RequestMapping("/user-service")
public class UserServiceRestController {

    IAccountLogic accountLogic = new AccountLogic();
    Response response = new Response();

    @GetMapping("/get-user")
    public ResponseEntity<String> getUser(@RequestBody String jsonString) throws IUserDAO.DALException, SQLException {
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        response = accountLogic.getUser(username);
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody String jsonString) throws IUserDAO.DALException, SQLException {
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        response = accountLogic.createUser(username, email, password);
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    @GetMapping("/authenticate-user")
    public ResponseEntity<String> authenticateUser(@RequestBody String jsonString) throws IUserDAO.DALException, SQLException {
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        response = accountLogic.authenticateUser(username, password);
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody String jsonString) throws IUserDAO.DALException, SQLException {
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        response = accountLogic.deleteUser(username, password);
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    @ExceptionHandler(IUserDAO.DALException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDALException(IUserDAO.DALException dalException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dalException.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleSQLException(SQLException sqlException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sqlException.getMessage());
    }
}
