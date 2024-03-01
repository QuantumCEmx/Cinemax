package com.general;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class Qr {

	public static String getQr(String text) {
		String stringName = "TestQr";
        int width = 40;
        int height = 40;
		Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
		qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		qrParam.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, qrParam);
			stringName = toAscii(bitMatrix);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringName;
	}

	public static String toAscii(BitMatrix bitMatrix) {
		StringBuilder sb = new StringBuilder();
		for (int rows = 0; rows < bitMatrix.getHeight(); rows++) {
			for (int cols = 0; cols < bitMatrix.getWidth(); cols++) {
				boolean x = bitMatrix.get(rows, cols);
				if (!x) {
					// white
					sb.append("\033[47m  \033[0m");
				} else {
					sb.append("\033[40m  \033[0m");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/* public static void main(String[] args) throws Exception {
		String text = "https://github.com/zhangshanhai/java-qrcode-terminal ";

		System.out.println(getQr(text));
	} */
}
