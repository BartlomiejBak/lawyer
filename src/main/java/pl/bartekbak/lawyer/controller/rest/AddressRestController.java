package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.spring.data.AddressServiceSpringData;

import java.util.List;

@RestController
public class AddressRestController {

    @Autowired
    AddressServiceSpringData addressService;

    @GetMapping("/address")
    public List<Address> getAllAddresses() {
        return addressService.findAllAddresses();
    }
}
