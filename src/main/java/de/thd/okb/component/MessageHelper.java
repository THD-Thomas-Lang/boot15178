package de.thd.okb.component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Password hash functions.
 * Uses md5 as hashing algorithm.
 *
 * @author tlang
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageHelper implements IMessageHelper {

    private final @NonNull
    MessageSource messageSource;

    /**
     * Translates a given job type code to a readable version.
     *
     * @param jobTypeCode a given job type code
     * @param locale      given locale
     * @return String
     */
    @Override
    public String translateJobType(@NonNull String jobTypeCode, @NonNull Locale locale) {
        try {
            return this.messageSource.getMessage(String.format("jobtype.%s", jobTypeCode.toLowerCase()), new Object[]{}, locale);
        } catch (NoSuchMessageException noSuchMessageException) {
            log.error(noSuchMessageException.getMessage());
            return jobTypeCode;
        }
    }

    /**
     * Translates the given job types code to a readable version.
     *
     * @param jobTypeCodes a given job type code
     * @param locale       given locale
     * @return String
     */
    @Override
    public String translateJobType(@NonNull List<String> jobTypeCodes, @NonNull Locale locale) {
        List<String> list = new ArrayList<>();
        jobTypeCodes.forEach((jobTypeCode) -> list.add(translateJobType(jobTypeCode, locale)));
        return String.join("; ", list);
    }

    /**
     * Translates the given job types code to a readable list version.
     *
     * @param jobTypeCodes a given job type code
     * @param locale       given locale
     * @return String
     */
    @Override
    public Iterable<String> translateJobTypeToList(List<String> jobTypeCodes, Locale locale) {
        Set<String> jobTypes = new HashSet<>();
        jobTypeCodes.forEach((jobType) -> {
            jobTypes.add(translateJobType(jobType, locale));
        });
        return jobTypes;
    }

    /**
     * Translates a given study course code to a readable version.
     *
     * @param studyCourseCode a given study course code
     * @param locale          given locale
     * @return String
     */
    @Override
    public String translateStudyCourse(String studyCourseCode, Locale locale) {
        return this.messageSource.getMessage(String.format("studycourse.%s", studyCourseCode.toLowerCase()), new Object[]{}, locale);
    }

    /**
     * Translates given study course codes to a readable version.
     *
     * @param studyCourseCodes a given study course code
     * @param locale           given locale
     * @return String
     */
    @Override
    public String translateStudyCourse(@NonNull List<String> studyCourseCodes, @NonNull Locale locale) {
        List<String> list = new ArrayList<>();
        studyCourseCodes.forEach((studyCourseCode) -> list.add(translateStudyCourse(studyCourseCode, locale)));
        return list.stream().collect(Collectors.joining("; "));
    }

    /**
     * Translates given study course codes to a readable (list) version.
     *
     * @param studyCourseCodes a given study course code
     * @param locale           given locale
     * @return String
     */
    @Override
    public Iterable<String> translateStudyCourseToList(List<String> studyCourseCodes, Locale locale) {
        Set<String> studyCourses = new HashSet<>();
        studyCourseCodes.forEach((studyCourse) -> {
            studyCourses.add(translateStudyCourse(studyCourse, locale));
        });
        return studyCourses;
    }
}
