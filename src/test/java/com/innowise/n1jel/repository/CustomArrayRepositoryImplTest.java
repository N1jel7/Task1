package com.innowise.n1jel.repository;

import com.innowise.n1jel.comparator.impl.CustomArrayComparatorImpl;
import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.repository.impl.CustomArrayRepositoryImpl;
import com.innowise.n1jel.service.AnalyticService;
import com.innowise.n1jel.service.impl.AnalyticServiceImpl;
import com.innowise.n1jel.warehouse.CustomArrayWarehouse;
import com.innowise.n1jel.warehouse.impl.CustomArrayWarehouseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayRepositoryImplTest {
    private CustomArrayRepository repository;
    private CustomArrayWarehouse warehouse;
    private AnalyticService analyticService;

    @BeforeEach
    void setUp() {
        repository = CustomArrayRepositoryImpl.getInstance();
        warehouse = CustomArrayWarehouseImpl.getInstance();
        analyticService = new AnalyticServiceImpl();

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
        assertTrue(result);
        assertEquals(1, repository.size());
        assertTrue(warehouse.getStatistic(array.getId()).isPresent());
    }

    @Test
    void shouldRemoveArrayFromRepository() {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3});
        repository.add(array);

        // when
        boolean result = repository.remove(array);

        // then
        assertTrue(result);
        assertEquals(0, repository.size());
        assertFalse(warehouse.getStatistic(array.getId()).isPresent());
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
        assertEquals(1, result.size());
        assertEquals(array2.getId(), result.get(0).getId());
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
        assertTrue(sorted.get(0).getId().compareTo(sorted.get(1).getId()) < 0);
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
        assertEquals(3, sorted.get(0).getLength());
        assertEquals(4, sorted.get(1).getLength());
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
        assertEquals(1, result.size());
        assertEquals(array1.getId(), result.get(0).getId());
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
        assertEquals(1, result.size());
        assertEquals(array1.getId(), result.get(0).getId());
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
        assertEquals(1, result.size());
        assertEquals(array2.getId(), result.get(0).getId());
    }

}
