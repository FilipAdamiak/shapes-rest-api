package pl.kurs.model.figures;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrapezoid is a Querydsl query type for Trapezoid
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrapezoid extends EntityPathBase<Trapezoid> {

    private static final long serialVersionUID = 1264960531L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrapezoid trapezoid = new QTrapezoid("trapezoid");

    public final pl.kurs.model.QShape _super;

    public final NumberPath<Double> area = createNumber("area", Double.class);

    public final NumberPath<Double> bottomBase = createNumber("bottomBase", Double.class);

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

    public final NumberPath<Double> upperBase = createNumber("upperBase", Double.class);

    //inherited
    public final NumberPath<Integer> version;

    public QTrapezoid(String variable) {
        this(Trapezoid.class, forVariable(variable), INITS);
    }

    public QTrapezoid(Path<? extends Trapezoid> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrapezoid(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrapezoid(PathMetadata metadata, PathInits inits) {
        this(Trapezoid.class, metadata, inits);
    }

    public QTrapezoid(Class<? extends Trapezoid> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new pl.kurs.model.QShape(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.id = _super.id;
        this.predicate = _super.predicate;
        this.version = _super.version;
    }

}

