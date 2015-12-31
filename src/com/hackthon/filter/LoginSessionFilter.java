package com.hackthon.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 *@author 作者 Email:xiangping165@sina.com
 *@version 创建时间：2013-12-15下午8:00:05
 *@类说明 进行登录过滤
 */

public class LoginSessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//不过滤的uri
		String[] notFilter=new String[]{"sign-in.html","login.htm","sign-up.html","register.htm"};
		
		//请求的uri
		String strUrl=request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//当strUrl中包含DataPortal时才进行过滤
		if(strUrl.indexOf("DataPortal")!=-1)
		{
			//是否过滤
			boolean doFilter=true;
			for(String s:notFilter)
			{
				//如果uri中包含不过滤的uri，则不进行过滤
				if(strUrl.indexOf(s)!=-1)
				{
				doFilter=false;
				break;
				}
				
			}	
			
			if(doFilter)
			{
				//执行过滤
				//从session中获取登录者实体
				Object obj=request.getSession().getAttribute("loginedUser");
				if(null==obj)
				{
					//如果session中不存在登录者实体，则弹出提示重新登陆
					//设置request和response 的字符集，防止乱码
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out=response.getWriter();
					String loginPage="sign-in.html";
					
					StringBuilder builder=new StringBuilder();
					builder.append("<script type=\"text/javascript\">");
//					builder.append("alert('网页过期，请重新登录！');");
					builder.append("window.top.location.href='");
					builder.append(loginPage);
					builder.append("';");
					builder.append("</script>");
					out.print(builder.toString());
					
					
				}
				else {
					//如果不执行过滤，则继续
					filterChain.doFilter(request, response);
				}
			}
			else
			{
				//如果uri中不包含DataPortal，则继续
				filterChain.doFilter(request, response);
				
				
				
			}
		}
		

	}

}
