package de.thd.okb.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

/**
 * @author tlang
 * Entity JobOfferOtherNewsletter.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "jobofferother", schema = "public", indexes = {
        @Index(name = "index_jobofferothernewsletter_jobType", columnList = "jobType"),
        @Index(name = "index_jobofferothernewsletter_jobOfferOtherName", columnList = "jobOfferOtherNewsletterName"),
        @Index(name = "index_jobofferothernewsletter_jobType", columnList = "jobType"),
})
@Getter
@Setter
@NoArgsConstructor
public class JobOfferOtherNewsletter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferOtherNewsletterId;

    @Size(max = 255)
    @Column
    private String jobOfferOtherNewsletterName;

    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate jobOfferOtherNewsletterDate = LocalDate.now();

    @Size(max = 512)
    @Column
    private String jobOfferOtherNewsletterShortDesc;

    @Size(max = 128)
    @Column(length = 128)
    private String jobOfferOtherNewsletterCity;

    @Size(max = 64)
    @Column(length = 64)
    private String jobOfferIsoCode = Locale.GERMANY.toString();

    @Size(max = 128)
    @Column(length = 128)
    private String jobOfferOtherNewsletterContactDescription;

    @Size(max = 128)
    @Column(length = 128)
    private String jobOfferOtherNewsletterContactHomepage;

    @NotNull
    @Size(max = 128, min = 2)
    @Column(length = 128, nullable = false)
    private String jobType;

    @NotNull
    @Column(nullable = false)
    private boolean repeat = true;

    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate jobOfferOtherNewsletterDateRepeatUntil = LocalDate.now().plusWeeks(1);

    @NotNull
    @Column(nullable = false, length = 128)
    @Size(max = 128, min = 2)
    private String createdBy;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param currentApplicationUser a given application user
     */
    public JobOfferOtherNewsletter(@NonNull String currentApplicationUser) {
        this();
        this.createdBy = currentApplicationUser;
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
        if (!(o instanceof JobOfferOtherNewsletter)) return false;
        JobOfferOtherNewsletter that = (JobOfferOtherNewsletter) o;
        return jobOfferOtherNewsletterId == that.jobOfferOtherNewsletterId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOfferOtherNewsletterId);
    }
}
