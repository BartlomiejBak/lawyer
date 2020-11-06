package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Case;
import pl.bartekbak.lawyer.service.spring.data.CaseServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CaseRestController {

    CaseServiceSpringData caseService;

    @Autowired
    public CaseRestController(CaseServiceSpringData caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/cases")
    public List<Case> getAllCases() {
        return caseService.findAllCases();
    }

    @GetMapping("/cases/{caseId}")
    public Case getCase(@PathVariable int caseId) {
        Case aCase = caseService.findCaseById(caseId);
        if (aCase == null) {
            throw new RuntimeException("No such Id in database");
        }
        return aCase;
    }

    @PostMapping("/cases")
    public Case addCase(@RequestBody Case aCase) {
        aCase.setId(0);
        caseService.saveCase(aCase);
        return aCase;
    }

    @PutMapping("/cases")
    public Case updateCase(@RequestBody Case aCase) {
        caseService.saveCase(aCase);
        return aCase;
    }

    @DeleteMapping("cases/{caseId}")
    public String deleteCase(@PathVariable int caseId) {
        Case aCase = caseService.findCaseById(caseId);
        if (aCase == null) {
            throw new RuntimeException("No such Id in database");
        }
        caseService.deleteCaseById(caseId);
        return "Case successfully deleted";
    }
}
