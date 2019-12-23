package BC_Hospital.Project.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import BC_Hospital.Project.DPaaS.SmartContract.MultipleAuthorities;

public class SmartContractForm extends MultipleAuthorities{
	
	//Thong tin benh an - 8 attributes
	public String MSBenhAn;
	public String SoBenhAn;
	public String Loai;

//	public String SoLuongHC;
//	public String HuyetSacTo;
//	public String Hematocrit;
//	public String MCV;
	public Hematology hematology;
	
	//Thong tin Xquang
//	public String MSXQuang;
//	public String SoXQuang;
//	public String ThoiGian;
//	public String ChuanDoanXQuang;
//	public String YeuCauChup;
//	public String KetQuaChup;
//	public String hashFile;
	public XRAY xray;
	public MultipartFile[] imageXQuang;
	

	public String ChuanDoanBenhAn;
	
	public SmartContractForm() {
		hematology = new Hematology();
		xray = new XRAY();
		authority = new HashSet<String>();
	}
	
	public String getMSBenhAn() {
		return MSBenhAn;
	}
	public void setMSBenhAn(String mSBenhAn) {
		MSBenhAn = mSBenhAn;
	}
	public String getSoBenhAn() {
		return SoBenhAn;
	}
	public void setSoBenhAn(String soBenhAn) {
		SoBenhAn = soBenhAn;
	}
	public String getLoai() {
		return Loai;
	}
	public void setLoai(String loai) {
		Loai = loai;
	}
	public Hematology getHematology() {
		return hematology;
	}
	public void setHematology(Hematology hematology) {
		this.hematology = hematology;
	}
	public XRAY getXray() {
		return xray;
	}
	public void setXray(XRAY xray) {
		this.xray = xray;
	}
	public MultipartFile[] getImageXQuang() {
		return imageXQuang;
	}
	public void setImageXQuang(MultipartFile[] imageXQuang) {
		this.imageXQuang = imageXQuang;
	}
	public Set<String> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<String> authority) {
		this.authority = authority;
	}

	public Map<String, AgreeState> getAgreeState() {
		return agreeState;
	}

	public void setAgreeState(Map<String, AgreeState> agreeState) {
		this.agreeState = agreeState;
	}
	public void createAgreeState() {
		agreeState = new HashMap<String, AgreeState>();
		for (String a : authority) {
			agreeState.put(a, AgreeState.NOTSEEN);
		}
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public String getChuanDoanBenhAn() {
		return ChuanDoanBenhAn;
	}
	public void setChuanDoanBenhAn(String chuanDoanBenhAn) {
		ChuanDoanBenhAn = chuanDoanBenhAn;
	}
}
