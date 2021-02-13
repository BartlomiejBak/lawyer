package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.service.PoaService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/poas")
public class PoaController {

    private PoaService service;

    public PoaController(PoaService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listAllPoa(Model model) {
        List<Poa> poaList = service.findAllPoa();
        model.addAttribute("poas", poaList);
        return "poas/list-poas";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Poa poa = new Poa();
        model.addAttribute("poa", poa);
        return "poas/add-poa-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("poaId") int id, Model model) {
        Poa poa = service.findPoaById(id);
        model.addAttribute(poa);
        return "poas/add-poa-form";
    }

    @PostMapping("/save")
    public String savePoa(@Valid @ModelAttribute("poaId") Poa poa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "poas/add-poa-form";
        }
        service.savePoa(poa);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String deletePoa(@RequestParam("poaId") int id) {
        service.deletePoaById(id);
        return "redirect:list";
    }
}
