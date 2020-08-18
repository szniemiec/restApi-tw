package org.company.services;

import com.google.gson.Gson;
import org.company.database.DbCredentials;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONService {

    public DbCredentials readEnvironment() {

        try {
            Gson gson = new Gson();
            BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/environmentSpecs/environment.json"));
            DbCredentials credentials = gson.fromJson(reader, DbCredentials.class);
            reader.close();
            return credentials;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
