package buptworker;

import buptworker.mapper.CltMainMapper;
import buptworker.entity.MchIntro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SjsdBackendApplicationTests {

    @Autowired
    private CltMainMapper cltMainMapper;
    @Test
    public void testListMch(){
        List<MchIntro> mchIntroList =  cltMainMapper.listMchIntro();
        mchIntroList.stream().forEach(mchIntro -> {
            System.out.println(mchIntro);
        });
    }

}
