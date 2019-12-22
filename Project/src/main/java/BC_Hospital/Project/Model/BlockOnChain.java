package BC_Hospital.Project.Model;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.persistence.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BC_Hospital.Project.DPaaS.DataManagement.StringUtils;

@Entity // Đánh dấu đây là một Entity, chịu sự quản lý của Hibernate
@Table(name = "onchain") // Entity này đại diện cho table BlockOnChain trong db
public class BlockOnChain {

	@Id // Đánh dấu biến ở dưới là primary key của table này
	@Column(name = "hash", unique = true)
	private String hash;

	@Column(name = "prevhash", unique = true)
	private String prevhash;

	@Column(name = "data", unique = true, columnDefinition = "TEXT")
	private String data;

	@Column(name = "timestamp", unique = true)
	private long timestamp;

	// Constructor mặc định
	public BlockOnChain() {
	}

	// Constructor có tham số
	public BlockOnChain(String hash, String prevhash, String data, long timestamp) {
		this.hash = hash;
		this.prevhash = prevhash;
		this.data = data;
		this.timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void caculateHash() throws Exception {
		if (this.prevhash.isEmpty() || this.data.isEmpty() || this.timestamp == 0l)
			throw new Exception("Source not completed. Add prevhash, data and timestamp!");
		this.hash = StringUtils.applySha256(prevhash + Long.toString(timestamp) + generateNonce() + data);
	}

	public String getPrevhash() {
		return prevhash;
	}

	public void setPrevhash(String prevhash) {
		this.prevhash = prevhash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setData(JsonObject jdata) {
		this.data = jdata.toString();
	}

	public void addData(String newData) {
		JsonObject newD = new JsonParser().parse(newData).getAsJsonObject();
		addData(newD);
	}

	public void addData(JsonObject newData) {
		// Chuyển data từ String sang JsonObject
		JsonObject thisData = new JsonParser().parse(this.data).getAsJsonObject();

		// Thêm từng Element trong newData vào data
		for (Map.Entry<String, JsonElement> entry : newData.entrySet()) {
			// Hướng 1 Nếu data đã tồn tại element đó và không phải dạng boolean thì biến nó
			// thành mảng
			// H1 if(thisData.has(entry.getKey()) &&
			// !entry.getValue().getAsJsonPrimitive().isBoolean()) {
			// Hướng 2 nếu data đã tồn tại element và element đó là dạng array thì thêm mảng
			JsonElement elem1 = thisData.get(entry.getKey());
			if (elem1 != null && elem1.isJsonArray()) {
				JsonArray array = new JsonArray();
				JsonElement elem2 = entry.getValue();
				// Dùng cộng mảng nếu vốn là mảng, ngược lại thì dùng cộng biến vào mảng
//				if(elem1.isJsonArray()) {
//					array.addAll(elem1.getAsJsonArray());
//				} else {
//					array.add(elem1);
//				}
				array.addAll(elem1.getAsJsonArray());

				if (elem2.isJsonArray()) {
					array.addAll(elem2.getAsJsonArray());
				} else {
					array.add(elem2);
				}
				// Hàm thêm mảng vào data
				thisData.add(entry.getKey(), array);
			} else {
				// Hàm thêm biến vào data
				thisData.add(entry.getKey(), entry.getValue());
			}
		}
		// Chuyển về String và cập nhật data
		this.data = thisData.toString();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	private String generateNonce() {
		// Cách 1
		long rand = new Date().getTime();
		// Cách 2
//	    SecureRandom secureRandom = new SecureRandom();
//	    StringBuilder stringBuilder = new StringBuilder();
//	    for (int i = 0; i < 13; i++) {
//	        stringBuilder.append(secureRandom.nextInt(10));
//	    }
		// tạo string của một số có 13 chữ số chuỗi có thể có độ dài khác lớn hơn 10 tùy
		// ý
		String dateTimeString = Long.toString(rand);
		byte[] nonceByte = dateTimeString.getBytes();

		return Base64.getEncoder().encodeToString(nonceByte);
	}

}
