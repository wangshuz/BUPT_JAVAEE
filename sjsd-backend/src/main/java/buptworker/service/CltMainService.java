package buptworker.service;

import buptworker.pojo.MchIntro;
import buptworker.pojo.MchType;

import java.util.List;

public interface CltMainService {

    public List<MchIntro> listMchIntro();

    public List<MchType> listMchType();

}
