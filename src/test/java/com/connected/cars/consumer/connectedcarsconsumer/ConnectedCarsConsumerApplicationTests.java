package com.connected.cars.consumer.connectedcarsconsumer;

import com.connected.cars.consumer.connectedcarsconsumer.controller.ConsumerController;
import com.connected.cars.consumer.connectedcarsconsumer.service.ConnectedCarsStoreDataToFile;
import com.connected.cars.consumer.connectedcarsconsumer.service.KafkaConsumerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConnectedCarsConsumerApplicationTests {

	@InjectMocks
	ConsumerController consumerController;

	@Mock
	ConnectedCarsStoreDataToFile storeDataToFile;

	@Test
	void getAllFileDataTest() {
		when(consumerController.getAllFileData()).thenReturn("true");

		Assertions.assertNotNull(storeDataToFile.csvToJson());
	}

}
