package pl.kurs.model.figures;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTriangle is a Querydsl query type for Triangle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTriangle extends EntityPathBase<Triangle> {

    private static final long serialVersionUID = -2099069315L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTriangle triangle = new QTriangle("triangle");

    public final pl.kurs.model.QShape _super;

    public final NumberPath<Double> area = createNumber("area", Double.class);

    public final NumberPath<Double> base = createNumber("base", Double.class);

    // inherited
    public final pl.kurs.model.QUser createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final NumberPath<Double> height = createNumber("height", Double.class);

    //inherited
    public final NumberPath<Integer> id;

    public final StringPath parameters = createString("parameters");

    //inherited
    public final SimplePath<com.querydsl.core.types.Predicate> predicate;

    public final StringPath type = createString("type");

    //inherited
    public final NumberPath<Integer> version;

    public QTriangle(String variable) {
        this(Triangle.class, forVariable(variable), INITS);
    }

    public QTriangle(Path<? extends Triangle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTriangle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTriangle(PathMetadata metadata, PathInits inits) {
        this(Triangle.class, metadata, inits);
    }

    public QTriangle(Class<? extends Triangle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new pl.kurs.model.QShape(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.id = _super.id;
        this.predicate = _super.predicate;
        this.version = _super.version;
    }

}

