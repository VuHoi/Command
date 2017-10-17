package model;

/**
 * Created by Billy on 10/16/2017.
 */

public class Command_Detail_Employ {
    String sophieu;
    int chuyenvien;
    String vaitro;
    int pos;
    String status;

    public String getSophieu() {
        return sophieu;
    }

    public void setSophieu(String sophieu) {
        this.sophieu = sophieu;
    }

    public int getChuyenvien() {
        return chuyenvien;
    }

    public void setChuyenvien(int chuyenvien) {
        this.chuyenvien = chuyenvien;
    }

    public Command_Detail_Employ(String sophieu, int chuyenvien,String vaitro) {
        this.sophieu = sophieu;
        this.chuyenvien = chuyenvien;
        this.vaitro=vaitro;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Command_Detail_Employ(String sophieu, int chuyenvien, String vaitro, int pos, String status) {

        this.sophieu = sophieu;
        this.chuyenvien = chuyenvien;
        this.vaitro = vaitro;
        this.pos = pos;
        this.status = status;
    }
}
