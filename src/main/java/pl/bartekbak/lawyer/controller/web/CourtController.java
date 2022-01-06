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
import pl.bartekbak.lawyer.dto.CourtDTO;
import pl.bartekbak.lawyer.service.CourtService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/courts")
public class CourtController {

    private final CourtService courtService;
    private static final String COURT_ADD_FORM = "courts/add-court-form";

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/list")
    public String listAllContacts(Model model) {
        List<CourtDTO> courtList = courtService.findAllCourts();
        model.addAttribute("courts", courtList);
        return "courts/list-courts";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        CourtDTO court = new CourtDTO();
        model.addAttribute("court", court);
        return COURT_ADD_FORM;
    }

    @GetMapping("/{courtId}/edit")
    public String showFormForUpdate(@PathVariable UUID courtId, Model model) {
        CourtDTO court = courtService.findCourtById(courtId);
        model.addAttribute("court", court);
        return COURT_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveCourt(@Valid @ModelAttribute("court") CourtDTO court, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return COURT_ADD_FORM;
        }
        courtService.saveCourt(court);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("courtId") UUID id) {
        courtService.deleteCourtById(id);
        return "redirect:list";
    }

    @GetMapping("/{courtId}")
    public ModelAndView showCourt(@PathVariable UUID courtId) {
        ModelAndView mav = new ModelAndView("courts/court-details");
        mav.addObject(courtService.findCourtById(courtId));
        return mav;
    }

}
