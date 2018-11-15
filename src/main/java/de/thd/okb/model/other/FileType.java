package de.thd.okb.model.other;

import lombok.Getter;

/**
 * Enum holding the current Role structure.
 *
 * @author tlang
 */
@Getter
public enum FileType {
    PDF("application/pdf", ".pdf"), CSV("text/comma-separated-values", ".csv");

    private final String mimeType;
    private final String extension;
    private final String value;

    /**
     * package-private standard constructor.
     * building up the enum
     *
     * @param mimeType  a given mimetype
     * @param extension a given file extension
     */
    FileType(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
        this.value = this.name();
    }
}
