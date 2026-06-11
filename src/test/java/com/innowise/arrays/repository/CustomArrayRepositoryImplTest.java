package com.innowise.arrays.repository;

import com.innowise.arrays.comparator.impl.CustomArrayComparatorImpl;
import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.repository.impl.CustomArrayRepositoryImpl;
import com.innowise.arrays.service.StatisticService;
import com.innowise.arrays.service.impl.StatisticServiceImpl;
import com.innowise.arrays.warehouse.CustomArrayWarehouse;
import com.innowise.arrays.warehouse.impl.CustomArrayWarehouseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayRepositoryImplTest {
    private final CustomArrayRepository repository = CustomArrayRepositoryImpl.getInstance();
    private final CustomArrayWarehouse warehouse = CustomArrayWarehouseImpl.getInstance();
    private final StatisticService analyticService = new StatisticServiceImpl();

    @BeforeEach
    void setUp() {
        repository.clear();
        warehouse.clear();
    }

    @Test
    void shouldAddArrayToRepository() {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3});

        // when
        boolean result = repository.add(array);

        // then
        assertAll("Add array validation",
                () -> assertTrue(result),
                () -> assertEquals(1, repository.size()),
                () -> assertTrue(warehouse.getStatistic(array.getId()).isPresent())
        );
    }

    @Test
    void shouldRemoveArrayFromRepository() {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3});
        repository.add(array);

        // when
        boolean result = repository.remove(array);

        // then
        assertAll("Remove array validation",
                () -> assertTrue(result),
                () -> assertEquals(0, repository.size()),
                () -> assertFalse(warehouse.getStatistic(array.getId()).isPresent())
        );
    }

    @Test
    void shouldFindAllArrays() {
        // given
        repository.add(new CustomArray(new int[]{1, 2}));
        repository.add(new CustomArray(new int[]{3, 4, 5}));

        // when
        List<CustomArray> all = repository.findAll();

        // then
        assertEquals(2, all.size());
    }

    @Test
    void shouldQueryBySpecification() {
        // given
        CustomArray array1 = new CustomArray(new int[]{1, 2, 3});
        CustomArray array2 = new CustomArray(new int[]{10, 20, 30});
        repository.add(array1);
        repository.add(array2);

        // when
        List<CustomArray> result = repository.query(array -> {
            int sum = analyticService.calculateSumOfElements(array).orElse(0);
            return sum > 10;
        });

        // then
        assertAll("Query by sum specification validation",
                () -> assertEquals(1, result.size()),
                () -> assertEquals(array2.getId(), result.get(0).getId())
        );
    }

    @Test
    void shouldSortById() {
        // given
        CustomArray array1 = new CustomArray(new int[]{5, 2, 3});
        CustomArray array2 = new CustomArray(new int[]{1, 4});
        repository.add(array1);
        repository.add(array2);

        // when
        List<CustomArray> sorted = repository.sort(new CustomArrayComparatorImpl().byId());

        // then
        assertAll("Sort by ID validation",
                () -> assertTrue(sorted.get(0).getId().compareTo(sorted.get(1).getId()) < 0),
                () -> assertNotNull(sorted.get(0).getId()),
                () -> assertNotNull(sorted.get(1).getId())
        );
    }

    @Test
    void shouldSortByLength() {
        // given
        CustomArray array1 = new CustomArray(new int[]{1, 2, 3});
        CustomArray array2 = new CustomArray(new int[]{1, 2, 3, 4});
        repository.add(array1);
        repository.add(array2);

        // when
        List<CustomArray> sorted = repository.sort(new CustomArrayComparatorImpl().byLength());

        // then
        assertAll("Sort by length validation",
                () -> assertEquals(3, sorted.get(0).getLength()),
                () -> assertEquals(4, sorted.get(1).getLength()),
                () -> assertTrue(sorted.get(0).getLength() < sorted.get(1).getLength())
        );
    }

    @Test
    void shouldQueryBySpecificationUsingFindMinValue() {
        // given
        CustomArray array1 = new CustomArray(new int[]{5, 10, 15});
        CustomArray array2 = new CustomArray(new int[]{1, 2, 3});
        repository.add(array1);
        repository.add(array2);

        // when - find arrays where min value > 3
        List<CustomArray> result = repository.query(array -> {
            int min = analyticService.findMinValue(array).orElse(Integer.MAX_VALUE);
            return min > 3;
        });

        // then
        assertAll("Query by min value specification validation",
                () -> assertEquals(1, result.size()),
                () -> assertEquals(array1.getId(), result.get(0).getId()),
                () -> assertTrue(analyticService.findMinValue(result.get(0)).orElse(0) > 3)
        );
    }

    @Test
    void shouldQueryBySpecificationUsingFindMaxValue() {
        // given
        CustomArray array1 = new CustomArray(new int[]{1, 2, 3});
        CustomArray array2 = new CustomArray(new int[]{5, 10, 15});
        repository.add(array1);
        repository.add(array2);

        // when - find arrays where max value < 10
        List<CustomArray> result = repository.query(array -> {
            int max = analyticService.findMaxValue(array).orElse(Integer.MIN_VALUE);
            return max < 10;
        });

        // then
        assertAll("Query by max value specification validation",
                () -> assertEquals(1, result.size()),
                () -> assertEquals(array1.getId(), result.get(0).getId()),
                () -> assertTrue(analyticService.findMaxValue(result.get(0)).orElse(0) < 10)
        );
    }

    @Test
    void shouldQueryBySpecificationUsingCalculateAverage() {
        // given
        CustomArray array1 = new CustomArray(new int[]{1, 2, 3});
        CustomArray array2 = new CustomArray(new int[]{10, 20, 30});
        repository.add(array1);
        repository.add(array2);

        // when - find arrays where average > 5
        List<CustomArray> result = repository.query(array -> {
            double avg = analyticService.calculateAverageOfElements(array).orElse(0.0);
            return avg > 5;
        });

        // then
        assertAll("Query by average value specification validation",
                () -> assertEquals(1, result.size()),
                () -> assertEquals(array2.getId(), result.get(0).getId()),
                () -> assertTrue(analyticService.calculateAverageOfElements(result.get(0)).orElse(0.0) > 5)
        );
    }
}