package com.dude.dudeproject.System;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class ImageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
    private static RandomClass r = new RandomClass();   //
    static String random;

    /**
     * 난수 생성 method
     * @return 난수값
     */
    public static String generateRandom() {
        //랜덤값 추출
        random = r.numrandom(10);
        System.out.println("랜덤값 : " + random);
        return random;
    }

    /**
     * QR image 생성 method
     * @param random
     * @param width
     * @param height
     * @return
     */
    public static byte[] getQRCodeImage(String random, int width, int height) {
        BufferedImage image = null;
        String text= "http://localhost:8090/" + random;

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           // MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);

            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF2e4e96,0xFFFFFFFF);

            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }

    }
}
