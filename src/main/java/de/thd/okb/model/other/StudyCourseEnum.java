package de.thd.okb.model.other;

import lombok.Getter;

/**
 * A given study course enum.
 *
 * @author tlang
 */
@Getter
public enum StudyCourseEnum {
    NONE("(Keine Angabe)");

    private final String label;
    private final String value;

    /**
     * package-private standard constructor.
     * building up the dto
     *
     * @param label a given label
     */
    StudyCourseEnum(String label) {
        this.label = label;
        this.value = this.name();
    }

    /**
     * to string representation of the current instance.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.getLabel();
    }
}
