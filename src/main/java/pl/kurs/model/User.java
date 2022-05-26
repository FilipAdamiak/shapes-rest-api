package pl.kurs.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "shapes")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "UC_USER_EMAIL")})
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private boolean deleted;
    @Version
    private int version;
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private Set<Shape> shapes = new HashSet<>();

    @Builder
    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public User() {

    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
