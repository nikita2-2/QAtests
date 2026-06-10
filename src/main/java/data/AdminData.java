package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String passport;
    private String phone;
    private String birthDate;
}
