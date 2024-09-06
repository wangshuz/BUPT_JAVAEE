package buptworker.service.impl;

import buptworker.entity.PresentName;
import buptworker.mapper.PresentNameMapper;
import buptworker.service.PresentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PresentNameService_ implements PresentNameService {
    @Autowired
    private PresentNameMapper presentNameMapper;

    @Override
    public PresentName ListMchPresentName(Integer id) {
        return presentNameMapper.listMchPresentName(id);
    }

    @Override
    public PresentName ListCltPresentName(Integer id) {
        return presentNameMapper.listCltPresentName(id);
    }
}
