package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.constant.StatusTrialPeriod;
import pro.sky.telegrambot.exception.OwnerNotFoundException;
import pro.sky.telegrambot.model.CatOwner;
import pro.sky.telegrambot.model.DogOwner;
import pro.sky.telegrambot.model.Pet;
import pro.sky.telegrambot.service.PetOwnerService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("pet_owner")
public class PetOwnerController {

    private final PetOwnerService petOwnerService;

    public PetOwnerController(PetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @Operation(
            summary = "Добавление владельца кошек в БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "владелец кошек добавлен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Pet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если владелец кошек уже добавлен"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "новый владелец кошек",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DogOwner.class)
                            )
                    }
            )
    )
    @PostMapping("cat_owner")
    public ResponseEntity<CatOwner> addCatOwner(@RequestBody CatOwner catOwner){
        CatOwner addCatOwner = petOwnerService.addCatOwner(catOwner);
        return ResponseEntity.status(HttpStatus.CREATED).body(addCatOwner);
    }

    @Operation(
            summary = "Добавление владельца собак в БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "владелец собак добавлен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Pet.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если владелец собак уже добавлен"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "новый владелец собак",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DogOwner.class)
                            )
                    }
            )
    )
    @PostMapping("dog_owner")
    public ResponseEntity<DogOwner> addDogOwner(@RequestBody DogOwner dogOwner){
        DogOwner addDogOwner = petOwnerService.addDogOwner(dogOwner);
        return ResponseEntity.status(HttpStatus.CREATED).body(addDogOwner);
    }

    @Operation(
            summary = "Вывести список всех владельцев кошек",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список владельцев кошек выведен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = CatOwner.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если владельцев кошек нет"
                    )
            }
    )
    @GetMapping("all_cat_owner")
    public ResponseEntity<Collection<CatOwner>> getAllCatOwner(){
        return ResponseEntity.ok(petOwnerService.allCatOwner());
    }

    @Operation(
            summary = "Вывести список всех владельцев собак",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список владельцев собак выведен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = DogOwner.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если владельцев собак нет"
                    )
            }
    )
    @GetMapping("all_dog_owner")
    public ResponseEntity<Collection<DogOwner>> getAllDogOwner(){
        return ResponseEntity.ok(petOwnerService.allDogOwner());
    }


    @Operation(
            summary = "Вывести список владельцев собак с завершившимся испытательным периодом",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список владельцев собак выведен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = DogOwner.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если владельцев собак нет"
                    )
            }
    )
    @GetMapping("dog/end_trial_period")
    public ResponseEntity<List<DogOwner>> getDogOwnersEndTrialPeriod() {
        List<DogOwner> dogOwners = petOwnerService.getDogOwnersEndTrialPeriod();
        if (dogOwners.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dogOwners);
    }

    @Operation(
            summary = "Вывести список владельцев кошек с завершенным тестовым периодом",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список владельцев кошек выведен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = DogOwner.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если владельцев кошек нет"
                    )
            }
    )
    @GetMapping("cat/end_trial_period")
    public ResponseEntity<List<CatOwner>> getCatOwnersEndTrialPeriod() {
        List<CatOwner> catOwners = petOwnerService.getCatOwnersEndTrialPeriod();
        if (catOwners.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(catOwners);
    }

    @Operation(
            summary = "Изменить статус и дату окончания испытательного периода для владельца кошки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Обновленный владелец кошки",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = DogOwner.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если по указанному id владелец кошки не найден"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если в запросе указан некорректный параметр"
                    )
            }
    )
    @PatchMapping("/cat/{ownerId}")
    public ResponseEntity<CatOwner> changeStatusTrialPeriodCat(@PathVariable @Parameter(description = "Иденификатор владельца") long ownerId,
                                                               @RequestParam(name = "new_STP") @Parameter(description = "Новый статус для испытательного периода") StatusTrialPeriod stp) {
        CatOwner catOwner;
        try {
            catOwner = petOwnerService.changeStatusTrialPeriodCat(ownerId, stp);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(catOwner);
    }

    @Operation(
            summary = "Изменить статус и дату окончания испытательного периода для владельца собаки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Обновленный владелец собаки",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = DogOwner.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если по указанному id владелец собаки не найден"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если в запросе указан некорректный параметр"
                    )
            }
    )
    @PatchMapping("/dog/{ownerId}")
    public ResponseEntity<DogOwner> changeStatusTrialPeriodDog(@PathVariable @Parameter(description = "Иденификатор владельца") Long ownerId,
                                                               @RequestParam(name = "new_STP") @Parameter(description = "Новый статус для испытательного периода") StatusTrialPeriod stp) {
        DogOwner dogOwner;
        try {
            dogOwner = petOwnerService.changeStatusTrialPeriodDog(ownerId, stp);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dogOwner);
    }
}
