import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import petclinic.OnlineShopApiClient;
import petclinic.Response;

public class OnlineShopApiTest {
    static String apiUrl;
    SoftAssertions softly = new SoftAssertions();

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    public void getProducts() throws Exception {
        OnlineShopApiClient client = new OnlineShopApiClient(apiUrl, "/api/shop/getproducts");
        int statusCode = client.getProductsStatusCode();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(statusCode).isEqualTo(200);
        //softly.assertAll();
        System.out.println("Status Code is: " + statusCode);

    }

    @Test
    public void getProducts_List1() throws InvalidResponseException {
        OnlineShopApiClient client = new OnlineShopApiClient(apiUrl, "/api/shop/getproducts");
        Response[] cartproducts = client.getProducts_List();
        SoftAssertions softly = new SoftAssertions();
        softly.assertAll();
        System.out.println("Get product details at position 7" + cartproducts[7]);
    }

    @Test
    public void getProducts_Count() throws InvalidResponseException {
        OnlineShopApiClient client = new OnlineShopApiClient(apiUrl, "/api/shop/getproducts");
        int productsCount = client.getProductsCount();
        System.out.println("Count of Available products  : " + productsCount);

    }

    @Test
    public void createAddProducts_Cart() throws InvalidResponseException {
        OnlineShopApiClient client = new OnlineShopApiClient(apiUrl, "/api/shop/addtocart");
        Response response1 = client.addAddItemsToCart(Response.builder()
                .amount("200.00")
                .currency("$")
                .id("50")
                .name("icecream")
                .build());
        softly.assertThat(response1.getAmount()).isEqualTo("200.00");
        softly.assertThat(response1.getCurrency()).isEqualTo("$");
        softly.assertThat(response1.getName()).isEqualTo("icecream");
        softly.assertThat(response1.getId()).isNotEmpty();
        softly.assertAll();

    }

 @Test
    public void deleteNewOwners_throughID_ShouldDisplayDeletedId() throws InvalidResponseException {
        //create a new product
       OnlineShopApiClient client = new OnlineShopApiClient(apiUrl, "/api/shop/addtocart/");
        Response response = client.addAddItemsToCart(Response.builder()
                .name("Cat")
                .amount("20.00")
                .currency("$")
                .id("39")
                .build());
        //delete the created product
        OnlineShopApiClient client1 = new OnlineShopApiClient(apiUrl, "/api/shop/clearcart" );
         ApiResponse<Response[]> deletedProduct = client1.deleteProducts();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(deletedProduct.getHttpStatusCode()).as("The Status code is :").isEqualTo(200);
        softly.assertAll();
    }
}

