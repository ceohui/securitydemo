package com.whale.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.whale.validator.MyConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = -3379923885183732446L;


    public interface UserSimpleView{};

    /**
     * 用户详情视图继承简单视图  包含了简单视图中的内容
     */
    public interface UserDetailView extends UserSimpleView{};

    private Integer id;

    @MyConstraint(message = "这是一个validate测试")
    @JsonView(UserSimpleView.class)
    @ApiModelProperty("用户名称")
    private String username;   //username展示在简单视图

    @NotBlank(message = "密码不能为空")
    @JsonView(UserDetailView.class)
    private  String password; // 同理 这个展示在详情视图  但由于接口的继承关系 username 也会展示在详情视图

    @Past(message = "生日必须为过去时间")   // 过去时间
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
