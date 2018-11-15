package de.thd.okb.model.other;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class LatLng {
    private BigDecimal lat;
    private BigDecimal lng;

    /**
     * Standard constructor.
     */
    public LatLng() {
        this.lat = BigDecimal.ZERO;
        this.lng = BigDecimal.ZERO;
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param lat a given lattitude
     * @param lng a given longitude
     */
    public LatLng(BigDecimal lat, BigDecimal lng) {
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param latt  a given lattitude
     * @param longt a given longitude
     */
    public LatLng(String latt, String longt) {
        this(new BigDecimal(latt), new BigDecimal(longt));
    }
}
