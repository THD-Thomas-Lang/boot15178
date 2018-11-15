package de.thd.okb.model.dto;

import de.thd.okb.model.db.Address;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Locale;

/**
 * A JobOfferDistance data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NotNull
    private long addressId;

    @Size(max = 128)
    private String street;

    @Size(max = 64)
    private String postcode;

    @NotNull
    @Size(max = 128)
    private String city;

    @Size(max = 64)
    private String isoCode = Locale.GERMANY.getCountry();

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param address a given address
     */
    AddressDto(@NonNull Address address) {
        this();
        this.addressId = address.getAddressId();
        this.street = address.getStreet();
        this.postcode = address.getPostcode();
        this.city = address.getCity();
        this.isoCode = address.getIsoCode();
    }
}
