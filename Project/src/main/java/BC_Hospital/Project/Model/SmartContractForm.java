package BC_Hospital.Project.Model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SmartContractForm {
	
	//Thong tin benh an - 8 attributes
	public String MSBenhAn;
	public String SoBenhAn;
	public String Loai;
	public String ChuanDoanBenhAn;
	public String SoLuongHC;
	public String HuyetSacTo;
	public String Hematocrit;
	public String MCV;
	
	//Thong tin Xquang
	public String MSXQuang;
	public String SoXQuang;
	public String ThoiGian;
	public String ChuanDoanXQuang;
	public String YeuCauChup;
	public String KetQuaChup;
	public String hashFile;
	public MultipartFile[] imageXQuang;
	
	//Thong tin hop dong
	public List<String> authority;
	public String threshold;
	
	


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


	public String getChuanDoanBenhAn() {
		return ChuanDoanBenhAn;
	}


	public void setChuanDoanBenhAn(String chuanDoanBenhAn) {
		ChuanDoanBenhAn = chuanDoanBenhAn;
	}


	public String getSoLuongHC() {
		return SoLuongHC;
	}


	public void setSoLuongHC(String soLuongHC) {
		SoLuongHC = soLuongHC;
	}


	public String getHuyetSacTo() {
		return HuyetSacTo;
	}


	public void setHuyetSacTo(String huyetSacTo) {
		HuyetSacTo = huyetSacTo;
	}


	public String getHematocrit() {
		return Hematocrit;
	}


	public void setHematocrit(String hematocrit) {
		Hematocrit = hematocrit;
	}


	public String getMCV() {
		return MCV;
	}


	public void setMCV(String mCV) {
		MCV = mCV;
	}


	public String getMSXQuang() {
		return MSXQuang;
	}


	public void setMSXQuang(String mSXQuang) {
		MSXQuang = mSXQuang;
	}


	public String getSoXQuang() {
		return SoXQuang;
	}


	public void setSoXQuang(String soXQuang) {
		SoXQuang = soXQuang;
	}



	public String getChuanDoanXQuang() {
		return ChuanDoanXQuang;
	}


	public void setChuanDoanXQuang(String chuanDoanXQuang) {
		ChuanDoanXQuang = chuanDoanXQuang;
	}


	public String getYeuCauChup() {
		return YeuCauChup;
	}


	public void setYeuCauChup(String yeuCauChup) {
		YeuCauChup = yeuCauChup;
	}


	public String getKetQuaChup() {
		return KetQuaChup;
	}


	public void setKetQuaChup(String ketQuaChup) {
		KetQuaChup = ketQuaChup;
	}


	public String getHashFile() {
		return hashFile;
	}


	public void setHashFile(String hashFile) {
		this.hashFile = hashFile;
	}


	public List<String> getAuthority() {
		return authority;
	}


	public void setAuthority(List<String> authority) {
		this.authority = authority;
	}


	public String getThoiGian() {
		return ThoiGian;
	}


	public void setThoiGian(String thoiGian) {
		ThoiGian = thoiGian;
	}


	public MultipartFile[] getImageXQuang() {
		return imageXQuang;
	}


	public void setImageXQuang(MultipartFile[] imageXQuang) {
		this.imageXQuang = imageXQuang;
	}


	public String getThreshold() {
		return threshold;
	}


	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}




	
	
	
	
	
}
