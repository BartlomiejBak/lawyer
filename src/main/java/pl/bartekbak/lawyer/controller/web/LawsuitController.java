package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.service.LawsuitService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/lawsuits")
public class LawsuitController {

    private final LawsuitService lawsuitService;
    private static final String LAWSUIT_ADD_FORM = "lawsuits/add-lawsuit-form";

    @Autowired
    public LawsuitController(LawsuitService lawsuitService) {
        this.lawsuitService = lawsuitService;
    }

    @GetMapping("/list")
    public String listAllLawsuits(Model model) {
        List<LawsuitDTO> lawsuitList = lawsuitService.findAllLawsuits();
        model.addAttribute("lawsuits", lawsuitList);
        return "lawsuits/list-lawsuits";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        LawsuitDTO lawsuit = new LawsuitDTO();
        model.addAttribute("lawsuit", lawsuit);
        return LAWSUIT_ADD_FORM;
    }

    @GetMapping("/{lawsuitId}/edit")
    public String showFormForUpdate(@PathVariable UUID lawsuitId, Model model) {
        LawsuitDTO lawsuit = lawsuitService.findLawsuitById(lawsuitId);
        model.addAttribute("lawsuit", lawsuit);
        return LAWSUIT_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveLawsuit(@Valid @ModelAttribute("lawsuit") LawsuitDTO lawsuit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return LAWSUIT_ADD_FORM;
        }
        lawsuitService.saveLawsuit(lawsuit);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("lawsuitId") UUID id) {
        lawsuitService.deleteLawsuitById(id);
        return "redirect:list";
    }

    @GetMapping("/{lawsuitId}")
    public ModelAndView showLawsuit(@PathVariable UUID lawsuitId) {
        ModelAndView mav = new ModelAndView("lawsuits/lawsuit-details");
        mav.addObject(lawsuitService.findLawsuitById(lawsuitId));
        return mav;
    }

}
