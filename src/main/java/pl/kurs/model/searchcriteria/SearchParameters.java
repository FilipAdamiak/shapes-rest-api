package pl.kurs.model.searchcriteria;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchParameters {

    private Double radiusFrom;
    private Double radiusTo;
    private String userEmail;
    private LocalDateTime createdFrom;
    private LocalDateTime createdTo;
    private Double sideFrom;
    private Double sideTo;
    private Double widthFrom;
    private Double widthTo;
    private Double heightFrom;
    private Double heightTo;
    private Double bottomBaseFrom;
    private Double bottomBaseTo;
    private Double upperBaseFrom;
    private Double upperBaseTo;

    @Builder
    public SearchParameters(Double radiusFrom, Double radiusTo, String userEmail, LocalDateTime createdFrom,
                            LocalDateTime createdTo, Double sideFrom, Double sideTo, Double widthFrom, Double widthTo,
                            Double heightFrom, Double heightTo, Double bottomBaseFrom, Double bottomBaseTo, Double upperBaseFrom, Double upperBaseTo) {
        this.radiusFrom = radiusFrom;
        this.radiusTo = radiusTo;
        this.userEmail = userEmail;
        this.createdFrom = createdFrom;
        this.createdTo = createdTo;
        this.sideFrom = sideFrom;
        this.sideTo = sideTo;
        this.widthFrom = widthFrom;
        this.widthTo = widthTo;
        this.heightFrom = heightFrom;
        this.heightTo = heightTo;
        this.bottomBaseFrom = bottomBaseFrom;
        this.bottomBaseTo = bottomBaseTo;
        this.upperBaseFrom = upperBaseFrom;
        this.upperBaseTo = upperBaseTo;
    }
}
