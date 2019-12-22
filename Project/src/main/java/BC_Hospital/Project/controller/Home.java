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
import BC_Hospital.Project.Model.ExamAndAnalysis;
import BC_Hospital.Project.Model.Node;
import BC_Hospital.Project.Model.SmartContractForm;
import BC_Hospital.Project.repository.BlockOffChainRepository;
import ch.qos.logback.core.util.FileUtil;
import net.bytebuddy.asm.Advice.This;



@Controller
public class Home {
	
	@Autowired
	private Offchain aOffchain;
	@Autowired
	private Onchain aOnchain;
	
	public List<MyTransaction> listTransaction = new ArrayList<MyTransaction>();
	private String publicKey;
	private String privateKey;
	
	private void addKeyToModel(Model model) {
		model.addAttribute("publicKey", publicKey);
		model.addAttribute("privateKey", privateKey);
	}

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
	public String login(Model model, @ModelAttribute("KeyData") KeyData keyData) {
		
		Node node = new Node(keyData.publicKey, keyData.privateKey);
		this.publicKey = keyData.publicKey;
		this.privateKey = keyData.privateKey;

		//model.addAttribute("node", node);
		addKeyToModel(model);
		
		
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

		System.out.println("input privateKey:\n"+keyData.privateKey );
		try {
			Node aNode= new Node(keyData.publicKey, keyData.privateKey);
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

	
	// Dang ky kha nang kham cua benh vien dangkyKhaNangKham
	@RequestMapping("/registratioOfExaminationAbility")
	public String registratioOfExaminationAbility(Model model,
			@ModelAttribute("examAndAnalysis") ExamAndAnalysis ability) {

		//System.out.println(String.join(",", ability.getKhaNangKham()));
		System.out.println(ability.xetNghiemMau);
		//System.out.println(ability.getKhaNangXetNghiem().get(0));
//		model.addAttribute("transaction", tenTransaction);
		
		
		addKeyToModel(model);
		return "index";
	}
	
	
	// Dang ky kha nang kham cua benh vien
	@RequestMapping("/taoSmartContract")
	public String taoSmartContract(Model model, @ModelAttribute("smartContractForm") SmartContractForm smartContract) {

			System.out.println(smartContract.ChuanDoanBenhAn);
			System.out.println(smartContract.threshold);
			System.out.println(smartContract.ThoiGian);

//		model.addAttribute("transaction", tenTransaction);
		
		
		return "index";
	}
	
	

}
