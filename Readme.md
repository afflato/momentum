// Inside your NiftyService constructor
public NiftyService() {
JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory();
requestFactory.setReadTimeout(Duration.ofSeconds(30)); // Example read timeout
requestFactory.setConnectTimeout(Duration.ofSeconds(10)); // Example connection timeout

    this.restClient = RestClient.builder()
            .baseUrl("https://www.niftyindices.com")
            .defaultHeaders(NiftyService::initHeaders)
            .requestFactory(requestFactory) // Set the customized request factory
            .build();
}

1. Create POJO Classes for the JSON Structure
   You need to define Java classes that represent the JSON structure.

Java

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// Main wrapper class for the entire JSON response
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore any fields not mapped to properties
public class Nifty50Response {
private int declines;
private String indexType;
private List<NiftyStockData> data; // List of stock data objects
private String trdVolumesum;
private List<LatestData> latestData; // List of latest data objects
private int advances;
private int unchanged;
private String trdValueSumMil;
private String time;
private String trdVolumesumMil;
private String trdValueSum;

    // Getters and Setters for all fields
    public int getDeclines() { return declines; }
    public void setDeclines(int declines) { this.declines = declines; }

    public String getIndexType() { return indexType; }
    public void setIndexType(String indexType) { this.indexType = indexType; }

    public List<NiftyStockData> getData() { return data; }
    public void setData(List<NiftyStockData> data) { this.data = data; }

    public String getTrdVolumesum() { return trdVolumesum; }
    public void setTrdVolumesum(String trdVolumesum) { this.trdVolumesum = trdVolumesum; }

    public List<LatestData> getLatestData() { return latestData; }
    public void setLatestData(List<LatestData> latestData) { this.latestData = latestData; }

    public int getAdvances() { return advances; }
    public void setAdvances(int advances) { this.advances = advances; }

    public int getUnchanged() { return unchanged; }
    public void setUnchanged(int unchanged) { this.unchanged = unchanged; }

    public String getTrdValueSumMil() { return trdValueSumMil; }
    public void setTrdValueSumMil(String trdValueSumMil) { this.trdValueSumMil = trdValueSumMil; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getTrdVolumesumMil() { return trdVolumesumMil; }
    public void setTrdVolumesumMil(String trdVolumesumMil) { this.trdVolumesumMil = trdVolumesumMil; }

    public String getTrdValueSum() { return trdValueSum; }
    public void setTrdValueSum(String trdValueSum) { this.trdValueSum = trdValueSum; }

    @Override
    public String toString() {
        return "Nifty50Response{" +
               "declines=" + declines +
               ", indexType='" + indexType + '\'' +
               ", data=" + data.size() + " items" + // Don't print full list, can be too long
               ", trdVolumesum='" + trdVolumesum + '\'' +
               ", latestData=" + latestData.size() + " items" +
               ", advances=" + advances +
               ", unchanged=" + unchanged +
               ", trdValueSumMil='" + trdValueSumMil + '\'' +
               ", time='" + time + '\'' +
               ", trdVolumesumMil='" + trdVolumesumMil + '\'' +
               ", trdValueSum='" + trdValueSum + '\'' +
               '}';
    }
}

// Class for individual stock data within the "data" array
@JsonIgnoreProperties(ignoreUnknown = true)
public class NiftyStockData {
@JsonProperty("ptsC") private String ptsC;
@JsonProperty("wklo") private String wklo;
@JsonProperty("iislPtsChange") private String iislPtsChange;
private String symbol;
@JsonProperty("cAct") private String cAct;
@JsonProperty("mPC") private String mPC;
@JsonProperty("wkhi") private String wkhi;
@JsonProperty("wklocm_adj") private String wklocmAdj;
@JsonProperty("trdVolM") private String trdVolM;
@JsonProperty("wkhicm_adj") private String wkhicmAdj;
@JsonProperty("mVal") private String mVal;
@JsonProperty("ltP") private String ltP;
@JsonProperty("xDt") private String xDt;
@JsonProperty("trdVol") private String trdVol;
@JsonProperty("ntP") private String ntP;
@JsonProperty("yPC") private String yPC;
private String previousClose;
private String dayEndClose;
private String high;
@JsonProperty("iislPercChange") private String iislPercChange;
private String low;
private String per;
private String open;

