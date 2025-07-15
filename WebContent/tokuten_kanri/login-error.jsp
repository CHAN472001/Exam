<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.html"%>

<p class="title">得点管理システム</p>

<!-- 固定のエラーメッセージ -->
<p style="color:red; text-align:center;">IDまたはパスワードが確認できませんでした。</p>

<form action="Login.action" method="post">
  <p>ID:
    <input type="text" class="text" name="id"
      value="${param.id != null ? param.id : ''}">
  </p>
  <p>
    パスワード:
    <input type="password" class="password" name="password" id="password">
    <label>
      <input type="checkbox" onclick="showPassword()"> パスワードを表示
    </label>
  </p>
  <p><input type="submit" class="submit" value="ログイン"></p>
</form>

<script>
function showPassword() {
  const passwordField = document.getElementById("password");
  passwordField.type = (passwordField.type === "password") ? "text" : "password";
}
</script>

<%@include file="../footer.html"%>
