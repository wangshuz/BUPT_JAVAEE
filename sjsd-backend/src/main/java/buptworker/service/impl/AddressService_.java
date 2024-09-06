package buptworker.service.impl;

import buptworker.entity.Address;
import buptworker.mapper.AddressMapper;
import buptworker.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressService_ implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressesByUserId(Integer id){
        return addressMapper.findAddressesByUserId(id);
    }
    @Override
    public void addAddress(Address address,Integer id)
    {
        if (Boolean.TRUE.equals(address.getIs_default())) {
            // 确保同一时间只有一个默认地址，先将所有的默认地址取消
            addressMapper.clearDefaultAddresses(id);
        }
        addressMapper.insertAddress(address,id);
    }
    @Override
    public void updateAddress(Address address,Integer id){
        if (Boolean.TRUE.equals(address.getIs_default())) {
            // 确保同一时间只有一个默认地址，先将所有的默认地址取消
            addressMapper.clearDefaultAddresses(id);
        }
        // 逻辑删除当前地址
        addressMapper.markAddressAsDeleted(address.getId());
        // 新增修改后的地址
        addressMapper.insertAddress(address,id);
    }
    @Override
    public void deleteAddress(int addressId){
        addressMapper.deleteAddressById(addressId);
    }
}
