package com.xmut.project.dao;

import com.xmut.project.entity.wordDetalis;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名大小升序执行
class wordDetailDaoTest {
    //通过spring容器注入Dao的实现类
    @Autowired
    private wordDetailDao wordDetailDao;
    @Test
    @Ignore
    void queryWordDetail() {
        List<wordDetalis> wordDetailList=wordDetailDao.queryWordDetail();
        for(wordDetalis wd:wordDetailList){
            System.out.println("Dao:");
            System.out.print(wd.getWordId());

        }

        // 验证预期值和实际值是否相符
        assertEquals( 20,wordDetailList.size());
    }

    @Test
    void queryWordDetailById() {
        wordDetalis wd = wordDetailDao.queryWordDetailById(10003);
        System.out.print(wd.getNoun());
        assertEquals("n.年；年度；年龄；学年",wd.getNoun());
    }

    @Test
    void insertWordDetail() {
        //创建一个区域对象
        wordDetalis wd = new wordDetalis();
        wd.setWord("word");
        wd.setSoundMark("wɜːrd");
        //将该对象实例添加入库
        int effectedNum = wordDetailDao.insertWordDetail(wd);
        //检测影响行数
        assertEquals(1, effectedNum);
        //校验总数是否+1
        List<wordDetalis> areaList = wordDetailDao.queryWordDetail();
        assertEquals(218, areaList.size());
    }

    @Test
    void updateWordDetail() {
        wordDetalis wd = new wordDetalis();
        wd.setWordId(10221);
        wd.setWord("development ");
        wd.setSoundMark("dɪ'veləpmənt");
        wd.setNoun("n.发展；开发；生长；进展；[摄]显影；新开发区");
        wd.setInflexion("形容词: developmental 副词: developmentally");
        System.out.print("==="+wd.toString());
        int num=wordDetailDao.updateWordDetail(wd);
        assertEquals(1,num);
    }

    @Test
    void deleteWordDetail() {
        int num=wordDetailDao.deleteWordDetail(10221);
        assertEquals(1,num);
    }
}