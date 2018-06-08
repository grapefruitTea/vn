package com.wxd.girl.repository;

import com.wxd.girl.dataobject.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: vn
 * @Date: 2018/6/4 23:01
 * @Description:
 */
public interface GirlRepository extends JpaRepository<Girl,Integer> {

    public List<Girl> findByAge(Integer age);
}
