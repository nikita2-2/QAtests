package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BirthData {
    private String birthPlace;
    private String motherName;
    private String fatherName;
    private String grandmotherName;
    private String grandfatherName;
}