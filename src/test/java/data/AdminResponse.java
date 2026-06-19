package data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminResponse {
    private DataBlock data;
    private String requestId;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataBlock {
        private Integer staffid;
    }
}