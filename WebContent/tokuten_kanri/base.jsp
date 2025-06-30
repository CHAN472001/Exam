<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.html" %>
<%@ page import="bean.Teacher" %>
<%
  Teacher teacher = (Teacher) session.getAttribute("teacher");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<main class="content" style="display: flex; justify-content: center; align-items: center; padding: 10px 20px;">
  <p class="title" style="margin: 0; font-size: 1.2em; display: flex; align-items: center; gap: 20px;">
    得点管理システム　<strong><%= teacher.getName() %></strong>
    <a href="Logout.action"
       style="color: #007bff; text-decoration: underline; font-size: 0.9em;">
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
