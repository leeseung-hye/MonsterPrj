<%@ tag language="java" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ tag body-content="empty"%>
<!-- 태그 사이에 바디가 없음을 의미 -->
<%@ attribute name="totalPageCount" type="java.lang.Integer"
	required="true"%>
<%@ attribute name="nowPage" type="java.lang.Integer" required="true"%>
<%
	int totalPageBlock = (int) (Math.ceil(totalPageCount / 10.0)); // 예. 총 11페이지 = 1~11페이지 -> 총 11개 페이지 블록
	int nowPageBlock = (int) Math.ceil(nowPage / 10.0); // 현재 페이지 블록 = 1~10까지 1개, 11페이지 1개 -> 총 2개 나우페이지 블록
	int startPage = (nowPageBlock - 1) * 10 + 1; // 나우페이지에서 첫페이지블록의 스타트페이지는 1, 두번째페이지블록의 스타트는 11
	int endPage = 0;
	String contextPath = application.getContextPath();
	if (contextPath == null || contextPath.trim().equals("")) {
		contextPath = "";
	}
	if (totalPageCount > nowPageBlock * 10) {
		endPage = nowPageBlock * 10; // 스타트와 동일하게 끝을 구하는 것으로 첫페이지블록 끝은 10, 두번째 페이지 블록의 끝은 11
	} else {
		endPage = totalPageCount;
	}

	if (nowPageBlock > 1) {
		out.print("<a href=\"" + contextPath + "/MonList.do?page=" + (startPage - 1) + "\">");
		out.print("◀</a>");
	}
	for (int i = startPage; i <= endPage; i++) { // 시작페이지부터 끝 페이지까지
		out.print(" ");
		out.print("<a href=\"" + contextPath + "/MonList.do?page=" + i + "\">");
		out.print(i);
		out.print("</a>");
	}
	if (nowPageBlock < totalPageBlock) {
		out.print(" ");
		out.print("<a href=\"" + contextPath + "/MonList.do?page=" + (endPage + 1) + "\">");
		out.print("▶");
		out.print("</a>");
	}
%>