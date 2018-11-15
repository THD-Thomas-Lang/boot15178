package de.thd.okb.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author tlang
 * Entity SearchProfileCompany.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "searchprofilecompany", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class SearchProfileCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long searchProfileCompanyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private SearchProfile searchProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Company company;


    /**
     * Checks whether the current instance is equals to another instance.
     *
     * @param o the other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchProfileCompany)) return false;
        SearchProfileCompany that = (SearchProfileCompany) o;
        return Objects.equals(searchProfile, that.searchProfile) &&
                Objects.equals(company, that.company);
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(searchProfile, company);
    }
}
