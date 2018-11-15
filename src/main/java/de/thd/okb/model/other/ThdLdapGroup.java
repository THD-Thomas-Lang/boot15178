package de.thd.okb.model.other;

/**
 * Enum holding the current Thd Ldap Group structure.
 *
 * @author tlang
 */
public enum ThdLdapGroup {
    PERS, STUD;

    private static final String[] labels = {"Personal", "Studenten"};

    /**
     * A string representation of the given entity.
     *
     * @return String
     */
    @Override
    public String toString() {
        return labels[ordinal()];
    }
}
