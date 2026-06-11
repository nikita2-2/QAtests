package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetApplicationsResponse {
    private List<UserRequestResponse.DataBlock> data;
    private String requestId;
}
