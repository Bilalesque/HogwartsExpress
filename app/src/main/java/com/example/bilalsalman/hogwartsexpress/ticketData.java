package com.example.bilalsalman.hogwartsexpress;

public class ticketData {

    Integer year,month,day,totalBussinessTickets,totalEconomyTickets,total,totalCount;
    String source,dest;
    String id;

    public ticketData(Integer year, Integer month, Integer day, Integer totalBussinessTickets, Integer totalEconomyTickets, Integer total, Integer totalCount, String source, String dest, String id) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalBussinessTickets = totalBussinessTickets;
        this.totalEconomyTickets = totalEconomyTickets;
        this.total = total;
        this.totalCount = totalCount;
        this.source = source;
        this.dest = dest;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ticketData() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getTotalBussinessTickets() {
        return totalBussinessTickets;
    }

    public void setTotalBussinessTickets(Integer totalBussinessTickets) {
        this.totalBussinessTickets = totalBussinessTickets;
    }

    public Integer getTotalEconomyTickets() {
        return totalEconomyTickets;
    }

    public void setTotalEconomyTickets(Integer totalEconomyTickets) {
        this.totalEconomyTickets = totalEconomyTickets;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}
