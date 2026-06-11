package com.innowise.arrays.app;

import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.exception.CustomArrayException;
import com.innowise.arrays.factory.CustomArrayFactoryImpl;
import com.innowise.arrays.parser.CustomArrayParserImpl;
import com.innowise.arrays.reader.CustomArrayReader;
import com.innowise.arrays.reader.CustomArrayReaderImpl;
import com.innowise.arrays.service.StatisticService;
import com.innowise.arrays.service.SortService;
import com.innowise.arrays.service.impl.StatisticServiceImpl;
import com.innowise.arrays.service.impl.SortServiceImpl;
import com.innowise.arrays.validator.CustomArrayLineValidatorImpl;
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
        StatisticService analyticService = new StatisticServiceImpl();
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
