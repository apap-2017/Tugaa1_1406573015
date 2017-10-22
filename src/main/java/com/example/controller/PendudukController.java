package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.PendudukModel;
import com.example.service.PendudukService;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.KecamatanModel;
import com.example.service.KeluargaService;

@Controller
public class PendudukController
{
    @Autowired
    PendudukService pendudukDAO;
	KeluargaService keluargaDAO;

	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
    @RequestMapping("/penduduk")
    public String searchPenduduk(@RequestParam(value="nik", required=false) String nik, Model model)
    {
		if(nik != null){
			PendudukModel penduduk = pendudukDAO.selectpenduduk (nik);
			model.addAttribute("penduduk",penduduk);
			if (penduduk != null) {
				return "viewPenduduk";
			}
			
		}
		else {
				return "not-found";
			}
			
		return "not-found";	
    }
	
	@RequestMapping("/penduduk/tambah")
	public String addPenduduk()	{
		return "tambahPenduduk";
	}
	
	@RequestMapping("/penduduk/tambah/submit")
    public String submitaddPenduduk(@RequestParam(value="nama", required=false) String nama,
								@RequestParam(value="tempat_lahir", required=false) String tempat_lahir,
								@RequestParam(value="tanggal_lahir", required=false) String tanggal_lahir,
								@RequestParam(value="is_wni", required=false) String is_wni,
								@RequestParam(value="pekerjaan", required=false) String pekerjaan,
								@RequestParam(value="jenis_kelamin", required=false) String jenis_kelamin,
								@RequestParam(value="status_anggota", required=false) String status_anggota,
								@RequestParam(value="golongan_darah", required=false) String golongan_darah,
								@RequestParam(value="agama", required=false) String agama,
								@RequestParam(value="status_perkawinan", required=false) String status_perkawinan,
								@RequestParam(value="is_wafat", required=false) String is_wafat,
								@RequestParam(value="id_keluarga", required=false) String id_keluarga,
												Model model)
    {
			PendudukModel penduduk = pendudukDAO.addpenduduk(nama, tempat_lahir, tanggal_lahir, is_wni, pekerjaan, golongan_darah, agama, status_perkawinan, is_wafat, id_keluarga, jenis_kelamin, status_anggota);
			pendudukDAO.addpenduduk2(penduduk);
			model.addAttribute("penduduk", penduduk);
			return "success-add";
    }
	
	@RequestMapping("/penduduk/mati")
    public String ubahMati(@RequestParam(value="nik", required=false) String nik, Model model)
    {
		String nikk = pendudukDAO.checkStatus(nik);
		model.addAttribute("nik", nikk);
		return "success-ubah";
    }
	
	@RequestMapping("/keluarga/tambah")
	public String addKeluarga(@RequestParam(value="alamat", required=false) String alamat,
							@RequestParam(value="rt", required=false) String rt,
							@RequestParam(value="rw", required=false) String rw,
							@RequestParam(value="kelurahan", required=false) String nama_kelurahan,
							Model model)	{
								
			if(alamat != null){
				KeluargaModel keluarga = pendudukDAO.addKeluarga(alamat, rt, rw, nama_kelurahan);
				pendudukDAO.addkeluarga2(keluarga);
				model.addAttribute("keluarga", keluarga);
				return "success-add-2";
			}
		return "addKeluarga";
	}
	
	@RequestMapping("/keluarga")
    public String searchNkk(@RequestParam(value="nkk", required=false) String nkk, Model model)
    {		
		if(nkk != null){
				KeluargaModel keluarga = pendudukDAO.selectkeluarga (nkk);
				model.addAttribute("keluarga",keluarga);
				if (keluarga != null) {
					return "viewNkk";
				} 
		}	
		else {
				return "not-found";
			}
			
		return "not-found";	
    }
	
	@RequestMapping("/penduduk/cari")
    public String searchkeluargas(@RequestParam(value="id_kota", required=false) String id_kota,
							@RequestParam(value="id_kecamatan", required=false) String id_kecamatan,
							@RequestParam(value="id_kelurahan", required=false) String id_kelurahan,
							Model model)
    {		
		if(id_kota != null){
			if(id_kecamatan != null){	
				if(id_kelurahan != null){
					KotaModel kota = pendudukDAO.selectkecamatan (id_kota);
					model.addAttribute("kota",kota);
					KecamatanModel kecamatan = pendudukDAO.selectkelurahan(id_kecamatan);
					model.addAttribute("kecamatan",kecamatan);
					KelurahanModel kelurahan = pendudukDAO.selectkeluargas(id_kelurahan);
					model.addAttribute("kelurahan",kelurahan);
					return "keluargaSearch";
				}
				KotaModel kota = pendudukDAO.selectkecamatan (id_kota);
				model.addAttribute("kota",kota);
				KecamatanModel kecamatan = pendudukDAO.selectkelurahan(id_kecamatan);
				model.addAttribute("kecamatan",kecamatan);
				return "kelurahanSearch";
			}
				KotaModel kota = pendudukDAO.selectkecamatan (id_kota);
				model.addAttribute("kota",kota);
				return "kecamatanSearch";
		}
		else {
				return "kotaSearch";
			}	
    }
	
	
	@RequestMapping("/penduduk/update/{nik}")
	public String updatePenduduk(Model model, @PathVariable(value = "nik") String nik){
		PendudukModel penduduk = pendudukDAO.selectpenduduk(nik);
		if(penduduk != null){
			model.addAttribute("penduduk",penduduk);
			return "form-update";
		}
		return "not-found";
	}
	
