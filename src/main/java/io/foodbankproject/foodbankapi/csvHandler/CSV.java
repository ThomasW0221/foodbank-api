package io.foodbankproject.foodbankapi.csvHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.Item;

public class CSV {

private static final char DEFAULT_SEPARATOR = ',';
    
	public static void main(String[] args) throws IOException {

		ArrayList<Item> x = new ArrayList<Item>();
		
        String csvFile = "C:\\Users\\Nick\\Documents\\scripts\\test.csv";
        FileWriter writer = new FileWriter(csvFile);

        List<Donation> developers = Arrays.asList(
        		new Donation("july 3", x, "Nick", "ntahan@uncc.edu", "800 college way", 5),
        		new Donation("july 3", x, "Nick", "ntahan@uncc.edu", "800 college way", 5),
        		new Donation("july 3", x, "Nick", "ntahan@uncc.edu", "800 college way", 5),
        		new Donation("july 3", x, "Nick", "ntahan@uncc.edu", "800 college way", 5),
        		new Donation("july 3", x, "Nick", "ntahan@uncc.edu", "800 college way", 5));

        writeLine(writer, Arrays.asList("Date", "List donated", "Donor name", "Donor email", "Donor address", "Weight"));

        for (Donation d : developers) {

            List<String> list = new ArrayList<>();
            list.add(d.getDateReceived());
            list.add(d.getItemsDonated().toString());
            list.add(d.getDonorName());
            list.add(d.getDonorEmail());
            list.add(d.getDonorAddress());
            list.add(String.valueOf(d.getDonationWeight()));

            writeLine(writer, list);

        }
        
        writer.flush();
        writer.close();
	}

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }
	
}
