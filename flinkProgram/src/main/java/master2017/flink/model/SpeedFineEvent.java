package master2017.flink.model;

import master2017.flink.utils.CsvFieldJoin;

public class SpeedFineEvent {
    private int time;
    private int vid;
    private int xway;
    private int seg;
    private int dir;
    private int spd;

    public SpeedFineEvent() {
        this.time = this.vid = this.xway = this.seg = this.dir = this.spd = 0;
    }

    public SpeedFineEvent(int time, int vid, int xway, int seg, int dir, int spd) {
        this.time = time;
        this.vid = vid;
        this.xway = xway;
        this.seg = seg;
        this.dir = dir;
        this.spd = spd;
    }

    public static SpeedFineEvent fromCarEvent(CarEvent carEvent) {
        return new SpeedFineEvent(
                carEvent.getTime(),
                carEvent.getVid(),
                carEvent.getXway(),
                carEvent.getSeg(),
                carEvent.getDir(),
                carEvent.getSpd());
    }

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

    public int getXway() {
        return xway;
    }

    public void setXway(int xway) {
        this.xway = xway;
    }

    public int getSeg() {
        return seg;
    }

    public void setSeg(int seg) {
        this.seg = seg;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    @Override
    public String toString() {
        return (new CsvFieldJoin<Integer>()).join(time, vid, xway, seg, dir, spd);
    }
}


