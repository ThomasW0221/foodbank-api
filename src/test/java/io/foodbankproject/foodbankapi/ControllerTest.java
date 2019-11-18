package io.foodbankproject.foodbankapi;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.foodbankproject.foodbankapi.controller.DonationController;
import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.Item;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(DonationController.class)
public class ControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@Autowired
	private static MockMvc mvc;
	
	@MockBean
	private static DonationController donationController;
	
	@Before
	public void beforeClass() throws Exception{
		Donation d1 = new Donation();
		addDonorInfoToDonation1(d1);
		System.out.println(d1.getItemsDonated().toString());
		String d1Json = generateDonationJson(d1);
		mvc.perform(post("/donation").contentType(APPLICATION_JSON_UTF8)
						.content(d1Json)).andExpect(status().isOk());
	}
	
	private static void addDonorInfoToDonation1(Donation d1) {
		d1.setDateReceived("2019-11-08");
		d1.setDonorName("Thomas Winterfield");
		d1.setDonorEmail("twinterfield68@gmail.com");
		d1.setDonorAddress("123 Fake st");
		d1.setDonationWeight(20);
		
		addItemsToDonation1(d1);
	}
	
	private static void addItemsToDonation1(Donation d1) {
		List<Item> itemList = new ArrayList<>();
		Item item1 = new Item("Beans", "Can of kidney beans", 5);
		item1.setDonation(d1);
		Item item2 = new Item("Apples", "granny smith", 5);
		item2.setDonation(d1);
		Item item3 = new Item("Steak", "t-bone", 1);
		item3.setDonation(d1);
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		d1.setItemsDonated(itemList);
	}
	
	private static String generateDonationJson(Donation d) {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String donationAsJson = "";
		try {
			donationAsJson = ow.writeValueAsString(d);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return donationAsJson;
	}
	
	@Test
	public void test() {
		System.out.println("test");
	}
	
	
}
