package de.thd.okb.model.profiles;

import de.thd.okb.model.db.SearchProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tlang
 * Entity AdminRole.
 * Maps the table from the datasource to this type.
 */
@Entity(name = "AdminRole")
@DiscriminatorValue("AdminRole")
@Getter
@Setter
@NoArgsConstructor
public class AdminRole extends AbstractRole {

    @Column
    private boolean emailNotification;

    @OneToMany(mappedBy = "abstractRole", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Set<SearchProfile> searchProfileSet = new HashSet<>();

    /**
     * Checks whether the current instance is equals to another instance.
     *
     * @param o the other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof AdminRole && super.equals(o);
    }

    /**
     * Computes a hash code.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
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
    @Override
    protected void syncValues(long abstractRoleId, @NonNull String firstName, @NonNull String lastName,
                              @NonNull String email, @NonNull String aboutMe) {
        this.setAbstractRoleId(abstractRoleId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setAboutMe(aboutMe);
    }
}