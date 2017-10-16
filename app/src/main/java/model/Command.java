package model;

/**
 * Created by Hoi on 10/16/2017.
 */

public class Command {
    public Command(String soPhieu, String ngay, String chiHuyTrucTiep, String giamSatAnToan, String noiCongTac, String noiDungCongTac, String donViYeuCau, String dieuKien, String ngayBatDau, String ngayKetThuc, String dungCu, String phuongTienId, String raCong, String ghiChu, String pos, String status) {
        SoPhieu = soPhieu;
        Ngay = ngay;
        ChiHuyTrucTiep = chiHuyTrucTiep;
        GiamSatAnToan = giamSatAnToan;
        NoiCongTac = noiCongTac;
        NoiDungCongTac = noiDungCongTac;
        DonViYeuCau = donViYeuCau;
        DieuKien = dieuKien;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
        DungCu = dungCu;
        PhuongTienId = phuongTienId;
        RaCong = raCong;
        GhiChu = ghiChu;
        this.pos = pos;
        this.status = status;
    }

    public String getSoPhieu() {
        return SoPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        SoPhieu = soPhieu;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getChiHuyTrucTiep() {
        return ChiHuyTrucTiep;
    }

    public void setChiHuyTrucTiep(String chiHuyTrucTiep) {
        ChiHuyTrucTiep = chiHuyTrucTiep;
    }

    public String getGiamSatAnToan() {
        return GiamSatAnToan;
    }

    public void setGiamSatAnToan(String giamSatAnToan) {
        GiamSatAnToan = giamSatAnToan;
    }

    public String getNoiCongTac() {
        return NoiCongTac;
    }

    public void setNoiCongTac(String noiCongTac) {
        NoiCongTac = noiCongTac;
    }

    public String getNoiDungCongTac() {
        return NoiDungCongTac;
    }

    public void setNoiDungCongTac(String noiDungCongTac) {
        NoiDungCongTac = noiDungCongTac;
    }

    public String getDonViYeuCau() {
        return DonViYeuCau;
    }

    public void setDonViYeuCau(String donViYeuCau) {
        DonViYeuCau = donViYeuCau;
    }

    public String getDieuKien() {
        return DieuKien;
    }

    public void setDieuKien(String dieuKien) {
        DieuKien = dieuKien;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public String getDungCu() {
        return DungCu;
    }

    public void setDungCu(String dungCu) {
        DungCu = dungCu;
    }

    public String getPhuongTienId() {
        return PhuongTienId;
    }

    public void setPhuongTienId(String phuongTienId) {
        PhuongTienId = phuongTienId;
    }

    public String getRaCong() {
        return RaCong;
    }

    public void setRaCong(String raCong) {
        RaCong = raCong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String SoPhieu;

    public Command(String soPhieu, String chiHuyTrucTiep, String giamSatAnToan, String noiCongTac,String donViYeuCau,String status) {
        SoPhieu = soPhieu;
        ChiHuyTrucTiep = chiHuyTrucTiep;
        GiamSatAnToan = giamSatAnToan;
        NoiCongTac = noiCongTac;
        DonViYeuCau=donViYeuCau;
        this.status=status;
    }

    String Ngay;
    String ChiHuyTrucTiep;
    String GiamSatAnToan;
    String NoiCongTac;
    String NoiDungCongTac;
    String DonViYeuCau;
    String DieuKien;
    String NgayBatDau;
    String NgayKetThuc;
    String DungCu;
    String PhuongTienId;
    String RaCong;
    String GhiChu;
    String pos;
    String status;

}
