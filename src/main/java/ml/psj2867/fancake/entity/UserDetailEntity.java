package ml.psj2867.fancake.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ml.psj2867.fancake.dao.UserDetailEntityDao;
import ml.psj2867.fancake.entity.embedded.AddressEmbedded;
import ml.psj2867.fancake.entity.embedded.BankEmbedded;
import ml.psj2867.fancake.entity.embedded.TermsEmbedded;

@Entity(name = UserDetailEntity.ENTITY_NAME )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailEntity{
    public final static String ENTITY_NAME = "user_detail_info";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Embedded
    private BankEmbedded bank;
    @Embedded
    private AddressEmbedded address;
    @Embedded
    private TermsEmbedded terms;

    public final static Integer DELETE_USER_ID = 0;
    public static UserDetailEntity defaultDeletedUserDetail(UserDetailEntityDao userDetailDao){        
        return userDetailDao.findById(DELETE_USER_ID)
                     .orElseGet( ()->makeDefaultDeletedUserDetail(userDetailDao) );
    }
    private static UserDetailEntity makeDefaultDeletedUserDetail(UserDetailEntityDao userDetailDao){
        UserDetailEntity detail = UserDetailEntity.builder()
                                            .idx(DELETE_USER_ID)
                                            .build();
        userDetailDao.save(detail);
        return detail;
    }

}