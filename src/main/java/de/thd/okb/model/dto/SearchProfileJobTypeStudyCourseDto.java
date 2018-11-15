package de.thd.okb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Search Profile Job Type Dto.
 * A Data Transfer Object, used for data exchange.
 *
 * @author tlang
 */
@Getter
@AllArgsConstructor
public class SearchProfileJobTypeStudyCourseDto {

    private long searchProfileId;
    private String code;
}
