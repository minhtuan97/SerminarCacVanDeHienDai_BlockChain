package BC_Hospital.Project.DPaaS.SmartContract;

import java.util.List;

import BC_Hospital.Project.Model.Node;

public class EmbeddedPermission {
	
	protected List<Node> authority;
	protected String owner;
	
	public void embeddedPermission(List<Node> temAuthority) {
		owner = msg.sender;
		authority = temAuthority;
	}
	
	public void changeAuthority(List<Node> temAuthority) {
		if(msg.sender == owner){
			authority = temAuthority;
		}
	}
	
	boolean permission(Node node) {
		for(Node au :authority){
			if(msg.sender == au.publicKey){
				//_;
				break;
			}
		}
	}

}
