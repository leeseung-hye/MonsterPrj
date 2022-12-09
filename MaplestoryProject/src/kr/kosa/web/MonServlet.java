package kr.kosa.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kosa.mon.MonDao;
import kr.kosa.mon.MonVo;

public class MonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MonServlet() {
		super();
		System.out.println("MonServlet 생성자 실행");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	ServletConfig config;
	MonDao dao = new MonDao();
	// dao 선언
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf('/'));
		// 주소의 글자 따오는 기
		String view = "/index.jsp";
		// 처음페이지 설정

		// 전체 리스트
		if("/MonList.do".equals(cmd)) {
			// 검색 할 분야와 검색 정보 저장 리스트
			Map<String, Object> map = new HashMap<String, Object>();

			// 페이지, 검색분야, 검색 단어, 현재 패이지
			String nowPageStr = request.getParameter("page");
			String searchField = request.getParameter("searchField");
			String searchWord = request.getParameter("searchWord");
			int nowPage = 1;

			//페이지 위치 숫자로 변형
			if(nowPageStr!=null) {
				nowPage = Integer.parseInt(nowPageStr);
			}
			//검색시 리시트에 해당 정보 저장
			if(searchWord != null) {
				map.put("searchField", searchField);
				map.put("searchWord", searchWord);
			}

			// 현재 입력된 모든 몬스터선언
			int totalCount = dao.getSearchMonCount(map);
			int totalPageCount = (int)Math.ceil(totalCount/10.0);

			//선언된 리스트 dao 연산 보내기
			map.put("totalCount", totalCount);
			List<MonVo> MonsterLists = dao.getSearchMonsByPage(map, nowPage);


			// 전달할 데이터 request 영역에 저장 이후 포워드
			request.setAttribute("totalPageCount", totalPageCount);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("MonsterLists", MonsterLists);
			request.setAttribute("map", map);

			view = "/WEB-INF/views/mon/monlist.jsp";

		}else if("/MonInsert.do".equals(cmd)) {
			System.out.println("입력양식을 요청합니다.");
			request.setAttribute("rList", dao.getregionList());
			request.setAttribute("tList", dao.gettypeList());
			request.setAttribute("sList", dao.getstarList());
			request.setAttribute("dList", dao.getdifficultyList());
			view = "/WEB-INF/views/mon/monform.jsp";
			// 몬스터 추가

		}else if ("/MonDetails.do".equals(cmd)) {  
			System.out.println("상세 정보를 요청합니다.");
			String monidStr = request.getParameter("monid");
			int monid = Integer.parseInt(monidStr);
			request.setAttribute("mon", dao.getMonDetails(monid));
			view = "/WEB-INF/views/mon/mondetails.jsp";
			// 몬스터 상세 정보 보기

		}else if ("/MonUpdate.do".equals(cmd)) {
			System.out.println("수정 정보를 요청합니다.");
			String monidStr = request.getParameter("monid");
			int monid = Integer.parseInt(monidStr);
			request.setAttribute("mon", dao.getMonDetails(monid));
			request.setAttribute("rList", dao.getregionList());
			request.setAttribute("tList", dao.gettypeList());
			request.setAttribute("sList", dao.getstarList());
			request.setAttribute("dList", dao.getdifficultyList());
			view="/WEB-INF/views/mon/monupdateform.jsp";
			// 몬스터 정보 수정

		}else if("/MonDelete.do".equals(cmd)) {
			view = "/WEB-INF/views/mon/mondeleteform.jsp";
		}
		// 몬스터 삭제
		RequestDispatcher disp = request.getRequestDispatcher(view);
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf('/'));
		// 입력 처리
		if("/MonInsert.do".equals(cmd)){
			request.setCharacterEncoding("utf-8");
			String monId = request.getParameter("monId");
			String regionList = request.getParameter("regionList");
			String monLevel = request.getParameter("monLevel");
			String typeList = request.getParameter("typeList");
			String monName = request.getParameter("monName");
			String starList = request.getParameter("starList");
			String difficultyList = request.getParameter("difficultyList");

			MonVo mon = new  MonVo();
			mon.setMonId(Integer.parseInt(monId));
			mon.setRegionList(regionList);
			mon.setMonLevel(Integer.parseInt(monLevel));
			mon.setTypeList(typeList);
			mon.setMonName(monName);
			mon.setStarList(Integer.parseInt(starList));
			mon.setDifficultyList(difficultyList);
			System.out.println("몬스터 생성중");
			dao.InsertMon(mon);
			response.sendRedirect("MonList.do");
			// 업데이트 처리
		}else if ("/MonUpdate.do".equals(cmd)) {
			request.setCharacterEncoding("utf-8");
			String monId = request.getParameter("monId");
			String regionList = request.getParameter("regionList");
			String monLevel = request.getParameter("monLevel");
			String typeList = request.getParameter("typeList");
			String monName = request.getParameter("monName");
			String starList = request.getParameter("starList");
			String difficultyList = request.getParameter("difficultyList");

			MonVo mon = new  MonVo();
			mon.setMonId(Integer.parseInt(monId));
			mon.setRegionList(regionList);
			mon.setMonLevel(Integer.parseInt(monLevel)); 
			mon.setTypeList(typeList);
			mon.setMonName(monName);
			mon.setStarList(Integer.parseInt(starList));
			mon.setDifficultyList(difficultyList);
			dao.updateMon(mon);
			response.sendRedirect("MonDetails.do?monid="+monId);	
		}else if("/MonDelete.do".equals(cmd)) {
			request.setCharacterEncoding("utf-8");
			String monid = request.getParameter("monid");
			String monname = request.getParameter("monname");
			dao.deleteMon(Integer.parseInt(monid),monname); 
			response.sendRedirect("MonList.do");
		}
	}
}
