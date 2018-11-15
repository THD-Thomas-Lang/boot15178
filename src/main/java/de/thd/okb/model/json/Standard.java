package de.thd.okb.model.json;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON response of consuming a REST service.
 * https://geocode.xyz/
 *
 * @author tlang
 * @see <a href="https://spring.io/guides/gs/consuming-rest/">Consuming a RESTful Web Service</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "addresst",
        "city",
        "prov",
        "countryname",
        "postal",
        "confidence"
})
@Data
public class Standard {

    @JsonProperty("addresst")
    @Valid
    private Addresst addresst;

    @JsonProperty("city")
    private String city;

    @JsonProperty("prov")
    private String prov;

    @JsonProperty("countryname")
    private String countryname;

    @JsonProperty("postal")
    @Valid
    private Postal postal;

    @JsonProperty("confidence")
    private String confidence;

    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
