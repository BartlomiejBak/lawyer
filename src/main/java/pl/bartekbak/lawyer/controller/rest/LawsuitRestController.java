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
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.spring.data.LawsuitServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/lawsuits")
public class LawsuitRestController {

    LawsuitServiceSpringData lawsuitService;

    @Autowired
    public LawsuitRestController(LawsuitServiceSpringData lawsuitService) {
        this.lawsuitService = lawsuitService;
    }

    @GetMapping
    public List<LawsuitDTO> getAllLawsuits() {
        return lawsuitService.findAllLawsuits();
    }

    @GetMapping("/{lawsuitId}")
    public LawsuitDTO getLawsuit(@PathVariable int lawsuitId) {
        LawsuitDTO lawsuit = lawsuitService.findLawsuitById(lawsuitId);
        if (lawsuit == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return lawsuit;
    }

    @PostMapping
    public LawsuitDTO addLawsuit(@RequestBody LawsuitDTO lawsuit) {
        lawsuit.setLawsuitId(0);
        lawsuitService.saveLawsuit(lawsuit);
        return lawsuit;
    }

    @PutMapping
    public LawsuitDTO updateLawsuit(@RequestBody LawsuitDTO lawsuit) {
        lawsuitService.saveLawsuit(lawsuit);
        return lawsuit;
    }

    @DeleteMapping("/{lawsuitId}")
    public String deleteLawsuit(@PathVariable int lawsuitId) {
        LawsuitDTO lawsuit = lawsuitService.findLawsuitById(lawsuitId);
        if (lawsuit == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        lawsuitService.deleteLawsuitById(lawsuitId);
        return "Lawsuit successfully deleted";
    }
}
