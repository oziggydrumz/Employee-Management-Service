package exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@AllArgsConstructor
public class EmployeeException extends Throwable {
    private  String message;
    private HttpStatus httpStatus;

    @Override
    public String getMessage(){
        return this.message;

    }
}
