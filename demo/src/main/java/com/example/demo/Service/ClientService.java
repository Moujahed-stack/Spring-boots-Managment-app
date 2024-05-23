package com.example.demo.Service;

import com.example.demo.model.client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public client createClient(client client) {
        return clientRepository.save(client);
    }

    public client updateClient(Long id, client clientDetails) {
        client client = clientRepository.findById(id).orElseThrow();
        client.setName(clientDetails.getName());
        client.setLastName(clientDetails.getLastName());
        client.setMobile(clientDetails.getMobile());
        return clientRepository.save(client);
    }
}
