package com.example.demo.Controller;

import com.example.demo.model.client;
import com.example.demo.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public client createClient(@RequestBody client client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<client> updateClient(@PathVariable Long id, @RequestBody client clientDetails) {
        return ResponseEntity.ok(clientService.updateClient(id, clientDetails));
    }
}
