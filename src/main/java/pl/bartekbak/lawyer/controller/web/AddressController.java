package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.AddressService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/addresses")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/list")
    public String listAddresses(Model model) {
        List<Address> addressList = addressService.findAllAddresses();
        model.addAttribute("addresses", addressList);
        return "addresses/list-addresses";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Address address = new Address();
        model.addAttribute("address", address);
        return "addresses/add-address-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("addressId") int id, Model model) {
        Address address = addressService.findAddressById(id);
        model.addAttribute(address);
        return "addresses/add-address-form";
    }

    @PostMapping("/save")
    public String saveAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "addresses/add-address-form";
        }

        addressService.saveAddress(address);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("addressId") int id) {
        addressService.deleteAddressById(id);
        return "redirect:list";
    }
}
