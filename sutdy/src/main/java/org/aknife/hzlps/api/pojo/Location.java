package org.aknife.hzlps.api.pojo;

/**
 * @ClassName Location
 * @Author HeZiLong
 * @Data 2021/1/8 15:11
 */
public class Location {

    /**
     * 表示某一区
     */
    private int area;

    /**
     * 表示该区的某一地名
     */
    private int place;

    /**
     * 表示该区的x轴位置
     */
    private int x;

    /**
     * 表示该区域的y轴位置
     */
    private int y;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "area=" + area +
                ", place=" + place +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
