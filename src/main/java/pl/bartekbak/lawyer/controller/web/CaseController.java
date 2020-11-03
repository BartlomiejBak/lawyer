package pl.bartekbak.lawyer.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Case;
import pl.bartekbak.lawyer.service.CaseService;

import java.util.List;

@Controller
@RequestMapping("/cases")
public class CaseController {

    private CaseService caseService;

    @Autowired
    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/list")
    public String listAllCases(Model model) {
        List<Case> caseList = caseService.findAllCases();
        model.addAttribute("cases", caseList);
        return "cases/list-cases";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Case aCase = new Case();
        model.addAttribute("cases", aCase);
        return "cases/add-case-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("caseId") int id, Model model) {
        Case aCase = caseService.findCaseById(id);
        model.addAttribute(aCase);
        return "cases/add-case-form";
    }

    @PostMapping("/save")
    public String saveCase(@ModelAttribute("case") Case aCase) {
        caseService.saveCase(aCase);

        //redirect prevents multiple saving
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("caseId") int id) {
        caseService.deleteCaseById(id);
        return "redirect:list";
    }
}
