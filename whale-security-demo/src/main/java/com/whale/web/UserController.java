package com.whale.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.whale.model.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
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
@RestController
public class UserController implements Serializable {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
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

    @RequestMapping(value = "/user1",method = RequestMethod.GET)
    public List<User> query1(User u){
        //ReflectionToStringBuilder.toString   org.apache.commons.lang3的工具类  可以以字符串的形式打印对象
        System.out.println(ReflectionToStringBuilder.toString(u,ToStringStyle.MULTI_LINE_STYLE));
        List<User> list = new ArrayList<User>();
        User user = new User();
        list.add(user);
        list.add(user);
        list.add(user);
        return list;
    }

    @RequestMapping(value = "/user2",method = RequestMethod.GET)
    public List<User> query2(User u, @PageableDefault(page = 0,size = 10) Pageable pageable){
        //ReflectionToStringBuilder.toString   org.apache.commons.lang3的工具类  可以以字符串的形式打印对象
        System.out.println(ReflectionToStringBuilder.toString(u,ToStringStyle.MULTI_LINE_STYLE));
        List<User> list = new ArrayList<User>();
        User user = new User();
        list.add(user);
        list.add(user);
        list.add(user);
        return list;
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping("/user3")
    public User getInfo(String id){
        System.out.println("=================");
        System.out.println(id);
        User u = new User();
        u.setUsername("tom");
        return u;
    }

    /**
     * @param idxx
     * @return
     */
    @RequestMapping("/user4/{id:\\d++}")
    @JsonView(User.UserSimpleView.class)
    public User getInfo4( @PathVariable(name = "id") String idxx){
        System.out.println("=================");
        System.out.println(idxx);
        User u = new User();
        u.setUsername("tom");
        return u;
    }


}
