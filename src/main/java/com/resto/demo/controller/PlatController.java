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

import com.resto.demo.model.Plat;
import com.resto.demo.service.PlatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/plats")
public class PlatController {

    @Autowired
    private PlatService platService;

    @Operation(summary = "getAllPlats", description = "Renvoie la liste de tous les plats disponible")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation reuissi",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Plat.class))),
        @ApiResponse(responseCode = "404", description = "Aucun plat trouver")
    })
    @GetMapping
    public List<Plat> getAllPlats() {
        return platService.getAllPlats();
    }


    @Operation(summary = "Get a plat by ID", description = "Recupere un plat specifique")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation reussi",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Plat.class))),
        @ApiResponse(responseCode = "404", description = "Ce plat n'exite pas")
    })
    @GetMapping("/{id}")
    public Plat getPlatById(@PathVariable Long id) {
        return platService.getPlatById(id);
    }

    @Operation(summary = "Ajouter Plats", description = "Ajoute un nouveau plat dans la base de donnee")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Plat ajouter avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Plat.class))),
        @ApiResponse(responseCode = "400", description = "Erreur lors de l'ajout du plat")
    })
    @PostMapping
    public Plat createPlat(@RequestBody Plat plat) {
        return platService.savePlat(plat);
    }


    @Operation(summary = "Modifier un plat", description = "Permet d'apporter des modification sur un plat existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plat modifier avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Plat.class))),
        @ApiResponse(responseCode = "404", description = "Erreur de modification du plat")
    })
    @PutMapping("/{id}")
    public Plat updatePlat(@PathVariable Long id, @RequestBody Plat platDetails) {
        Plat plat = platService.getPlatById(id);
        if (plat != null) {
            // Mettez à jour les champs de plat avec platDetails
            return platService.savePlat(plat);
        }
        return null;
    }


    @Operation(summary = "Supprimer un plat", description = "Permet de supprimer un plat specifique en passant son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Plat supprimer avec succes"),
        @ApiResponse(responseCode = "404", description = "Erreur de suppression: Ce plat n'existe pas")
    })
    @DeleteMapping("/{id}")
    public void deletePlat(@PathVariable Long id) {
        platService.deletePlat(id);
    }
}

