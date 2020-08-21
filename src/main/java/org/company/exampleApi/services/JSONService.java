package org.company.exampleApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.company.exampleApi.database.DbCredentials;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONService {
    private ObjectMapper mapper;

    public DbCredentials readEnvironment() {

        try {
            Gson gson = new Gson();
            BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/environmentSpecs/localEnvironment.json"));
            DbCredentials credentials = gson.fromJson(reader, DbCredentials.class);
            reader.close();
            return credentials;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        this.mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        return jsonString;
    }

}
