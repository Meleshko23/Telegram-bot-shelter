package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Info;
import pro.sky.telegrambot.model.Pet;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.service.InfoService;

import java.util.Collection;

@RestController
@RequestMapping("info")
public class InfoController {

    private InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @Operation(
            summary = "Добавление питомца в приют",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "питомец добавлен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Pet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если питомец уже находится в приюте"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "новый питомец",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Pet.class)
                            )
                    }
            )
    )
    @PostMapping("pet")
    public ResponseEntity<Pet> addPet(@Parameter( name = "питомец") @RequestBody Pet pet){
        Pet addPet = infoService.addPet(pet);
        return ResponseEntity.ok(addPet);
    }

    @Operation(
            summary = "Добавление волонтера в базу данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "волонтер добавлен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Pet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если волонтер уже находится в базе данных"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "новый волонтер",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Pet.class)
                            )
                    }
            )
    )
    @PostMapping("volunteer")
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer){
        Volunteer addVolunteer = infoService.addVolunteer(volunteer);
        return ResponseEntity.ok(addVolunteer);
    }

    @Operation(
            summary = "Обновление информации в базе данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "информация обновлена",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Pet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если информации нет в базе данных"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "обновление информации",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Pet.class)
                            )
                    }
            )
    )
    @PutMapping
    public ResponseEntity<Info> editInfo(@RequestBody Info info){
        Info editInfo = infoService.editInfo(info);
        return ResponseEntity.ok(editInfo);
    }

    @Operation(
            summary = "Вывести список питомцев",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список питомцев выведен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Pet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если питомцев нет"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "все питомцы",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Pet.class)
                            )
                    }
            )
    )
    @GetMapping("all_pet")
    public ResponseEntity<Collection<Pet>> getAllPet(){
        return ResponseEntity.ok(infoService.getAllPet());
    }

    @Operation(
            summary = "Вывести список волонтеров",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список волонтеров выведен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Pet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если волонтеров нет"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "все волонтеры",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Pet.class)
                            )
                    }
            )
    )
    @GetMapping("all_volunteer")
    public ResponseEntity<Collection<Volunteer>> getAllVolunteer(){
        return ResponseEntity.ok(infoService.getAllVolunteer());
    }

    @Operation(
            summary = "Вывести список информации",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список информации выведен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Pet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если информации нет"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "вся информация",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Pet.class)
                            )
                    }
            )
    )
    @GetMapping("all_info")
    public ResponseEntity<Collection<Info>> getAllInfo(){
        return ResponseEntity.ok(infoService.getAllInfo());
    }
}
