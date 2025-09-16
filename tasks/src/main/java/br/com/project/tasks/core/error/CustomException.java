// package br.com.project.tasks.core.error;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;

// import br.com.project.tasks.core.model.ErrorResponse;
// import reactor.core.publisher.Mono;

// @ControllerAdvice
// public class CustomException {
    
//     @ExceptionHandler(RuntimeException.class)
//     public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
//         return Mono
//             .just(exception)
//             .map(ErrorResponse::internalError)
//             .map(error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error))
//             .block();
//     }
// }
