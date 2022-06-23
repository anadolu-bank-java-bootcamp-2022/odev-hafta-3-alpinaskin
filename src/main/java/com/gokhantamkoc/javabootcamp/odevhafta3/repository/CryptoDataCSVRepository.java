package com.gokhantamkoc.javabootcamp.odevhafta3.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ch.qos.logback.core.util.TimeUtil;
import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;
import com.gokhantamkoc.javabootcamp.odevhafta3.util.TimeUtils;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CryptoDataCSVRepository implements CSVRepository {
	
	private final String COMMA_DELIMITER = ",";

	@Override
	public List<Candle> readCSV(String filename) throws FileNotFoundException, IOException {
		List<Candle> candles = new ArrayList<Candle>();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
		// Bu alandan itibaren kodunuzu yazabilirsiniz
		CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
				.withSkipLines(1)
				.build();
		candles = csvReader.readAll().stream().map(data -> {
			Candle candle = new Candle(Long.parseLong(data[0]), // date
					Double.parseDouble(data[3]), // open
					Double.parseDouble(data[4]), // high
					Double.parseDouble(data[5]), // low
					Double.parseDouble(data[6]), // close
					Double.parseDouble(data[7])); // volume
			System.out.println("Candle değer: " + candle.getTime() + "  close:" + candle.getClose() + " high:" + candle.getHigh());
			return candle;
		}).collect(Collectors.toList());
		// Bu alandan sonra kalan kod'a dokunmayiniz.
		return candles;
	}

}
