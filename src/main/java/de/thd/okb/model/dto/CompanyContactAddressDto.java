package de.thd.okb.model.dto;

import de.thd.okb.model.db.Address;
import de.thd.okb.model.db.CompanyContact;
import lombok.*;

/**
 * A JobOfferDistance data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyContactAddressDto {

    private CompanyContactDto companyContactDto;
    private AddressDto addressDto;
    private boolean useHqAddress = true;
    private long companyId;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyContact a given company contact
     * @param address        a given address
     * @param companyId      a given company id
     */
    public CompanyContactAddressDto(@NonNull CompanyContact companyContact, @NonNull Address address, long companyId) {
        this();
        this.companyContactDto = new CompanyContactDto(companyContact);
        this.addressDto = new AddressDto(address);
        this.companyId = companyId;
        this.useHqAddress = companyContact.isUseHqAddress();
    }


    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyId a given company id
     */
    public CompanyContactAddressDto(long companyId) {
        this();
        this.companyContactDto = new CompanyContactDto();
        this.addressDto = new AddressDto();
        this.companyId = companyId;
    }
}
