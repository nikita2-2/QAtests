package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessResponse {
    private DataBlock data;
    private String requestId;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataBlock {
        private Integer applicationid;
        private Integer citizenid;
        private Integer applicantid;
        private Integer staffid;
        private String dateofapplication;
        private String kindofapplication;
        private String statusofapplication; // Наше поле статуса!
    }
}
