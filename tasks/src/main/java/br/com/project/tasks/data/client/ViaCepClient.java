package br.com.project.tasks.data.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.project.tasks.core.Address;
import br.com.project.tasks.core.error.CepNotFoundException;
import reactor.core.publisher.Mono;

@Component
public class ViaCepClient {
    private final WebClient webClient;
    private static final String VIA_CEP_URI = "/{cep}/json";

    public ViaCepClient(WebClient viaWebClient) {
        this.webClient = viaWebClient;
    }

    public Mono<Address> getAddress(String zipCode) {
        return webClient
            .get()
            .uri(VIA_CEP_URI, zipCode)
            .retrieve()
            .bodyToMono(Address.class)
            .onErrorResume(error -> Mono.error(CepNotFoundException::new));
    }

}
