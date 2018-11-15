package de.thd.okb.model.other;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A given job type enum.
 *
 * @author tlang
 */
@Getter
public enum JobTypeEnum {
    BM("Bachelor- / Masterarbeit"),
    BE("Berufseinsteiger"),
    FJ("Ferienjob / Nebenjob"),
    FM("freie Mitarbeit"),
    PP("Praxisprojekt"),
    PR("Praktikum"),
    SHK("Werkstudent"),
    YP("Berufserfahrene"),
    SA("Studienabbrecher"),
    FC("firstcontact"),
    WS("Student. Hilfskraft"),
    SU("Startup - Gründer sucht Mitgründer"),
    TT("Tutor");

    private final String label;
    private final String value;

    /**
     * package-private standard constructor.
     * building up the dto
     *
     * @param label a given label
     */
    JobTypeEnum(String label) {
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

    /**
     * Gets a list of internal job types.
     *
     * @return Iterable of JobTypeEnum
     */
    public static Iterable<JobTypeEnum> getInternalJobTypes() {
        List<JobTypeEnum> jobTypes = new ArrayList<>();
        jobTypes.add(JobTypeEnum.WS);
        jobTypes.add(JobTypeEnum.TT);
        return jobTypes;
    }
}
