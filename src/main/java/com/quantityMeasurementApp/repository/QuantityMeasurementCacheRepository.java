package com.quantityMeasurementApp.repository;

import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    // ✅ Eager initialization — thread-safe, no race condition
    private static final QuantityMeasurementCacheRepository instance =
            new QuantityMeasurementCacheRepository();

    // ✅ Thread-safe list
    private final List<QuantityMeasurementEntity> cache =
            Collections.synchronizedList(new ArrayList<>());

    private QuantityMeasurementCacheRepository(){}

    public static QuantityMeasurementCacheRepository getInstance(){
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity){
        if(entity == null){
            throw new IllegalArgumentException("Entity cannot be null");
        }
        cache.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll(){
        return Collections.unmodifiableList(cache); // ✅ Bahar se modify na ho
    }
}