package de.thd.okb.model.profiles;

import de.thd.okb.model.db.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author tlang
 * Entity Address.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "abstractrole", schema = "public", indexes = {
        @Index(name = "index_abstractrole_email", columnList = "email")
})
@Getter
@Setter
@Inheritance
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long abstractRoleId;

    @Column(length = 128, nullable = false)
    @NotNull
    @Size(max = 128)
    private String firstName;

    @Column(length = 128, nullable = false)
    @NotNull
    @Size(max = 128)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    private ApplicationUser applicationUser;

    @Column(length = 128, nullable = false)
    @NotNull
    @Size(max = 128)
    @Email
    private String email;

    @Column(length = 4096)
    @Size(max = 4096)
    private String aboutMe;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param abstractRoleId a given abstract role id
     * @param firstName      a given first name
     * @param lastName       a given last name
     * @param email          a given email
     * @param aboutMe        a given about me
     */
    public AbstractRole(@NotNull long abstractRoleId, @NotNull @Size(max = 128) String firstName,
                        @NotNull @Size(max = 128) String lastName, @NotNull @Size(max = 128) @Email String email,
                        @Size(max = 4096) String aboutMe) {
        this.abstractRoleId = abstractRoleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.aboutMe = aboutMe;
    }

    /**
     * Checks whether the current instance is equals to another instance.
     *
     * @param o the other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractRole that = (AbstractRole) o;
        return abstractRoleId == that.abstractRoleId;
    }

    /**
     * Computes a hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(abstractRoleId);
    }

    /**
     * Syncs some values
     *
     * @param abstractRoleId a given abstract role id
     * @param firstName      a given firstname
     * @param lastName       a given lastname
     * @param email          a given email
     * @param aboutMe        given about me
     */
    protected abstract void syncValues(long abstractRoleId, String firstName, String lastName, String email, String aboutMe);
}
