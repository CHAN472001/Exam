<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="base.jsp" %>

<section style="flex:1;">

  <h2 class="title">成績参照</h2>

  <!-- フォーム横並び -->
  <div class="form-container">

    <!-- 科目情報フォーム -->
    <form method="get" action="TestList.action" class="search-box">
      <div class="form-title">科目情報</div>

      <label for="ent_year">入学年度:</label>
      <select name="ent_year" id="ent_year" required>
        <option value="">選択してください</option>
        <c:forEach var="y" items="${entYears}">
          <option value="${y}">${y}</option>
        </c:forEach>
      </select>

      <label for="class_num">クラス番号:</label>
      <select name="class_num" id="class_num" required>
        <option value="">選択してください</option>
        <c:forEach var="c" items="${classNums}">
          <option value="${c}">${c}</option>
        </c:forEach>
      </select>

      <label for="subject_cd">科目:</label>
      <select name="subject_cd" id="subject_cd" required>
        <option value="">選択してください</option>
        <c:forEach var="s" items="${subjects}">
          <option value="${s.cd}">${s.name}</option>
        </c:forEach>
      </select>

      <button type="submit" class="form-button">科目別検索</button>
    </form>

    <!-- 学生情報フォーム -->
    <form method="get" action="TestList.action" class="search-box">
      <div class="form-title">学生情報</div>

      <label for="student_no">学生番号:</label>
      <input type="number" name="student_no" id="student_no" required />

      <button type="submit" class="form-button">学生別検索</button>
    </form>

  </div>

  <!-- 科目別結果表示 -->
  <c:if test="${not empty testListByClass}">
    <h4 class="subtitle">科目別 成績一覧</h4>
    <p>科目: ${selectedSubject.name} (${selectedSubject.cd})</p>

    <table>
      <tr>
        <th>入学年度</th>
        <th>クラス番号</th>
        <th>学生番号</th>
        <th>氏名</th>
        <c:forEach begin="1" end="${maxNo}" var="i">
          <th>${i}回</th>
        </c:forEach>
      </tr>
      <c:forEach var="student" items="${studentsWithScores}">
        <tr>
          <td>${student.entYear}</td>
          <td>${student.classNum}</td>
          <td>${student.no}</td>
          <td>${student.name}</td>
          <c:forEach begin="1" end="${maxNo}" var="i">
            <td><c:out value="${student.points[i]}" default="-" /></td>
          </c:forEach>
        </tr>
      </c:forEach>
    </table>
  </c:if>

  <!-- 学生別結果表示 -->
  <c:if test="${not empty student}">
    <h4 class="subtitle">学生別 成績一覧</h4>
    <p>学生番号: ${student.no}</p>
    <p>氏名: ${student.name}</p>

    <table>
      <tr>
        <th>科目コード</th>
        <th>科目名</th>
        <th>回数</th>
        <th>点数</th>
      </tr>
      <c:forEach var="t" items="${testListByStudent}">
        <tr>
          <td>${t.subjectCd}</td>
          <td>${subjectMap[t.subjectCd]}</td>
          <td>${t.no}</td>
          <td>${t.point}</td>
        </tr>
      </c:forEach>
    </table>
  </c:if>

</section>

<%@ include file="../footer.html" %>