package user_service.controller;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_service.SQLDatabase.IUserDAO;
import user_service.logic.AccountLogic;
import user_service.logic.IAccountLogic;
import user_service.model.Response;
import user_service.rabbitMQ.ConfigureRabbitMQ;
import user_service.rabbitMQ.RabbitLog;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/account-service")
public class UserServiceRestController {

    IAccountLogic accountLogic = new AccountLogic();
    Response response = new Response();
    private final RabbitTemplate rabbitTemplate;
    private RabbitLog rabbitLog;

    public UserServiceRestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/get-user")
    public ResponseEntity<String> getUser(@RequestBody String jsonString) throws IUserDAO.DALException, SQLException {
        //Create the rabbitLog.
        rabbitLog = new RabbitLog("get-user", "GET", LocalDate.now().toString(), LocalTime.now().toString(), jsonString);
        //Parse jsonObject
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        //Send to logic controller
        response = accountLogic.getUser(username);
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
        //Complete rabbitLog and send it to rabbitMQ to be picked up by network logger.
        rabbitLog.setOutput(response.toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(false);
        //Send the rabbitlog (using helper function).
        sendRabbitLog();
        //And finally return.
        return responseEntity;
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody String jsonString) throws IUserDAO.DALException, SQLException {
        //Create the rabbitLog.
        rabbitLog = new RabbitLog("create-user", "POST", LocalDate.now().toString(), LocalTime.now().toString(), jsonString);
        //Parse jsonObject
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        //Send to logic controller
        response = accountLogic.createUser(username, email, password);
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
        //Complete rabbitLog and send it to rabbitMQ to be picked up by network logger.
        rabbitLog.setOutput(response.toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(false);
        //Send the rabbitlog (using helper function).
        sendRabbitLog();
        //And finally return.
        return responseEntity;
    }

    @PostMapping("/authenticate-user")
    public ResponseEntity<String> authenticateUser(@RequestBody String jsonString) throws IUserDAO.DALException, SQLException {
        //Create the rabbitLog.
        rabbitLog = new RabbitLog("authenticate-user", "GET", LocalDate.now().toString(), LocalTime.now().toString(), jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        response = accountLogic.authenticateUser(username, password);
        //Complete rabbitLog and send it to rabbitMQ to be picked up by network logger.
        rabbitLog.setOutput(response.toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(false);
        //Send the rabbitlog (using helper function).
        sendRabbitLog();
        //And finally return.
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    @PostMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody String jsonString) throws IUserDAO.DALException, SQLException {
        //Create the rabbitLog.
        rabbitLog = new RabbitLog("delete-user", "DELETE", LocalDate.now().toString(), LocalTime.now().toString(), jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        response = accountLogic.deleteUser(username, password);
        //Complete rabbitLog and send it to rabbitMQ to be picked up by network logger.
        rabbitLog.setOutput(response.toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(false);
        //Send the rabbitlog (using helper function).
        sendRabbitLog();
        //And finally return.
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    @ExceptionHandler(IUserDAO.DALException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDALException(IUserDAO.DALException dalException) {
        rabbitLog.setOutput(dalException.getResponse().toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(true);
        sendRabbitLog();
        return ResponseEntity.status(HttpStatus.OK).body(dalException.getResponse().toJSONString());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleSQLException(SQLException sqlException) {

        Response response = new Response(404, true, sqlException.getMessage());
        rabbitLog.setOutput(response.toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(true);
        sendRabbitLog();
        return ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
    }

    public void sendRabbitLog(){
        try {
            rabbitTemplate.convertAndSend(ConfigureRabbitMQ.EXCHANGE_NAME,
                    "logger.message", rabbitLog.toJson().toString());
        } catch (Exception e) {
            System.out.println("Failed to send RabbitMQ activity-log properly.");
        }
    }
}
