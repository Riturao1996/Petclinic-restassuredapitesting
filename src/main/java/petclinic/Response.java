package petclinic;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class Response{
	@Expose
	private String name;
	@Expose
	private String currency;
	@Expose
	private String id;
	@Expose
	private String amount;
	@Override
	public String toString() {
		return "Response{" +
				"name='" + name + '\'' +
				", currency='" + currency + '\'' +
				", id=" + id +
				", amount='" + amount + '\'' +
				'}';
	}
}