package buptworker.service;

import buptworker.entity.MchIntro;
import buptworker.entity.MchType;
import buptworker.entity.TopPicture;

import java.util.List;

public interface CltMainService {

    public List<MchIntro> listMchIntro();

    public List<MchType> listMchType();

    public List<TopPicture> listTopPicture(int cltId);

}
