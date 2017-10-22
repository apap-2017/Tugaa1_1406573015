package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;

@Mapper
public interface PendudukMapper
{
	//soal 1
    @Select("select id, nik, nama, status_dalam_keluarga, tempat_lahir, id_keluarga, tanggal_lahir, jenis_kelamin, golongan_darah, agama, is_wni, status_perkawinan, pekerjaan, is_wafat "+
	"from penduduk where nik = #{nik}")
    @Results(value = { @Result(property = "nik", column = "nik"), @Result(property = "nama", column = "nama"),
			@Result(property = "tempat_lahir", column = "tempat_lahir"), @Result(property = "tanggal_lahir", column = "tanggal_lahir"), 
			@Result(property = "jenis_kelamin", column = "jenis_kelamin"), @Result(property = "golongan_darah", column = "golongan_darah"),
			@Result(property = "agama", column = "agama"), @Result(property = "is_wni", column = "is_wni"), 
			@Result(property = "status_perkawinan", column = "status_perkawinan"), 
			@Result(property = "id", column = "id"), 
			@Result(property = "status_dalam_keluarga", column = "status_dalam_keluarga"), 
			@Result(property = "pekerjaan", column = "pekerjaan"), @Result(property = "is_wafat", column = "is_wafat"),
			@Result(property = "id_keluarga", column = "id_keluarga") })
	PendudukModel selectpenduduk(@Param("nik") String nik);
	
	@Select("select id,id_kelurahan, alamat, rt, rw " +
	"from keluarga where id = #{id_keluarga}")
    @Results(value = { @Result(property = "alamat", column = "alamat"),
			@Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw")})
	KeluargaModel selectkeluarga(@Param("id_keluarga") String id_keluarga);
	
	@Select("select * " +
	"from kecamatan where id = #{id_kecamatan}")
    @Results(value = { @Result(property = "nama_kecamatan", column = "nama_kecamatan"),
					@Result(property = "id", column = "id"),})
	KecamatanModel selectkecamatan(@Param("id_kecamatan") String id_kecamatan);
	
	@Select("select id,nama_kota " +
	"from kota where id = #{id_kota}")
    @Results(value = { @Result(property = "nama_kota", column = "nama_kota")})
	KotaModel selectkota(@Param("id_kota") String id_kota);
	
	//Soal 2
	@Select("select nik, nama, tempat_lahir, id_keluarga, tanggal_lahir, jenis_kelamin, golongan_darah, agama, is_wni, status_perkawinan, pekerjaan, is_wafat "+
	"from penduduk where id_keluarga = #{id}")
    @Results(value = { @Result(property = "nik", column = "nik"), @Result(property = "nama", column = "nama"),
			@Result(property = "tempat_lahir", column = "tempat_lahir"), @Result(property = "tanggal_lahir", column = "tanggal_lahir"), 
			@Result(property = "jenis_kelamin", column = "jenis_kelamin"), @Result(property = "golongan_darah", column = "golongan_darah"),
			@Result(property = "agama", column = "agama"), @Result(property = "is_wni", column = "is_wni"), 
			@Result(property = "status_perkawinan", column = "status_perkawinan"), 
			@Result(property = "perkerjaan", column = "perkerjaan"), @Result(property = "is_wafat", column = "is_wafat"),
			@Result(property = "id_keluarga", column = "id_keluarga") })
	List<PendudukModel> selectpenduduks(@Param("id") String id);
	
	@Select("select id, nomor_kk, id_kelurahan, alamat, rt, rw " +
	"from keluarga where nomor_kk = #{nkk}")
    @Results(value = { @Result(property = "alamat", column = "alamat"),
			@Result(property = "id", column = "id"),
			@Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw")})
	KeluargaModel selectkeluarga2(@Param("nkk") String nkk);
	
	@Select("select id_kecamatan, nama_kelurahan " +
	"from kelurahan where id = #{id_kelurahan}")
    @Results(value = { @Result(property = "nama_kelurahan", column = "nama_kelurahan")})
	KelurahanModel selectkelurahan(@Param("id_kelurahan") String id_kelurahan);
	
	//soal 4
	@Select("select kode_kelurahan " +
	"from kelurahan where nama_kelurahan = #{nama_kelurahan}")
	String selectkodekelurahan(@Param("nama_kelurahan") String nama_kelurahan);
	
	@Select("select id " +
	"from kelurahan where nama_kelurahan = #{nama_kelurahan}")
	String selectidkelurahan(@Param("nama_kelurahan") String nama_kelurahan);
	
	@Select("Select nomor_kk from keluarga where nomor_kk between #{min} and #{max} Order by nomor_kk desc limit 1")
	String getNkkKeluarga(@Param("min") String min, @Param("max") String max);
	
	@Select("select id,nama_kecamatan from kecamatan where id_kota = #{id}")
	List<KecamatanModel> selectkecamatans(@Param("id") String id);
	
	@Select("select id, nama_kelurahan from kelurahan where id_kecamatan = #{id}")
	List<KelurahanModel> selectkelurahans(@Param("id") String id);
	
	//soal 8
	@Select("SELECT * from penduduk where id_keluarga IN (select id from keluarga where id_kelurahan = #{id})")
	@Results(value = { @Result(property = "nomor_kk", column = "nomor_kk")})
	List<PendudukModel> selectkeluargas(@Param("id") String id);
		
	// soal 3	
	@Select("Select max(id) from penduduk")
	String getMaxId();
	
	//soal 4
	@Select("Select max(id) from keluarga")
	String getMaxIdKeluarga();
	
	//soal 3
	@Select("Select nik from penduduk where nik between #{min} and #{max} and jenis_kelamin = #{jenis_kelamin} Order by nik desc limit 1")
	String getNikPenduduk(@Param("min") String min, @Param("max") String max, @Param("jenis_kelamin") String jenis_kelamin);
	
	@Insert("insert into penduduk(id,nik,nama,tempat_lahir,tanggal_lahir,jenis_kelamin, is_wni, id_keluarga,agama,pekerjaan,status_perkawinan,status_dalam_keluarga,golongan_darah,is_wafat) values(#{id}, #{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, "+
	"#{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
	void addpenduduks(PendudukModel penduduk);
	
	// soal 4
	@Insert("insert into keluarga(id,nomor_kk,alamat,rt,rw,id_kelurahan,is_tidak_berlaku) values (#{id}, #{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, "+
		"#{is_tidak_berlaku}) ")
	void addkeluargas(KeluargaModel keluarga);
	
	
	@Update("update penduduk SET is_wafat = #{is_wafat} where id = #{id}")
	void updateStatusHidup(PendudukModel penduduk);
	
	// soal 5
	@Update("update penduduk SET nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, "+
    "agama = #{agama}, pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} where id = #{id}")
	void updatePenduduk(PendudukModel penduduk);
	
	//soal 6
	@Update("update keluarga SET nomor_kk = #{nomor_kk}, alamat = #{alamat}, RT = #{rt}, RW = #{rw}, id_kelurahan = #{id_kelurahan}, is_tidak_berlaku = #{is_tidak_berlaku} where id = #{id}")
	void updateKeluarga(KeluargaModel keluarga);
}



