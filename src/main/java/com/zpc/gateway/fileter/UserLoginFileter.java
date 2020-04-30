package com.zpc.gateway.fileter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
/**
 * 网关过滤器，加入到Spring容器
 * @author user
 *
 */
@Component
public class UserLoginFileter extends ZuulFilter{
	/**
	 * 过滤器的具体业务逻辑
	 */
	@Override
	public Object run() throws ZuulException {
		
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest  request = requestContext.getRequest();
		String token = request.getParameter("token");
		if(StringUtils.isEmpty(token)){
			requestContext.setSendZuulResponse(false);//过滤该请求，不对其进行路由
			requestContext.setResponseStatusCode(401);//设置状态响应码
			requestContext.setResponseBody("token is Empty!");
			return null;
		}
		return null;
	}
	/**
	 * 返回一个Boolean值，判断该过滤器是否需要执行。返回true执行，返回false不执行。
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高。
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 返回字符串代表过滤器的类型
	 *  a)pre：请求在被路由之前执行
		b)routing：在路由请求时调用
		c)post：在routing和errror过滤器之后调用
		d)error：处理请求时发生错误调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	
}
