package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "number_id")
    private Long id;

    private String name;

    @Embedded   // 안전하게 두 군데 다 씀
    private Address address;

    @OneToMany(mappedBy = "member") // mapping 된 거울. 읽기 전용(?)
    private List<Order> orders = new ArrayList<>(); // 이 모양 변경하지 말 것

}
