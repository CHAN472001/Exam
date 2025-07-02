package bean;

import java.io.Serializable;

public class Test implements Serializable {
    private int studentNo;
    private String studentName;  // ← 追加
    private String subjectCd;
    private String schoolCd;
    private int no; // 回数
    private int point;
    private int classNum;
    private int entYear;  // ← 入学年度

    public int getStudentNo() { return studentNo; }
    public void setStudentNo(int studentNo) { this.studentNo = studentNo; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getSubjectCd() { return subjectCd; }
    public void setSubjectCd(String subjectCd) { this.subjectCd = subjectCd; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public int getClassNum() { return classNum; }
    public void setClassNum(int classNum) { this.classNum = classNum; }

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }
}
