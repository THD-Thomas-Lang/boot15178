package de.thd.okb.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A given DTO (Data Transfer Object).
 * Used to transfer Data between two Types.
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
public class StudyCourseDto {
    private String name;
    private String shortname;
}
