<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="base.jsp" %>

<section class="px-4 py-3">
  <h2 class="h4 mb-3 text-center">学生削除確認</h2>

  <p>以下の学生を削除してもよろしいですか？</p>

  <table class="table table-bordered w-50 mx-auto">
    <tr><th>学生番号</th><td>${student.no}</td></tr>
    <tr><th>氏名</th><td>${student.name}</td></tr>
  </table>

  <form action="StudentDeleteComplete.action" method="post" class="text-center mt-4">
    <input type="hidden" name="no" value="${student.no}" />
    <button type="submit" class="btn btn-danger">削除する</button>
    <a href="StudentList.action" class="btn btn-secondary">キャンセル</a>
  </form>
</section>

<%@ include file="../footer.html" %>
