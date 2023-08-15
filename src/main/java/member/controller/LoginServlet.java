package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;

public class LoginServlet extends HttpServlet{
	/*1) 클라이언트가 전송한 파라미터 받기
	 *2) 넘겨받은 파라미터를 이용하여 오라클에서 조회하기
	 *3) 회원존재 -> 정보를 세션에 보관함
	 *    회원 존재X -> 에러 메시지 전송(js)
	 */
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="jsp";
	String pw="1234";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//클라이언트의 요청이 post인 경우, doPost 메서드가 동작함
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		
		//출력
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("아이디는 "+id+"<br>");
		out.print("비번은 "+pass+"<br>");
		
		//오라클 조회
		try {
			//오라클 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//접속
			con=DriverManager.getConnection(url, user, pw);
			
			//쿼리 수행
			String sql="select * from member where id=? and pass=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs=pstmt.executeQuery(); //select 쿼리 수행 및 표 반환
			
			if(rs.next()) { //회원과 일치하는 레코드가 있다면, 로그인 성공
				//회원 정보를 가져와서, 세션 객체에 담기
				//추후 응답으로 인해 연결이 끊어지더라도 다시 재접속 시 회원 정보를 출력해줌(회원을 기억하는 효과)
				Member member=new Member(); //빈 회원 객체 DTO 생성
				member.setMember_idx(rs.getInt("member_idx"));
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setRegdate(rs.getString("regdate"));
				
				//세션 객체에 DTO를 담아두기
				//jsp에서의 session 내장객체의 자료형은
				HttpSession session=request.getSession();
				//String key=Integer.toString(member.getMember_idx());
				session.setAttribute("member", member); //(키값, DTO객체)
				
				//웹사이트의 mypage, main 등으로 원하는 페이지로 재접속할 것을 명령
				//클라이언트인 웹브라우저로 하여금, 지정한 url로 다시 들어오게 명령
				response.sendRedirect("/member/mypage.jsp");
				
			}else { //레코드가 존재하지 않으면 로그인 실패
				out.print("<script>");
				out.print("alert('로그인 정보가 올바르지 않다 뿅');");
				out.print("history.back();"); //다시 이전페이지로 돌아가게 함
				out.print("</script>");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
}
