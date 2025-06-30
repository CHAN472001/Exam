<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>

<section style="flex: 1;">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4" style="text-align: center;">
  学生情報登録
</h2>


  <c:if test="${not empty message}">
    <div class="alert-box">${message}</div>
  </c:if>

  <form action="StudentCreate.action" method="post" class="px-4">
    <c:if test="${not empty errorNo}">
      <div class="alert-box">⚠️ ${errorNo}</div>
    </c:if>
    <label>学生番号:</label>
    <input type="text" name="no" class="text" value="${no}" required>

    <c:if test="${not empty errorName}">
      <div class="alert-box">⚠️ ${errorName}</div>
    </c:if>
    <label>名前:</label>
    <input type="text" name="name" class="text" value="${name}" required>

    <c:if test="${not empty errorEntYear}">
      <div class="alert-box">⚠️ ${errorEntYear}</div>
    </c:if>
    <label>入学年度:</label>
    <select name="ent_year" class="text" required>
      <option value="">-- 選択してください --</option>
      <c:forEach var="year" items="${entYearList}">
        <option value="${year}" <c:if test="${ent_year == year}">selected</c:if>>${year}</option>
      </c:forEach>
    </select>

    <c:if test="${not empty errorClassNum}">
      <div class="alert-box">⚠️ ${errorClassNum}</div>
    </c:if>
    <label>クラス:</label>
    <input type="text" name="class_num" class="text" value="${class_num}" required>



    <label>在学中:
      <input type="checkbox" name="is_attend" value="true"
        <c:if test="${is_attend eq 'true' || is_attend eq 'on'}">checked</c:if>>
    </label>

    <div style="text-align: center; margin-top: 10px;">
      <input type="submit" value="追加" class="submit">
    </div>
  </form>

  <p style="text-align: center; margin-top: 15px;">
    <a href="StudentList.action" class="submit" style="display: inline-block; text-decoration: none;">学生一覧に戻る</a>
  </p>
</section>

</div> <%-- containerの閉じタグ --%>

<%@ include file="../footer.html" %>