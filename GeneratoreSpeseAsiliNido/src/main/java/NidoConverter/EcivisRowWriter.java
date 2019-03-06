package NidoConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class EcivisRowWriter {
	private static final String OUTPUT_FILE = "C:\\Users\\administrator\\Desktop\\TESTS\\testFile.txt";

	public EcivisRowWriter() {
	}

	public Writer getWriter() {
		Writer w = null;
		try {
			w = new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE), "UTF-8");
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return w;
	}

}
