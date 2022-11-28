package pro.sky.telegrambot.controller;

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

    @PostMapping("pet")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet){
        Pet addPet = infoService.addPet(pet);
        return ResponseEntity.ok(addPet);
    }

    @PostMapping("volunteer")
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer){
        Volunteer addVolunteer = infoService.addVolunteer(volunteer);
        return ResponseEntity.ok(addVolunteer);
    }

    @PutMapping
    public ResponseEntity<Info> editInfo(@RequestBody Info info){
        Info editInfo = infoService.editInfo(info);
        return ResponseEntity.ok(editInfo);
    }

    @GetMapping("all_pet")
    public ResponseEntity<Collection<Pet>> getAllPet(){
        return ResponseEntity.ok(infoService.getAllPet());
    }

    @GetMapping("all_volunteer")
    public ResponseEntity<Collection<Volunteer>> getAllVolunteer(){
        return ResponseEntity.ok(infoService.getAllVolunteer());
    }

    @GetMapping("all_info")
    public ResponseEntity<Collection<Info>> getAllInfo(){
        return ResponseEntity.ok(infoService.getAllInfo());
    }
}
