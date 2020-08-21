package org.company.exampleApi.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
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

@WebServlet (name = "PokemonController", urlPatterns = {"/pokemons"}, loadOnStartup = 1)
public class ApplicationServlet extends HttpServlet {
    private PokemonService pokemonService = new PokemonService();
    private TrainerService trainerService = new TrainerService();
    private StatsService statsService = new StatsService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            resp.setStatus(200);


            System.out.println("req = " + req);
            System.out.println("req uri = " + req.getRequestURI());
            System.out.println("req path translated = " + req.getPathTranslated());
            System.out.println("req method = " + req.getMethod());
            System.out.println("req servlet path = " + req.getServletPath());
            System.out.println("resp status = " + resp.getStatus());


            Map<String, String> uriAsMap = mapUriString(req.getRequestURI());
            String response = "";
            try {
                response = getEntityString(uriAsMap);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            System.out.println("response = " + response);
            resp.getWriter().write(response);
            resp.getWriter().println("Heelo");
        }




        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
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
        uriMap.put("elementType", uriList.get(serviceTypeIndex));

        if (uriList.size() == 2) {
            uriMap.put("id", uriList.get(elementIdIndex));
        }

        System.out.println(uriMap);
        return uriMap;
    }

    private String getEntityString(Map<String, String> uriMap) throws JsonProcessingException, SQLException {
        String elementTypeString = uriMap.get("typeOfService");
        switch (elementTypeString) {
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
