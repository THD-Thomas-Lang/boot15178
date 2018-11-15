package de.thd.okb.component;

import de.thd.okb.model.db.JobOffer;
import de.thd.okb.model.dto.ExpiredJobOfferProjection;
import org.springframework.mail.SimpleMailMessage;

/**
 * Helper for creating mails.
 *
 * @author tlang
 */
public interface IMailHelper {

    /**
     * Generates email messages based on the given values.
     *
     * @param emailAddress given email addresses
     * @param jobOffers    given job offers
     * @return Iterable of SimpleMailMessage
     */
    Iterable<SimpleMailMessage> generateNewJobOfferEmails(Iterable<String> emailAddress, Iterable<JobOffer> jobOffers);

    /**
     * Generates email messages based on the soon to be expired jobs.
     *
     * @param soonToBeExpiredJobOffers given soon to be expired jobs
     * @return Iterable of SimpleMailMessage
     */
    Iterable<SimpleMailMessage> generateSoonToBeExpiredJobOfferEmails(Iterable<ExpiredJobOfferProjection> soonToBeExpiredJobOffers);


}
