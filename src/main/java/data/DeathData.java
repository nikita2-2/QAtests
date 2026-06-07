package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeathData {
    private String deathDate;
    private String deathPlace;
}