package de.thd.okb.model.db;

import de.thd.okb.model.dto.AddressDto;
import de.thd.okb.model.other.LatLng;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author tlang
 * Entity Address.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "address", schema = "public", indexes = {
        @Index(name = "index_address_city", columnList = "city"),
        @Index(name = "index_address_street", columnList = "street")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long addressId;

    @Column(precision = 12, scale = 10)
    private BigDecimal lat;

    @Column(precision = 13, scale = 10)
    private BigDecimal lng;

    @Size(max = 128)
    @Column(length = 128)
    private String street;

    @Size(max = 64)
    @Column(length = 64)
    private String postcode;

    @NotNull
    @Size(max = 128)
    @Column(nullable = false, length = 128)
    private String city;

    @Size(max = 64)
    @Column(length = 64)
    private String isoCode;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param addressDto a given address dto
     * @param latLng     a given lat long type
     */
    public Address(AddressDto addressDto, LatLng latLng) {
        this();
        this.syncValues(addressDto, (latLng == null) ? BigDecimal.ZERO : latLng.getLat(), (latLng == null) ? BigDecimal.ZERO : latLng.getLng());
    }

    /**
     * Is the current instance equals the given instance.
     *
     * @param o a given instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return addressId == address.addressId &&
                Objects.equals(street, address.street) &&
                Objects.equals(postcode, address.postcode) &&
                Objects.equals(city, address.city);
    }

    /**
     * Computes a hash for the current intance
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(addressId, street, postcode, city);
    }

    /**
     * Readable display of the current instance.
     *
     * @return String
     */
    @Override
    public String toString() {
        final List<String> strings = Arrays.asList(Locale.getISOCountries());
        final Optional<String> countryCode = strings.stream().filter(s -> s.equals(this.isoCode)).findFirst();
        if (!countryCode.isPresent()) return "";
        Locale locale = new Locale("", countryCode.get());
        return String.format("%s, %s %s, %s", this.street, this.postcode, this.city, locale.getDisplayCountry());
    }

    /**
     * Formats the given address.
     *
     * @return String
     */
    public String format() {
        final List<String> strings = Arrays.asList(Locale.getISOCountries());
        final Optional<String> countryCode = strings.stream().filter(s -> s.equals(this.isoCode)).findFirst();
        if (!countryCode.isPresent()) return "";
        Locale locale = new Locale("", countryCode.get());
        return String.format("%s%s%s %s%s%s", this.street, System.lineSeparator(), this.postcode, this.city, System.lineSeparator(),
                locale.getDisplayCountry());
    }

    /**
     * Syncs values between a dto and the current instance.
     *
     * @param addressDto a given dto
     * @param latitude   a given latitude
     * @param longitude  a given longitude
     */
    public void syncValues(@NonNull AddressDto addressDto, @NonNull BigDecimal latitude, @NonNull BigDecimal longitude) {
        this.city = addressDto.getCity();
        this.postcode = addressDto.getPostcode();
        this.street = addressDto.getStreet();
        this.isoCode = addressDto.getIsoCode();
        this.lat = latitude;
        this.lng = longitude;
    }

    /**
     * Syncs values between a dto and the current instance.
     *
     * @param address   a given other address
     * @param latitude  a given latitude
     * @param longitude a given longitude
     */
    public void syncValues(@NonNull Address address, BigDecimal latitude, BigDecimal longitude) {
        this.city = address.getCity();
        this.postcode = address.getPostcode();
        this.street = address.getStreet();
        this.isoCode = address.getIsoCode();
        this.lat = latitude;
        this.lng = longitude;
    }
}
