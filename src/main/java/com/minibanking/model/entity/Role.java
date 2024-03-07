package com.minibanking.model.entity;

import com.minibanking.model.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import static com.minibanking.util.EntityConstant.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PREFIX + ROLE)

public class Role extends AbstractEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
