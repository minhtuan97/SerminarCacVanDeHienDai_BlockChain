package BC_Hospital.Project.DPaaS.SmartContract;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import BC_Hospital.Project.Model.AgreeState;
import BC_Hospital.Project.Model.Transation;

public class MultipleAuthorities extends Transation {
	
	//Thong tin hop dong
	public Set<String> authority;
	public Map<String, AgreeState> agreeState;
	public int threshold;
	
	public String publicKey;// = "msg.sender";
	
	protected boolean agreeing;
	protected String agreeRequester;
	protected String agreeRequest;
	protected boolean agreePermission;
	protected boolean init;
	
	public boolean isAgreePermission() {
		return agreePermission;
	}

	public void setAgreePermission(boolean agreePermission) {
		this.agreePermission = agreePermission;
	}

	public void initial(Set<String> authority, int threshold) {
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
			agreeState.put(au, AgreeState.NOTSEEN);
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
	
	public void agreeSignature(String publicKey, Boolean agree) {
		AgreeState as = agree?AgreeState.AGREED:AgreeState.DECLINED;
		agreeState.put(publicKey, as);
		if(agreeResult()){
			agreePermission = true;
		}
	}
	
	private boolean agreeResult() {
		int k = 0;
		List<String> au = new ArrayList<String>();
		au.addAll(authority);
		for(int i = 0; i <authority.size(); i++){
				if(agreeState.get(au.get(i)) == AgreeState.AGREED)
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
