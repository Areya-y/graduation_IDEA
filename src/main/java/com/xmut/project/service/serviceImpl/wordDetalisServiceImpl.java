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
        return wdList;
    }

    @Override
    public List<wordDetalis> searchWord(String word) {
        return wdDao.searchWord(word);
    }

    @Transient
    @Override
    public boolean insertWordDetail(wordDetalis wordDetalis) {
        if(wordDetalis.getWord()==null&&"".equals(wordDetalis.getWord())&&wordDetalis.getSoundMark()==null&&"".equals(wordDetalis.getSoundMark())){
            throw new RuntimeException("插入不能为空！");
        }
        else{
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
    }

    @Override
    public boolean updateWordDetail(wordDetalis wordDetalis) {
        if(wordDetalis.getWord()==null&&"".equals(wordDetalis.getWord())&&wordDetalis.getSoundMark()==null&&"".equals(wordDetalis.getSoundMark())){
            throw new RuntimeException("更新不能为空！");
        }
        else{
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

