package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")    // dtype의 default 값
@Getter
@Setter
public class Book extends Item {

    private String author;
    private String isbn;

}
