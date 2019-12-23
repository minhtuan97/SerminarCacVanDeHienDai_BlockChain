//Load Trang - An Table Kha Nang Kham Va Xet Nghiem
var listBenhVien=[];
var listHopDong=[];
var giaodichdangxem="";

$( document ).ready(function() {
	 $("#data_table").hide();
	 
	 //Gọi hàm ajax lấy ra danh sách bệnh viện
	searchHospital((data)=>{
		listBenhVien = JSON.parse(data);

		var publicKeys = "";
		listBenhVien.forEach(element => {
			publicKeys += '<a  class="dropdown-item MyMemeber" onClick="memberClick(this)">'+element.publicKey+'</a>';		
		});
		

		$("#listDropdownHospital").append(publicKeys);
	});
	
	//Gọi hàm ajax lấy ra danh sách hợp đồng
	searchSmartContract((data)=>{
		console.log(data);
		console.log(data.listSCWait[0].ChuanDoanBenhAn);
		listHopDong = data;
	});
});



// Click Vao Mot Thanh Vien Tham Gia
function memberClick(a){
	if(!$('#data_table').is(':visible')){
		$("#data_table").show();
	}
	
	let key = $(a).text();
	let khanangkham = "* ";
	let khanangxetnghiem = "* ";

	listBenhVien.forEach(element => {
		$("#keyBenhVien").val(element.publicKey);
		if(element.publicKey==key){
			element.ability.benhNhiemTrung? khanangkham+="Bệnh nhiễm trùng * " :  khanangkham+="";
			element.ability.buouTanSinh? khanangkham+="Bướu tân sinh * " :  khanangkham+="";
			element.ability.benhVeMau? khanangkham+="Bệnh về máu * " :  khanangkham+="";
			element.ability.benhNoiTietDinhDuongVaChuyenHoa? khanangkham+="Bệnh nội tiết dinh dưỡng và chuyển hóa * " :  khanangkham+="";
			element.ability.benhTamThan? khanangkham+="Bệnh tâm thần * " :  khanangkham+="";
			element.ability.benhHeThanKinh? khanangkham+="Bệnh hệ thần kinh * " :  khanangkham+="";
			element.ability.benhVeMat? khanangkham+="Bệnh về mắt * " :  khanangkham+="";
			element.ability.benhTaiMuiHong? khanangkham+="Bệnh tai mũi họng * " :  khanangkham+="";
			element.ability.benhHeTuanHoan? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhHeHoHap? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhHeTieuHoa? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhDaVaMoDuoiDa? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhCoXuongKhop? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhHeSinhDucTietNieu? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.thaiNghenSinhDe? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.vetThuongNgoDoc? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.cacYeuToSauChuaTriYTe? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			
			element.ability.xetNghiemMau? khanangxetnghiem+="Benh nhiem trung" :  khanangkham+="";
			element.ability.chupXQuang? khanangxetnghiem+="Benh nhiem trung" :  khanangkham+="";
		
			
		}
		
		$("#listKhaNangKham").text(khanangkham);
		$("#listKhaXetNghiem").text(khanangxetnghiem);
			
	});
	
}



// Insert thanh vien tham gia vào TextBox
$(function() {
	$('#Inser_btn').click(function() {
		var oldText = $("#danhsachThanhVien").val();
		var span = $('#keyBenhVien').val();

		var result;

		if (oldText == "")
			result = span;
		else
			result = oldText + "," + span;

		$('#danhsachThanhVien').val(result);

	});
});



// Hien thi giao dich can xac thuc
$(function() {
	$('#needPermit')
			.click(
					function() {
						// Thay ten button
						$("#dropdownMenuButton").text('Giao dịch cần xác thực');
						
						if (!$('#sectionXacNhan').is(':visible')) {
							$("#sectionXacNhan").show();
						}

						$(".danhsachgiaodich").html("");
						
						
						var publicKeys = "";
						
						//Gọi hàm ajax lấy ra danh sách hợp đồng
						searchSmartContract((data)=>{
							listhd = data;

							var publicKeys = "";
							listhd.listSCWait.forEach(element => {
								publicKeys += "<li><i class='task-icon bg-c-green'></i> <a onClick='showDiv(this)'  href='javascript:void(0)'>"+element.MSBenhAn+"</a></li>";		
							});
							

							$(".danhsachgiaodich").append(publicKeys);
						});

					});
});

