package pl.bartekbak.lawyer.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.service.EventService;

import java.util.List;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private EventService service;

    @GetMapping("/list")
    public String listAllEvents(Model model) {
        List<Event> eventList = service.findAllCourts();
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
    public String saveEvent(@ModelAttribute("event") Event event) {
        service.saveEvent(event);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("eventId") int id) {
        service.deleteEventById(id);
        return "redirect:list";
    }
}
