package io.foodbankproject.foodbankapi.csvHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.Item;

public class CSV {

private static final char DEFAULT_SEPARATOR = ',';
    
	public static void main(String[] args) throws IOException {

		ArrayList<Item> x = new ArrayList<Item>();
		
        String csvFile = "C:\\Users\\Nick\\Documents\\example\\test.csv";
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
            d.getItemsDonated();
            System.out.println(d.toString());
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
    
    /*
    @RequestMapping(value = "/downloadCSV")
    public void downloadCSV(HttpServletResponse response) throws IOException {
 
        String csvFileName = "books.csv";
 
        response.setContentType("text/csv");
 
        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
 
        Book book1 = new Book("Effective Java", "Java Best Practices",
                "Joshua Bloch", "Addision-Wesley", "0321356683", "05/08/2008",
                38);
 
        Book book2 = new Book("Head First Java", "Java for Beginners",
                "Kathy Sierra & Bert Bates", "O'Reilly Media", "0321356683",
                "02/09/2005", 30);
 
        Book book3 = new Book("Thinking in Java", "Java Core In-depth",
                "Bruce Eckel", "Prentice Hall", "0131872486", "02/26/2006", 45);
 
        Book book4 = new Book("Java Generics and Collections",
                "Comprehensive guide to generics and collections",
                "Naftalin & Philip Wadler", "O'Reilly Media", "0596527756",
                "10/24/2006", 27);
 
        List<Book> listBooks = Arrays.asList(book1, book2, book3, book4);
 
        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
 
        String[] header = { "Title", "Description", "Author", "Publisher",
                "isbn", "PublishedDate", "Price" };
 
        csvWriter.writeHeader(header);
 
        for (Book aBook : listBooks) {
            csvWriter.write(aBook, header);
        }
 
        csvWriter.close();
    } */
	
}
