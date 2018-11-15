package de.thd.okb.component;

import lombok.NonNull;

import java.util.List;
import java.util.Locale;

/**
 * Helper for localization/messaging.
 *
 * @author tlang
 */
public interface IMessageHelper {

    /**
     * Translates a given job type code to a readable version.
     *
     * @param jobTypeCode a given job type code
     * @param locale   given locale
     * @return String
     */
    String translateJobType(@NonNull String jobTypeCode, Locale locale);

    /**
     * Translates the given job types code to a readable version.
     *
     * @param jobTypeCodes a given job type code
     * @param locale       given locale
     * @return String
     */
    String translateJobType(@NonNull List<String> jobTypeCodes, Locale locale);

    /**
     * Translates the given job types code to a readable list version.
     *
     * @param jobTypeCodes a given job type code
     * @param locale       given locale
     * @return String
     */
    Iterable<String> translateJobTypeToList(@NonNull List<String> jobTypeCodes, Locale locale);

    /**
     * Translates a given study course code to a readable version.
     *
     * @param studyCourseCode a given study course code
     * @param locale   given locale
     * @return String
     */
    String translateStudyCourse(@NonNull String studyCourseCode, Locale locale);

    /**
     * Translates given study course codes to a readable version.
     *
     * @param studyCourseCodes a given study course code
     * @param locale           given locale
     * @return String
     */
    String translateStudyCourse(@NonNull List<String> studyCourseCodes, Locale locale);

    /**
     * Translates given study course codes to a readable (list) version.
     *
     * @param studyCourseCodes a given study course code
     * @param locale           given locale
     * @return String
     */
    Iterable<String> translateStudyCourseToList(@NonNull List<String> studyCourseCodes, Locale locale);


}
