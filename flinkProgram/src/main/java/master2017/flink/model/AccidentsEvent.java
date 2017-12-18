package master2017.flink.model;


import master2017.flink.utils.CsvFieldJoin;

import java.util.ArrayList;
import java.util.List;

public class AccidentsEvent implements Cloneable {

    private final static int TIME_INTERVAL = 30;
    private final static int REPORT_INTERVAL = 4;
    private int time1;
    private int time2;
    private int vid;
    private int xWay;
    private int seg;
    private int dir;
    private int pos;

    public AccidentsEvent() {
    }

    public AccidentsEvent(int time1, int time2, int vid, int xWay, int seg, int dir, int pos) {
        this.time1 = time1;
        this.time2 = time2;
        this.vid = vid;
        this.xWay = xWay;
        this.seg = seg;
        this.dir = dir;
        this.pos = pos;
    }

    public static AccidentsEvent fromCarEvent(CarEvent carEvent) {
        return new AccidentsEvent(
                carEvent.getTime(),
                carEvent.getTime(),
                carEvent.getVid(),
                carEvent.getXway(),
                carEvent.getSeg(),
                carEvent.getDir(),
                carEvent.getPos()
        );
    }

    public boolean isTimeConsecutive(CarEvent carEvent) {
        return carEvent.getTime() == this.time2 + TIME_INTERVAL;
    }

    public boolean has4orMoreRecords() {
        return (this.getTime2() - this.getTime1()) / TIME_INTERVAL + 1 >= REPORT_INTERVAL;
    }

    private AccidentsEvent copyWithTime(int time1, int time2) {
        AccidentsEvent accidentsEvent = this.clone();
        accidentsEvent.setTime1(time1);
        accidentsEvent.setTime2(time2);
        return accidentsEvent;
    }

    public List<AccidentsEvent> splite() {
        List<AccidentsEvent> events = new ArrayList<AccidentsEvent>();
        for (int i = this.time1; i < this.time2; i += TIME_INTERVAL) {
            if (i + TIME_INTERVAL * (REPORT_INTERVAL - 1) <= this.time2) {
                events.add(copyWithTime(i, i + TIME_INTERVAL * (REPORT_INTERVAL - 1)));
            } else {
                break;
            }
        }
        return events;
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return (new CsvFieldJoin<Integer>()).join(time1, time2, vid, xWay, seg, dir, pos);
    }

    public AccidentsEvent clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException exception) {
            System.err.println("Not support cloneable");
        }
        return (AccidentsEvent) object;
    }
}
