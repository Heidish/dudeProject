package com.dude.dudeproject.System;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
@Cacheable(cacheNames = "qr-code-cache", sync = true)
public class ImageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
    private static RandomClass r = new RandomClass();

    public Mono<byte[]> generateQRCode(String text, int width, int height) {

        Assert.hasText(String.valueOf(text), "문자는 비어있어야 합니다.");
        Assert.isTrue(width > 0, "너비는 0 이상 이여야 합니다.");
        Assert.isTrue(height > 0, "높이는 0 이상 이여야 합니다.");


        return Mono.create(sink -> {
            LOGGER.info("이미지 만들기:  site=[{}], width=[{}], height=[{}]", text, width, height);
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                String ssd = text+r.numrandom();
                BitMatrix matrix = new MultiFormatWriter().encode(ssd,BarcodeFormat.QR_CODE, width, height);
                MatrixToImageWriter.writeToStream(matrix, MediaType.IMAGE_PNG.getSubtype(), baos, new MatrixToImageConfig());
                sink.success(baos.toByteArray());

                byte[] array = baos.toByteArray();
                System.out.println("값 모에용?" +Arrays.toString(array));

                System.out.println("값 나와요: ?" +r.numrandom().toString());






            } catch (WriterException | IOException e) {
                sink.error(e);
            }

        });

    }

//    @CacheEvict(cacheNames = "qr-code-cache", allEntries = true)
//    public void purgeCache() {
//        LOGGER.info("캐시 무효화");
//    }
}
