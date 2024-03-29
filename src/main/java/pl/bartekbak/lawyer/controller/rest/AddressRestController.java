package pl.bartekbak.lawyer.controller.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.jooq.AddressServiceJooq;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
public class AddressRestController {

    private final AddressServiceJooq addressService;

    public AddressRestController(AddressServiceJooq addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.findAllAddresses();
    }

    @GetMapping("/{addressId}")
    public AddressDTO getAddress(@PathVariable UUID addressId) {
        AddressDTO address = addressService.findAddressById(addressId);
        if (address == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return address;
    }

    @PostMapping
    public AddressDTO addAddress(@RequestBody AddressDTO address) {
        address.setAddressId(UUID.randomUUID());
        addressService.saveAddress(address);
        return address;
    }

    @PutMapping
    public AddressDTO updateAddress(@RequestBody AddressDTO address) {
        addressService.saveAddress(address);
        return address;
    }

    @DeleteMapping("/{addressId}")
    public String deleteAddress(@PathVariable UUID addressId) {
        AddressDTO address = addressService.findAddressById(addressId);
        if (address == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        addressService.deleteAddressById(addressId);
        return "Address successfully deleted";
    }
}
