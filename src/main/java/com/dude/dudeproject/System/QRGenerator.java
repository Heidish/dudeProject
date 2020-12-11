package com.dude.dudeproject.System;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRGenerator {

        public static RandomClass random = new RandomClass();

        public static String r;



        public static byte[] generateQRCodeImage(String text, int width, int height)
                throws WriterException, IOException {


            r = random.numrandom(10);

            text = "http://localhost:8090/" + r;

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
            byte[] qrimage=out.toByteArray();

            return qrimage;
        }

}
