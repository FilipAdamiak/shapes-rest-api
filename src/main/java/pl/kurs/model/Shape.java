package pl.kurs.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.model.interfaces.ShapeFeature;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(exclude = "createdBy")
public abstract class Shape implements ShapeFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private LocalDateTime createdDate;
    @Version
    private int version;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User createdBy;

    public Shape(LocalDateTime createdDate, User createdBy) {
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        createdBy.addShape(this);
    }

    public Shape(){}

}
