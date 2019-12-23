package BC_Hospital.Project.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate.BooleanOperator;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BC_Hospital.Project.AuServices.FileComparision;
import BC_Hospital.Project.DPaaS.DataManagement.Offchain;
import BC_Hospital.Project.DPaaS.DataManagement.Onchain;
import BC_Hospital.Project.Model.AgreeState;
import BC_Hospital.Project.Model.BlockOnChain;
import BC_Hospital.Project.Model.KeyData;
import BC_Hospital.Project.Model.MyTransaction;
import BC_Hospital.Project.Model.Node;
import BC_Hospital.Project.Model.SmartContractForm;

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
//		System.out.println(String.join(",",listHospital));

		return listHospital;
	}
	
	@RequestMapping("/ajax/getListSmartContract")
	@ResponseBody
	public String getListSmartContract(HttpServletRequest request) {

		this.publicKey = request.getParameter("publicKey");
		this.privateKey = request.getParameter("privateKey");
		Node node = new Node(this.publicKey, this.privateKey);
		
		//Lấy ra những block mà trường data có chứa mảng smartContracts
		List<BlockOnChain> listBlock = aOnchain.findByDataContain("\"smartContracts\":[");

		// lấy danh sách Smart Contract
		List<SmartContractForm> listSCWait = new ArrayList<>(); //đang chờ publickey
		List<SmartContractForm> listSCDone = new ArrayList<>(); //đã làm việc xong với publickey
		
		for (BlockOnChain block : listBlock) {
			JsonObject obj = new JsonParser().parse(block.getData()).getAsJsonObject();
			JsonArray smartContracts = obj.get("smartContracts").getAsJsonArray();
			for (JsonElement elem : smartContracts) {
				SmartContractForm sc = new Gson().fromJson(elem, SmartContractForm.class);
				
				if(sc.agreeState.get(publicKey)!= AgreeState.NOTSEEN) {
					listSCDone.add(sc);
				} else {
					listSCWait.add(sc);
				}
			}
		}
//		System.out.println(String.join(",",listHospital));
		JsonObject jlistSmartContract = new JsonObject();
		jlistSmartContract.add("listSCWait", new Gson().toJsonTree(listSCWait).getAsJsonArray());
		jlistSmartContract.add("listSCDone", new Gson().toJsonTree(listSCDone).getAsJsonArray());
			
		return jlistSmartContract.toString();
	}
	
	@RequestMapping("/ajax/getPicture")
	@ResponseBody
	public byte[] getPicture(HttpServletRequest request) {
		
		this.publicKey = request.getParameter("publicKey");
		this.privateKey = request.getParameter("privateKey");
		Node node = new Node(this.publicKey, this.privateKey);
		String fileHash = request.getParameter("fileHash");
		
		byte[] file = aOffchain.obtainOffChainData(fileHash).get().getFile();
		
		if(FileComparision.Compare(fileHash, file))
			return file;
		
		
		return null;
	}
	
	@RequestMapping("/ajax/updateAgree")
	@ResponseBody
	public String updateAgree(HttpServletRequest request) {
		
		this.publicKey = request.getParameter("publicKey");
		this.privateKey = request.getParameter("privateKey");
		Node node = new Node(this.publicKey, this.privateKey);
		
		String MSBenhAn = request.getParameter("MSBenhAn");
		Boolean agree = Boolean.parseBoolean(request.getParameter("agreeState"));
//		AgreeState agreeState = agreement? AgreeState.AGREED:AgreeState.DECLINED;
		
		//Lấy ra những block mà trường data có chứa mảng smartContracts
		List<BlockOnChain> listBlock = aOnchain.findByDataContain("\"MSBenhAn\": \""+MSBenhAn+"\"");
		
		for (BlockOnChain block : listBlock) {
			JsonObject obj = new JsonParser().parse(block.getData()).getAsJsonObject();
			JsonArray smartContracts = obj.get("smartContracts").getAsJsonArray();
//			for (JsonElement elem : smartContracts) {
			SmartContractForm sc = new Gson().fromJson(smartContracts.get(0), SmartContractForm.class);
			
			if(sc.agreeState.containsKey(publicKey)) {
				sc.agreeSignature(publicKey, agree);
			}
//			}
		}
		
		return "";
	}
}
