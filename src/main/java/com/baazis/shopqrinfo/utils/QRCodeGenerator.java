package com.baazis.shopqrinfo.utils;

import com.baazis.shopqrinfo.model.shops.Shops;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

    public static void generateQRCode(Shops shops) throws WriterException, IOException {
        String qrCodePath = "Shop\\";
        String qrCodeName = qrCodePath+ shops.getFirstName()+ shops.getId()+"-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                 "ID: "+ shops.getId()+ "\n"+
                         "Firstname: "+ shops.getFirstName()+ "\n"+
                         "Lastname: "+ shops.getLastName()+ "\n"+
                         "Mobile: "+ shops.getMobile()+ "\n" +
                         "Email: "+ shops.getEmail()+ "\n" +
                         "Link: "+ shops.getLink(), BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
}
