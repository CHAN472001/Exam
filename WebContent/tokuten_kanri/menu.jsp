<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>

<!-- メイン中央表示エリア -->
<section style="flex: 1; padding: 20px;">
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

      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      min-width: 200px;
    }

    .box a {
      color: #333;
      text-decoration: none;
      margin: 10px 0;
    }

    .box a:hover {
      text-decoration: underline;
    }

    .box-student {
      background-color: paleVioletRed; /* 学生管理用 */
    }

    .box-score {
      background-color: aquamarine; /* 成績管理用 */
    }

    .box-subject {
      background-color: lavender; /* 科目管理用 */
    }
  </style>

  <h2 style="text-align: center;">メニュー</h2>

  <div class="box-container">
    <div class="box box-student">
      <a href="../tokuten_kanri/StudentList.action">学生管理</a>
    </div>

    <div class="box box-score">
      <h4>成績管理</h4>
      <a href="../tokuten_kanri/TestRegist.action">成績登録</a>
      <a href="../tokuten_kanri/TestList.action">成績参照</a>
    </div>

    <div class="box box-subject">
      <a href="../tokuten_kanri/SubjectList.action">科目管理</a>
    </div>
  </div>
</section>

</div> <%-- container の閉じタグ（base.jsp側で開いている） --%>

<%@ include file="../footer.html" %>
