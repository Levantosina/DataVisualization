package com.app.measurement.thingSpeak;

import com.app.measurement.measurement.Measurement;
import com.app.measurement.measurement.MeasurementRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.logging.Logger;

@Service
public class ThingSpeakService {

    private static final Logger LOGGER = Logger.getLogger(ThingSpeakService.class.getName());

    private final RestTemplate restTemplate;
    private final MeasurementRepository measurementRepository;


    @Autowired
    public ThingSpeakService(RestTemplate restTemplate, MeasurementRepository measurementRepository) {
        this.restTemplate = restTemplate;
        this.measurementRepository = measurementRepository;
    }

    public String fetchDataFromThingSpeak() {
        String fullUrl = "https://api.thingspeak.com/channels/2105786/fields/3,4.json?&results=2000";
        LOGGER.info("Fetching data from ThingSpeak. URL: " + fullUrl);
        String response = restTemplate.getForObject(fullUrl, String.class);
        LOGGER.info("ThingSpeak API Response: " + response);
        return response;
    }

    public void saveDataToDatabase() {
        String data = fetchDataFromThingSpeak();

        LOGGER.info("ThingSpeak Data: " + data);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(data);


            JsonNode feedsNode = rootNode.path("feeds");

            for (JsonNode entryNode : feedsNode) {
                JsonNode field3Node = entryNode.path("field3");
                JsonNode field4Node = entryNode.path("field4");

                if (!field3Node.isMissingNode() && !field4Node.isMissingNode()) {
                    String field3String = field3Node.asText();
                    String field4String = field4Node.asText();

                    if (field3String != null && field4String != null && !field4String.equals("null")) {
                        try {
                            Float field3Value = Float.parseFloat(field3String);


                            Float field4Value = Float.parseFloat(field4String);


                            field3Value = (field3Value == null) ? 0.0f : field3Value;
                            field4Value = (field4Value == null) ? 0.0f : field4Value;

                            // Create a Measurement object and save it to the database
                            Measurement measurement = new Measurement();
                            measurement.setCurrent(field4Value);
                            measurement.setVoltage(field3Value);

                            measurementRepository.save(measurement);
                            LOGGER.info("Saved measurement to the database: " + measurement);
                        } catch (NumberFormatException e) {
                            LOGGER.warning("Error parsing values for entry: " + entryNode + ". Exception: " + e.getMessage());
                        }
                    } else {

                        LOGGER.warning("Skipping entry with null values in field3 or field4. Entry: " + entryNode);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.warning("Error parsing ThingSpeak response: " + e.getMessage());
        }
    }
}