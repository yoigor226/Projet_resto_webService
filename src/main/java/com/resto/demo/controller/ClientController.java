package com.resto.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.demo.model.Client;
import com.resto.demo.model.Menu;
import com.resto.demo.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @Operation(summary = "Recuperer Client", description = "Renvoi la liste de toutes les Clients disponible")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation reussi",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Aucun Client trouve")
    })
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }


    @Operation(summary = "Recuperer un client specifique", description = "Permet de recuperer un client specifique en passant sont ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Client recuperer avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Erreur: Cet client n'exite pas")
    })
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }


    @Operation(summary = "Creer Client", description = "Permet de creer une nouveau client et l'enregistrer dans la base de donnee")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Client crrer avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "400", description = "Erreur lors de la creation du client")
    })
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }


    @Operation(summary = "Modifier Client", description = "Permet de modifier un Client existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Client modifier avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Erreur lors de la modification du Client")
    })
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            // Mettez à jour les champs de client avec clientDetails
            return clientService.saveClient(client);
        }
        return null;
    }


    @Operation(summary = "Supprimer Client", description = "Permet de supprimer un client grace a son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Client supprimer avec succes"),
        @ApiResponse(responseCode = "404", description = "Erreur lors de la suppression du client")
    })
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}

