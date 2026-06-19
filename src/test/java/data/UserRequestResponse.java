package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRequestResponse {
    private DataBlock data;
    private String requestId;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataBlock {
        private Integer applicantid;
        private Integer citizenid;
        private Integer applicationid;
        private Integer birthcertificateid;
        private Integer marriagecertificateid;
        private Integer deathcertificateid;
        private String statusofapplication;
    }
}
