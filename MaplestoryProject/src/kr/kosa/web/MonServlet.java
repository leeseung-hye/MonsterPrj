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
		System.out.println("MonServlet ������ ����");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	ServletConfig config;
	MonDao dao = new MonDao();
	// dao ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf('/'));
		// �ּ��� ���� ������ ��
		String view = "/index.jsp";
		// ó�������� ����

		// ��ü ����Ʈ
		if("/MonList.do".equals(cmd)) {
			// �˻� �� �о߿� �˻� ���� ���� ����Ʈ
			Map<String, Object> map = new HashMap<String, Object>();

			// ������, �˻��о�, �˻� �ܾ�, ���� ������
			String nowPageStr = request.getParameter("page");
			String searchField = request.getParameter("searchField");
			String searchWord = request.getParameter("searchWord");
			int nowPage = 1;

			//������ ��ġ ���ڷ� ����
			if(nowPageStr!=null) {
				nowPage = Integer.parseInt(nowPageStr);
			}
			//�˻��� ����Ʈ�� �ش� ���� ����
			if(searchWord != null) {
				map.put("searchField", searchField);
				map.put("searchWord", searchWord);
			}

			// ���� �Էµ� ��� ���ͼ���
			int totalCount = dao.getSearchMonCount(map);
			int totalPageCount = (int)Math.ceil(totalCount/10.0);

			//����� ����Ʈ dao ���� ������
			map.put("totalCount", totalCount);
			List<MonVo> MonsterLists = dao.getSearchMonsByPage(map, nowPage);


			// ������ ������ request ������ ���� ���� ������
			request.setAttribute("totalPageCount", totalPageCount);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("MonsterLists", MonsterLists);
			request.setAttribute("map", map);

			view = "/WEB-INF/views/mon/monlist.jsp";

		}else if("/MonInsert.do".equals(cmd)) {
			System.out.println("�Է¾���� ��û�մϴ�.");
			request.setAttribute("rList", dao.getregionList());
			request.setAttribute("tList", dao.gettypeList());
			request.setAttribute("sList", dao.getstarList());
			request.setAttribute("dList", dao.getdifficultyList());
			view = "/WEB-INF/views/mon/monform.jsp";
			// ���� �߰�

		}else if ("/MonDetails.do".equals(cmd)) {  
			System.out.println("�� ������ ��û�մϴ�.");
			String monidStr = request.getParameter("monid");
			int monid = Integer.parseInt(monidStr);
			request.setAttribute("mon", dao.getMonDetails(monid));
			view = "/WEB-INF/views/mon/mondetails.jsp";
			// ���� �� ���� ����

		}else if ("/MonUpdate.do".equals(cmd)) {
			System.out.println("���� ������ ��û�մϴ�.");
			String monidStr = request.getParameter("monid");
			int monid = Integer.parseInt(monidStr);
			request.setAttribute("mon", dao.getMonDetails(monid));
			request.setAttribute("rList", dao.getregionList());
			request.setAttribute("tList", dao.gettypeList());
			request.setAttribute("sList", dao.getstarList());
			request.setAttribute("dList", dao.getdifficultyList());
			view="/WEB-INF/views/mon/monupdateform.jsp";
			// ���� ���� ����

		}else if("/MonDelete.do".equals(cmd)) {
			view = "/WEB-INF/views/mon/mondeleteform.jsp";
		}
		// ���� ����
		RequestDispatcher disp = request.getRequestDispatcher(view);
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf('/'));
		// �Է� ó��
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
			System.out.println("���� ������");
			dao.InsertMon(mon);
			response.sendRedirect("MonList.do");
			// ������Ʈ ó��
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
