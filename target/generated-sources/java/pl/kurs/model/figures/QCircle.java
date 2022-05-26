package pl.kurs.model.figures;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCircle is a Querydsl query type for Circle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCircle extends EntityPathBase<Circle> {

    private static final long serialVersionUID = -1582953851L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCircle circle = new QCircle("circle");

    public final pl.kurs.model.QShape _super;

    public final NumberPath<Double> area = createNumber("area", Double.class);

    // inherited
    public final pl.kurs.model.QUser createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    //inherited
    public final NumberPath<Integer> id;

    public final StringPath parameters = createString("parameters");

    //inherited
    public final SimplePath<com.querydsl.core.types.Predicate> predicate;

    public final NumberPath<Double> radius = createNumber("radius", Double.class);

    public final StringPath type = createString("type");

    //inherited
    public final NumberPath<Integer> version;

    public QCircle(String variable) {
        this(Circle.class, forVariable(variable), INITS);
    }

    public QCircle(Path<? extends Circle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCircle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCircle(PathMetadata metadata, PathInits inits) {
        this(Circle.class, metadata, inits);
    }

    public QCircle(Class<? extends Circle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new pl.kurs.model.QShape(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.id = _super.id;
        this.predicate = _super.predicate;
        this.version = _super.version;
    }

}

