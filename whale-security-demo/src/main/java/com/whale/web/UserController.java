package com.whale.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.whale.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    //当我们的请求路径直接为 “/user” 时 ，如果是get请求就找对应的get方法，如果是post请求就找这个方法
    //此时我们分别有两个方法没有写映射路径，一个是post 一个是get
    @PostMapping
    public User createUser(@Valid @RequestBody User u){

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
    public User getInfo4( @PathVariable(name = "id") String idxx){
        System.out.println("=================");
        System.out.println(idxx);
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
    private void test(){

    }


}
