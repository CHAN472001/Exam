<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<form action="Login.action" method="post">
    <p>ログイン名: <input type="text" name="login"></p>
    <p>
        パスワード:
        <input type="password" name="password" id="password">
        <label>
            <input type="checkbox" onclick="showPassword()"> パスワードを表示
        </label>
    </p>
    <p><input type="submit" value="ログイン"></p>
</form>

<script>
function showPassword() {
    const pwField = document.getElementById("password");
    pwField.type = (pwField.type === "password") ? "text" : "password";
}
</script>

<%@include file="../footer.html"%>
