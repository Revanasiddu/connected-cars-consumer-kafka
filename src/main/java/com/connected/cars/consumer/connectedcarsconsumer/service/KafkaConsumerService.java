package com.connected.cars.consumer.connectedcarsconsumer.service;

import com.connected.cars.consumer.connectedcarsconsumer.model.FileDataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Revanasidd Namadev.
 * User: rnamade1
 * Date: 9/22/2021
 * Time: 4:18
 */
@Service
@Slf4j
public class KafkaConsumerService {

    @Autowired
    ConnectedCarsStoreDataToFile storeDataToFile;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.template.topic}")
    public void KafkaListener(String payload) throws IOException {
        String fileType = null;
        try {
            Map<String, String> dataMap = objectMapper.readValue(String.valueOf(payload), Map.class);
            fileType = dataMap.get("fileType");
            FileDataModel fileDataModel = objectMapper.convertValue(dataMap.get("fileDataModel"), FileDataModel.class);

            if ("xml".equalsIgnoreCase(fileType)) {
                storeDataToFile.parseObjectToXml(fileDataModel);
            } else {
                storeDataToFile.parseObjectToCsv(fileDataModel);
            }

        } catch (IOException e) {
            log.error("unable to write the data to a given file fileType={} error={}", fileType, e.getMessage());
        }

    }

}
