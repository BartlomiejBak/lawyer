package pl.bartekbak.lawyer.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.AddressService;

import java.util.List;

@Controller
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
    public String saveAddress(@ModelAttribute("address") Address address) {
        addressService.saveAddress(address);

        //redirect prevents multiple saving
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("addressId") int id) {
        addressService.deleteAddressById(id);
        return "redirect:list";
    }
}
