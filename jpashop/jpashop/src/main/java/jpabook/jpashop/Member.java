package jpabook.jpashop;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter@Setter
public class Member {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    private String username;

}
