package com.whale.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.whale.exception.UserNotExistException;
import com.whale.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web
 * @Description: TODO
 * @date 2019/1/22 0:47
 */
@RequestMapping("user")
//每个方法的路径前面都有一个user 可以抽取出来放到类上 ，spring 会将类上的路径+方法上的路径 作为访问路径
@RestController
public class UserController implements Serializable {


    @GetMapping("/me")
    public Object getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication;
    }

    @GetMapping("/me1")
    public Object getCurrentUser1(Authentication authentication ){
        //spring 会自动找到Authentication类型的数据注入
        return  authentication;
    }

    @GetMapping("/me2")
    public Object getCurrentUser2(@AuthenticationPrincipal UserDetails user){
        return user;
    }


    //当我们的请求路径直接为 “/user” 时 ，如果是get请求就找对应的get方法，如果是post请求就找这个方法
    //此时我们分别有两个方法没有写映射路径，一个是post 一个是get
    @PostMapping
    @ApiOperation("用户创建")
    public User createUser(@Valid @RequestBody User u ,BindingResult errors){

        System.out.println(u.getId());
        System.out.println(u.getUsername());
        System.out.println(u.getPassword());
        System.out.println(u.getBirthday());
        User user = new User();
        user.setId(1);
        return user;
    }



    //@RequestMapping(value = "/user",method = RequestMethod.GET)
    //@GetMapping("user")
    @GetMapping
    public List<User> query(@RequestParam String username){
        //@RequestParam String username  如果请求过来没有username这个参数会返回一个400错误
        System.out.println(username);
        List<User> list = new ArrayList<User>();
        User user = new User();
        list.add(user);
        list.add(user);
        list.add(user);
        return list;
    }
    /**
     * @param idxx
     * @return
     */
    @GetMapping("{id:\\d++}")
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务") //方法的描述
    public User getInfo4( @PathVariable(name = "id") @ApiParam("用户id") String idxx){
        System.out.println("=================");

        //throw new UserNotExistException(idxx);
        //throw  new RuntimeException("user is not exists");
        System.out.println(idxx);
        System.out.println("进入getInfo服务");
        User u = new User();
        u.setUsername("tom");
        return u;
    }


    @PutMapping("{id:\\d++}")
    public User updateUser(@Valid @RequestBody User u, BindingResult errors){

        //如果有错误 循环打印
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error-> {
                FieldError fieldError = (FieldError)error;
                String message = fieldError.getField()+" "+error.getDefaultMessage();
                System.out.println(message);
            });
        }
        System.out.println(u.getId());
        System.out.println(u.getUsername());
        System.out.println(u.getPassword());
        System.out.println(u.getBirthday());
        User user = new User();
        user.setId(1);
        return user;
    }

    @DeleteMapping("{id:\\d++}")
    public void deleteUser(@PathVariable String id){
        System.out.println(id);
    }
    private void test(){

    }


}
