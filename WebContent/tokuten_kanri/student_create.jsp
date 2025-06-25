<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.html" %>
<%@ page import="bean.Teacher" %>
<%
  Teacher teacher = (Teacher) session.getAttribute("teacher");
%>

<main class="content" style="display: flex; justify-content: space-between; align-items: center; padding: 10px 20px;">
  <p class="title" style="margin: 0;">
    得点管理システム　<strong><%= teacher.getName() %></strong>
    <a href="Logout.action" style="color: #007bff; text-decoration: underline; font-size: 0.9em;">
      ログアウト
    </a>
  </p>
</main>

<c:if test="${not empty message}">
  <div class="alert-box">${message}</div>
</c:if>

<form action="StudentCreate.action" method="post">

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

  <c:if test="${not empty errorSchoolCd}">
    <div class="alert-box">⚠️ ${errorSchoolCd}</div>
  </c:if>
  <label>学校コード:</label>
  <input type="text" name="school_cd" class="text" value="${school_cd}" required>

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

<%@ include file="../footer.html" %>
