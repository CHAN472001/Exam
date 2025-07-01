<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>

<section style="flex: 1;">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4" style="text-align: center;">科目一覧</h2>

  <div class="my-2 text-end px-4">
    <a href="SubjectCreate.action">新規登録</a>
  </div>

  <c:choose>
    <c:when test="${not empty subjects}">
      <table class="table table-hover">
        <thead>
          <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th></th> <!-- 変更ボタン用 -->
            <th></th> <!-- 削除ボタン用 -->
          </tr>
        </thead>
        <tbody>
          <c:forEach var="subject" items="${subjects}">
            <tr>
              <td>${subject.cd}</td>
              <td>${subject.name}</td>
              <td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
              <td><a href="SubjectDelete.action?cd=${subject.cd}" onclick="return confirm('削除してもよろしいですか？');">削除</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <div class="text-danger">科目が登録されていません。</div>
    </c:otherwise>
  </c:choose>
</section>

</div>

<%@ include file="../footer.html" %>
