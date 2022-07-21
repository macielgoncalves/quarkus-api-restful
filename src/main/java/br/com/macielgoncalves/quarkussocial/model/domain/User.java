package br.com.macielgoncalves.quarkussocial.model.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
// public class User extends PanacheEntityBase {
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;
}
