package com.STIproject.STI.controllers;


import com.STIproject.STI.exceptions.NotFoundException;
import com.STIproject.STI.projectData.CSVDataServices;
import com.STIproject.STI.projectData.ProjectDataService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;

/**
 * Passing data in GET requests
 */

@RestController

public class DataController {
    private CSVDataServices csvObj = new CSVDataServices();

    /**
     * Get json from oa.scv
     *
     * @return json file with today OA-covid info
     */
    @GetMapping("/OA-cov")
    public Map<String, String> getOACovid() {
        Map<String, String> json = csvObj.getOACovid();
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get json from oa.scv
     *
     * @param date date in dd-MM-yy format
     * @return json file with today OA-covid info
     */
    @GetMapping("/OA-cov/{date}")
    public Map<String, String> getOACovid(@PathVariable String date) {
        Map<String, String> json = csvObj.getOACovid(date);
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get json from oa.scv
     *
     * @return json file with today OA-Vaccination info
     */
    @GetMapping("/OA-vac")
    public Map<String, String> getOAVaccination() {
        Map<String, String> json = csvObj.getOAVaccination();
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get json from oa.scv
     *
     * @param date date in dd-MM-yy format
     * @return json file with today OA-Vaccination info
     */
    @GetMapping("/OA-vac/{date}")
    public Map<String, String> getOAVaccination(@PathVariable String date) {
        Map<String, String> json = csvObj.getOAVaccination(date);
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get json from who-cov.scv
     *
     * @param name country name
     * @return json file with today who-covid info
     */
    @GetMapping("/Who-cov/{name}")
    public Map<String, String> getWhoCovid(@PathVariable String name) {
        Map<String, String> json = csvObj.getWhoCovid(name);
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get json from who-cov.scv
     *
     * @param name country name
     * @param date date in dd-MM-yy format
     * @return json file with who-covid info for the date
     */
    @GetMapping("/Who-cov/{name}/{date}")
    public Map<String, String> getWhoCovid(@PathVariable String name, @PathVariable String date) {
        Map<String, String> json = csvObj.getWhoCovid(name, date);
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get json from who-vac.scv
     *
     * @param name country name
     * @return json file with today who-Vaccination info
     */
    @GetMapping("/Who-vac/{name}")
    public Map<String, String> getWhoVaccination(@PathVariable String name) {
        Map<String, String> json = csvObj.getWhoVaccination(name);
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get json from who-vac.scv
     *
     * @param name country name
     * @param date date in dd-MM-yy format
     * @return json file with who-Vaccination info for the date
     */
    @GetMapping("/Who-vac/{name}/{date}")
    public Map<String, String> getWhoVaccination(@PathVariable String name, @PathVariable String date) {
        Map<String, String> json = csvObj.getWhoVaccination(name, date);
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get list of country names from who-cov
     *
     * @return json file
     */
    @GetMapping("/statesCov")
    public Map<String, ArrayList<String>> getStatesWhoCovid() {
        Map<String, ArrayList<String>> json = csvObj.getStatesWhoCovid();
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * Get list of country names from who-vac
     *
     * @return json file
     */
    @GetMapping("/statesVac")
    public Map<String, ArrayList<String>> getWhoStatesVaccination() {
        Map<String, ArrayList<String>> json = csvObj.getWhoStatesVaccination();
        if (json == null) throw new NotFoundException();
        return json;
    }
    /**
     * Get list of dates from existing files in /appdata
     * @return json file
     */
//    @GetMapping("/dates")
//    public Map<String, SortedSet<String>> getFilesInAppdata() {
//        Map<String, SortedSet<String>> json = new ProjectDataService().filesInAppdata();
//        if (json == null) throw new NotFoundException();
//        return json;
//    }



    /**
     * mzcrVsWhoCases
     */

    @GetMapping("/mzcrVsWhoCases")
    public Map<String, Map<String, String>> getMzcrVsWhoCases (){
        Map<String, Map<String, String>> json = csvObj.MzcrVsWhoCases();
        if (json == null) throw new NotFoundException();
        return json;
    }
    /**
     * mzcrVsWhoVac
     */
    @GetMapping("/mzcrVsWhoVac")
    public Map<String, Map<String, String>> getMzcrVsWhoVac (){
        Map<String, Map<String, String>> json = csvObj.MzcrVsWhoVac();
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * existedDate
     */
    @GetMapping("/existedDate")
    public Map<String, SortedSet<String>> getFilesInAppdata() {
        Map<String, SortedSet<String>> json = new ProjectDataService().filesInAppdata();
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * whoCases
     */
    @GetMapping("/whoCases")
    public Map<Integer, Map<String, String>> getWhoCases(@RequestParam String date){
        Map<Integer, Map<String, String>> json = csvObj.whoCases(date);
        if (json == null) throw new NotFoundException();
        return json;
    }

    /**
     * whoVac
     */
    @GetMapping("/whoVac")
    public Map<Integer, Map<String, String>> getWhoVac(@RequestParam String date){
        Map<Integer, Map<String, String>> json = csvObj.whoVac(date);
        if (json == null) throw new NotFoundException();
        return json;
    }

}
