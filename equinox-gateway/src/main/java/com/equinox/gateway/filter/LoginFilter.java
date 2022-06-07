package com.equinox.gateway.filter;

//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

//import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter {// extends ZuulFilter
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        //true表示过滤器生效
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        // 登录校验逻辑。
//        // 1）获取Zuul提供的请求上下文对象
//        RequestContext context = RequestContext.getCurrentContext();
//        // 2) 从上下文中获取request对象
//        HttpServletRequest req = context.getRequest();
//        // 3) 从请求中获取token
//        String token = req.getParameter("access-token");
//        if (token == null || "".equals(token.trim())) {
//            //没有token，鉴权校验失败，拦截
//            context.setSendZuulResponse(false);
//            context.getResponse().setContentType("text/html;charset=UTF-8");
//            context.setResponseBody("鉴权失败，access-token为空");
//            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//        }
//        // 校验通过，可以考虑把用户信息放入上下文，继续向后执行
//        return null;
//    }
}
