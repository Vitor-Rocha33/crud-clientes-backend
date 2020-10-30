package com.desafio.desafiodevjr.services;

import com.desafio.desafiodevjr.domain.Adress;
import com.desafio.desafiodevjr.domain.Client;
import com.desafio.desafiodevjr.dto.ClientDTO;
import com.desafio.desafiodevjr.dto.NewClientDTO;
import com.desafio.desafiodevjr.repository.AdressRepository;
import com.desafio.desafiodevjr.repository.ClientRepository;

import com.desafio.desafiodevjr.services.exceptions.DataIntegrityException;
import com.desafio.desafiodevjr.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private AdressRepository adressRepository;

    public Client find(Integer id) {
        Optional <Client> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
    }
    public Client update(Client obj) {
        Client newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public Client insert(Client obj) {
        obj.setId(null);
        obj = repo.save(obj);
        adressRepository.saveAll(obj.getAdresses());
        return obj;
    }

    public void delete (Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        }catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
        }
    }

    public Client fromDTO(ClientDTO objDto) {
        return new Client(objDto.getId(), null, objDto.getName());
    }

    public Client fromDTO(NewClientDTO objDto) {
        Client cli = new Client(null, objDto.getCpf(), objDto.getName());
        Adress ad = new Adress(null, objDto.getCep(), objDto.getLogradouro(), objDto.getBairro(), objDto.getCidade(), objDto.getUf(), objDto.getComplemento(), cli);
        cli.getAdresses().add(ad);
        cli.getPhones().add(objDto.getPhone1());
        cli.getEmail().add(objDto.getEmail1());

        if (objDto.getEmail2() != null) {
            cli.getEmail().add(objDto.getEmail2());
        }
        if (objDto.getEmail3() != null) {
            cli.getEmail().add(objDto.getEmail3());
        }

        if (objDto.getPhone2() != null) {
            cli.getPhones().add(objDto.getPhone2());
        }
        if (objDto.getPhone3() != null) {
            cli.getPhones().add(objDto.getPhone3());
        }
        return cli;
    }

    private void updateData (Client newObj, Client obj) {
        newObj.setName(obj.getName());
    }

    public List<Client> findAll() {
        return repo.findAll();
    }

}
