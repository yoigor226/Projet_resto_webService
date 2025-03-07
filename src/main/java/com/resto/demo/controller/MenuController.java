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

import com.resto.demo.model.Menu;
import com.resto.demo.service.MenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @Operation(summary = "Recuperer le Menu", description = "Renvoi la liste de tous les menus disponible")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation reussi",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Aucun menu trouve")
    })
    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }


    @Operation(summary = "Recuperer un menu specifique", description = "Permet de recuperer un menu specifique en passant sont ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Menu recuperer avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Erreur: Ce Menu n'exite pas")
    })
    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }

    @Operation(summary = "Creer Menu", description = "Permet de creer un nouveau menu et l'enregistrer dans la base de donnee")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Menu crrer avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "400", description = "Erreur lors de la creation du menu")
    })
    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }


    @Operation(summary = "Modifier Menu", description = "Permet de modifier un menu existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Menu modifier avec succes",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Menu.class))),
        @ApiResponse(responseCode = "404", description = "Erreur lors de la modification du menu")
    })
    @PutMapping("/{id}")
    public Menu updateMenu(@PathVariable Long id, @RequestBody Menu menuDetails) {
        Menu menu = menuService.getMenuById(id);
        if (menu != null) {
            // Mettez à jour les champs de menu avec menuDetails
            return menuService.saveMenu(menu);
        }
        return null;
    }


    @Operation(summary = "Supprimer Menu", description = "Permet de supprimer un menu grace a son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Menu supprimer avec succes"),
        @ApiResponse(responseCode = "404", description = "Erreur lors de la suppression du menu")
    })
    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }
}

