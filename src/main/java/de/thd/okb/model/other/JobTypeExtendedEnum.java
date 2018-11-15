package de.thd.okb.model.other;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A given job type extended enum.
 *
 * @author tlang
 */
@Getter
public enum JobTypeExtendedEnum {
    SS,
    EV,
    ST;

    /**
     * Gets a list of internal job types.
     *
     * @return Iterable of JobTypeEnum
     */
    public static Iterable<JobTypeExtendedEnum> getJobTypesExtended() {
        List<JobTypeExtendedEnum> jobTypes = new ArrayList<>();
        jobTypes.add(JobTypeExtendedEnum.SS);
        jobTypes.add(JobTypeExtendedEnum.EV);
        jobTypes.add(JobTypeExtendedEnum.ST);
        return jobTypes;
    }
}
