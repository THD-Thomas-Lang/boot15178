package de.thd.okb.model.other;

import de.thd.okb.model.dto.PersonDto;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 * Maps a ldap query attributes result to a dto.
 *
 * @author tlang
 */
public class PersonAttributesMapper implements AttributesMapper<PersonDto> {
    /**
     * Does the actual mapping
     *
     * @param attributes given attributes
     * @return PersonDto
     * @throws NamingException the given exception
     */
    @Override
    public PersonDto mapFromAttributes(Attributes attributes) throws NamingException {
        return new PersonDto((String) attributes.get("givenName").get(),
                (String) attributes.get("sn").get(), (String) attributes.get("mail").get(), (String) attributes.get("uid").get());
    }
}
