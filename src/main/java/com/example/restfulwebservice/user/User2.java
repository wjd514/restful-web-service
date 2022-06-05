package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
public class User2 {
    @javax.persistence.Id
    @GeneratedValue
    private Integer Id;

    @Size(min=2, message = "Name은 2글자 이상 입려해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력을 입력해 주세요")
    private String name;
    @Past
    @ApiModelProperty(notes = "사용자의 등록일을 입력을 입력해 주세요")
    private Date joinDate;

//    @JsonIgnore
    @ApiModelProperty(notes = "사용자의 패스워드를 입력을 입력해 주세요")
    private String password;
//    @JsonIgnore
    @ApiModelProperty(notes = "사용자의 주민번호를을 입력을 입력해 주세요")
    private  String ssn;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Post> post;


    public User2(int id, String name, Date joinDate, String password, String ssn) {
        this.Id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
