package master2017.flink.model;

import java.io.Serializable;

public class CarEvent {
    private int time;
    private int vid;
    private int spd;
    private int xway;
    private int lane;
    private int dir;
    private int seg;
    private int pos;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getXway() {
        return xway;
    }

    public void setXway(int xway) {
        this.xway = xway;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSeg() {
        return seg;
    }

    public void setSeg(int seg) {
        this.seg = seg;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public CarEvent() {
        this.time = this.vid = this.spd = this.xway = this.lane = this.dir = this.seg = this.pos = 0;
    }

    public CarEvent(int time, int vid, int spd, int xway, int lane, int dir, int seg, int pos) {
        this.time = time;
        this.vid = vid;
        this.spd = spd;
        this.xway = xway;
        this.lane = lane;
        this.dir = dir;
        this.seg = seg;
        this.pos = pos;
    }
}
