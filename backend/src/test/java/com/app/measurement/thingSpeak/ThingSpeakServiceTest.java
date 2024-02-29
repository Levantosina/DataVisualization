package com.app.measurement.thingSpeak;


import com.app.measurement.measurement.MeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


class ThingSpeakServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MeasurementRepository measurementRepository;

    @InjectMocks
    private ThingSpeakService thingSpeakService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchDataFromThingSpeak() {

        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn("Mocked ThingSpeak Data");

        String result = thingSpeakService.fetchDataFromThingSpeak();

        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));

        assertEquals("Mocked ThingSpeak Data", result);
    }

    @Test
    void TestSaveDataToDatabase() {

        String testData = "{ \"feeds\": [{ \"field3\": \"10.5\", \"field4\": \"20.3\" }]}";

        when(thingSpeakService.fetchDataFromThingSpeak()).thenReturn(testData);

       thingSpeakService.saveDataToDatabase();

      verify(measurementRepository).save(any());
    }
}
