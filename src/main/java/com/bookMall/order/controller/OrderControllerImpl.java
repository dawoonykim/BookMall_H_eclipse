package com.bookMall.order.controller;

import com.bookMall.common.base.BaseController;
import com.bookMall.goods.vo.GoodsVO;
import com.bookMall.member.vo.MemberVO;
import com.bookMall.order.service.OrderService;
import com.bookMall.order.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "orderControllerImpl")
@RequestMapping(value = "/order")
public class OrderControllerImpl extends BaseController implements OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderVO orderVO;

    @Override
    @RequestMapping(value = "/orderEachGoods.do")
    public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO order_VO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

//        log.info("OrderControllerImpl orderEachGoods order_VO : " + order_VO.toString());

        Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");
        log.info("OrderControllerImpl orderEachGoods isLogOn : " + isLogOn);
        String action = (String) session.getAttribute("action");
        log.info("OrderControllerImpl orderEachGoods action : " + action);
        if (isLogOn == null || !isLogOn) {
            orderVO = order_VO;
            session.setAttribute("orderInfo", orderVO);
            session.setAttribute("action", "/order/orderEachGoods.do");
            return new ModelAndView("redirect:/member/loginForm.do");
        } else {
            if (action != null && action.equals("/order/orderEachGoods.do")) {
                orderVO = (OrderVO) session.getAttribute("orderInfo");
                session.removeAttribute("action");
            } else {
                orderVO = order_VO;
            }
        }

        int goodsPrice = 0;
        int goodsDeliveryPrice = 0;
        int goodsPoint = 0;
        Map cartMap = (Map) session.getAttribute("cartMap");

        GoodsVO goodsVO = null;

        if (cartMap != null) {
            List<GoodsVO> myGoodsList = (List<GoodsVO>) cartMap.get("myGoodsList");
            if (myGoodsList != null && !myGoodsList.isEmpty()) {
                int index = Integer.parseInt(request.getParameter("index"));
                if (index >= 0 && index < myGoodsList.size()) {
                    goodsVO = myGoodsList.get(index);
                    if (goodsVO != null) {
                        goodsPrice = goodsVO.getGoodsPrice();
                        goodsDeliveryPrice = goodsVO.getGoodsDeliveryPrice();
                        goodsPoint = goodsVO.getGoodsPoint();
                        log.info("OrderControllerImpl orderEachGoods orderVO |" + " orderVO.goodsId : " + orderVO.getGoodsId() + ", goodsPrice : " + goodsVO.getGoodsPrice() + ", goodsDeliveryPrice : " + goodsVO.getGoodsDeliveryPrice() + ", goodsPoint : " + goodsVO.getGoodsPoint());
                    }
                }
            }
        } else {
            goodsVO = (GoodsVO) session.getAttribute("goodsInfo");
            goodsPrice = goodsVO.getGoodsPrice();
            goodsDeliveryPrice = goodsVO.getGoodsDeliveryPrice();
            goodsPoint = goodsVO.getGoodsPoint();
            log.info("OrderControllerImpl orderEachGoods goodsVO |" + " goodsVO.goodsId : " + goodsVO.getGoodsId() + ", goodsPrice : " + goodsVO.getGoodsPrice() + ", goodsDeliveryPrice : " + goodsVO.getGoodsDeliveryPrice() + ", goodsPoint : " + goodsVO.getGoodsPoint());
        }

        log.info("OrderControllerImpl orderEachGoods goodsPrice : " + goodsPrice);
        log.info("OrderControllerImpl orderEachGoods goodsDeliveryPrice : " + goodsDeliveryPrice);
        log.info("OrderControllerImpl orderEachGoods goodsPoint : " + goodsPoint);

        orderVO.setGoodsPrice(goodsPrice);
        orderVO.setGoodsDeliveryPrice(goodsDeliveryPrice);
        orderVO.setGoodsPoint(goodsPoint);

        String viewName = (String) request.getAttribute("viewName");
        log.info("OrderControllerImpl orderEachGoods viewName : " + viewName);
        ModelAndView mav = new ModelAndView(viewName);

        List myOrderList = new ArrayList<OrderVO>();
        log.info("OrderControllerImpl orderEachGoods myOrderList : " + myOrderList);
        myOrderList.add(orderVO);

        MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");
        log.info("OrderControllerImpl orderEachGoods memberInfo : " + memberInfo.getMemberId() + " " + memberInfo.getMemberPw() + " " + memberInfo.getMemberName());

        session.setAttribute("myOrderList", myOrderList);
        session.setAttribute("orderer", memberInfo);
        return mav;
    }

    @Override
    @RequestMapping(value = "/orderAllCartGoods.do", method = RequestMethod.POST)
    public ModelAndView orderAllCartGoods(@RequestParam("h_order_each_goods_qty") String hOrderEachGoodsQty, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        log.info("OrderControllerImpl orderAllCartGoods viewName : " + viewName);
        ModelAndView mav = new ModelAndView(viewName);
        log.info("OrderControllerImpl orderAllCartGoods mav : " + mav);
        HttpSession session = request.getSession();
        Map cartMap = (Map) session.getAttribute("cartMap");
        log.info("OrderControllerImpl orderAllCartGoods cartMap : " + cartMap);
        List myOrderList = new ArrayList<OrderVO>();
        log.info("OrderControllerImpl orderAllCartGoods myOrderList : " + myOrderList);

        List<GoodsVO> myGoodsList = (List<GoodsVO>) cartMap.get("myGoodsList");
        log.info("OrderControllerImpl orderAllCartGoods myGoodsList : " + myGoodsList.toString());
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        log.info("OrderControllerImpl orderAllCartGoods memberVO : " + memberVO.getMemberId() + " " + memberVO.getMemberPw() + " " + memberVO.getMemberName());

        String [] cartGoodsQty = hOrderEachGoodsQty.split(",");
        for (int i = 0; i < cartGoodsQty.length; i++) {
            String[] cart_goods = cartGoodsQty[i].split(":");
            for (int j = 0; j < myGoodsList.size(); j++) {
                GoodsVO goodsVO = myGoodsList.get(j);
                log.info("OrderControllerImpl orderAllCartGoods goodsVO.getGoodsFileName : " + goodsVO.getGoodsFileName());
                int goods_id = goodsVO.getGoodsId();
                log.info("OrderControllerImpl orderAllCartGoods goods_id : " + goods_id);
                if (goods_id == Integer.parseInt(cart_goods[0])) {
                    OrderVO orderVO1 = new OrderVO();
                    String goods_title = goodsVO.getGoodsTitle();
                    log.info("OrderControllerImpl orderAllCartGoods goods_title : " + goods_title);
                    int goodsPrice = goodsVO.getGoodsPrice();
                    log.info("OrderControllerImpl orderAllCartGoods goods_price : " + goodsPrice);
                    int goodsDeliveryPrice = goodsVO.getGoodsDeliveryPrice();
                    log.info("OrderControllerImpl orderAllCartGoods goods_delivery_price : " + goodsDeliveryPrice);
                    int goodsPoint = goodsVO.getGoodsPoint();
                    log.info("OrderControllerImpl orderAllCartGoods goods_point : " + goodsPoint);
                    int goodsSalesPrice = goodsVO.getGoodsSalesPrice();
                    log.info("OrderControllerImpl orderAllCartGoods goods_sales_price : " + goodsSalesPrice);
                    String goodsFileName = goodsVO.getGoodsFileName();
                    log.info("OrderControllerImpl orderAllCartGoods goods_fileName : " + goodsFileName);

                    orderVO1.setGoodsId(goods_id);
                    orderVO1.setGoodsTitle(goods_title);
                    orderVO1.setGoodsSalesPrice(goodsSalesPrice);
                    orderVO1.setGoodsPrice(goodsPrice);
                    orderVO1.setGoodsPoint(goodsPoint);
                    orderVO1.setGoodsDeliveryPrice(goodsDeliveryPrice);
                    orderVO1.setGoodsFileName(goodsFileName);
                    orderVO1.setOrderGoodsQty(Integer.parseInt(cart_goods[1]));
                    myOrderList.add(orderVO1);
                    break;
                }
            }
        }

        session.setAttribute("myOrderList", myOrderList);
        log.info("OrderControllerImpl orderAllCartGoods myOrderList : " + myOrderList);
        session.setAttribute("orderer", memberVO);
        log.info("OrderControllerImpl orderAllCartGoods memberVO : " + memberVO.getMemberId() + " " + memberVO.getMemberPw() + " " + memberVO.getMemberName());
        return mav;
    }

    @Override
    @RequestMapping(value = "/payToOrderGoods.do", method = RequestMethod.POST)
    public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        log.info("OrderControllerImpl payToOrderGoods viewName : " + viewName);
        ModelAndView mav = new ModelAndView(viewName);
        log.info("OrderControllerImpl payToOrderGoods mav : " + mav);

        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("orderer");
        String member_id = memberVO.getMemberId();
        log.info("OrderControllerImpl payToOrderGoods member_id : " + member_id);
        String orderer_name = memberVO.getMemberName();
        log.info("OrderControllerImpl payToOrderGoods orderer_name : " + orderer_name);
        String orderer_hp = memberVO.getHp1() + "-" + memberVO.getHp2() + "-" + memberVO.getHp3();
        log.info("OrderControllerImpl payToOrderGoods orderer_hp : " + orderer_hp);

        List<OrderVO> myOrderList = (List<OrderVO>) session.getAttribute("myOrderList");
        log.info("OrderControllerImpl payToOrderGoods myOrderList : " + myOrderList);

        for (int i = 0; i < myOrderList.size(); i++) {
            OrderVO orderVO = myOrderList.get(i);
            orderVO.setMemberId(member_id);
            orderVO.setOrdererName(orderer_name);
            orderVO.setReceiverName(receiverMap.get("receiverName"));

            orderVO.setReceiverHp1(receiverMap.get("receiverHp1"));
            orderVO.setReceiverHp2(receiverMap.get("receiverHp2"));
            orderVO.setReceiverHp3(receiverMap.get("receiverHp3"));
            orderVO.setReceiverTel1(receiverMap.get("receiverTel1"));
            orderVO.setReceiverTel2(receiverMap.get("receiverTel2"));
            orderVO.setReceiverTel3(receiverMap.get("receiverTel3"));

            orderVO.setDeliveryAddress(receiverMap.get("deliveryAddress"));
            orderVO.setDeliveryMessage(receiverMap.get("deliveryMessage"));
            orderVO.setDeliveryMethod(receiverMap.get("deliveryMethod"));
            orderVO.setGiftWrapping(receiverMap.get("giftWrapping"));
            orderVO.setPayMethod(receiverMap.get("payMethod"));
            orderVO.setCardComName(receiverMap.get("cardComName"));
            orderVO.setCardPayMonth(receiverMap.get("cardPayMonth"));
            orderVO.setPayOrdererHpNum(receiverMap.get("payOrdererHpNum"));
            orderVO.setOrdererHp(orderer_hp);

            myOrderList.set(i, orderVO);

        }
        log.info("OrderControllerImpl payToOrderGoods myOrderList : " + myOrderList);
        log.info("orderService.addNewOrder(myOrderList); 작동 전");
        orderService.addNewOrder(myOrderList);
        log.info("orderService.addNewOrder(myOrderList); 작동 후");

        mav.addObject("myOrderInfo", receiverMap);
        mav.addObject("myOrderList", myOrderList);

        return mav;
    }
}
