package com.wxd.girl.controller;

import com.wxd.girl.dataobject.Result;
import com.wxd.girl.properties.GirlProperties;
import com.wxd.girl.repository.GirlRepository;
import com.wxd.girl.service.GirlService;
import com.wxd.girl.dataobject.Girl;
import com.wxd.girl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: vn
 * @Date: 2018/6/3 18:14
 * @Description:
 */
@RestController
public class GirlController {
    private final static Logger log = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlProperties girlProperties;

    @Autowired
    private GirlRepository repository;


    @Autowired
    private GirlService girlService;


    @RequestMapping(value = "/hello/{id}/{name}", method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id, @PathVariable("name") String str) {

        return "id = " + id + " ,name = " + str;
//        return girlProperties.getCupSize();

    }

    /**
     * @auther: vn
     * @date: 2018/6/3 22:29
     * @Description: ?id=xx
     * @param:
     * @return:
     */
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping(value = "/hello")
    //设置是否是必传参数及默认值
    public String say1(@RequestParam(value = "id", required = false, defaultValue = "0") Integer code) {

        return "id = " + code;
//        return girlProperties.getCupSize();

    }


    @PostMapping(value = "/girls")
    public Result<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

//            result.setData(bindingResult.getFieldError().getDefaultMessage());
//            log.error(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }

        girl.setAge(girl.getAge());
        girl.setCupSize(girl.getCupSize());

        return ResultUtil.success(repository.save(girl));
    }

    @GetMapping(value = "/girls")
    public List<Girl> findGirls(){
        System.out.println("findGirls");
        int a = 8/0;
        System.out.println("3333333333333");
        return repository.findAll();
    }

    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        //jpa 版本导致不能直接使用findOne方法
        return repository.findById(id).get();
    }

    @PutMapping(value = "/girls/{id}")
    public Girl updateGirl(@PathVariable("id") Integer id, @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        girl.setId(id);
        return repository.save(girl);
    }


    @DeleteMapping(value = "/girls/{id}")
    public void deleteGirl(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    }

    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age) {
        return repository.findByAge(age);
    }


    @PostMapping(value = "/girls/addtwo")
    public void addTwo() {
        girlService.addGirls();
    }

    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
            girlService.getAge(id);
    }

}
