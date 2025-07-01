<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>

<section style="flex: 1;">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4" style="text-align: center;">科目登録</h2>

  <c:if test="${not empty errorCd}">
    <div class="alert-box">⚠️ ${errorCd}</div>
  </c:if>

  <c:if test="${not empty errorName}">
    <div class="alert-box">⚠️ ${errorName}</div>
  </c:if>

  <form action="SubjectCreateDone.action" method="post" class="px-4">
    <label>科目コード (3文字):</label>
    <input type="text" name="cd" class="text" value="${cd}" maxlength="3" required />

    <label>科目名:</label>
    <input type="text" name="name" class="text" value="${name}" required />

    <div style="text-align: center; margin-top: 10px;">
      <input type="submit" value="登録" class="submit" />
    </div>
  </form>

  <p style="text-align: center; margin-top: 15px;">
    <a href="SubjectList.action" class="submit" style="text-decoration:none;">科目一覧に戻る</a>
  </p>
</section>

</div>

<%@ include file="../footer.html" %>
