package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String address;
    private String passport;
}
