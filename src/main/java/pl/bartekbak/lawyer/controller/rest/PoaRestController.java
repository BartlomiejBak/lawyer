package pl.bartekbak.lawyer.controller.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.PoaDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.jooq.PoaServiceJooq;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/poas")
public class PoaRestController {

    private final PoaServiceJooq service;

    public PoaRestController(PoaServiceJooq service) {
        this.service = service;
    }

    @GetMapping
    public List<PoaDTO> getAllPoas() {
        return service.findAllPoa();
    }

    @GetMapping("/{poaId}")
    public PoaDTO getPoa(@PathVariable UUID poaId) {
        PoaDTO poa = service.findPoaById(poaId);
        if (poa == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return poa;
    }

    @PostMapping
    public PoaDTO addPoa(@RequestBody PoaDTO poa) {
        poa.setPoaId(UUID.randomUUID());
        service.savePoa(poa);
        return poa;
    }

    @PutMapping
    public PoaDTO updatePoa(@RequestBody PoaDTO poa) {
        service.savePoa(poa);
        return poa;
    }

    @DeleteMapping("/{poaId}")
    public String deletePoa(@PathVariable UUID poaId) {
        PoaDTO poa = service.findPoaById(poaId);
        if (poa == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        service.deletePoaById(poaId);
        return "Poa successfully deleted";
    }
}
