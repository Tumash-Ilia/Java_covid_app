package com.STIproject.STI.projectData;

import com.STIproject.STI.exceptions.NotFoundException;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data control class
 */

public class ProjectDataService {
    private Date dateToday = new Date();
    private Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
    private final List<Map<String, String>> data = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("https://covid19.who.int/WHO-COVID-19-global-table-data.csv",
                    "appdata" + File.separator + "who-cases-" + sdf.format(dateToday) + ".csv");
        }});
        add(new HashMap<String, String>() {{
            put("https://onemocneni-aktualne.mzcr.cz/api/v2/covid-19/zakladni-prehled.csv",
                    "appdata" + File.separator + "mzcr-" + sdf.format(dateToday) + ".csv");
        }});
        add(new HashMap<String, String>() {{
            put("https://covid19.who.int/who-data/vaccination-data.csv",
                    "appdata" + File.separator + "who-vaccination-" + sdf.format(dateToday) + ".csv");
        }});
    }};

    public List<Map<String, String>> getData() {
        return data;
    }

    public String getDateToday() {
        return sdf.format(dateToday);
    }

    /**
     * Download data from get request
     */
    public void downloadData() {
        for (Map<String, String> hashMap : data) {
            for (Map.Entry entry : hashMap.entrySet()) {
                String url = entry.getKey().toString();
                String fileName = entry.getValue().toString();
                HttpGet request = new HttpGet(url);
                String result;
                // add request headers
                request.addHeader(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
                request.addHeader(HttpHeaders.ACCEPT, "text/csv");
                request.addHeader(HttpHeaders.USER_AGENT, "Java");

                try (CloseableHttpResponse response = httpClient.execute(request);
                     FileWriter writer = new FileWriter(fileName, false)
                ) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        result = EntityUtils.toString(entity);
                        writer.write(result);
                        writer.flush();
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Close HTTP client stream
     */
    public void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Editing who-cov.csv for further work
     */

    public void edit_who_cov() {
        String fileName = data.get(0).get("https://covid19.who.int/WHO-COVID-19-global-table-data.csv");
        List<String[]> records = null;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(fileName));
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] headerRecord = {"modified_date", "yesterday_date", "country", "cases_total", "confirmed_cases_yesterday"};
            csvWriter.writeNext(headerRecord);
            for (String[] record : records) {
                if (record[1].equals("Europe")) {
                    String[] s = {sdf.format(dateToday), sdf.format(yesterday), record[0].replace(",", ""),
                            record[2], record[6]};
                    csvWriter.writeNext(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Editing oa.csv for further work
     */
    public void edit_oa() {
        String fileName = data.get(1).get("https://onemocneni-aktualne.mzcr.cz/api/v2/covid-19/zakladni-prehled.csv");
        List<String[]> records = null;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(fileName));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {

            String[] headerRecord = {"modified_date", "yesterday_date",
                    "country", "cases_total", "vaccination_total", "vaccination_yesterday", "confirmed_cases_yesterday"};
            csvWriter.writeNext(headerRecord);


            for (String[] record : records) {
//                double per100 = Double.parseDouble(record[16]) / 10650000*100;
//                double roundOff = (double) Math.round(per100 * 10) / 10;
                String[] s = {sdf.format(dateToday), sdf.format(yesterday), "Czechia",
                        record[2], record[16], record[17], record[8]};
                csvWriter.writeNext(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Editing who-vac.csv for further work
     */
    public void edit_who_vac() {
        String fileName = data.get(2).get("https://covid19.who.int/who-data/vaccination-data.csv");
        List<String[]> records = null;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            records = csvReader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(fileName));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] headerRecord = {"modified_date", "date_updated", "country", "vaccination_total", "vaccination_per_100"};
            csvWriter.writeNext(headerRecord);
            for (String[] record : records) {
                if (record[2].equals("EURO")) {
                    String sDate1 = record[4];
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                    String[] s = {sdf.format(dateToday), sdf.format(date1),record[0].replace(",", ""), record[5], record[7]};
                    csvWriter.writeNext(s);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * Get list of dates from existing files in /appdata
     *
     * @return Map<String, SortedSet <String>> "dates" : list of dates
     */
    public Map<String, SortedSet<String>> filesInAppdata() {
        Map<String, SortedSet<String>> result = new HashMap<>();
        File[] files = new File("appdata").listFiles();
        Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{2}");

        SortedSet<String> dateSet = new TreeSet<>();
        if (files == null) throw new NotFoundException();
        for (File file : files) {
            if (file.isFile()) {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.find()) {
                    dateSet.add(matcher.group());
                }
            }
        }
        result.put("dates", dateSet);
        return result;
    }

    /**
     * Check eth connection
     */
    public boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }


}
