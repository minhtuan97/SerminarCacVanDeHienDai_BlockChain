package BC_Hospital.Project.controller;

import java.io.Console;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.criteria.Predicate.BooleanOperator;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String getPicture(HttpServletRequest request) {
		
		this.publicKey = request.getParameter("publicKey");
		this.privateKey = request.getParameter("privateKey");
		Node node = new Node(this.publicKey, this.privateKey);
		String fileHash = request.getParameter("fileHash");
		
		Blob file = aOffchain.obtainOffChainData(fileHash).get().getFile();
		byte[] fileByte = "".getBytes();
		try {
			fileByte = file.getBytes(1, (int) file.length());
			file.free();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("file:" +FileComparision.Compare(fileHash, fileByte));
		if(FileComparision.Compare(fileHash, fileByte))
			return Base64.getEncoder().encodeToString(fileByte);
		
		return "null";
	}
	
	@RequestMapping(value="/ajax/updateAgree")
	@ResponseBody
	public boolean updateAgree(HttpServletRequest request) {
		
		this.publicKey = request.getParameter("publicKey");
		this.privateKey = request.getParameter("privateKey");
		Node node = new Node(this.publicKey, this.privateKey);
		
		String MSBenhAn = request.getParameter("MSBenhAn");
		Boolean agree = Boolean.parseBoolean(request.getParameter("agreeState"));
//		AgreeState agreeState = agreement? AgreeState.AGREED:AgreeState.DECLINED;
		System.out.println(MSBenhAn+", "+agree);
		//Lấy ra những block mà trường data có chứa mảng smartContracts
		String searchText= "\"MSBenhAn\":\""+MSBenhAn+"\"";
		System.out.println(searchText);
		List<BlockOnChain> listBlock = aOnchain.findByDataContain(searchText);
	
		
		for (BlockOnChain block : listBlock) {
			JsonObject obj = new JsonParser().parse(block.getData()).getAsJsonObject();
			JsonArray smartContracts = obj.get("smartContracts").getAsJsonArray();
//			for (JsonElement elem : smartContracts) {
			SmartContractForm sc = new Gson().fromJson(smartContracts.get(0), SmartContractForm.class);
			System.out.println("benhanh:"+sc.getMSBenhAn());
			if(sc.agreeState.containsKey(publicKey)) {
				sc.agreeSignature(publicKey, agree);
				List<SmartContractForm> list = new ArrayList<>();
				list.add(sc);
				JsonArray array = new Gson().toJsonTree(list).getAsJsonArray();
				JsonObject object = new JsonObject();
				object.add("smartContracts", array);
				
				//Thêm dữ liệu vào database Onchain
				aOnchain.storeOnChainDataDependLastBlock(object);
				
				return true;
			}
//			}
		}
		
		return false;
	}
}
