package pl.kurs.model.figures;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSquare is a Querydsl query type for Square
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSquare extends EntityPathBase<Square> {

    private static final long serialVersionUID = -1117411630L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSquare square = new QSquare("square");

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

    public final NumberPath<Double> side = createNumber("side", Double.class);

    public final StringPath type = createString("type");

    //inherited
    public final NumberPath<Integer> version;

    public QSquare(String variable) {
        this(Square.class, forVariable(variable), INITS);
    }

    public QSquare(Path<? extends Square> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSquare(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSquare(PathMetadata metadata, PathInits inits) {
        this(Square.class, metadata, inits);
    }

    public QSquare(Class<? extends Square> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new pl.kurs.model.QShape(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.id = _super.id;
        this.predicate = _super.predicate;
        this.version = _super.version;
    }

}

