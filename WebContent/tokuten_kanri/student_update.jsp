<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>

<section style="flex: 1;">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4" style="text-align: center;">学生情報変更</h2>

  <c:if test="${not empty message}">
    <div class="alert-box">${message}</div>
  </c:if>

  <form action="StudentUpdateComplete.action" method="post" class="px-4">
    <!-- 学生番号（表示のみ） -->
    <label>学生番号:</label>
    <input type="text" name="no" value="${student.no}" readonly class="text" />

    <!-- 入学年度（表示のみ） -->
    <label>入学年度:</label>
    <input type="text" name="ent_year" value="${student.entYear}" readonly class="text" />

    <!-- 名前（変更可） 最初空欄 -->
    <label>名前:</label>
    <input type="text" name="name" value="" required class="text" />

    <!-- クラス番号（変更可） 最初空欄 -->
    <label>クラス番号:</label>
    <input type="text" name="class_num" value="" required class="text" />

    <!-- 在学中（変更可） -->
    <label>在学中:
      <input type="checkbox" name="is_attend" value="true"
        <c:if test="${student.attend}">checked</c:if> />
    </label>

    <div style="text-align: center; margin-top: 10px;">
      <input type="submit" value="変更を保存" class="submit" />
    </div>
  </form>

  <p style="text-align: center; margin-top: 15px;">
    <a href="StudentList.action" class="submit" style="text-decoration:none;">学生一覧に戻る</a>
  </p>
</section>

</div> <%-- container閉じタグ --%>

<%@ include file="../footer.html" %>
