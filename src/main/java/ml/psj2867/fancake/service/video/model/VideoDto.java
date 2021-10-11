package ml.psj2867.fancake.service.video.model;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ml.psj2867.fancake.entity.VideoEntity;
import ml.psj2867.fancake.service.channel.model.ChannelDto;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class VideoDto{

    private ChannelDto channel;
    private String title;
    private double marketCap;
    private double pricePerShare;
    private int totalAmount;
    private int currentAmount;
    private int videoIdx;
    private LocalDateTime expirationDate;
    private boolean isOnSale;

    public static VideoDto of(VideoEntity videoEntity, EntityManager em){
        VideoDto videoDto = of(videoEntity);
        videoDto.setCurrentAmount(videoEntity.getCurrentStockSize(em));
        return videoDto;
    }
    private static VideoDto of(VideoEntity videoEntity){
        return VideoDto.builder()
                    .title(videoEntity.getVideoTitle())
                    .expirationDate(videoEntity.getExpirationDate())
                    .marketCap(videoEntity.getPricePerShare() * videoEntity.getStockSize())
                    .pricePerShare(videoEntity.getPricePerShare())
                    .totalAmount(videoEntity.getStockSize())
                    .channel(ChannelDto.of(videoEntity.getChannel()))
                    .videoIdx(videoEntity.getIdx())
                    .isOnSale(checkOnSale(videoEntity))
                    .build();
    }
    private static boolean checkOnSale(VideoEntity videoEntity){
        return videoEntity.getExpirationDate().isAfter(LocalDateTime.now());
    }

}
