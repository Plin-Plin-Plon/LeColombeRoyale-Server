package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author paulo
 */
@Entity(name = "Role")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idRole;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    public Role() {
    }
}
