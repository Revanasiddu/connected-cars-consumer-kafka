package com.connected.cars.consumer.connectedcarsconsumer.service;

import com.connected.cars.consumer.connectedcarsconsumer.model.FileDataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Revanasidd Namadev.
 * User: rnamade1
 * Date: 9/26/2021
 * Time: 4:48
 */

@Service
@Slf4j
public class ConnectedCarsStoreDataToFile {

    @Autowired
    ObjectMapper objectMapper;

    @Value("${file.path.xml}")
    private String xmlFilePath;

    @Value("${file.path.csv}")
    private String csvFilePath;

    public void parseObjectToCsv(FileDataModel fileDataModel) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(csvFilePath)) {
            CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
            Map<String, String> keys = objectMapper.convertValue(fileDataModel, Map.class);
            keys.keySet().stream().forEach(key -> csvSchemaBuilder.addColumn(key));

            CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.writerFor(FileDataModel.class)
                    .with(csvSchema)
                    .writeValue(fileOutputStream, fileDataModel);
        }
    }

    public String csvToJson() {
        String data = null;
        try (InputStream input = new FileInputStream(csvFilePath)) {
            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            // Read data from CSV file
            List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

            data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void parseObjectToXml(FileDataModel xmlModel) {
        try {
            JAXBContext contextObj = JAXBContext.newInstance(FileDataModel.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.marshal(xmlModel, new FileOutputStream(xmlFilePath));
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage());
        }
    }

    /*public String XmltoJson() {
        String fileData = null;
        try (InputStream input = new FileInputStream(xmlFilePath)) {
            xmlMapper xmlMapper = new xmlMapper();
            FileDataModel fileDataModel = xmlMapper.readValue(input, FileDataModel.class);
            ObjectMapper mapper = new ObjectMapper();
            fileData = mapper.writeValueAsString(fileDataModel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }*/
}
