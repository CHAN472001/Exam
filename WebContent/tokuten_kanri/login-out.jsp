<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.Teacher" %>
<%@ include file="../header.html" %>

<%
    Teacher teacher = (Teacher) session.getAttribute("teacher");

%>
<main class="content">
    <p class="title" style="align-items: center; justify-content: space-between;">
      <span>得点管理システム　<strong><%= teacher.getName() %></strong></span>
    </p>

 </main>

<div class="container">

  <!-- メニュー部分 -->
  <nav class="menu">
    <div>
      <p><strong>メニュー</strong></p>
      <ul>
        <li><a href="../tokuten_kanri/student_list.jsp">学生管理</a></li>
        <li><a href="../kadai/insert">成績管理</a></li>
        <li><a href="../kadai/update">成績登録</a></li>
        <li><a href="../kadai/update">成績参照</a></li>
        <li><a href="../kadai/delete">科目管理</a></li>
      </ul>
    </div>
    <form action="Logout.action" method="post" style="margin:0;">
      <input type="submit" value="ログアウト" class="logout-button" />
    </form>
  </nav>

  <!-- メインコンテンツ部分 -->


</div>

<%@ include file="../footer.html" %>
