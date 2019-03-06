package NidoConverter;

import java.io.Writer;
import java.util.List;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.processor.ObjectRowWriterProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;

public class NidoConverter {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// The settings object provides many configuration options
		CsvParserSettings parserSettings = new CsvParserSettings();

		// You can configure the parser to automatically detect what line
		// separator sequence is in the input
		parserSettings.setLineSeparatorDetectionEnabled(true);
		parserSettings.setHeaderExtractionEnabled(true);

		parserSettings.getFormat().setDelimiter(',');
		// BeanListProcessor converts each parsed row to an instance of a given
		// class, then stores each instance into a list.
		BeanListProcessor<RigaExport> rowProcessor = new BeanListProcessor<RigaExport>(RigaExport.class);

		// You can configure the parser to use a RowProcessor to process the
		// values of each parsed row.
		// You will find more RowProcessors in the
		// 'com.univocity.parsers.common.processor' package, but you can also
		// create your own.
		parserSettings.setRowProcessor(rowProcessor);

		// Let's consider the first parsed row as the headers of each column in
		// the file.
		parserSettings.setHeaderExtractionEnabled(true);

		// creates a parser instance with the given settings
		CsvParser parser = new CsvParser(parserSettings);

		// the 'parse' method will parse the file and delegate each parsed row
		// to the RowProcessor you defined

		EcivisCsvReader reader = new EcivisCsvReader("/pagamenti_nido_2017.csv");
		parser.parse(reader.getReader());

		List<RigaExport> beans = rowProcessor.getBeans();
		// get the parsed records from the RowListProcessor here.
		// Note that different implementations of RowProcessor will provide
		// different sets of functionalities.

		//// WRITING PHASE

		EcivisRowWriter owriter = new EcivisRowWriter();

		FixedWidthFields lengthsROW = new FixedWidthFields(1, 16, 1, 16, 9, 624, 1130, 1);
		FixedWidthWriterSettings settingsROW = new FixedWidthWriterSettings(lengthsROW);
		settingsROW.setNullValue(" ");
		settingsROW.getFormat().setPadding(' ');
		ObjectRowWriterProcessor processorROW = new ObjectRowWriterProcessor();
		settingsROW.setRowWriterProcessor(processorROW);

		FixedWidthFields lengthsMASTER = new FixedWidthFields(1, 5, 1, 17, 16, 60, 40, 2, 24, 20, 1, 8, 40, 2, 1, 4, 16,
				1, 100, 1438, 1);
		FixedWidthWriterSettings settingsMASTER = new FixedWidthWriterSettings(lengthsMASTER);
		settingsMASTER.setNullValue(" ");
		ObjectRowWriterProcessor processorMASTER = new ObjectRowWriterProcessor();
		settingsMASTER.setRowWriterProcessor(processorMASTER);

		FixedWidthFields lengthsFINAL = new FixedWidthFields(1, 5, 1, 17, 16, 60, 40, 2, 24, 20, 1, 8, 40, 2, 1, 4, 16,
				1, 100, 1438, 1);
		FixedWidthWriterSettings settingsFINAL = new FixedWidthWriterSettings(lengthsFINAL);
		settingsFINAL.setNullValue(" ");
		ObjectRowWriterProcessor processorFINAL = new ObjectRowWriterProcessor();
		settingsFINAL.setRowWriterProcessor(processorFINAL);

		Writer totalWriter = owriter.getWriter();

		// PRIMA RIGA
		FixedWidthWriter writerMASTER = new FixedWidthWriter(totalWriter, settingsMASTER);
		writerMASTER.addValue("0");
		writerMASTER.addValue("ASI00");
		writerMASTER.addValue("0");// 3
		writerMASTER.addValue(""); // 4
		writerMASTER.addValue("82000250504");// 5
		writerMASTER.addValue("COMUNE DI SAN MINIATO");// 6
		writerMASTER.addValue("SAN MINIATO"); // 7
		writerMASTER.addValue("PI"); // 8
		writerMASTER.addValue(""); // 9
		writerMASTER.addValue(""); // 10
		writerMASTER.addValue(""); // 11
		writerMASTER.addValue(""); // 12
		writerMASTER.addValue(""); // 13
		writerMASTER.addValue(""); // 14
		writerMASTER.addValue("0"); // 15
		writerMASTER.addValue("2017"); // 16
		writerMASTER.addValue(""); // 17
		writerMASTER.addValue(""); // 18
		writerMASTER.addValue("SCUOLA@COMUNE.SAN-MINIATO.PI.ITA"); // 19
		writerMASTER.addValue(""); //20
		writerMASTER.addValue("A"); //21

		writerMASTER.writeValuesToRow();
		// writerMASTER.close();

		// RIGHE CENTRALI 
		
		//(1, 16, 1, 16, 9, 625, 1130, 1);
		FixedWidthWriter writerROW = new FixedWidthWriter(totalWriter, settingsROW);
		for (RigaExport bean : beans) {
			writerROW.addValue("1");
			writerROW.addValue(bean.getCodiceFiscale());
			writerROW.addValue("1");
			writerROW.addValue("");
			writerROW.addValue(String.format("%09d",Math.round(Float.parseFloat(bean.getTotalePagato().replace(".", "").replace(',', '.')))));
			writerROW.addValue("");
			writerROW.addValue("");
			writerROW.addValue("A");
			writerROW.writeValuesToRow();
		}
		writerROW.writeValuesToRow();
		// writerROW.close();

		// ULTIMA RIGA
		FixedWidthWriter writerFINAL = new FixedWidthWriter(totalWriter, settingsFINAL);

		writerFINAL.addValue("9");
		writerFINAL.addValue("ASI00");
		writerFINAL.addValue("0");// 3
		writerFINAL.addValue(""); // 4
		writerFINAL.addValue("82000250504");// 5
		writerFINAL.addValue("COMUNE DI SAN MINIATO");// 6
		writerFINAL.addValue("SAN MINIATO"); // 7
		writerFINAL.addValue("PI"); // 8
		writerFINAL.addValue(""); // 9
		writerFINAL.addValue(""); // 10
		writerFINAL.addValue(""); // 11
		writerFINAL.addValue(""); // 12
		writerFINAL.addValue(""); // 13
		writerFINAL.addValue(""); // 14
		writerFINAL.addValue("0"); // 15
		writerFINAL.addValue("2017"); // 16
		writerFINAL.addValue(""); // 17
		writerFINAL.addValue(""); // 18
		writerFINAL.addValue("SCUOLA@COMUNE.SAN-MINIATO.PI.ITA"); // 19
		writerFINAL.addValue("");
		writerFINAL.addValue("A");

		writerFINAL.writeValuesToRow();
		writerFINAL.close();

	}

}
