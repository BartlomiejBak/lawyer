package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.service.LawsuitService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/lawsuits")
public class LawsuitController {

    private LawsuitService lawsuitService;

    @Autowired
    public LawsuitController(LawsuitService lawsuitService) {
        this.lawsuitService = lawsuitService;
    }

    @GetMapping("/list")
    public String listAllLawsuits(Model model) {
        List<Lawsuit> lawsuitList = lawsuitService.findAllLawsuits();
        model.addAttribute("lawsuits", lawsuitList);
        return "lawsuits/list-lawsuits";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Lawsuit lawsuit = new Lawsuit();
        model.addAttribute("lawsuit", lawsuit);
        return "lawsuits/add-lawsuit-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("lawsuitId") int id, Model model) {
        Lawsuit lawsuit = lawsuitService.findLawsuitById(id);
        model.addAttribute(lawsuit);
        return "lawsuits/add-lawsuit-form";
    }

    @PostMapping("/save")
    public String saveLawsuit(@Valid @ModelAttribute("lawsuit") Lawsuit lawsuit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "lawsuits/add-lawsuit-form";
        }
        lawsuitService.saveLawsuit(lawsuit);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("lawsuitId") int id) {
        lawsuitService.deleteLawsuitById(id);
        return "redirect:list";
    }
}
