package BC_Hospital.Project.Model;

import java.util.ArrayList;
import java.util.List;

public class ExamAndAnalysis {
	List<String> khaNangKham;
	
	public ExamAndAnalysis() {
		khaNangKham = new ArrayList<String>();
		khaNangXetNghiem = new ArrayList<String>();
	}

	public List<String> getKhaNangKham() {
		return khaNangKham;
	}

	public void setKhaNangKham(List<String> khaNangKham) {
		this.khaNangKham = khaNangKham;
	}
	
	List<String> khaNangXetNghiem;
	
	public List<String> getKhaNangXetNghiem() {
		return khaNangXetNghiem;
	}

	public void setKhaNangXetNghiem(List<String> khaNangXetNghiem) {
		this.khaNangXetNghiem = khaNangXetNghiem;
	}
	
}
