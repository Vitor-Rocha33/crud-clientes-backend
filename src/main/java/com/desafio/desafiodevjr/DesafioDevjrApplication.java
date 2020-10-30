package com.desafio.desafiodevjr;

import com.desafio.desafiodevjr.domain.Adress;
import com.desafio.desafiodevjr.domain.Client;
import com.desafio.desafiodevjr.repository.AdressRepository;
import com.desafio.desafiodevjr.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class DesafioDevjrApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AdressRepository adressRepository;

    public static void main(String[] args) {
        SpringApplication.run(DesafioDevjrApplication.class, args);
    }

    public void run(String... args) throws Exception {
        Client cli1 = new Client(null, "36378912377", "Joao Teste");

        cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
        cli1.getEmail().addAll(Arrays.asList("email1@email.com", "email2@email.com"));

        Adress a1 = new Adress(null, "38220834", "Rua flores", "Jardim", "Goiania", "GO",  null, cli1);

        cli1.getAdresses().addAll(Arrays.asList(a1));

        clientRepository.saveAll(Arrays.asList(cli1));
        adressRepository.saveAll(Arrays.asList(a1));
    }
}
