package de.thd.okb.model.json;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

/**
 * JSON response of consuming a REST service.
 * https://geocode.xyz/
 *
 * @author tlang
 * @see <a href="https://spring.io/guides/gs/consuming-rest/">Consuming a RESTful Web Service</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder()
@Data
public class Postal {

    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}