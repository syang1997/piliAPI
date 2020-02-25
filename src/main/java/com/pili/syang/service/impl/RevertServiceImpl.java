package com.pili.syang.service.impl;

import com.pili.syang.entity.Revert;
import com.pili.syang.repository.RevertRepository;
import com.pili.syang.service.RevertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
        public class RevertServiceImpl implements RevertService {

    @Autowired
    private RevertRepository revertRepository;

    @Override
    public boolean addRevert(Revert revert) {
        try {

            Revert save = revertRepository.save(revert);
            if (save==null){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void addPraise(Integer id) {
        Revert byRid = revertRepository.findByRid(id);
        byRid.setPraise(byRid.getPraise()+1);
        revertRepository.save(byRid);
    }
}
