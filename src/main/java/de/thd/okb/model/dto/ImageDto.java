package de.thd.okb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A JobOfferDistance data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private String fileName;
    private String mimeType;
    private String originalFileName;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param fileName a given file name
     * @param mimeType a given mimetype
     */
    public ImageDto(String fileName, String mimeType) {
        this(fileName, mimeType, null);
    }


    /**
     * Readable print version for the current instance.
     * Used to represent database file names.
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s|%s", fileName, mimeType);
    }
}
