package pl.kurs.model.figures;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRectangle is a Querydsl query type for Rectangle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRectangle extends EntityPathBase<Rectangle> {

    private static final long serialVersionUID = 1288669082L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRectangle rectangle = new QRectangle("rectangle");

    public final pl.kurs.model.QShape _super;

    public final NumberPath<Double> area = createNumber("area", Double.class);

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

    public final NumberPath<Double> width = createNumber("width", Double.class);

    public QRectangle(String variable) {
        this(Rectangle.class, forVariable(variable), INITS);
    }

    public QRectangle(Path<? extends Rectangle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRectangle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRectangle(PathMetadata metadata, PathInits inits) {
        this(Rectangle.class, metadata, inits);
    }

    public QRectangle(Class<? extends Rectangle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new pl.kurs.model.QShape(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.id = _super.id;
        this.predicate = _super.predicate;
        this.version = _super.version;
    }

}