//Hàm truy vấn danh sách bệnh viện
function searchHospital(callBack) {

	var publicKey = $("#publicKey").html();
	var privateKey = $("#privateKey").html();

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/ajax/getListHospital",
		data : {
			publicKey : publicKey,
			privateKey : privateKey
		},
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			callBack(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}


//Hien thi giao dich da xac thuc
$(function() {
	$('#permitted')
			.click(
					function() {
						// Thay ten button
						$("#dropdownMenuButton").text('Giao dịch đã xác thực');
						
						if ($('#sectionXacNhan').is(':visible')) {
							$("#sectionXacNhan").hide();
						}

						$(".danhsachgiaodich").html("");
						
						
						var publicKeys = "";
						
						//Gọi hàm ajax lấy ra danh sách hợp đồng
						searchSmartContract((data)=>{
							listhd = data;

							var publicKeys = "";
							listhd.listSCDone.forEach(element => {
								publicKeys += "<li><i class='task-icon bg-c-green'></i> <a onClick='showDiv(this)'  href='javascript:void(0)'>"+element.MSBenhAn+"</a></li>";		
							});
							

							$(".danhsachgiaodich").append(publicKeys);
						});
						
					});
});

//Hàm truy vấn danh sách hợp đồng
function searchSmartContract(callBack) {

	var publicKey = $("#publicKey").html();
	var privateKey = $("#privateKey").html();

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/ajax/getListSmartContract",
		data : {
			publicKey : publicKey,
			privateKey : privateKey
		},
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			callBack(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

//Hàm truy vấn hình ảnh
function searchPicture(fileHash, callBack) {

	var publicKey = $("#publicKey").html();
	var privateKey = $("#privateKey").html();

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/ajax/getPicture",
		data : {
			publicKey : publicKey,
			privateKey : privateKey,
			fileHash: fileHash
		},
		dataType : 'text',
		timeout : 100000,
		success : function(data) {
			callBack(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

//Hàm cập nhật trạng thái đồng ý
function updateAgree(MSBenhAn, agreeState, callBack) {

	var publicKey = $("#publicKey").html();
	var privateKey = $("#privateKey").html();

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/ajax/updateAgree",
		data : {
			publicKey : publicKey,
			privateKey : privateKey,
			MSBenhAn: MSBenhAn,
			agreeState: agreeState
		},
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			callBack(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}


//Hien thi chi tiet giao dich
function showDiv(a){
	var mSBenhAn = $(a).text();;
	giaodichdangxem = mSBenhAn;
	
	
	//Gọi hàm ajax lấy ra benh an
	searchSmartContract((data)=>{
		listhd = data;

		var publicKeys = "";
		
		//Quet da xac thuc
		listhd.listSCDone.forEach(element => {
			if(element.MSBenhAn ==mSBenhAn)	{
				showChiTietBenhAn(element);
			}
		});
		
		//Quet chua xac thuc
		listhd.listSCWait.forEach(element => {
			if(element.MSBenhAn ==mSBenhAn)	{
				showChiTietBenhAn(element);
			}
		});


	});
	

	
	
}


function showChiTietBenhAn(Element){

	//Thông tin bệnh án
	$("#MSMau").text(Element.hematology.MS);
	$("#LoaiMau").text(Element.hematology.Loai);
	//$("#ChuanDoanMau").text(Element.hematology);
	$("#SoLuongHC").text(Element.hematology.SoLuongHC);
	$("#HuyetSacTo").text(Element.hematology.HuyetSacTo);
	$("#Hematocrit").text(Element.hematology.Hematocrit);
//	$("#MCV").text(Element.hematology);
//	$("#MCH").text(Element.hematology);
//	$("#MCHC").text(Element.hematology);
//	$("#HCCoNhan").text(Element.hematology);
//	$("#HCLuoi").text(Element.hematology);
//	$("#SLTieuCau").text(Element.hematology);
//	$("#KSVSotRet").text(Element.hematology);
//	$("#SLBC").text(Element.hematology);
//	$("#TPBC").text(Element.hematology);
//	$("#DoanTrungTinh").text(Element.hematology);
//	$("#DoanUaAxit").text(Element.hematology);
//	$("#DoanUaBazo").text(Element.hematology);
//	$("#Mono").text(Element.hematology);
//	$("#Lympho").text(Element.hematology);
//	$("#TeBaoBT").text(Element.hematology);
//	$("#MauLang").text(Element.hematology);
//	$("#TGMauChay").text(Element.hematology);
//	$("#TGMauDong").text(Element.hematology);
//	$("#HeABO").text(Element.hematology);
//	$("#HeRh").text(Element.hematology); 
	
	//Thông tin XQuang
	$("#MSXQuang").text(Element.xray.MS);
	$("#SoXQuang").text(Element.xray.So);
	$("#ThoiGian").text(Element.xray.ThoiGian);
	$("#ChuanDoanXQuang").text(Element.xray.MS);
	$("#YeuCauChup").text(Element.xray.YeuCauChup);
	$("#KetQuaChup").text(Element.xray.KetQuaChup);
	$("#ChuanDoanBenhAn").text(Element.ChuanDoanBenhAn);
	
	searchPicture(Element.xray.hashFile, (data)=>{
		
		
		//$("#hashFile").text(Element.xray.hashFile);
		console.log(data);
		if(data!="null")
			$('#ItemPreview').attr('src', 'data:image/png;base64,'+data);
		else
			$("#ItemPreview").attr('src', 'https://www.freeiconspng.com/uploads/error-icon-28.png');
	});
	

}



$('#khongdongy').on("click", function() {
	updateAgree(giaodichdangxem, false,()=>{
		alert("Không đồng ý giao dịch");
	})
})

$('#dongy').on("click", function() {
	updateAgree(giaodichdangxem, true,()=>{
		alert("Đồng ý giao dịch");
	})
})


