<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.html" %>
<%@ page import="bean.Teacher" %>
<%
  Teacher teacher = (Teacher) session.getAttribute("teacher");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<main class="content" style="display: flex; justify-content: space-between; align-items: center; padding: 10px 20px;">
  <p class="title" style="margin: 0;">
    得点管理システム　<strong><%= teacher.getName() %></strong>
    <a href="Logout.action" style="color: #007bff; text-decoration: underline; font-size: 0.9em;">
      ログアウト
    </a>
  </p>
</main>
<div class="container" style="display: flex; gap: 20px;">
  <nav class="menu" style="width: 20%; border-right: 2px solid #ccc; padding: 10px;">
    <p><strong>メニュー</strong></p>
    <ul>
      <li><a href="../tokuten_kanri/StudentList.action">学生管理</a></li>
    </ul>
    <p style="margin: 0;">成績管理</p>
    <ul>
      <li><a href="../kadai/insert">成績登録</a></li>
      <li><a href="../kadai/update">成績参照</a></li>
      <li><a href="../kadai/delete">科目管理</a></li>
    </ul>
  </nav>

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
</div>

<%@ include file="../footer.html" %>
