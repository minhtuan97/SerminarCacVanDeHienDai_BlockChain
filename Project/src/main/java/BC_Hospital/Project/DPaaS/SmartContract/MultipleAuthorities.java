package BC_Hospital.Project.DPaaS.SmartContract;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleAuthorities {
	
	protected List<String> authority;
	protected int threshold;
	
	public String publicKey = "msg.sender";
	
	protected boolean agreeing;
	protected String agreeRequester;
	protected String agreeRequest;
	protected boolean agreePermission;
	protected Map<String, Boolean> agreeState;
	protected boolean init;

	public void initial(List<String> authority, int threshold) {
		if(init) return;
		
		this.authority = authority;
		this.threshold = threshold;
		init = true;
	}
	
	private void initialAgree() {
		agreeing = false;
		agreeRequester = "";
		agreeRequest = "";
		agreePermission = false;
		agreeState = new HashMap<>();
		for (String au : authority) {
			agreeState.put(au, false);
		}
	}
	
	public void  applyAgreeRequest(String request) {
		if(agreeing == true)
			return;
		initialAgree();
		agreeing = true;
		agreeRequester = publicKey;//msg.sender;
		agreeRequest = request;
	}
	
	public void agreeSignature(Boolean agree) {
		agreeState.put(publicKey, agree);
		if(agreeResult()){
			agreePermission = true;
		}
	}
	
	private boolean agreeResult() {
		int k = 0;
		for(int i = 0; i <authority.size(); i++){
				if(agreeState.get(authority.get(i)) == true)
				k++;
		}
		if(k >= threshold)
			return true;
		else
			return false;

	}
	
	public void pre_agree() {
		if(agreeing == true && agreePermission == true &&
				publicKey == agreeRequester){
			//____________;
			initialAgree();
		}
	}
	
	public void cancelAgree() {
		if(publicKey == agreeRequester)
			initialAgree();
	}
}
