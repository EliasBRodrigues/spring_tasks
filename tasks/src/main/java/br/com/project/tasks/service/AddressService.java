package br.com.project.tasks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.project.tasks.core.Address;
import br.com.project.tasks.core.error.CepNotFoundException;
import br.com.project.tasks.data.client.ViaCepClient;
import reactor.core.publisher.Mono;

@Service
public class AddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    private final ViaCepClient viaCepClient;

    public AddressService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public Mono<Address> getAddress(String zipCode){
        return Mono.just(zipCode)
            .doOnNext(it -> LOGGER.info("address to zipcode: {}", zipCode))
            .flatMap(viaCepClient::getAddress)
            .doOnError(it -> Mono.error(CepNotFoundException::new));
    }

}
