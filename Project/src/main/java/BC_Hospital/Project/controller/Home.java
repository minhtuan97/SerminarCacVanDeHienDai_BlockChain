package BC_Hospital.Project.controller;

import java.util.ArrayList;

import java.util.List;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Home {
	
	public List<MyTransaction> listTransaction = new ArrayList<MyTransaction>();
	
	@RequestMapping("/")
	public String welcome(Model model) {
		return "login";
	}

	
	// Login
	@RequestMapping("/login")
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
	
	

	// Tao mot smart constract
	@RequestMapping("/submit")
	public String login(Model model, @ModelAttribute("newTransaction") MyTransaction transaction) {

		String tenTransaction = transaction.nameTransaction;
			

		model.addAttribute("transaction", tenTransaction);
		
		
		return "account";
	}


}
