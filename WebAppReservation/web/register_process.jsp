
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONObject"%>

<%
    try {
	com.servies.HouseRentalServices_Service service = new com.servies.HouseRentalServices_Service();
	com.servies.HouseRentalServices port = service.getHouseRentalServicesPort();
	 // TODO initialize WS operation arguments here
         
	java.lang.String username = request.getParameter("username");
	java.lang.String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
	java.lang.String nama = request.getParameter("fullname");
	java.lang.String noTelp = request.getParameter("notelp");
	// TODO process result here
        response.setContentType("application/json;charset=utf-8");
        JSONObject obj = new JSONObject();
        if(password.equals(repassword))
        {
            java.lang.Boolean result = port.register(username, password, nama, noTelp);
            if (result) 
            {         
                obj.put("status", "sukses");
//                    session.setAttribute("Register", "sukses");
//                    response.sendRedirect("regis.jsp");
            }
            else
            {
                obj.put("status", "gagal"); 
//                session.setAttribute("Register", "gagal");
//                response.sendRedirect("regis.jsp");
            }
        }
        else
        {
            obj.put("status", "notmatch"); 
//            session.setAttribute("Register", "notmatch");
//            response.sendRedirect("regis.jsp");
        }
//        out.print(obj);
//        out.flush();
        PrintWriter pw = response.getWriter(); 
        pw.print(obj.toString());
        pw.close();
    } 
        catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>


