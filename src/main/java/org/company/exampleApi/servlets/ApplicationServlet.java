package org.company.exampleApi.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import org.company.exampleApi.services.PokemonService;
import org.company.exampleApi.services.StatsService;
import org.company.exampleApi.services.TrainerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "PokemonController", urlPatterns = {"/pokemons"}, loadOnStartup = 1)
public class ApplicationServlet extends HttpServlet {
    private final PokemonService pokemonService = new PokemonService();
    private final TrainerService trainerService = new TrainerService();
    private final StatsService statsService = new StatsService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        System.out.println("req uri = " + req.getRequestURI());
        System.out.println("req servlet path = " + req.getServletPath());

        Map<String, String> uriAsMap = mapUriString(req.getRequestURI());
        String response = "";
        try {
            response = getRecordString(uriAsMap);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("response = " + response);
        resp.getWriter().write(response);
        resp.getWriter().println("Heelo");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String uri = httpServletRequest.getRequestURI();
        Map<String, String> uriMap = mapUriString(uri);
        String response = addNewRecord(uriMap, httpServletRequest);

        System.out.println(response);
        httpServletResponse.getWriter().write(response);
    }

    private String addNewRecord(Map<String, String> uriMap, HttpServletRequest httpServletRequest) {
        String serviceType = uriMap.get("typeOfService");
        switch (serviceType) {
            case "pokemons":
                System.out.println("we are in case pokemons");
                return pokemonService.addPokemonByRequest(httpServletRequest);
            case "trainers":
                System.out.println("we are in case trainers");
                // TODO:
//                return trainerService.addTrainerByRequest(httpServletRequest);
            case "stats":
                // TODO:
                System.out.println("we are in case stats");
//                return statsService.addStatsByRequest(httpServletRequest);
            default:
                return "Invalid input";
        }
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String uri = httpServletRequest.getRequestURI();
        Map<String, String> uriMap = mapUriString(uri);
        String response = "";
        response = updateRecord(uriMap, httpServletRequest);

        System.out.println(response);
        httpServletResponse.getWriter().write(response);
    }

    private String updateRecord(Map<String, String> uriMap, HttpServletRequest httpServletRequest) {
        String serviceType = uriMap.get("typeOfService");

        switch (serviceType) {
            case "pokemons":
                System.out.println("we are in case pokemons");
                // TODO:
//                return pokemonService.updatePokemonByRequest(httpServletRequest);
            case "trainers":
                System.out.println("we are in case trainers");
                // TODO:
//                return trainerService.updateTrainerByRequest(httpServletRequest);
            case "stats":
                // TODO:
                System.out.println("we are in case stats");
//                return statsService.updateStatsByRequest(httpServletRequest);
            default:
                return "Invalid input";
        }
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String uri = httpServletRequest.getRequestURI();
        Map<String, String> uriMap = mapUriString(uri);
        String response = null;
        try {
            response = deleteRecord(uriMap, httpServletRequest);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(response);
        httpServletResponse.getWriter().write(response);
    }

    private String deleteRecord(Map<String, String> uriMap, HttpServletRequest httpServletRequest) throws SQLException {
        String serviceType = uriMap.get("typeOfService");
        switch (serviceType) {
            case "pokemons":
                System.out.println("we are in case pokemons");
                return pokemonService.deletePokemonByRequest(httpServletRequest);
            case "trainers":
                System.out.println("we are in case trainers");
                // TODO:
//                return trainerService.deleteTrainerByRequest(httpServletRequest);
            case "stats":
                // TODO:
                System.out.println("we are in case stats");
//                return statsService.deleteStatsByRequest(httpServletRequest);
            default:
                return "Invalid input";
        }
    }

    private Map<String, String> mapUriString(String uri) {
        System.out.println("uri = " + uri);
        List<String> uriList = new LinkedList<>(Arrays.asList(uri.split("/")));
        int slashIndex = 0;
        System.out.println(uriList);

        uriList.remove(slashIndex); // removing empty string
        System.out.println(uriList);

        int serviceTypeIndex = 0;
        int elementIdIndex = 1; // NOT ALWAYS REQUIRED!

        Map<String, String> uriMap = new HashMap<>();
        uriMap.put("typeOfService", uriList.get(serviceTypeIndex));

        if (uriList.size() == 2) {
            uriMap.put("id", uriList.get(elementIdIndex));
        }

        System.out.println(uriMap);
        return uriMap;
    }

    private String getRecordString(Map<String, String> uriMap) throws JsonProcessingException, SQLException {
        String serviceType = uriMap.get("typeOfService");
        switch (serviceType) {
            case "pokemons":
                System.out.println("we are in case pokemons");
                return pokemonService.getPokemonAsJson(uriMap);
            case "trainers":
                System.out.println("we are in case trainers");
                return trainerService.getTrainerAsJson(uriMap);
            case "stats":
                System.out.println("we are in case stats");
                return statsService.getStatsAsJson(uriMap);
            default:
                return "Invalid input";
        }
    }
}
