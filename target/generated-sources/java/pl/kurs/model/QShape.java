package pl.kurs.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShape is a Querydsl query type for Shape
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShape extends EntityPathBase<Shape> {

    private static final long serialVersionUID = -1215084053L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShape shape = new QShape("shape");

    public final QUser createdBy;

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SimplePath<com.querydsl.core.types.Predicate> predicate = createSimple("predicate", com.querydsl.core.types.Predicate.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QShape(String variable) {
        this(Shape.class, forVariable(variable), INITS);
    }

    public QShape(Path<? extends Shape> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShape(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShape(PathMetadata metadata, PathInits inits) {
        this(Shape.class, metadata, inits);
    }

    public QShape(Class<? extends Shape> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUser(forProperty("createdBy")) : null;
    }

}

