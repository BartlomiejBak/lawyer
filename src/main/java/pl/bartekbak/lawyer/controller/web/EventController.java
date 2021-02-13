package pl.bartekbak.lawyer.controller.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.service.EventService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/events")
public class EventController {

    private EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listAllEvents(Model model) {
        List<Event> eventList = service.findAllEvents();
        model.addAttribute("events", eventList);
        return "events/list-events";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "events/add-event-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("eventId") int id, Model model) {
        Event event = service.findEventById(id);
        model.addAttribute(event);
        return "events/add-event-form";
    }

    @PostMapping("/save")
    public String saveEvent(@Valid @ModelAttribute("event") Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "events/add-event-form";
        }

        service.saveEvent(event);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("eventId") int id) {
        service.deleteEventById(id);
        return "redirect:list";
    }
}
