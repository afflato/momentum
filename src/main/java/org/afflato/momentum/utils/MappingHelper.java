package org.afflato.momentum.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.afflato.momentum.model.IndexDto;
import org.afflato.momentum.model.SecurityDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MappingHelper {
    private static final List<String> nifty50 = loadCsv(getPath(MainIndices.NIFTY50));
    private static final List<String> niftynext50 = loadCsv(getPath(MainIndices.NIFTYNEXT));
    private static final List<String> midcap = loadCsv(getPath(MainIndices.NIFTYMIDCAP));
    private static final List<String> smallcap = loadCsv(getPath(MainIndices.NIFTYSMALLCAP));

    public static String getPath(String commit, MomentumIndices index) {
        return String.format("momentum/%s/%s/%s", commit, index.getPath(), index.getFile());
    }


    public static String getPath(String commit, MomentumIndices index, String lastWorkingDay) {
        return String.format("momentum/%s/%s/%s/%s", commit, index.getPath(), lastWorkingDay, index.getFile());
    }

    public static String getPath(MainIndices index) {
        return String.format("%s/%s", index.getPath(), index.getFile());
    }

    public static List<String> loadCsv(String category) {
        ClassPathResource classPathResource = new ClassPathResource(category);

        List<String> momentum = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build())) {

            for (CSVRecord record : csvParser) {
                momentum.add(record.get(2));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return momentum;
    }

    public static String getSubCategory(String symbol) {
        if (nifty50.contains(symbol)) {
            return "NIFTY50";
        } else if (niftynext50.contains(symbol)) {
            return "NEXT50";
        } else if (midcap.contains(symbol)) {
            return "MIDCAP";
        } else if (smallcap.contains(symbol)) {
            return "SMLCAP";
        } else
            return "MCRCAP";
    }



    public static void initHeaders(HttpHeaders headers) {
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Accept-Language", "en-US,en;q=0.9");
        headers.set("Cache-Control", "no-cache");
        headers.set("Pragma", "no-cache");

        headers.set("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headers.set("Sec-Ch-Ua-Mobile", "?0");
        headers.set("Sec-Ch-Ua-Platform", "\"Windows\"");

        headers.set("Sec-Fetch-Dest", "document");
        headers.set("Sec-Fetch-Mode", "navigate");
        headers.set("Sec-Fetch-Site", "none");
        headers.set("Sec-Fetch-User", "?1");

        headers.set("Upgrade-Insecure-Requests", "1");

        headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");


    }




    public static List<IndexDto> parseCsvContent(String csvContent) {
        List<IndexDto> records = new ArrayList<>();
        try (Reader in = new StringReader(csvContent);
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase(true).withTrim())) {

            for (CSVRecord csvRecord : parser) {
                String symbol = csvRecord.get("Symbol");
                String companyName = csvRecord.get("Company Name");
                String industry = csvRecord.get("Industry");

                records.add(new IndexDto(symbol, companyName, industry));
            }
        }
        catch (IOException e) {
            System.err.println("ERRPR "+ e.getMessage());
            e.printStackTrace();
        }
        return records;
    }



    public static SecurityDto getSecurityDto(String body) {
        SecurityDto securityDto;
        ObjectMapper om = new ObjectMapper();
        try {
            securityDto = om.readValue(body, SecurityDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return securityDto;
    }


}