	@RequestMapping("/penduduk/update/submit")
	public String updateSubmitPenduduk(@RequestParam(value="nama", required=false) String nama,
								@RequestParam(value="nik", required=false) String nik,
								@RequestParam(value="tempat_lahir", required=false) String tempat_lahir,
								@RequestParam(value="tanggal_lahir", required=false) String tanggal_lahir,
								@RequestParam(value="is_wni", required=false) String is_wni,
								@RequestParam(value="pekerjaan", required=false) String pekerjaan,
								@RequestParam(value="jenis_kelamin", required=false) String jenis_kelamin,
								@RequestParam(value="status_dalam_keluarga", required=false) String status_dalam_keluarga,
								@RequestParam(value="golongan_darah", required=false) String golongan_darah,
								@RequestParam(value="agama", required=false) String agama,
								@RequestParam(value="status_perkawinan", required=false) String status_perkawinan,
								@RequestParam(value="is_wafat", required=false) String is_wafat,
								@RequestParam(value="id_keluarga", required=false) String id_keluarga,
												Model model){
			PendudukModel pends = pendudukDAO.selectpenduduk(nik);
			String id = pends.getId();
			//kalo nik ga berubah
			if(pends.getTanggal_lahir().equalsIgnoreCase(tanggal_lahir) && pends.getJenis_kelamin() == Integer.parseInt(jenis_kelamin)){
				PendudukModel penduduk = pendudukDAO.uppenduduk2(id, nik, nama, tempat_lahir, tanggal_lahir, is_wni, pekerjaan, golongan_darah, agama, status_perkawinan, is_wafat, id_keluarga, jenis_kelamin, status_dalam_keluarga);
				pendudukDAO.updatePenduduk(penduduk);	
			} 
			else{
				PendudukModel penduduk = pendudukDAO.uppenduduk(id, nama, tempat_lahir, tanggal_lahir, is_wni, pekerjaan, golongan_darah, agama, status_perkawinan, is_wafat, id_keluarga, jenis_kelamin, status_dalam_keluarga);
				pendudukDAO.updatePenduduk(penduduk);
			}
			model.addAttribute("nik", nik);
			return "success-update";

	
	}
	
	//Soal 6
	@RequestMapping("/keluarga/update/{nkk}")
	public String updateKeluarga(Model model, @PathVariable(value = "nkk") String nkk){
		KeluargaModel keluarga = pendudukDAO.selectkeluarga(nkk);
		KelurahanModel kelurahan = keluarga.getKelurahan();
		String nama = kelurahan.getNama_kelurahan();
		keluarga.setId(nama);
		if(keluarga != null){
			model.addAttribute("keluarga",keluarga);
			return "form-update-keluarga";
		}
		return "not-found";
	}
	
	@RequestMapping("/keluarga/update/submit")
	public String updateSubmitKeluarga(@RequestParam(value="alamat", required=false) String alamat,
								@RequestParam(value="nkk", required=false) String nkk,
								@RequestParam(value="rt", required=false) String rt,
								@RequestParam(value="rw", required=false) String rw,
								@RequestParam(value="kelurahan", required=false) String kelurahan,
												Model model){
			KeluargaModel keluarga = pendudukDAO.selectkeluarga(nkk);
			KelurahanModel kelurahans = keluarga.getKelurahan();
			System.out.println(rw);
			System.out.println(kelurahan);
			String id =  keluarga.getId();
			String idk = kelurahans.getId();
			System.out.println(idk);
			int idtb= keluarga.getIs_tidak_berlaku();
			//kalo nkk ga berubah
				System.out.println(kelurahans.getNama_kelurahan());
				KeluargaModel keluargas = pendudukDAO.upKeluarga2(id, nkk, alamat, rt, rw, idk, idtb);
				pendudukDAO.updateKeluarga(keluargas);	
			model.addAttribute("nkk", nkk);
			return "success-update-2";

	
	}
}
