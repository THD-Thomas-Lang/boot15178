package de.thd.okb.model.dto;

import lombok.*;

/**
 * Coordinates of a location
 *
 * @author tlang
 */
@Getter
@Setter
public class LocationCoordinates {
    private double latitude;
    private double longitude;

    private void validatePoint(@NonNull LocationCoordinates locationCoordinates) {
        if (locationCoordinates.getLatitude() > 90)
            locationCoordinates.setLatitude(90 - (locationCoordinates.getLatitude() - 90));
        if (locationCoordinates.getLatitude() < -90)
            locationCoordinates.setLatitude(-90 - (locationCoordinates.getLatitude() + 90));
        if (locationCoordinates.getLongitude() > 180)
            locationCoordinates.setLongitude(-180 + (locationCoordinates.getLongitude() - 180));
        if (locationCoordinates.getLongitude() < -180)
            locationCoordinates.setLongitude(180 + (locationCoordinates.getLongitude() + 180));
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param latitude  a given latitude
     * @param longitude a given longitude
     */
    public LocationCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        validatePoint(this);
    }
}
