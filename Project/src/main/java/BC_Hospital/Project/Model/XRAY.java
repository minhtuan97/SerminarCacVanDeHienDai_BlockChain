package BC_Hospital.Project.Model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import BC_Hospital.Project.DPaaS.DataManagement.StringUtils;

public class XRAY extends Transation {
	
	String MS;
	String So;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date ThoiGian;
	String ChuanDoan;
	String YeuCauChup;
	String KetQuaChup;
	String hashFile;
	byte[] file;
	
	
	public String getMS() {
		return MS;
	}
	public void setMS(String mS) {
		MS = mS;
	}
	public String getSo() {
		return So;
	}
	public void setSo(String so) {
		So = so;
	}
	public Date getThoiGian() {
		return ThoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		ThoiGian = thoiGian;
	}
	public String getChuanDoan() {
		return ChuanDoan;
	}
	public void setChuanDoan(String chuanDoan) {
		ChuanDoan = chuanDoan;
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
	
	public void generateHashFile () {
		this.hashFile = StringUtils.applySha256(file);
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
}
