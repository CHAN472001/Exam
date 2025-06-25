<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>

<section style="flex: 1;">
  <h2 style="margin: 30px auto 20px auto; text-align: center; font-weight: bold; color: #2c3e50; max-width: 400px;">
    学生情報登録
  </h2>

  <label style="font-size: 1.2em; font-weight: bold; color: #333; display: block; margin-bottom: 20px;">
    登録が完了しました。
  </label>

  <ul style="list-style: none; padding: 0; display: flex; gap: 20px;">
    <li>
      <a href="StudentCreate.action"
         style="padding: 10px 15px; background-color: #b3d7f5; color: white; border-radius: 5px; font-weight: bold; text-decoration: none; display: inline-block; max-width: 120px; text-align: center;">
        戻る
      </a>
    </li>
    <li>
      <a href="StudentList.action"
         style="padding: 10px 15px; background-color: #91c4f2; color: white; border-radius: 5px; font-weight: bold; text-decoration: none; display: inline-block; max-width: 120px; text-align: center;">
        学生一覧
      </a>
    </li>
  </ul>
</section>

</div> <%-- containerの閉じタグ --%>

<%@ include file="../footer.html" %>
