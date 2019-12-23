package BC_Hospital.Project.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BC_Hospital.Project.DPaaS.DataManagement.Offchain;
import BC_Hospital.Project.DPaaS.DataManagement.Onchain;
import BC_Hospital.Project.Model.BlockOnChain;
import BC_Hospital.Project.Model.KeyData;
import BC_Hospital.Project.Model.MyTransaction;
import BC_Hospital.Project.Model.Node;

@RestController
public class AjaxController {

	@Autowired
	private Offchain aOffchain;
	@Autowired
	private Onchain aOnchain;

	public List<MyTransaction> listTransaction = new ArrayList<MyTransaction>();
	private String publicKey;
	private String privateKey;
	
	@RequestMapping("/ajax/getListHospital")
	@ResponseBody
	public List<String> getListHospital(HttpServletRequest request) {

		this.publicKey = request.getParameter("publicKey");
		this.privateKey = request.getParameter("privateKey");
		Node node = new Node(this.publicKey, this.privateKey);
		
		//Lấy ra những block mà trường data có chứa mảng hospitals
		List<BlockOnChain> listBlock = aOnchain.findByDataContain("\"hospitals\":[");

		// lấy danh sách bệnh viện
		List<String> listHospital = new ArrayList<>();
		for (BlockOnChain block : listBlock) {
			JsonObject obj = new JsonParser().parse(block.getData()).getAsJsonObject();
			
			listHospital.add(obj.get("hospitals").toString());
		}
		System.out.println(String.join(",",listHospital));

		return listHospital;
	}
}
