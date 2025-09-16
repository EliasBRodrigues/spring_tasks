package br.com.project.tasks.core.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer status;
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
    }

    public static ErrorResponse internalError(RuntimeException exception) {
        return ErrorResponse
            .builder()
            .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .withMessage(exception.getMessage()).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builderFrom(ErrorResponse errorResponse){
        return new Builder(errorResponse);
    }

    public static class Builder {
        private Integer status;
        private String message;

        public Builder(ErrorResponse errorResponse) {
            this.status = errorResponse.status;
            this.message = errorResponse.message;
        }

        public Builder() {}

        public Builder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

}
