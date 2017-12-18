package master2017.flink.model;

import master2017.flink.utils.CsvFieldJoin;

public class AvgSpeedFinesEvent {
    private int time1;
    private int time2;
    private int minSeg;
    private int maxSeg;
    private int vid;
    private int xWay;
    private int dir;
    private int avgSpd;

    public AvgSpeedFinesEvent() {
    }

    public AvgSpeedFinesEvent(int time1, int time2, int minSeg, int maxSeg, int vid, int xWay, int dir, int avgSpd) {
        this.time1 = time1;
        this.time2 = time2;
        this.minSeg = minSeg;
        this.maxSeg = maxSeg;
        this.vid = vid;
        this.xWay = xWay;
        this.dir = dir;
        this.avgSpd = avgSpd;
    }

    public static AvgSpeedFinesEvent fromCarEvent(CarEvent event) {
        return new AvgSpeedFinesEvent(
                event.getTime(),
                event.getTime(),
                event.getSeg(),
                event.getSeg(),
                event.getVid(),
                event.getXway(),
                event.getDir(),
                event.getSpd()
        );
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        if (time1 < this.time1)
            this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        if (time2 > this.time2)
        this.time2 = time2;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getxWay() {
        return xWay;
    }

    public void setxWay(int xWay) {
        this.xWay = xWay;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getAvgSpd() {
        return avgSpd;
    }

    public void setAvgSpd(int avgSpd) {
        this.avgSpd = (avgSpd + this.avgSpd) / 2;
    }

    public int getMinSeg() {
        return minSeg;
    }

    public void setMinSeg(int minSeg) {
        if (minSeg < this.minSeg)
            this.minSeg = minSeg;
    }

    public int getMaxSeg() {
        return maxSeg;
    }

    public void setMaxSeg(int maxSeg) {
        if (maxSeg > this.maxSeg)
            this.maxSeg = maxSeg;
    }

    @Override
    public String toString() {
        return (new CsvFieldJoin<Integer>()).join(time1, time2, vid, xWay, dir, avgSpd);
    }
}
