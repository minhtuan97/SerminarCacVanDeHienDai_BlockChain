package BC_Hospital.Project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BC_Hospital.Project.DPaaS.DataManagement.Offchain;
import BC_Hospital.Project.DPaaS.DataManagement.Onchain;
import BC_Hospital.Project.Model.KeyData;
import BC_Hospital.Project.Model.MyTransaction;
import BC_Hospital.Project.Model.Node;

@RestController
public class AjaxReceiver {

	@Autowired
	private Offchain aOffchain;
	@Autowired
	private Onchain aOnchain;

	public List<MyTransaction> listTransaction = new ArrayList<MyTransaction>();
	private String publicKey;
	private String privateKey;
	
	@RequestMapping("/ajax/get")
	public String get(Model model, @ModelAttribute("KeyData") KeyData keyData) {

		Node node = new Node(keyData.publicKey, keyData.privateKey);
		this.publicKey = keyData.publicKey;
		this.privateKey = keyData.privateKey;
;

		// Get Transaction
		listTransaction.add(new MyTransaction("Transaction 1"));
		listTransaction.add(new MyTransaction("Transaction 2"));

		model.addAttribute("listTransaction", listTransaction);

		return "index";
	}
}
