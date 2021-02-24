package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.service.spring.data.LawsuitServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/lawsuit")
public class LawsuitRestController {

    LawsuitServiceSpringData lawsuitService;

    @Autowired
    public LawsuitRestController(LawsuitServiceSpringData lawsuitService) {
        this.lawsuitService = lawsuitService;
    }

    @GetMapping("/all")
    public List<Lawsuit> getAllLawsuits() {
        return lawsuitService.findAllLawsuits();
    }

    @GetMapping("/id/{lawsuitId}")
    public Lawsuit getLawsuit(@PathVariable int lawsuitId) {
        Lawsuit lawsuit = lawsuitService.findLawsuitById(lawsuitId);
        if (lawsuit == null) {
            throw new RuntimeException("No such Id in database");
        }
        return lawsuit;
    }

    @PostMapping("/register")
    public Lawsuit addLawsuit(@RequestBody Lawsuit lawsuit) {
        lawsuit.setLawsuitId(0);
        lawsuitService.saveLawsuit(lawsuit);
        return lawsuit;
    }

    @PutMapping("/register")
    public Lawsuit updateLawsuit(@RequestBody Lawsuit lawsuit) {
        lawsuitService.saveLawsuit(lawsuit);
        return lawsuit;
    }

    @DeleteMapping("/remove/{lawsuitId}")
    public String deleteLawsuit(@PathVariable int lawsuitId) {
        Lawsuit lawsuit = lawsuitService.findLawsuitById(lawsuitId);
        if (lawsuit == null) {
            throw new RuntimeException("No such Id in database");
        }
        lawsuitService.deleteLawsuitById(lawsuitId);
        return "Lawsuit successfully deleted";
    }
}
