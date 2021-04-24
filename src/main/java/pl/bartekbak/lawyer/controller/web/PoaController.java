package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.dto.PoaDTO;
import pl.bartekbak.lawyer.service.PoaService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/poas")
public class PoaController {

    private final PoaService poaService;
    private static final String POA_ADD_FORM = "poas/add-poa-form";

    public PoaController(PoaService poaService) {
        this.poaService = poaService;
    }

    @GetMapping("/list")
    public String listAllPoa(Model model) {
        List<PoaDTO> poaList = poaService.findAllPoa();
        model.addAttribute("poas", poaList);
        return "poas/list-poas";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        PoaDTO poa = new PoaDTO();
        model.addAttribute("poa", poa);
        return POA_ADD_FORM;
    }

    @GetMapping("/{poaId}/edit")
    public String showFormForUpdate(@PathVariable int poaId, Model model) {
        PoaDTO poa = poaService.findPoaById(poaId);
        model.addAttribute("poa", poa);
        return POA_ADD_FORM;
    }

    @PostMapping("/save")
    public String savePoa(@Valid @ModelAttribute("poaId") PoaDTO poa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return POA_ADD_FORM;
        }
        poaService.savePoa(poa);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String deletePoa(@RequestParam("poaId") int id) {
        poaService.deletePoaById(id);
        return "redirect:list";
    }

    @GetMapping("/{poaId}")
    public ModelAndView showPoa(@PathVariable int poaId) {
        ModelAndView mav = new ModelAndView("poas/poa-details");
        mav.addObject(poaService.findPoaById(poaId));
        return mav;
    }
}
