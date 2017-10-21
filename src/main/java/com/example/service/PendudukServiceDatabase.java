package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KecamatanModel;
import com.example.model.KotaModel;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService
{
    @Autowired
    private PendudukMapper pendudukMapper;


	@Override
	public PendudukModel selectpenduduk (String nik) {
		PendudukModel penduduk = pendudukMapper.selectpenduduk(nik);
		KeluargaModel keluarga = pendudukMapper.selectkeluarga(penduduk.getId_keluarga());
		KelurahanModel kelurahan = pendudukMapper.selectkelurahan(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = pendudukMapper.selectkecamatan(kelurahan.getId_kecamatan());
		KotaModel kota = pendudukMapper.selectkota(kecamatan.getId_kota());
		penduduk.setKeluarga(keluarga);
		keluarga.setKelurahan(kelurahan);
		kelurahan.setKecamatan(kecamatan);
		kecamatan.setKota(kota);
		return penduduk;
	}
	
	@Override
	public KeluargaModel selectkeluarga (String nkk) {
		KeluargaModel keluarga = pendudukMapper.selectkeluarga2(nkk);
		List<PendudukModel> penduduk = pendudukMapper.selectpenduduks(keluarga.getId());
		KelurahanModel kelurahan = pendudukMapper.selectkelurahan(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = pendudukMapper.selectkecamatan(kelurahan.getId_kecamatan());
		KotaModel kota = pendudukMapper.selectkota(kecamatan.getId_kota());
		keluarga.setPenduduks(penduduk);
		keluarga.setKelurahan(kelurahan);
		kelurahan.setKecamatan(kecamatan);
		kecamatan.setKota(kota);
		return keluarga;
	}

	@Override
	public PendudukModel addpenduduk (String nama, String tempat, String tanggal, String is_wni, String pekerjaan, String golongan_darah, String agama, String status_perkawinan, String is_wafat, String id_keluarga, String jenis_kelamin,String status_dalam_keluarga) {
		KeluargaModel keluarga = pendudukMapper.selectkeluarga(id_keluarga);
		KelurahanModel kelurahan = pendudukMapper.selectkelurahan(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = pendudukMapper.selectkecamatan(kelurahan.getId_kecamatan());
		String nik1 = kecamatan.getKode_kecamatan();
		String[] tanggals = tanggal.split("-");
		String tanggalf = tanggals[2] + tanggals[1] + tanggals[0].substring(2,tanggals[0].length());
		System.out.println(tanggalf);
		nik1 = nik1.substring(0, nik1.length() - 1);
		if(jenis_kelamin.equals("1")){
			int nik2 = Integer.parseInt(tanggals[2].substring(0,1));
			nik2 += 4;
			System.out.println(nik2);
			String temp =nik2 + tanggals[2].substring(1,2);
			tanggalf = temp + tanggals[1] + tanggals[0].substring(2,tanggals[0].length());
			System.out.println(tanggalf);
		}
		String nik = nik1 + tanggalf;
		String min = nik +"0001";
		String max = nik +"0999";
		String nik4 = pendudukMapper.getNikPenduduk(min,max, jenis_kelamin);
		String nikf = "";
		if(nik4 != null){
		nik4 = nik4 = nik4.substring(12, nik4.length());
		int yes = Integer.parseInt(nik4) +1;
			if (yes < 10 ){
			nikf = nik + "000" + yes;
			}
			else if (yes < 100 ){
			nikf = nik + "00"+ yes;
			}
			else if (yes < 1000 ){
			nikf = nik + "0"+ yes;
			}
		}
		
		else{
				nikf = nik + "0001";
		}
		
		int id = Integer.parseInt(pendudukMapper.getMaxId())+1;
		String ids = ""+id;
		int jk = Integer.parseInt(jenis_kelamin);
		int wni = Integer.parseInt(is_wni);
		PendudukModel penduduk1 = new PendudukModel(ids, nikf, nama, tempat, tanggal, jk, wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, Integer.parseInt(is_wafat), null);
		System.out.println(nik4);
		return penduduk1;
	}
	
	//soal 5
	@Override
	public PendudukModel uppenduduk (String id, String nama, String tempat, String tanggal, String is_wni, String pekerjaan, String golongan_darah, String agama, String status_perkawinan, String is_wafat, String id_keluarga, String jenis_kelamin,String status_dalam_keluarga) {
		KeluargaModel keluarga = pendudukMapper.selectkeluarga(id_keluarga);
		KelurahanModel kelurahan = pendudukMapper.selectkelurahan(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = pendudukMapper.selectkecamatan(kelurahan.getId_kecamatan());
		String nik1 = kecamatan.getKode_kecamatan();
		String[] tanggals = tanggal.split("-");
		String tanggalf = tanggals[2] + tanggals[1] + tanggals[0].substring(2,tanggals[0].length());
		System.out.println(tanggalf);
		nik1 = nik1.substring(0, nik1.length() - 1);
		if(jenis_kelamin.equals("1")){
			int nik2 = Integer.parseInt(tanggals[2].substring(0,1));
			nik2 += 4;
			System.out.println(nik2);
			String temp =nik2 + tanggals[2].substring(1,2);
			tanggalf = temp + tanggals[1] + tanggals[0].substring(2,tanggals[0].length());
			System.out.println(tanggalf);
		}
		String nik = nik1 + tanggalf;
		String min = nik +"0001";
		String max = nik +"0999";
		String nik4 = pendudukMapper.getNikPenduduk(min,max, jenis_kelamin);
		String nikf = "";
		if(nik4 != null){
		nik4 = nik4 = nik4.substring(12, nik4.length());
		int yes = Integer.parseInt(nik4) +1;
			if (yes < 10 ){
			nikf = nik + "000" + yes;
			}
			else if (yes < 100 ){
			nikf = nik + "00"+ yes;
			}
			else if (yes < 1000 ){
			nikf = nik + "0"+ yes;
			}
		}
		
		else{
				nikf = nik + "0001";
		}
		int jk = Integer.parseInt(jenis_kelamin);
		int wni = Integer.parseInt(is_wni);
		PendudukModel penduduk1 = new PendudukModel(id, nikf, nama, tempat, tanggal, jk, wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, Integer.parseInt(is_wafat), null);
		return penduduk1;
	}
	
	@Override
	public PendudukModel uppenduduk2 (String id, String nik, String nama, String tempat, String tanggal, String is_wni, String pekerjaan, String golongan_darah, String agama, String status_perkawinan, String is_wafat, String id_keluarga, String jenis_kelamin,String status_dalam_keluarga) 
	{

		int jk = Integer.parseInt(jenis_kelamin);
		int wni = Integer.parseInt(is_wni);
		PendudukModel penduduk1 = new PendudukModel(id, nik, nama, tempat, tanggal, jk, wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, Integer.parseInt(is_wafat), null);
		return penduduk1;
	}
	
	@Override
	public void addpenduduk2(PendudukModel penduduk){
			pendudukMapper.addpenduduks(penduduk);
	}
	
	@Override
	public KeluargaModel addKeluarga(String alamat, String rt, String rw, String nama_kelurahan){
		String awal6 = pendudukMapper.selectkodekelurahan(nama_kelurahan);
		String id_kelurahan = pendudukMapper.selectidkelurahan(nama_kelurahan);
			System.out.println(id_kelurahan);
		awal6 = awal6.substring(0,6);
			System.out.println(awal6);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		System.out.println(date);
		String[] tanggals = date.split("-");
		String tanggalf = tanggals[2] + tanggals[1] + tanggals[0].substring(2,tanggals[0].length());
		String nkk = awal6 + tanggalf;
		String min = nkk +"0001";
		System.out.println(nkk);
		String max = nkk +"0999";
		System.out.println(max);
		String nkkf = pendudukMapper.getNkkKeluarga(min,max);
		if(nkkf != null){
		System.out.println(nkkf);
		nkkf = nkkf = nkkf.substring(12, nkkf.length());
		int yes = Integer.parseInt(nkkf) +1;
			if (yes < 10 ){
			nkkf =nkk + "000"+ yes;
			}
			else if (yes < 100 ){
			nkkf =nkk + "00"+ yes;
			}
			else if (yes < 1000 ){
			nkkf =nkk + "0"+ yes;
			}
		}
		else{
			nkkf = nkk + "0001";
		}
			System.out.println(nkkf);
			int id = Integer.parseInt(pendudukMapper.getMaxIdKeluarga())+1;
			String ids = ""+id;
			KeluargaModel keluarga = new KeluargaModel(ids,nkkf,alamat,rt,rw,id_kelurahan,0,null,null);
			return keluarga;
			
	}
	
	
	@Override
	public void addkeluarga2(KeluargaModel keluarga){
			pendudukMapper.addkeluargas(keluarga);
	}
	
	
	@Override
	public KotaModel selectkecamatan(String id_kota) {
		KotaModel kota = pendudukMapper.selectkota(id_kota);
		List<KecamatanModel> kecamatan = pendudukMapper.selectkecamatans(id_kota);
		kota.setKecamatans(kecamatan);
		return kota;
	}
	
	@Override
	public KecamatanModel selectkelurahan(String id_kecamatan){
		KecamatanModel kecamatan = pendudukMapper.selectkecamatan(id_kecamatan);
		List<KelurahanModel> kelurahan = pendudukMapper.selectkelurahans(id_kecamatan);
		kecamatan.setKelurahans(kelurahan);
		return kecamatan;
	}
	
	//soal 8
	@Override
	public KelurahanModel selectkeluargas(String id_kelurahan){ 
		KelurahanModel kelurahan = pendudukMapper.selectkelurahan(id_kelurahan);
		List<PendudukModel> penduduks = pendudukMapper.selectkeluargas(id_kelurahan);
		kelurahan.setPenduduks(penduduks);
		return kelurahan;
	}
	
	@Override
	public String checkStatus(String nik){
		PendudukModel penduduk = pendudukMapper.selectpenduduk(nik);
		System.out.println(penduduk.getId());
		int newStatus = 2;
		if(penduduk.getIs_wafat() == 0){
			newStatus = 1 ;
		}
		else{
			newStatus = 0;
		}
		penduduk.setIs_wafat(0);
		pendudukMapper.updateStatusHidup(penduduk);
		if(newStatus == 1 ){
			KeluargaModel keluarga = pendudukMapper.selectkeluarga(penduduk.getId_keluarga());
			List<PendudukModel> penduduks = pendudukMapper.selectpenduduks(keluarga.getNomor_kk());
			if(penduduks == null){
					keluarga.setIs_tidak_berlaku(1);
			}
		}
		return penduduk.getNik();
	}
	
	@Override
	public void updatePenduduk(PendudukModel penduduk){
		System.out.println(penduduk.getId());
		pendudukMapper.updatePenduduk(penduduk);
	}
	
}