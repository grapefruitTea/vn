package com.wxd.girl.service;

import com.wxd.girl.dataobject.Girl;
import com.wxd.girl.enums.ResultEnum;
import com.wxd.girl.exception.GirlException;
import com.wxd.girl.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: vn
 * @Date: 2018/6/4 23:38
 * @Description:
 */
@Service
public class GirlService {
    @Autowired
    private GirlRepository repository;

    //发现Hibernate默认创建的表是MyISAM引擎，MyISAM引擎不支持事务操作，
    // 所以@Transaction注解不会起作用；所以我们要做的就是，在配置文件中添加配置，将数据库引擎改为InnoDB
    @Transactional
    public void addGirls() {
        Girl girl = new Girl();
        girl.setCupSize("B");
        girl.setAge(14);

        repository.save(girl);


        Girl girl1 = new Girl();
        girl1.setCupSize("AA");
        girl1.setAge(13);

        repository.save(girl1);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = repository.findById(id).get();
        Integer age = girl.getAge();
        if (age < 10) {
            //你还在上小学
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }
}
