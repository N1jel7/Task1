package com.innowise.n1jel.app;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;
import com.innowise.n1jel.factory.CustomArrayFactoryImpl;
import com.innowise.n1jel.parser.CustomArrayParserImpl;
import com.innowise.n1jel.reader.CustomArrayReader;
import com.innowise.n1jel.reader.CustomArrayReaderImpl;
import com.innowise.n1jel.service.AnalyticService;
import com.innowise.n1jel.service.SortService;
import com.innowise.n1jel.service.impl.AnalyticServiceImpl;
import com.innowise.n1jel.service.impl.SortServiceImpl;
import com.innowise.n1jel.validator.CustomArrayLineValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    private final static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws CustomArrayException {
        log.info("Application started");

        // 1. Read file
        CustomArrayReader reader = new CustomArrayReaderImpl();
        List<String> lines = reader.readAllLinesFromFile("data/input.txt");

        // 2. Validate and parse
        CustomArrayLineValidatorImpl validator = new CustomArrayLineValidatorImpl();
        CustomArrayParserImpl parser = new CustomArrayParserImpl();
        CustomArrayFactoryImpl factory = new CustomArrayFactoryImpl();

        List<CustomArray> arrays = new ArrayList<>();

        for (String line : lines) {
            if (validator.isValidLine(line)) {
                Optional<int[]> parsedData = parser.parseIntFromString(line);
                if (parsedData.isPresent()) {
                    CustomArray array = factory.createCustomArray(parsedData.get());
                    arrays.add(array);
                    log.info("Created array: {}", array);
                }
            } else {
                log.warn("Invalid line skipped: {}", line);
            }
        }

        // 3. Process arrays
        AnalyticService analyticService = new AnalyticServiceImpl();
        SortService sortService = new SortServiceImpl();

        for (CustomArray array : arrays) {
            log.info("Processing array: {}", array.getArray());

            // Find min and max
            Optional<Integer> min = analyticService.findMinValue(array);
            Optional<Integer> max = analyticService.findMaxValue(array);
            Optional<Integer> sum = analyticService.calculateSumOfElements(array);
            Optional<Double> average = analyticService.calculateAverageOfElements(array);

            min.ifPresent(v -> log.info("Min: {}", v));
            max.ifPresent(v -> log.info("Max: {}", v));
            sum.ifPresent(v -> log.info("Sum: {}", v));
            average.ifPresent(v -> log.info("Average: {}", v));

            // Sort
            sortService.bubbleSort(array);
            log.info("After bubble sort: {}", array.getArray());
        }

        log.info("Application finished");
    }
}
