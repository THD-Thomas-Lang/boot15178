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
public class PersonDto {

    private String firstName;
    private String lastName;
    private String email;
    private String login;
}
