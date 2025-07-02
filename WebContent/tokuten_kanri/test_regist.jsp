<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="base.jsp" %>

<section style="flex:1;">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4" style="text-align:center;">成績登録</h2>

  <c:if test="${not empty message}">
    <div class="alert-box">⚠️ ${message}</div>
  </c:if>

  <!-- 条件フォーム -->
  <form method="get" action="TestRegist.action" class="px-4">
    <label>入学年度:</label>
    <select name="ent_year" required>
      <option value="">選択してください</option>
      <c:forEach var="y" items="${entYears}">
        <option value="${y}" <c:if test="${y == selectedEntYear}">selected</c:if>>${y}</option>
      </c:forEach>
    </select>

    <label>クラス番号:</label>
    <select name="class_num" required>
      <option value="">選択してください</option>
      <c:forEach var="c" items="${classNums}">
        <option value="${c}" <c:if test="${c == selectedClassNum}">selected</c:if>>${c}</option>
      </c:forEach>
    </select>

    <label>科目:</label>
    <select name="subject_cd" required>
      <option value="">選択してください</option>
      <c:forEach var="s" items="${subjects}">
        <option value="${s.cd}" <c:if test="${s.cd == selectedSubjectCd}">selected</c:if>>${s.name}</option>
      </c:forEach>
    </select>

    <label>回数:</label>
    <input type="number" name="no" value="${selectedNo}" min="1" style="width:60px;" required/>

    <button type="submit">検索</button>
  </form>

  <!-- 検索結果テーブル -->
  <c:if test="${not empty testList}">
    <form action="TestRegistDone.action" method="post" class="px-4" style="margin-top:20px;">
      <table border="1" cellpadding="5" cellspacing="0" style="width:100%; text-align:center;">
        <tr>
          <th>入学年度</th>
          <th>クラス番号</th>
          <th>学生番号</th>
          <th>氏名</th>
          <th>点数</th>
        </tr>
        <c:forEach var="t" items="${testList}">
          <tr>
            <td>${selectedEntYear}</td>
            <td>${selectedClassNum}</td>
            <td>${t.studentNo}</td>
            <td>${t.studentName}</td>
            <td>
              <input type="hidden" name="student_no" value="${t.studentNo}" />
              <input type="number" name="point" value="${t.point}" min="0" max="100" required/>
            </td>
          </tr>
        </c:forEach>
      </table>

      <!-- 条件も一緒に送る -->
      <input type="hidden" name="subject_cd" value="${selectedSubjectCd}"/>
      <input type="hidden" name="no" value="${selectedNo}"/>
      <input type="hidden" name="class_num" value="${selectedClassNum}"/>

      <div style="text-align:center; margin-top:20px;">
        <button type="submit" class="submit">登録して終了</button>
      </div>
    </form>
  </c:if>

</section>

<%@ include file="../footer.html" %>
