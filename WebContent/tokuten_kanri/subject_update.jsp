<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ include file="base.jsp" %>

<section style="flex: 1;">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4" style="text-align: center;">科目情報変更</h2>

  <c:if test="${not empty message}">
    <div class="alert-box">⚠️ ${message}</div>
  </c:if>

  <form action="SubjectUpdateDone.action" method="post" class="px-4">

    <label>科目コード:</label>
    <div style="margin-bottom: 10px;">
      ${subject.cd}
    </div>
    <input type="hidden" name="cd" value="${subject.cd}" />

    <label>科目名:</label>
    <input type="text" name="name" class="text" value="${subject.name}" required />

    <input type="hidden" name="school_cd" value="${subject.schoolCd}" />

    <div style="text-align: center; margin-top: 10px;">
      <input type="submit" value="変更" class="submit" />
    </div>
  </form>

  <p style="text-align: center; margin-top: 15px;">
    <a href="SubjectList.action" class="submit" style="text-decoration:none;">科目一覧に戻る</a>
  </p>
</section>

</div>

<%@ include file="../footer.html" %>
