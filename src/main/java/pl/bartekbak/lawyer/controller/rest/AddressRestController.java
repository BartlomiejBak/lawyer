package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.spring.data.AddressServiceSpringData;

import java.util.List;

@RestController
public class AddressRestController {

    AddressServiceSpringData addressService;

    @Autowired
    public AddressRestController(AddressServiceSpringData addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public List<Address> getAllAddresses() {
        return addressService.findAllAddresses();
    }

    @GetMapping("/addresses/{addressId}")
    public Address getAgreement(@PathVariable int addressId) {
        Address agreement = addressService.findAddressById(addressId);
        if (agreement == null) {
            throw new RuntimeException("No such Id in database");
        }
        return agreement;
    }

    @PostMapping("/addresses")
    public Address addAddress(@RequestBody Address address) {
        address.setId(0);
        addressService.saveAddress(address);
        return address;
    }

    @PutMapping("/addresses")
    public Address updateAddress(@RequestBody Address address) {
        addressService.saveAddress(address);
        return address;
    }

    @DeleteMapping("addresses/{addressId}")
    public String deleteAddress(@PathVariable int addressId) {
        Address address = addressService.findAddressById(addressId);
        if (address == null) {
            throw new RuntimeException("No such Id in database");
        }
        addressService.deleteAddressById(addressId);
        return "Address successfully deleted";
    }
}
