package ml.psj2867.demo.service.video.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ml.psj2867.demo.entity.StockEntity;
import ml.psj2867.demo.entity.UserEntity;
import ml.psj2867.demo.entity.VideoEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BuyStockForm {
    @NotNull
    private int size;


    public StockEntity toEntity(VideoEntity video , UserEntity user){
        return StockEntity.builder()
                            .video(video)
                            .owner(user)
                            .price(video.getPricePerShare())
                            .size(this.getSize())
                            .build();
    }
}