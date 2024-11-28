package ru.extoozy.datageneratorservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.extoozy.datageneratorservice.model.Data;
import ru.extoozy.datageneratorservice.model.DataTestOptions;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TestDataServiceImpl implements TestDataService {

    private final DataService dataService;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    @Override
    public void sendMessage(DataTestOptions options) {
        if (options.getMeasurementTypes().length > 0) {
            executorService.scheduleAtFixedRate(
                    () -> {
                        Data data = Data.builder()
                                .sensorId((long) getRandomNumber(1, 10))
                                .timestamp(LocalDateTime.now())
                                .measurementType(getRandomMeasurementType(options.getMeasurementTypes()))
                                .measurement(getRandomNumber(15, 100))
                                .build();
                        dataService.send(data);
                    },
                    0,
                    options.getDelayInSeconds(),
                    TimeUnit.SECONDS
            );
        }
    }

    private double getRandomNumber(int min, int max) {
        return (Math.random() * (max - min)) + min;
    }

    private Data.MeasurementType getRandomMeasurementType(Data.MeasurementType[] measurementTypes) {
        int randomIndex = (int) getRandomNumber(0, measurementTypes.length);
        return measurementTypes[randomIndex];
    }
}
