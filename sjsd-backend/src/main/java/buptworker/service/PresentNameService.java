package buptworker.service;

import buptworker.entity.PresentName;

import buptworker.entity.MchIntro;
import buptworker.entity.MchType;

import java.util.List;
public interface PresentNameService {
    public PresentName ListCltPresentName(Integer id);
    public PresentName ListMchPresentName(Integer id);
}
