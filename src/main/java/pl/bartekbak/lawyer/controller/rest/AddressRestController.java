package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.spring.data.AddressServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressRestController {

    AddressServiceSpringData addressService;

    @Autowired
    public AddressRestController(AddressServiceSpringData addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/all")
    public List<Address> getAllAddresses() {
        return addressService.findAllAddresses();
    }

    @GetMapping("/id/{addressId}")
    public Address getAddress(@PathVariable int addressId) {
        Address agreement = addressService.findAddressById(addressId);
        if (agreement == null) {
            throw new RuntimeException("No such Id in database");
        }
        return agreement;
    }

    @PostMapping("/register")
    public Address addAddress(@RequestBody Address address) {
        address.setId(0);
        addressService.saveAddress(address);
        return address;
    }

    @PutMapping("/register")
    public Address updateAddress(@RequestBody Address address) {
        addressService.saveAddress(address);
        return address;
    }

    @DeleteMapping("/remove/{addressId}")
    public String deleteAddress(@PathVariable int addressId) {
        Address address = addressService.findAddressById(addressId);
        if (address == null) {
            throw new RuntimeException("No such Id in database");
        }
        addressService.deleteAddressById(addressId);
        return "Address successfully deleted";
    }
}
