package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
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
    public List<AddressDTO> getAllAddresses() {
        return addressService.findAllAddresses();
    }

    @GetMapping("/id/{addressId}")
    public AddressDTO getAddress(@PathVariable int addressId) {
        AddressDTO agreement = addressService.findAddressById(addressId);
        if (agreement == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return agreement;
    }

    @PostMapping("/register")
    public AddressDTO addAddress(@RequestBody AddressDTO address) {
        address.setAddressId(0);
        addressService.saveAddress(address);
        return address;
    }

    @PutMapping("/register")
    public AddressDTO updateAddress(@RequestBody AddressDTO address) {
        addressService.saveAddress(address);
        return address;
    }

    @DeleteMapping("/remove/{addressId}")
    public String deleteAddress(@PathVariable int addressId) {
        AddressDTO address = addressService.findAddressById(addressId);
        if (address == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        addressService.deleteAddressById(addressId);
        return "Address successfully deleted";
    }
}
