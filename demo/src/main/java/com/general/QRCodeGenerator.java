package com.general;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class QRCodeGenerator {

    public static void main(String[] args) {
        String text = "Hello, world!";
        String filePath = "qrcode.png";
        int width = 300;
        int height = 300;

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            File qrCodeFile = new File(filePath);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", new FileOutputStream(qrCodeFile));
            System.out.println("QR code generated successfully!");
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
