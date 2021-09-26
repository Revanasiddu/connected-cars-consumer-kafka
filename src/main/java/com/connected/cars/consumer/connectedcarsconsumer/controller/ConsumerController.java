package com.connected.cars.consumer.connectedcarsconsumer.controller;

import com.connected.cars.consumer.connectedcarsconsumer.service.ConnectedCarsStoreDataToFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Revanasidd Namadev.
 * User: rnamade1
 * Date: 9/26/2021
 * Time: 4:13
 */

@RestController
@RequestMapping(value = "${app.context.path}")
@Slf4j
public class ConsumerController {

    @Autowired
    ConnectedCarsStoreDataToFile storeDataToFile;

    @GetMapping(value = "/filedata",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String getAllFileData(){
        return storeDataToFile.csvToJson();
    }
}
