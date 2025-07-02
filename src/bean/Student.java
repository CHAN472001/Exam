package bean;

import java.io.Serializable;
import java.util.Map;

public class Student implements Serializable {
    private int no;
    private String name;
    private int entYear;
    private int classNum;
    private boolean isAttend;
    private String schoolCd;

    private Map<Integer, Integer> points; // ← 追加：回数 → 点数

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public int getClassNum() { return classNum; }
    public void setClassNum(int classNum) { this.classNum = classNum; }

    public boolean isAttend() { return isAttend; }
    public void setAttend(boolean isAttend) { this.isAttend = isAttend; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }

    public Map<Integer, Integer> getPoints() { return points; }
    public void setPoints(Map<Integer, Integer> points) { this.points = points; }
}
