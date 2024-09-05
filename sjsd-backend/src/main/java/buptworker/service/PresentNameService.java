package buptworker.service;

import buptworker.entity.PresentName;

import buptworker.entity.MchIntro;
import buptworker.entity.MchType;

import java.util.List;
public interface PresentNameService {
    public List<PresentName> ListCltPresentName(Integer id);
    public List<PresentName> ListMchPresentName(Integer id);
}
