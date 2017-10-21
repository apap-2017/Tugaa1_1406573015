package com.example.service;

import java.util.List;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;

public interface PendudukService
{
    PendudukModel selectpenduduk (String nik);
	KeluargaModel selectkeluarga (String nkk);
	KotaModel selectkecamatan (String id_kota);
	KecamatanModel selectkelurahan (String id_kecamatan);
	KelurahanModel selectkeluargas (String id_kelurahan); 
	PendudukModel addpenduduk (String nama, String tempat, String tanggal, String is_wni, String perkerjaan, String golongan_darah, String agama, String status_perkawinan, String is_wafat, String id_keluarga, String jenis_kelamin, String status_anggota);
	void addpenduduk2(PendudukModel penduduk);
	void addkeluarga2(KeluargaModel keluarga);
	KeluargaModel addKeluarga(String alamat, String RT, String rw, String nama_kelurahan);
	String checkStatus(String nik);
	void updatePenduduk(PendudukModel penduduk);
	PendudukModel uppenduduk (String id, String nama, String tempat, String tanggal, String is_wni, String pekerjaan, String golongan_darah, String agama, String status_perkawinan, String is_wafat, String id_keluarga, String jenis_kelamin,String status_dalam_keluarga) ;
	PendudukModel uppenduduk2 (String id, String nik, String nama, String tempat, String tanggal, String is_wni, String pekerjaan, String golongan_darah, String agama, String status_perkawinan, String is_wafat, String id_keluarga, String jenis_kelamin,String status_dalam_keluarga) ;
}
