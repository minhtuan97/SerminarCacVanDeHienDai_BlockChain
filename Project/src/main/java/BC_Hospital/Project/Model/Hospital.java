package BC_Hospital.Project.Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Hospital {

	public String publicKey;
	public ExamAndAnalysis ability;
	
	public Hospital() {}
	
	public Hospital(String publicKey, ExamAndAnalysis ability) {
		super();
		this.publicKey = publicKey;
		this.ability = ability;
	}
	
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public ExamAndAnalysis getAbility() {
		return ability;
	}
	public void setAbility(ExamAndAnalysis ability) {
		this.ability = ability;
	}
	
	public JsonObject toJsonObject() {
		JsonObject object = new JsonObject();
		
		object.add(this.publicKey, new Gson().toJsonTree(ability));
		
		return object;
	}
}
