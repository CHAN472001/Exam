<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>
 <p class="title">得点管理システム</p>
<form action="Login.action" method="post">

    <p>ID: <input type="text" class="text" name="id"></p>
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

