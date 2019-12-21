package BC_Hospital.Project.controller;

import java.util.List;
import java.util.Optional;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import BC_Hospital.Project.AuServices.KeyGeneration;
import BC_Hospital.Project.DPaaS.DataManagement.Offchain;
import BC_Hospital.Project.DPaaS.DataManagement.Onchain;
import BC_Hospital.Project.Model.BlockOffChain;
import BC_Hospital.Project.Model.BlockOnChain;
import BC_Hospital.Project.Model.Node;
import BC_Hospital.Project.repository.BlockOffChainRepository;
import ch.qos.logback.core.util.FileUtil;



@Controller
public class Home {
	
	@Autowired
	private Offchain aOffchain;
	@Autowired
	private Onchain aOnchain;
	
	public List<MyTransaction> listTransaction = new ArrayList<MyTransaction>();

	@RequestMapping("/demo")
    @ResponseBody
    public Optional<BlockOnChain> welcome2() {
		BlockOnChain blockOnChain = new BlockOnChain("112233456", "112233455", "11223345603030303 ahi dsoac ", 11111);
		//BlockOffChain  aBlockOffChain = new BlockOffChain("112233456", "03030303 ahi dsoac ".getBytes());
		//return aOffchain.storeOffChainData("1122334567", "03030303 ahi dsoac ".getBytes());
		//return blockOffChainRepository.findByhashfile("112233456");
        //return aOffchain.obtainOffChainData("112233456");
		//return aOffchain.finaAll();
//		aOnchain.storeOnChainData("attribute", "ciphertext", blockOnChain );
//		return"aaa";
		return aOnchain.obtainOnChainData("112233456");
    }

	
	@RequestMapping("/")
	public String welcome(Model model) {
		return "login";
	}

	
	// Login
	@RequestMapping("/home")
	public String login(Model model, @ModelAttribute("personForm") PersonForm personForm) {

		String name = personForm.getTen();
		String publicKey = personForm.getPublicKey();
		
		PersonForm newPerson = new PersonForm();
		newPerson.setTen(name);
		newPerson.setPublicKey(publicKey);
		

		model.addAttribute("person", newPerson);
		
		
		//Get Transaction
		listTransaction.add(new MyTransaction("Transaction 1"));
		listTransaction.add(new MyTransaction("Transaction 2"));
		
		model.addAttribute("listTransaction", listTransaction);
		
		return "index";
	}
	
	
	//Lay chi tiet mot giao dich
	@RequestMapping("/getDetailTransaction")
	public String getDetailTransaction(Model model) {
		
	
		MyTransaction transaction = new MyTransaction("ABC");
		
		model.addAttribute("detailTransaction", transaction);
		
		return "index";
	}
	
	
	//Lay giao dich da xac thuc
	@RequestMapping("/getPermitted")
	public String getPermitted(Model model) {
		
		listTransaction.clear();
		//Get Transaction Need Permit
		listTransaction.add(new MyTransaction("Transaction 1"));
		listTransaction.add(new MyTransaction("Transaction 2"));
		
		model.addAttribute("listTransaction", listTransaction);
		
		return "index";
	}
	
	
	//Lay giao dich can xac thuc
	@RequestMapping("/getNeedPermission")
	public String getNeedPermission(Model model) {
		
		listTransaction.clear();
		//Get Transaction Need Permit
		listTransaction.add(new MyTransaction("Transaction 3"));
		listTransaction.add(new MyTransaction("Transaction 4"));
		
		model.addAttribute("listTransaction", listTransaction);
		
		return "index";
	}
	
	
	//Xac thuc mot giao dich
	@RequestMapping("/permitTransaction/{name}")
	public String permitTransaction(@PathVariable("name") String name, Model model) {
		
		//System.out.print(choose);
		
		model.addAttribute("transaction", name);
		
		return "account";
	}
	
	//Luu vao file
	@RequestMapping("/saveFile")
	public String saveFile(Model model, @ModelAttribute("keyData") KeyData keyData) {

		System.out.println("input privateKey"+keyData.privateKey );
		try {
			Node aNode= new Node(keyData.privateKey, keyData.publicKey);
			System.out.println("ket qua: "+Base64.getEncoder().encodeToString(aNode.getPrivateKey().getEncoded()));
			//System.out.println("ket qua"+aNode.getPrivateKey().toString());
		} catch (Exception e) {
			System.out.println("Loi khong the convert");
			
		}
		System.out.println("Het");

		
		
		
		return "generation";
	}
	
	//Tao mot key
	@RequestMapping("/generateKey")
	public String generateKey(Model model) {
		Node node = KeyGeneration.Generate();
		String publicKey = Base64.getEncoder().encodeToString(node.getPublicKey().getEncoded());	
		String privateKey = Base64.getEncoder().encodeToString(node.getPrivateKey().getEncoded());	;	

		model.addAttribute("publicKey", publicKey);
		model.addAttribute("privateKey", privateKey);
		
		
		return "generation";
	}
	

	// Tao mot smart constract
	@RequestMapping("/submit")
	public String login(Model model, @ModelAttribute("newTransaction") MyTransaction transaction) {

		String tenTransaction = transaction.nameTransaction;
			

		model.addAttribute("transaction", tenTransaction);
		
		
		return "account";
	}


}
