package RestaurantApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestValidationException extends RuntimeException {
    private final BindingResult bindingResult;
    public RestValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
    public BindingResult getBindingResult() {
        return this.bindingResult;
    }
}
