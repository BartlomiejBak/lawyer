package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.spring.data.CourtServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/court")
public class CourtRestController {
    CourtServiceSpringData service;

    @Autowired
    public CourtRestController(CourtServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Court> getAllCourts() {
        return service.findAllCourts();
    }

    @GetMapping("/id/{courtId}")
    public Court getCourt(@PathVariable int courtId) {
        Court court = service.findCourtById(courtId);
        if (court == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return court;
    }

    @PostMapping("/register")
    public Court addCourt(@RequestBody Court court) {
        court.setCourtId(0);
        service.saveCourt(court);
        return court;
    }

    @PutMapping("/register")
    public Court updateCourt(@RequestBody Court court) {
        service.saveCourt(court);
        return court;
    }

    @DeleteMapping("/remove/{courtId}")
    public String deleteCourt(@PathVariable int courtId) {
        Court court = service.findCourtById(courtId);
        if (court == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        service.deleteCourtById(courtId);
        return "Court successfully deleted";
    }

}
