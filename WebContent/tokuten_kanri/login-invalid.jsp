<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../header.html"%>

<p class="title">得点管理システム</p>

<form action="Login.action" method="post">

    <c:if test="${not empty errorId}">
        <div class="alert-box">
            ⚠️ <span>${errorId}</span>
            <div class="arrow"></div>
        </div>
    </c:if>
    <p>ID: <input type="text" class="text" name="id" value="${id}"></p>


    <c:if test="${not empty errorPassword}">
        <div class="alert-box">
            ⚠️ <span>${errorPassword}</span>
             <div class="arrow"></div>
        </div>
    </c:if>
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
