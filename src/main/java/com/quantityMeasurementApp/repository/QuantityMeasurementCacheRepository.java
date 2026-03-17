package com.quantityMeasurementApp.repository;

import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {


    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementCacheRepository.class);

    private static final QuantityMeasurementCacheRepository instance =
            new QuantityMeasurementCacheRepository();

    private final List<QuantityMeasurementEntity> cache =
            Collections.synchronizedList(new ArrayList<>());

    private QuantityMeasurementCacheRepository() {
        logger.info("QuantityMeasurementCacheRepository initialized.");
    }

    public static QuantityMeasurementCacheRepository getInstance() {
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        cache.add(entity);
        logger.info("Entity saved to cache: {}", entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return Collections.unmodifiableList(cache);
    }

    @Override
    public List<QuantityMeasurementEntity> findByOperation(String operation) {
        return cache.stream()
                .filter(e -> e.getOperation().equalsIgnoreCase(operation))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementEntity> findByMeasurementType(String measurementType) {
        return cache.stream()
                .filter(e -> measurementType.equalsIgnoreCase(e.getMeasurementType()))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalCount() {
        return cache.size();
    }

    @Override
    public void deleteAll() {
        cache.clear();
        logger.info("All entities deleted from cache.");
    }

    @Override
    public String getPoolStatistics() {
        return "Cache repository — total entries: " + cache.size();
    }
}