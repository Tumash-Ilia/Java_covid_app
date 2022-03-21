package com.STIproject.STI.projectData;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVDataServices {
    private ProjectDataService pd = new ProjectDataService();

    /**
     * Converting data from csv to Map
     *
     * @return Map<String, String> with covid data
     */
    public Map<String, String> getOACovid() {
        String fileName = "appdata" + File.separator + "mzcr-" + pd.getDateToday() + ".csv";
        List<String[]> records = null;
        Map<String, String> oaMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        for (String[] record : records) {
            oaMap.put("Name", record[0]);
            oaMap.put("Cases - cumulative total", record[1]);
            oaMap.put("Cases - newly reported in last 24 hours", record[2]);
            oaMap.put("Date", record[4]);
        }
        return oaMap;
    }

    /**
     * Converting data from csv to Map
     *
     * @param date date in dd-MM-yy format
     * @return Map<String, String> with covid data
     */
    public Map<String, String> getOACovid(String date) {
        String fileName = "appdata" + File.separator + "mzcr-" + date + ".csv";
        List<String[]> records = null;
        Map<String, String> oaMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        for (String[] record : records) {
            oaMap.put("Name", record[0]);
            oaMap.put("Cases - cumulative total", record[1]);
            oaMap.put("Cases - newly reported in last 24 hours", record[2]);
            oaMap.put("Date", record[4]);
        }
        return oaMap;
    }

    /**
     * Converting data from csv to Map
     *
     * @return Map<String, String> with vaccination data
     */
    public Map<String, String> getOAVaccination() {
        String fileName = "appdata" + File.separator + "mzcr-" + pd.getDateToday() + ".csv";
        List<String[]> records = null;
        Map<String, String> oaMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        for (String[] record : records) {
            oaMap.put("Name", record[0]);
            oaMap.put("TOTAL_VACCINATIONS", record[3]);
            oaMap.put("TOTAL_VACCINATIONS_PER100", record[5]);
            oaMap.put("Date", record[4]);
        }
        return oaMap;

    }

    /**
     * Converting data from csv to Map
     *
     * @param date date in dd-MM-yy format
     * @return Map<String, String> with vaccination data
     */

    public Map<String, String> getOAVaccination(String date) {
        String fileName = "appdata" + File.separator + "mzcr-" + date + ".csv";
        List<String[]> records = null;
        Map<String, String> oaMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        for (String[] record : records) {
            oaMap.put("Name", record[0]);
            oaMap.put("TOTAL_VACCINATIONS", record[3]);
            oaMap.put("TOTAL_VACCINATIONS_PER100", record[5]);
            oaMap.put("Date", record[4]);
        }
        return oaMap;

    }

    /**
     * Converting data from csv to Map
     *
     * @param name country name
     * @return Map<String, String> with covid data
     */
    public Map<String, String> getWhoCovid(String name) {
        String fileName = "appdata" + File.separator + "who-cases-" + pd.getDateToday() + ".csv";
        List<String[]> records = null;
        Map<String, String> whoMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        for (String[] record : records) {
            if (record[0].equals(name)) {
                whoMap.put("Name", record[0]);
                whoMap.put("Cases - cumulative total", record[1]);
                whoMap.put("Cases - newly reported in last 24 hours", record[2]);
                whoMap.put("Date", record[3]);
            }
        }
        return whoMap;
    }

    /**
     * Converting data from csv to Map
     *
     * @param name country name
     * @param date date in dd-MM-yy format
     * @return Map<String, String> with covid data
     */
    public Map<String, String> getWhoCovid(String name, String date) {
        String fileName = "appdata" + File.separator + "who-cases-" + date + ".csv";
        List<String[]> records = null;
        Map<String, String> whoMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        for (String[] record : records) {
            if (record[0].equals(name)) {
                whoMap.put("Name", record[0]);
                whoMap.put("Cases - cumulative total", record[1]);
                whoMap.put("Cases - newly reported in last 24 hours", record[2]);
                whoMap.put("Date", record[3]);
            }
        }
        return whoMap;
    }

    /**
     * Converting data from csv to Map
     *
     * @param name country name
     * @return Map<String, String> with vaccination data
     */
    public Map<String, String> getWhoVaccination(String name) {
        String fileName = "appdata" + File.separator + "who-vaccination-" + pd.getDateToday() + ".csv";
        List<String[]> records;
        Map<String, String> whoMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        for (String[] record : records) {
            if (record[0].equals(name)) {
                whoMap.put("Name", record[0]);
                whoMap.put("TOTAL_VACCINATIONS", record[1]);
                whoMap.put("TOTAL_VACCINATIONS_PER100", record[2]);
                whoMap.put("Date", record[3]);
            }
        }
        return whoMap;

    }

    /**
     * Converting data from csv to Map
     *
     * @param name country name
     * @param date date in dd-MM-yy format
     * @return Map<String, String> with vaccination data
     */

    public Map<String, String> getWhoVaccination(String name, String date) {
        String fileName = "appdata" + File.separator + "who-vaccination-" + date + ".csv";
        List<String[]> records;
        Map<String, String> whoMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        for (String[] record : records) {
            if (record[0].equals(name)) {
                whoMap.put("Name", record[0]);
                whoMap.put("TOTAL_VACCINATIONS", record[1]);
                whoMap.put("TOTAL_VACCINATIONS_PER100", record[2]);
                whoMap.put("Date", record[3]);
            }
        }
        return whoMap;
    }

    /**
     * Get map of country names from who-cov
     *
     * @return Map<String, ArrayList < String>> "Name" : country names list
     */
    public Map<String, ArrayList<String>> getStatesWhoCovid() {
        String fileName = "appdata" + File.separator + "who-cases-" + pd.getDateToday() + ".csv";
        List<String[]> records = null;
        Map<String, ArrayList<String>> whoMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        ArrayList<String> list = new ArrayList<>();
        for (String[] record : records) {
            list.add(record[0]);
        }
        whoMap.put("Name", list);
        return whoMap;
    }


    /**
     * Get map of country names from who-vac
     *
     * @return Map<String, ArrayList < String>> "Name" : country names list
     */
    public Map<String, ArrayList<String>> getWhoStatesVaccination() {
        String fileName = "appdata" + File.separator + "who-vaccination-" + pd.getDateToday() + ".csv";
        List<String[]> records;
        Map<String, ArrayList<String>> whoMap = new HashMap<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        ArrayList<String> list = new ArrayList<>();
        for (String[] record : records) {
            list.add(record[0]);
        }
        whoMap.put("Name", list);
        return whoMap;
    }

    /**
     * NEW METHODS
     */
    public static List<String[]> readCSV(String fileName) {
        List<String[]> records;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            return null;
        }
        return records;
    }

    public Map<String, Map<String, String>> MzcrVsWhoCases() {
        Map<String, Map<String, String>> result = new HashMap<>();
        Map<String, SortedSet<String>> dates = new ProjectDataService().filesInAppdata();
        for (String date :
                dates.get("dates")
        ) {
            Map<String, String> row = new HashMap<>();
            String fileNameWho = "appdata" + File.separator + "who-cases-" + date + ".csv";
            String fileNameMzcr = "appdata" + File.separator + "mzcr-" + date + ".csv";
            List<String[]> whoCSV = readCSV(fileNameWho);
            List<String[]> mzcrCSV = readCSV(fileNameMzcr);

            for (String[] record : whoCSV) {
                if (record[2].equals("Czechia")) {
                    row.put("date", date);
                    row.put("who_24_hours", record[4]);
                    row.put("who_total", record[3]);
                }
            }
            for (String[] record : mzcrCSV) {
                row.put("mzcr_24_hours", record[6]);
                row.put("mzcr_total", record[3]);
            }
            result.put(date, row);
        }
        return result;
    }


    public Map<String, Map<String, String>> MzcrVsWhoVac() {
        Map<String, Map<String, String>> result = new HashMap<>();
        Map<String, SortedSet<String>> dates = new ProjectDataService().filesInAppdata();
        for (String date :
                dates.get("dates")
        ) {
            Map<String, String> row = new HashMap<>();
            String fileNameWho = "appdata" + File.separator + "who-vaccination-" + date + ".csv";
            String fileNameMzcr = "appdata" + File.separator + "mzcr-" + date + ".csv";
            List<String[]> whoCSV = readCSV(fileNameWho);
            List<String[]> mzcrCSV = readCSV(fileNameMzcr);

            for (String[] record : whoCSV) {
                if (record[2].equals("Czechia")) {
                    row.put("file_date", date);
                    row.put("who_last_update_date", record[1]);
                    row.put("who_total_vac", record[3]);
                }
            }
            for (String[] record : mzcrCSV) {
                row.put("mzcr_last_update_date", record[1]);
                row.put("mzcr_total_vac", record[4]);
            }
            result.put(date, row);
        }
        return result;
    }

    public Map<Integer, Map<String, String>> whoCases(String date) {
        Map<Integer, Map<String, String>> result = new HashMap<>();
        String fileNameWho = "appdata" + File.separator + "who-cases-" + date + ".csv";
        List<String[]> whoCSV = readCSV(fileNameWho);
        for (int i = 0; i < whoCSV.size(); i++) {
            String[] record = whoCSV.get(i);
            Map<String, String> row = new HashMap<>();
            row.put("modified_date", record[0]);
            row.put("yesterday_date", record[1]);
            row.put("country", record[2]);
            row.put("cases_total", record[3]);
            row.put("confirmed_cases_yesterday", record[4]);
            result.put(i, row);
        }
        return result;
    }

    public Map<Integer, Map<String, String>> whoVac(String date) {
        Map<Integer, Map<String, String>> result = new HashMap<>();
        String fileNameWho = "appdata" + File.separator + "who-vaccination-" + date + ".csv";
        List<String[]> whoCSV = readCSV(fileNameWho);
        for (int i = 0; i < whoCSV.size(); i++) {
            String[] record = whoCSV.get(i);
            Map<String, String> row = new HashMap<>();
            row.put("modified_date", record[0]);
            row.put("date_updated", record[1]);
            row.put("country", record[2]);
            row.put("vaccination_total", record[3]);
            row.put("vaccination_per_100", record[4]);
            result.put(i, row);
        }
        return result;
    }


}
