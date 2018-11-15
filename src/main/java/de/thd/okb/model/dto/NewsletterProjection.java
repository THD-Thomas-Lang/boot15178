package de.thd.okb.model.dto;

/**
 * A NewsletterProjection data projection interface.
 *
 * @author tlang
 */

public interface NewsletterProjection {

    /**
     * Formats a given newsletter job offer description.
     *
     * @return String
     */
    default String formatJobDescription() {
        return String.format("%s (%s)", getJobOfferShortDesc() == null || getJobOfferShortDesc().isEmpty() ? getCompanyName() : getJobOfferShortDesc(), getJobOfferCity());
    }

    /**
     * Formats a given newsletter job offer contact.
     *
     * @return String
     */
    default String formatJobContact() {
        String phone = getJobOfferContactPhone() != null && !getJobOfferContactPhone().isEmpty() ? String.format("Telefon: %s", getJobOfferContactPhone()) : "";
        String contactDescription = getJobOfferContactDescription() != null && !getJobOfferContactDescription().isEmpty() ? String.format("Kontakt: %s", getJobOfferContactDescription()) : "";
        String email = getJobOfferContactEmail() != null && !getJobOfferContactEmail().isEmpty() ? String.format("E-Mail/Homepage: %s", getJobOfferContactEmail()) : "";
        return String.format("%s %s %s", contactDescription, email, phone);
    }

    /**
     * The given company name.
     *
     * @return String
     */
    String getCompanyName();

    /**
     * The given job type code.
     *
     * @return String
     */
    String getJobTypeCode();

    /**
     * The given job offer short description.
     *
     * @return String
     */
    String getJobOfferShortDesc();

    /**
     * The given job offer city.
     *
     * @return String
     */
    String getJobOfferCity();

    /**
     * The given job offer contact description.
     *
     * @return String
     */
    String getJobOfferContactDescription();

    /**
     * The given job offer contact email.
     *
     * @return String
     */
    String getJobOfferContactEmail();

    /**
     * The given job offer contact phone.
     *
     * @return String
     */
    String getJobOfferContactPhone();

    /**
     * The given job offer id.
     *
     * @return long
     */
    long getJobOfferId();

    /**
     * The type of the job offer
     * @return String
     */
    String getJobOfferType();

}
