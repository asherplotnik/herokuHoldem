package app.core.filters;

import app.core.security.JwtUtil;
import app.core.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddressFilter implements Filter {

    private JwtUtil jwtUtil;

    @Autowired
    AddressService addressService;

    public AddressFilter() {
    }

    public AddressFilter(JwtUtil jwtUtil) {
        super();
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String token = req.getHeader("token");
        String url = req.getRequestURI();
        System.out.println(req.getRemoteAddr());
//        try{
//            jwtUtil.isTokenExpired(token);
//            String ipAddress = jwtUtil.extractIpAddress(token);
//            int id = jwtUtil.extractId(token);
//            if(url.contains("/api")) {
//                if(ipAddress.equals(req.getRemoteAddr())){
//                    if(addressService.approveIpAddress(req.getRemoteAddr(),id)) {
//                        chain.doFilter(request, response);
//                    } else {
//                       sendErrorResponse(res,"Duplicate ip address !!!");
//                    }
//                } else {
//                    sendErrorResponse(res,"different ip address issue");
//                }
//            }
//        } catch (Exception e){
//            sendErrorResponse(res,"you are not authorized");
//        }
        chain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse res , String message) throws IOException {
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Headers","*");
        res.setHeader("Access-Control-Expose-Headers","*");
        res.sendError(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
