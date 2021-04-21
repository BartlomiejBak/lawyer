package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
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
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.service.EventService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private static final String EVENT_ADD_FORM = "events/add-event-form";

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list")
    public String listAllEvents(Model model) {
        List<EventDTO> eventList = eventService.findAllEvents();
        model.addAttribute("events", eventList);
        return "events/list-events";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        EventDTO event = new EventDTO();
        model.addAttribute("event", event);
        return EVENT_ADD_FORM;
    }

    @GetMapping("/{eventId}/edit")
    public String showFormForUpdate(@PathVariable int eventId, Model model) {
        EventDTO event = eventService.findEventById(eventId);
        model.addAttribute("event", event);
        return EVENT_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveEvent(@Valid @ModelAttribute("event") EventDTO event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return EVENT_ADD_FORM;
        }

        eventService.saveEvent(event);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("eventId") int id) {
        eventService.deleteEventById(id);
        return "redirect:list";
    }

    @GetMapping("/{eventId}")
    public ModelAndView showEvent(@PathVariable int eventId) {
        ModelAndView mav = new ModelAndView("events/event-details");
        mav.addObject(eventService.findEventById(eventId));
        return mav;
    }
}
