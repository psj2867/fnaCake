package ml.psj2867.demo.service.user.model;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ml.psj2867.demo.entity.UserEntity;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateForm {
    

    @Nullable private String password;
    @Nullable private String phoneNumber;

    @Nullable private String accountNumber;
    @Nullable private String accountName;
    @Nullable private String accountOwner;

    @Nullable private String recipient;
    @Nullable private String address;
    @Nullable private String addressDetail;

    @Nullable private String zipCode;

    public void overWrite(UserEntity user){
        if(this.password != null) user.setPassword(password);
        if(this.phoneNumber != null) user.setPhoneNumber(phoneNumber);

        if(this.accountNumber != null) user.getBank().setAccountNumber(this.accountNumber);
        if(this.accountName != null) user.getBank().setAccountName(this.accountName);
        if(this.accountOwner != null) user.getBank().setAccountOwner(this.accountOwner);

        if(this.recipient != null) user.getAddress().setAddressRecipient(this.recipient);
        if(this.address != null) user.getAddress().setAddress(this.address);
        if(this.addressDetail != null) user.getAddress().setAddressDetail(this.addressDetail);
        if(this.zipCode != null) user.getAddress().setAddressZipCode(this.zipCode);
    }

}