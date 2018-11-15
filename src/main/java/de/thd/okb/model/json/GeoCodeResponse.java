package de.thd.okb.model.json;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

/**
 * JSON response of consuming a REST service.
 * https://geocode.xyz/
 *
 * @author tlang
 * @see <a href="https://spring.io/guides/gs/consuming-rest/">Consuming a RESTful Web Service</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "standard",
        "longt",
        "latt"
})
@Data
public class GeoCodeResponse {

    @JsonProperty("standard")
    @Valid
    private Standard standard;

    @JsonProperty("longt")
    private String longt;

    @JsonProperty("latt")
    private String latt;
}
