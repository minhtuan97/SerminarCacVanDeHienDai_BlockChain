package BC_Hospital.Project.DPaaS.SmartContract;

import java.util.List;

import BC_Hospital.Project.Model.Node;

public class EmbeddedPermission {
	
	protected List<Node> authority;
	protected String owner;
	
	public String publicKey = "msg.sender";
	
	public void embeddedPermission(List<Node> temAuthority) {
		owner = publicKey;//msg.sender;
		authority = temAuthority;
	}
	
	public void changeAuthority(List<Node> temAuthority) {
		if(publicKey == owner){
			authority = temAuthority;
		}
	}
	
	void permission(Node node) {
		for(Node au :authority){
			if(publicKey == au.getPublicKey().toString()){
				//_;
				return;
			}
		}
		
		authority.add(node);
	}

}
