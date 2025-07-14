	<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ include file="base.jsp" %>
	
	<section class="px-4 py-3">
	  <h2 class="h4 mb-3 text-center">科目削除確認</h2>
	
	  <p>以下の科目を削除してもよろしいですか？</p>
	
	  <table class="table table-bordered w-50 mx-auto">
	    <tr><th>科目コード</th><td>${subject.cd}</td></tr>
	    <tr><th>科目名</th><td>${subject.name}</td></tr>
	  </table>
	
	  <form action="SubjectDeleteComplete.action" method="post" class="text-center mt-4">
	    <input type="hidden" name="cd" value="${subject.cd}" />
	    <button type="submit" class="btn btn-danger">削除する</button>
	    <a href="SubjectList.action" class="btn btn-secondary">キャンセル</a>
	  </form>
	</section>
	
	<%@ include file="../footer.html" %>
