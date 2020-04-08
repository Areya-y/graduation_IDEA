package com.xmut.project.service.serviceImpl;

import com.xmut.project.dao.wordDetailDao;
import com.xmut.project.entity.wordDetalis;
import com.xmut.project.service.wordDetalisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
@Service
public class wordDetalisServiceImpl implements wordDetalisService {
    @Autowired
    private wordDetailDao wdDao;
    @Override
    public List<wordDetalis> queryWordDetail() {
        List<wordDetalis> wdList=wdDao.queryWordDetail();
        for(wordDetalis wd:wdList){
            System.out.print("iml==============");
            System.out.print(wd.getWordId());
        }
        return wdList;
    }

    @Override
    public wordDetalis queryWordDetailById(int wordId) {
//        wordId=1/0;
        return wdDao.queryWordDetailById(wordId);
    }
    @Transient
    @Override
    public boolean insertWordDetail(wordDetalis wordDetalis) {
        if(wordDetalis.getWord()!=null&&"".equals(wordDetalis.getWord())&&wordDetalis.getSoundMark()!=null&&"".equals(wordDetalis.getSoundMark())){
            try{
                int num=wdDao.insertWordDetail(wordDetalis);
                if(num>0){
                    return true;
                }else {
                    throw new RuntimeException("插入失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("插入失败:"+e.getMessage());
            }
        }
        else{
            throw new RuntimeException("插入不能为空！");
        }
    }

    @Override
    public boolean updateWordDetail(wordDetalis wordDetalis) {
        if(wordDetalis.getWord()!=null&&"".equals(wordDetalis.getWord())&&wordDetalis.getSoundMark()!=null&&"".equals(wordDetalis.getSoundMark())){
            try{
                int num=wdDao.updateWordDetail(wordDetalis);
                if(num>0){
                    return true;
                }else {
                    throw new RuntimeException("更新失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("更新失败:"+e.getMessage());
            }
        }
        else{
            throw new RuntimeException("更新不能为空！");
        }
    }


    @Override
    public boolean deleteWordDetail(int wordId) {
        if(wordId>0){
            try{
                int num=wdDao.deleteWordDetail(wordId);
                if(num>0){
                    return true;
                }else {
                    throw new RuntimeException("删除失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("删除失败:"+e.getMessage());
            }
        }
        else{
            throw new RuntimeException("ID不能为空！");
        }
    }
}

