package com.dude.dudeproject.System;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class QRGenerator {


    public static void generateQRCodeImage(String random)
            throws WriterException, IOException {

        try {
            File file = null;
            // 큐알이미지를 저장할 디렉토리 지정
            file = new File("./../QRimages");
            if(!file.exists()) {
                file.mkdirs();
            }
            String url = "http://localhost:8090/"+random;
            // 코드인식시 링크걸 URL주소
            String codeurl = new String(url.getBytes("UTF-8"), "ISO-8859-1");
            // 큐알코드 바코드 생상값
            int qrcodeColor =   0xFF2e4e96;
            // 큐알코드 배경색상값
            int backgroundColor = 0xFFFFFFFF;

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // 3,4번째 parameter값 : width/height값 지정
            BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE,300, 300);

            // 색상 지정 config
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);


            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);

            // ImageIO를 사용한 바코드 파일쓰기
            ImageIO.write(bufferedImage, "png", new File("./../QRimages/"+random+".png"));
            System.out.println("finish");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
