<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>学生一覧</title>
  <style>
    table, th, td {
      border: 1px solid #000;
      border-collapse: collapse;
      padding: 5px;
    }
  </style>
</head>
<body>

<h2>学生一覧</h2>

<!-- StudentActionをPOSTで呼ぶフォーム -->
<form action="Student.action" method="post">
  <input type="submit" value="学生一覧を表示（更新）">
</form>

<table>
  <thead>
    <tr>
      <th>学生番号</th>
      <th>氏名</th>
      <th>入学年度</th>
      <th>クラス番号</th>
      <th>在学中</th>
      <th>学校コード</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="s" items="${students}">
      <tr>
        <td>${s.no}</td>
        <td>${s.name}</td>
        <td>${s.entYear}</td>
        <td>${s.classNum}</td>
        <td>
          <c:choose>
            <c:when test="${s.attend}">在学</c:when>
            <c:otherwise>退学</c:otherwise>
          </c:choose>
        </td>
        <td>${s.schoolCd}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
