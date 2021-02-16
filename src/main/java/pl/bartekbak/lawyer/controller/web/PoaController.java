package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.service.PoaService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/poas")
public class PoaController {

    private final PoaService poaService;
    private static final String POA_ADD_FORM = "poas/list-poas";

    public PoaController(PoaService poaService) {
        this.poaService = poaService;
    }

    @GetMapping("/list")
    public String listAllPoa(Model model) {
        List<Poa> poaList = poaService.findAllPoa();
        model.addAttribute("poas", poaList);
        return POA_ADD_FORM;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Poa poa = new Poa();
        model.addAttribute("poa", poa);
        return POA_ADD_FORM;
    }

    @GetMapping("/{poaId}/edit")
    public String showFormForUpdate(@PathVariable int poaId, Model model) {
        Poa poa = poaService.findPoaById(poaId);
        model.addAttribute(poa);
        return POA_ADD_FORM;
    }

    @PostMapping("/save")
    public String savePoa(@Valid @ModelAttribute("poaId") Poa poa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return "poas/add-poa-form";
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
