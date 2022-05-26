package pl.kurs.model.searchcriteria;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parameters {

    private Double radius;
    private Double width;
    private Double height;
    private Double base;
    private Double side;
    private Double bottomBase;
    private Double upperBase;

    @Builder
    public Parameters(Double radius, Double width, Double height, Double base, Double side, Double bottomBase, Double upperBase) {
        this.radius = radius;
        this.width = width;
        this.height = height;
        this.base = base;
        this.side = side;
        this.bottomBase = bottomBase;
        this.upperBase = upperBase;
    }

}
