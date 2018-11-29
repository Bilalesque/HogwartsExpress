package com.example.bilalsalman.hogwartsexpress;

public class RouteObjForFireBase {

    String start, stop1, stop2, stop3, stop4, stop5, trackId, dest, id;

    public RouteObjForFireBase() {
    }

    public RouteObjForFireBase(String start, String stop1, String stop2, String stop3, String stop4, String stop5, String trackId, String dest, String id) {
        this.start = start;
        this.stop1 = stop1;
        this.stop2 = stop2;
        this.stop3 = stop3;
        this.stop4 = stop4;
        this.stop5 = stop5;
        this.trackId = trackId;
        this.dest = dest;
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop1() {
        return stop1;
    }

    public void setStop1(String stop1) {
        this.stop1 = stop1;
    }

    public String getStop2() {
        return stop2;
    }

    public void setStop2(String stop2) {
        this.stop2 = stop2;
    }

    public String getStop3() {
        return stop3;
    }

    public void setStop3(String stop3) {
        this.stop3 = stop3;
    }

    public String getStop4() {
        return stop4;
    }

    public void setStop4(String stop4) {
        this.stop4 = stop4;
    }

    public String getStop5() {
        return stop5;
    }

    public void setStop5(String stop5) {
        this.stop5 = stop5;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}