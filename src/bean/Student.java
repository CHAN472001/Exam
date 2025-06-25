package bean;

import java.io.Serializable;

public class Student implements Serializable {
    private int no;
    private String name;
    private int entYear;    // 入学年度
    private int classNum;   // クラス番号
    private boolean attend; // 在学中フラグ
    private String schoolCd; // 追加された場合のため

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public int getClassNum() { return classNum; }
    public void setClassNum(int classNum) { this.classNum = classNum; }

    public boolean isAttend() { return attend; }
    public void setAttend(boolean attend) { this.attend = attend; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }
}
