package buptworker.service;

import buptworker.entity.Address;

import java.util.List;

public interface AddressService {
    public List<Address> getAddressesByUserId(Integer id);
    public void addAddress(Address address,Integer id);
    public void updateAddress(Address address,Integer id);
    public void deleteAddress(int addressId);

}
