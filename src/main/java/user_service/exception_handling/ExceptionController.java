package user_service.exception_handling;

import org.json.HTTP;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import user_service.SQLDatabase.ICannabisDAO;


@ControllerAdvice
//@Order(2)
public class ExceptionController extends ResponseEntityExceptionHandler {

/*
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<String> handleEmptyField(EmptyFieldException emptyFieldException){
        return new ResponseEntity<String>(emptyFieldException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DALException.class)
    public ResponseEntity<String> handleDALException(DALException dalException){
        return new ResponseEntity<String>(dalException.getMessage(), HttpStatus.BAD_REQUEST);
    }
 */
}
