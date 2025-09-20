package br.com.project.tasks.core.error;

public class CepNotFoundException extends RuntimeException {
    public CepNotFoundException() {
        super("CEP not found");
    }
}
