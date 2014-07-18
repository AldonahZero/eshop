package com.shop.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.domain.Goods;
import com.shop.domain.ShoppingCart;
import com.shop.service.HGCartSer;
import com.shop.service.HGService;
import com.shop.service.impl.HGCartSerImpl;
import com.shop.service.impl.HGServiceImpl;

public class DelGoodsCon extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡsession����������Ϣ
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");
		//��ȡShoppingCart���û�������Ϣ
		Map<String, Goods> map = shoppingCart.getMap();
		
		boolean bool = false;//�ж��Ƿ�ɾ���ɹ�
		
		
		//��ȡҳ�洫�ݵ���Ϣ
		HGService service = new HGServiceImpl();
		HGCartSer cartSer = new HGCartSerImpl();
		//�������û�����ͬʱ��ʹ������
		String[] parameterValues = request.getParameterValues("del");
		//��ȡÿ��checkBox�е�valueֵ����user.id
		for (String temp : parameterValues) {
			bool = cartSer.deleteGoods(temp);
		}
		
		if(bool){
			session.setAttribute("shoppingCart", shoppingCart);
			//���������е�������ҳ�����չʾ
			request.getRequestDispatcher("/WEB-INF/jsps/shoppingCart.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "ɾ��ʧ��");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}