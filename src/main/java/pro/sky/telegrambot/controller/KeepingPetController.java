package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.model.KeepingPet;
import pro.sky.telegrambot.model.Pet;
import pro.sky.telegrambot.service.KeepingPetService;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("keeping_pet")
public class KeepingPetController {

    private KeepingPetService keepingPetService;

    public KeepingPetController(KeepingPetService keepingPetService) {
        this.keepingPetService = keepingPetService;
    }

    @Operation(
            summary = "Вывести список отчетов по дате",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список отчетов выведен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = KeepingPet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если отчетов нет"
                    )
            }
    )
    @GetMapping("{date}")
    public ResponseEntity<Collection<KeepingPet>> getAllKeepingPet(@PathVariable LocalDate date){
        return ResponseEntity.ok(keepingPetService.getAllKeepingPet(date));
    }
}
