<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.Teacher" %>
<%@ include file="../header.html" %>

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

<div class="container" style="display:flex;">

  <nav class="menu" style="width: 20%; border-right: 2px solid #ccc; padding:10px;">
    <!-- 左サイドメニュー -->
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

  <main class="content" style="flex:1; padding:20px;">
    <!-- 中央表示エリア -->
    <style>
      .box-container {
        display: flex;
        justify-content: space-between;
        gap: 20px;
        flex-wrap: wrap;
      }
      .box {
        flex: 1;
        padding: 20px;
        border-radius: 10px;
        font-weight: bold;
        text-align: center;

        /* 追加 */
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

        min-width: 200px;
      }
      .box a {
        color: #333;
        text-decoration: none;
        display: block;
        margin: 10px 0;
      }
      .box a:hover {
        text-decoration: underline;
      }
.box-student {
  background-color: paleVioletRed; /* 薄めの赤・ピンク系（元: #f8d7da） */
}

.box-score {
  background-color: aquamarine; /* 薄緑（元: #d4edda） */
}

.box-subject {
  background-color: lavender;
}

      }
    </style>

    <h2 style="text-align:center;">メニュー</h2>

    <div class="box-container">
      <div class="box box-student">
        <a href="../tokuten_kanri/StudentList.action">学生管理</a>
      </div>

      <div class="box box-score">
        <h4>成績管理</h4>
        <a href="../kadai/insert">成績登録</a>
        <a href="../kadai/update">成績参照</a>
      </div>

      <div class="box box-subject">
        <a href="../kadai/delete">科目管理</a>
      </div>
    </div>
  </main>

</div>

<%@ include file="../footer.html" %>
