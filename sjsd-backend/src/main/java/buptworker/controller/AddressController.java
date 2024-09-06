package buptworker.controller;

import buptworker.entity.Address;
import buptworker.entity.Result;
import buptworker.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/get")
    public Result getAddresses( Integer userId) {
        List<Address> addresses = addressService.getAddressesByUserId(userId);
        return Result.success(addresses);
    }

    @PostMapping("/add")
    public Result addAddress(@RequestBody Address address, @RequestParam Integer userId) {
        addressService.addAddress(address, userId);
        return Result.success("Address added successfully");
    }

    @PostMapping("/update")
    public Result updateAddress(@RequestBody Address address, @RequestParam Integer userId) {
        addressService.updateAddress(address, userId);
        return Result.success("Address updated successfully");
    }

    @PostMapping("/delete")
    public Result deleteAddress(@RequestParam Integer addressId) {
        addressService.deleteAddress(addressId);
        return Result.success("Address deleted successfully");
    }
}
