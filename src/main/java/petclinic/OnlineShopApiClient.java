package petclinic;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class OnlineShopApiClient extends ApiClient {
    public OnlineShopApiClient(String baseUrl, String basePathOnlineShop) {
        super(baseUrl, basePathOnlineShop);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }

    public Integer getProductsStatusCode() throws Exception {
        ApiResponse response = caller.executeRequest(getRequest(), Method.GET, Response.class);
        return response.getHttpStatusCode();

    }

    public Response[] getProducts_List() throws InvalidResponseException {
        ApiResponse <Response[]> response = caller.executeRequest(getRequest(), Method.GET, Response[].class);
    return response.getContent();
    }

    public int getProductsCount() throws InvalidResponseException {
        ApiResponse<Response[]> response = caller.executeRequest(getRequest(), Method.GET, Response[].class);
        return response.getContent().length;
    }

    public Response addAddItemsToCart(Response response) throws InvalidResponseException {
        ApiRequest request = getRequest().withBody(response).withHeader("Content-Type", "application/json");
        ApiResponse<Response> response1 = caller.executeRequest(request, Method.POST, Response.class);
        return response1.getContent();
    }
    public ApiResponse<Response[]> deleteProducts() throws InvalidResponseException{
        ApiResponse<Response[]> response = caller.executeRequest(getRequest(),Method.DELETE, Response[].class);
        return response;
}
}