    // Getters and Setters for all fields
    public String getPtsC() { return ptsC; }
    public void setPtsC(String ptsC) { this.ptsC = ptsC; }

    public String getWklo() { return wklo; }
    public void setWklo(String wklo) { this.wklo = wklo; }

    public String getIislPtsChange() { return iislPtsChange; }
    public void setIislPtsChange(String iislPtsChange) { this.iislPtsChange = iislPtsChange; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getCAct() { return cAct; }
    public void setCAct(String cAct) { this.cAct = cAct; }

    public String getMPC() { return mPC; }
    public void setMPC(String mPC) { this.mPC = mPC; }

    public String getWkhi() { return wkhi; }
    public void setWkhi(String wkhi) { this.wkhi = wkhi; }

    public String getWklocmAdj() { return wklocmAdj; }
    public void setWklocmAdj(String wklocmAdj) { this.wklocmAdj = wklocmAdj; }

    public String getTrdVolM() { return trdVolM; }
    public void setTrdVolM(String trdVolM) { this.trdVolM = trdVolM; }

    public String getWkhicmAdj() { return wkhicmAdj; }
    public void setWkhicmAdj(String wkhicmAdj) { this.wkhicmAdj = wkhicmAdj; }

    public String getMVal() { return mVal; }
    public void setMVal(String mVal) { this.mVal = mVal; }

    public String getLtP() { return ltP; }
    public void setLtP(String ltP) { this.ltP = ltP; }

    public String getXDt() { return xDt; }
    public void setXDt(String xDt) { this.xDt = xDt; }

    public String getTrdVol() { return trdVol; }
    public void setTrdVol(String trdVol) { this.trdVol = trdVol; }

    public String getNtP() { return ntP; }
    public void setNtP(String ntP) { this.ntP = ntP; }

    public String getYPC() { return yPC; }
    public void setYPC(String yPC) { this.yPC = yPC; }

    public String getPreviousClose() { return previousClose; }
    public void setPreviousClose(String previousClose) { this.previousClose = previousClose; }

    public String getDayEndClose() { return dayEndClose; }
    public void setDayEndClose(String dayEndClose) { this.dayEndClose = dayEndClose; }

    public String getHigh() { return high; }
    public void setHigh(String high) { this.high = high; }

    public String getIislPercChange() { return iislPercChange; }
    public void setIislPercChange(String iislPercChange) { this.iislPercChange = iislPercChange; }

    public String getLow() { return low; }
    public void setLow(String low) { this.low = low; }

    public String getPer() { return per; }
    public void setPer(String per) { this.per = per; }

    public String getOpen() { return open; }
    public void setOpen(String open) { this.open = open; }

    @Override
    public String toString() {
        return "NiftyStockData{" +
               "symbol='" + symbol + '\'' +
               ", ltP='" + ltP + '\'' +
               ", per='" + per + '\'' +
               '}';
    }
}

// Class for data within the "latestData" array
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatestData {
@JsonProperty("yHigh") private String yHigh;
private String date;
private String high;
@JsonProperty("yLow") private String yLow;
private String low;
private String ch;
private String indexName;
private String ltp;
@JsonProperty("yCls") private String yCls;
private String per;
private String open;
@JsonProperty("mCls") private String mCls;

    // Getters and Setters for all fields
    public String getYHigh() { return yHigh; }
    public void setYHigh(String yHigh) { this.yHigh = yHigh; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getHigh() { return high; }
    public void setHigh(String high) { this.high = high; }

    public String getYLow() { return yLow; }
    public void setYLow(String yLow) { this.yLow = yLow; }

    public String getLow() { return low; }
    public void setLow(String low) { this.low = low; }

    public String getCh() { return ch; }
    public void setCh(String ch) { this.ch = ch; }

    public String getIndexName() { return indexName; }
    public void setIndexName(String indexName) { this.indexName = indexName; }

    public String getLtp() { return ltp; }
    public void setLtp(String ltp) { this.ltp = ltp; }

    public String getYCls() { return yCls; }
    public void setYCls(String yCls) { this.yCls = yCls; }

    public String getPer() { return per; }
    public void setPer(String per) { this.per = per; }

    public String getOpen() { return open; }
    public void setOpen(String open) { this.open = open; }

    public String getMCls() { return mCls; }
    public void setMCls(String mCls) { this.mCls = mCls; }

    @Override
    public String toString() {
        return "LatestData{" +
               "indexName='" + indexName + '\'' +
               ", ltp='" + ltp + '\'' +
               ", ch='" + ch + '\'' +
               '}';
    }
}
Key points for POJOs:

@JsonIgnoreProperties(ignoreUnknown = true): This is very important. It tells Jackson to ignore any JSON fields that don't have a corresponding property in your Java class. This makes your code more resilient to API changes where new fields might be added.

@JsonProperty("jsonFieldName"): Use this annotation when your Java field name doesn't exactly match the JSON field name (e.g., ptsC in JSON becomes ptsC in Java). If they are identical, the annotation is optional, but often good for clarity.

Data Types: Notice that almost all fields are String in the JSON response, even numbers. It's safer to map them to String first and then parse them to int, double, or BigDecimal within your application logic if you need to perform calculations. This avoids JsonMappingException if a field might sometimes be null or an unexpected string.

2. Update your NiftyService
   You'll need a new method in your NiftyService to call this new endpoint and deserialize the JSON into your Nifty50Response object.

Java

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType; // Import MediaType for JSON
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.Duration; // For timeouts
import java.util.ArrayList;
import java.util.List;

@Service
public class NiftyService {

    // You can externalize this URL as well if needed
    // @Value("${NIFTY_LIVE_DATA_URL}")
    // private String niftyLiveDataUrl;

    private final RestClient restClient;

    public NiftyService() {
        // Create a dedicated RestClient for this service if headers/base URL differ
        // Or, if headers/base URL are largely the same, reuse the existing one,
        // but note the new base URL in this example.
        this.restClient = RestClient.builder()
                .baseUrl("https://iislliveblob.niftyindices.com") // Base URL for the new service
                .defaultHeaders(NiftyService::initHeaders) // Reuse your existing headers
                // Example of adding timeouts for this client
                .build();
    }

    // Reuse the existing initHeaders, as these seem to be general browser-like headers
    private static void initHeaders(HttpHeaders headers) {
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        headers.set("Accept", "application/json, text/plain, */*"); // Crucial: Set Accept to JSON
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Accept-Language", "en-US,en;q=0.9");
        headers.set("Cache-Control", "no-cache");
        headers.set("Pragma", "no-cache");
        headers.set("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headers.set("Sec-Ch-Ua-Mobile", "?0");
        headers.set("Sec-Ch-Ua-Platform", "\"Windows\"");
        headers.set("Sec-Fetch-Dest", "empty"); // Often 'empty' for JSON APIs
        headers.set("Sec-Fetch-Mode", "cors"); // Often 'cors' for JSON APIs
        headers.set("Sec-Fetch-Site", "none"); // Or 'cross-site' depending on the domain relationship
        // Removed Upgrade-Insecure-Requests as it's not typical for JSON API calls
    }

    // --- Existing NiftyAlpha50 CSV Call (keep this if you still need it) ---
    @Value("${NIFTYALPHA50}") // Ensure this property is configured in application.properties/yaml
    private String niftyAlpha50Url;

    public List<NiftyAlphaRecord> getAlpha50Client() {
        // You might want a separate RestClient bean for niftyindices.com if its headers/baseUrl differ greatly
        // For simplicity, here we're using the same 'restClient' instance.
        // Make sure the baseUrl of this client is set appropriately for the CSV call if it's different.
        // Or, you can override the baseUrl for this specific call:
        // .uri(URI.create("https://www.niftyindices.com/IndexConstituent/ind_nifty_Alpha_Index.csv"))
        String csvData = restClient.get()
                .uri(niftyAlpha50Url) // Use the @Value injected URL
                .retrieve()
                .body(String.class);
        List<NiftyAlphaRecord> niftyAlphaRecords = parseCsvContent(csvData);
        return niftyAlphaRecords;
    }

    private List<NiftyAlphaRecord> parseCsvContent(String csvContent) {
        List<NiftyAlphaRecord> records = new ArrayList<>();
        try (Reader in = new StringReader(csvContent);
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase(true).withTrim())) {

            for (CSVRecord csvRecord : parser) {
                String symbol = csvRecord.get("Symbol");
                String companyName = csvRecord.get("Company Name");
                String industry = csvRecord.get("Industry");

                records.add(new NiftyAlphaRecord(symbol, companyName, industry));
            }
        }
        catch (IOException e) {
            System.err.println("Error parsing CSV content: "+ e.getMessage());
            e.printStackTrace();
            // Depending on your error handling strategy, you might re-throw or return empty list
            // throw new RuntimeException("Error parsing CSV content", e);
        }
        return records;
    }

    public static class NiftyAlphaRecord {
        private String symbol;
        private String companyName;
        private String industry;

        public NiftyAlphaRecord(String symbol, String companyName, String industry) {
            this.symbol = symbol;
            this.companyName = companyName;
            this.industry = industry;
        }
        // Getters
        public String getSymbol() { return symbol; }
        public String getCompanyName() { return companyName; }
        public String getIndustry() { return industry; }

        @Override
        public String toString() {
            return "NiftyAlphaRecord{" +
                    "symbol='" + symbol + '\'' +
                    ", companyName='" + companyName + '\'' +
                    ", industry='" + industry + '\'' +
                    '}';
        }
    }


    // --- New Nifty 50 Live Data JSON Call ---
    public Nifty50Response getNifty50LiveData() {
        // Note: The base URL for this RestClient is "https://iislliveblob.niftyindices.com"
        // So the URI here is just the path part.
        return restClient.get()
                .uri("/jsonfiles/equitystockwatch/EquityStockWatchNIFTY%2050.json")
                .accept(MediaType.APPLICATION_JSON) // Explicitly request JSON
                .retrieve()
                .body(Nifty50Response.class); // Directly deserialize into your POJO
    }
}
Key Changes and Considerations:
New Base URL: The new service is on https://iislliveblob.niftyindices.com, which is different from https://www.niftyindices.com.

Option 1 (Recommended): Create a separate RestClient bean specifically for this new base URL if you foresee it having different default headers or different timeout requirements than your existing RestClient. This provides better isolation and manageability.

Option 2 (Used in example): If the headers are largely the same and you just want to add a new method, you can keep one RestClient bean and set its baseUrl to one of the common domains, then use absolute URIs for the other, or dynamically set the baseUrl in the RestClient.Builder if it's constructed on the fly. For simplicity in the example, I've updated the RestClient constructor's baseUrl to the new JSON service's base.

Accept Header: Changed Accept to application/json, text/plain, */* in initHeaders to signal that you prefer JSON responses. This is important for JSON APIs.

Sec-Fetch-Dest / Sec-Fetch-Mode: Changed to empty and cors respectively. These headers depend on how the browser actually makes the request, and for fetching data from an API, empty (for destination) and cors (for mode) are common for JavaScript fetch calls.

No Upgrade-Insecure-Requests: This header is typically sent by browsers when navigating to a page, suggesting an upgrade from HTTP to HTTPS. It's usually not sent for direct API calls fetching JSON data.

Direct JSON Deserialization: Instead of body(String.class) and then manual parsing, RestClient directly supports deserializing the JSON response into your POJO using body(Nifty50Response.class). This handles all the JSON parsing via Jackson automatically.

Error Handling: Remember to add robust error handling using .onStatus() for this new API as well, similar to what we discussed for the CSV service, as network calls can fail for various reasons.

By defining the POJO structure, you leverage Spring Boot's automatic JSON serialization/deserialization capabilities, making API calls to JSON endpoints incredibly streamlined!