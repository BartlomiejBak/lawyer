package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.service.CourtService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/courts")
public class CourtController {

    private CourtService service;

    @Autowired
    public CourtController(CourtService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listAllContacts(Model model) {
        List<Court> courtList = service.findAllCourts();
        model.addAttribute("courts", courtList);
        return "courts/list-courts";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Court court = new Court();
        model.addAttribute("court", court);
        return "courts/add-court-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("courtId") int id, Model model) {
        Court court = service.findCourtById(id);
        model.addAttribute(court);
        return "courts/add-court-form";
    }

    @PostMapping("/save")
    public String saveCourt(@Valid @ModelAttribute("court") Court court, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "courts/add-court-form";
        }
        service.saveCourt(court);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("courtId") int id) {
        service.deleteCourtById(id);
        return "redirect:list";
    }

}
