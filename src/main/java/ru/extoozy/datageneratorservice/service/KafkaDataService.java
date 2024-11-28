package ru.extoozy.datageneratorservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import ru.extoozy.datageneratorservice.model.Data;

@Service
@RequiredArgsConstructor
public class KafkaDataService implements DataService {

    private final KafkaSender<String, Object> sender;


    @Override
    public void send(Data data) {

        String topic = switch (data.getMeasurementType()) {
            case TEMPERATURE -> "data-temperature";
            case VOLTAGE -> "data-voltage";
            case POWER -> "data-power";
        };


        sender.send(
                Mono.just(
                        SenderRecord.create(
                                topic,
                                0,
                                System.currentTimeMillis(),
                                String.valueOf(data.hashCode()),
                                data,
                                null
                        )
                )
        ).subscribe();
    }
}
