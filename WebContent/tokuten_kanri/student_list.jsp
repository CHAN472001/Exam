<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>

<section style="flex: 1;">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
  <div class="my-2 text-end px-4">
    <a href="StudentCreate.action">新規登録</a>
  </div>

  <form method="get" action="StudentList.action">
    <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
      <div class="col-4">
        <label class="form-label" for="student-f1-select">入学年度</label>
        <select class="form-select" id="student-f1-select" name="f1">
          <option value="0" <c:if test="${f1 == '0' || f1 == null}">selected</c:if>>------</option>
          <c:forEach var="year" items="${ent_year_set}">
            <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
          </c:forEach>
        </select>
      </div>

      <div class="col-4">
        <label class="form-label" for="student-f2-select">クラス</label>
        <select class="form-select" id="student-f2-select" name="f2">
          <option value="0" <c:if test="${f2 == '0' || f2 == null}">selected</c:if>>------</option>
          <c:forEach var="num" items="${class_num_set}">
            <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
          </c:forEach>
        </select>
      </div>

      <div class="col-2 form-check text-center">
        <label class="form-label" for="student-f3-check">在学中
          <input class="form-check-input" type="checkbox" id="student-f3-check" name="f3" value="t"
            <c:if test="${f3 == 't'}">checked</c:if> />
        </label>
      </div>

      <div class="col-2 text-center">
        <button class="btn btn-secondary" id="filter-button">絞り込み</button>
      </div>

      <div class="mt-2 text-warning">${errors["f1"]}</div>
    </div>
  </form>

  <c:choose>
    <c:when test="${not empty students and fn:length(students) > 0}">
      <div>検索結果: ${fn:length(students)} 件</div>
      <table class="table table-hover">
        <thead>
          <tr>
            <th>入学年度</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>クラス</th>
            <th class="text-center">在学中</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="student" items="${students}">
            <tr>
              <td>${student.entYear}</td>
              <td>${student.no}</td>
              <td>${student.name}</td>
              <td>${student.classNum}</td>
              <td class="text-center">
                <c:choose>
                  <c:when test="${student.attend}">○</c:when>
                  <c:otherwise>X</c:otherwise>
                </c:choose>
              </td>
              <td><a href="${pageContext.request.contextPath}/StudentUpdate.action?no=${student.no}">変更</a></td>
              <td><a href="${pageContext.request.contextPath}/StudentDelete.action?no=${student.no}">削除</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <c:if test="${filterExecuted}">
        <div class="text-danger">学生情報が見つかりませんでした。</div>
      </c:if>
    </c:otherwise>
  </c:choose>
</section>

</div> <%-- containerの閉じタグ --%>

<%@ include file="../footer.html" %>
