package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.CourtDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.spring.data.CourtServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
public class CourtRestController {
    CourtServiceSpringData service;

    @Autowired
    public CourtRestController(CourtServiceSpringData service) {
        this.service = service;
    }

    @GetMapping
    public List<CourtDTO> getAllCourts() {
        return service.findAllCourts();
    }

    @GetMapping("/{courtId}")
    public CourtDTO getCourt(@PathVariable int courtId) {
        CourtDTO court = service.findCourtById(courtId);
        if (court == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return court;
    }

    @PostMapping
    public CourtDTO addCourt(@RequestBody CourtDTO court) {
        court.setCourtId(0);
        service.saveCourt(court);
        return court;
    }

    @PutMapping
    public CourtDTO updateCourt(@RequestBody CourtDTO court) {
        service.saveCourt(court);
        return court;
    }

    @DeleteMapping("/{courtId}")
    public String deleteCourt(@PathVariable int courtId) {
        CourtDTO court = service.findCourtById(courtId);
        if (court == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        service.deleteCourtById(courtId);
        return "Court successfully deleted";
    }

}
