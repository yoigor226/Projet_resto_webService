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

import com.resto.demo.model.Commande;
import com.resto.demo.model.Menu;
import com.resto.demo.service.CommandeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;


    @Operation(summary = "Recuperer la Commande", description = "Renvoi la liste de toutes les commandes disponible")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation reussi",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Aucune commande trouve")
    })
    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }


    @Operation(summary = "Recuperer une Commande specifique", description = "Permet de recuperer une Commande specifique en passant sont ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Commande recuperer avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Erreur: Cette Commande n'exite pas")
    })
    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }


    @Operation(summary = "Creer Commande", description = "Permet de creer une nouvelle Commande et l'enregistrer dans la base de donnee")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Commande crrer avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "400", description = "Erreur lors de la creation de la Commande")
    })
    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.saveCommande(commande);
    }


    @Operation(summary = "Modifier Commande", description = "Permet de modifier une Commande existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Commande modifier avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Erreur lors de la modification de la Commande")
    })
    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        Commande commande = commandeService.getCommandeById(id);
        if (commande != null) {
            // Mettez à jour les champs de commande avec commandeDetails
            return commandeService.saveCommande(commande);
        }
        return null;
    }


    @Operation(summary = "Supprimer Menu", description = "Permet de supprimer une commande grace a son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Menu supprimer avec succes"),
        @ApiResponse(responseCode = "404", description = "Erreur lors de la suppression de la commande")
    })
    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }
}

