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
    private AddressService addressService;

    public AddressFilter(JwtUtil jwtUtil, AddressService addressService) {
        super();
        this.jwtUtil = jwtUtil;
        this.addressService = addressService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String token = req.getHeader("token");
        String url = req.getRequestURI();
        String method = req.getMethod();
        System.out.println(req.getRemoteAddr());
        try {
            if(restrictedPath(url) && !method.equals("OPTIONS")) {
                jwtUtil.isTokenExpired(token);
                String ipAddress = jwtUtil.extractIpAddress(token);
                int id = jwtUtil.extractId(token);
                if(ipAddress.equals(req.getRemoteAddr())){
                    if(addressService.approveIpAddress(req.getRemoteAddr(),id)) {
                        chain.doFilter(request, response);
                    } else {
                       sendErrorResponse(res,"Duplicate ip address !!!");
                    }
                } else {
                    sendErrorResponse(res,"different ip address issue");
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e){
            e.printStackTrace();
            sendErrorResponse(res,"you are not authorized");
        }
//        chain.doFilter(request, response);
    }

    private boolean restrictedPath(String url) {
        if (url.contains("/api")){
            if(url.contains("/login") || url.contains("/signup")){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private void sendErrorResponse(HttpServletResponse res , String message) throws IOException {
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Headers","*");
        res.setHeader("Access-Control-Expose-Headers","*");
        res.sendError(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
