package de.thd.okb.component;

import de.thd.okb.model.db.JobOffer;
import de.thd.okb.model.dto.ExpiredJobOfferProjection;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Helper for creating mails.
 *
 * @author tlang
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailHelper implements IMailHelper {

    private final @NonNull
    Environment environment;

    private final @NonNull
    MessageSource messageSource;

    private final @NonNull
    TemplateEngine templateEngine;

    private final @NonNull
    IMessageHelper messageHelper;

    /**
     * Generates email messages based on the given values.
     *
     * @param emailAddress given email addresses
     * @param jobOffers    given job offers
     * @return Iterable of SimpleMailMessage
     */
    @Override
    public Iterable<SimpleMailMessage> generateNewJobOfferEmails(@NonNull Iterable<String> emailAddress, @NonNull Iterable<JobOffer> jobOffers) {
        final Context ctx = new Context(LocaleContextHolder.getLocale());
        Set<SimpleMailMessage> simpleMailMessages = new HashSet<>();
        final SimpleMailMessage[] simpleMailMessage = {new SimpleMailMessage()};
        emailAddress.forEach((email) -> jobOffers.forEach((jobOffer -> {

            simpleMailMessage[0].setSubject(this.messageSource.getMessage("email.joboffer.subject", new Object[]{}, LocaleContextHolder.getLocale()));
            simpleMailMessage[0].setFrom(this.environment.getRequiredProperty("email.noreply"));
            simpleMailMessage[0].setTo(email);

            final @NotNull long jobOfferId = jobOffer.getJobOfferId();

            ctx.setVariable("company", jobOffer.getCompany().getName());
            ctx.setVariable("contact", jobOffer.getCompanyContact().toString());
            ctx.setVariable("description", jobOffer.getDescription());
            ctx.setVariable("jobofferid", String.valueOf(jobOfferId));

            final List<String> jobTypeCodes = jobOffer.getJobOfferJobTypeSet().stream().map(s -> s.getJobType().getCode()).collect(Collectors.toList());
            final List<String> studyCourseCodes = jobOffer.getJobOfferStudyCourseSet().stream().map(s -> s.getStudyCourse().getCode()).collect(Collectors.toList());

            ctx.setVariable("jobtypes", messageHelper.translateJobTypeToList(jobTypeCodes, LocaleContextHolder.getLocale()));
            ctx.setVariable("studycourses", messageHelper.translateStudyCourseToList(studyCourseCodes, LocaleContextHolder.getLocale()));
            final String text = templateEngine.process("text/newJobOffersNotification", ctx);
            simpleMailMessage[0].setText(text);
            simpleMailMessages.add(simpleMailMessage[0]);
            log.error(simpleMailMessage[0].toString());

        })));
        return simpleMailMessages;
    }

    /**
     * Generates email messages based on the soon to be expired jobs.
     *
     * @param soonToBeExpiredJobOffers given soon to be expired jobs
     * @return Iterable of SimpleMailMessage
     */
    @Override
    public Iterable<SimpleMailMessage> generateSoonToBeExpiredJobOfferEmails(Iterable<ExpiredJobOfferProjection> soonToBeExpiredJobOffers) {
        final Context ctx = new Context(LocaleContextHolder.getLocale());
        List<SimpleMailMessage> simpleMailMessages = new ArrayList<>();
        final SimpleMailMessage[] simpleMailMessage = {new SimpleMailMessage()};
        final String[] email = {soonToBeExpiredJobOffers.iterator().next().getEmail()};
        StringBuilder stringBuilder = new StringBuilder();
        soonToBeExpiredJobOffers.forEach((jobOfferProjection) -> {
            if (!email[0].equals(jobOfferProjection.getEmail())) {
                simpleMailMessage[0].setText(stringBuilder.toString());
                simpleMailMessages.add(new SimpleMailMessage(simpleMailMessage[0]));
                stringBuilder.setLength(0);
            }

            ctx.setVariable("expirationDate", jobOfferProjection.getValidUntilDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            ctx.setVariable("description", jobOfferProjection.getShortDesc());
            ctx.setVariable("jobOfferId", String.valueOf(jobOfferProjection.getOfferId()));
            final String text = templateEngine.process("text/expiredJobOfferNotification", ctx);
            stringBuilder.append(text);
            simpleMailMessage[0].setSubject(this.messageSource.getMessage("email.joboffer.expired.subject", new Object[]{}, LocaleContextHolder.getLocale()));
            simpleMailMessage[0].setFrom(this.environment.getRequiredProperty("email.noreply"));
            simpleMailMessage[0].setTo(jobOfferProjection.getEmail());


            email[0] = jobOfferProjection.getEmail();

        });
        log.error(simpleMailMessages.toString());
        return simpleMailMessages;
    }
}
