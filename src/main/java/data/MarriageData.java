package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarriageData {
    private String regDate;
    private String newLastName;
    private String spouseLastName;
    private String spouseFirstName;
    private String spouseMiddleName;
    private String spouseBirthDate;
    private String spousePassport;
}
