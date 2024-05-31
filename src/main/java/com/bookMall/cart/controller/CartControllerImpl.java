package com.bookMall.cart.controller;

import com.bookMall.cart.service.CartService;
import com.bookMall.cart.vo.CartVO;
import com.bookMall.goods.vo.GoodsVO;
import com.bookMall.member.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/cart")
@Controller(value = "cartController")
public class CartControllerImpl implements CartController {

    private static final Logger log = LoggerFactory.getLogger(CartControllerImpl.class);
    @Autowired
    CartService cartService;
    @Autowired
    CartVO cartVO;
    @Autowired
    MemberVO memberVO;

    @RequestMapping(value = "/addGoodsInCart.do", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
    public @ResponseBody Object addGoodsInCart(@RequestParam("goodsId") String goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("CartControllerImpl addGoodInCart goodsId : " + goods_id);
        HttpSession session = request.getSession();
        log.info("CartControllerImpl addGoodsInCart session : " + session.getId());
        memberVO = (MemberVO) session.getAttribute("memberInfo");
        log.info("CartControllerImpl addGoodsInCart memberVO : " + memberVO.getMemberId() + " " + memberVO.getMemberPw());
        String member_id = memberVO.getMemberId();
        log.info("CartControllerImpl addGoodsInCart member_id : " + member_id);
        cartVO.setMemberId(member_id);
        cartVO.setGoodsId(Integer.parseInt(goods_id));
        cartVO.setMemberId(member_id);
        boolean isAlreadyExisted = cartService.findCartGoods(cartVO);
        log.info("CartControllerImpl addGoodsInCart isAlreadyExisted : " + isAlreadyExisted);
        if (isAlreadyExisted) {
            return "alreadyExisted";
        } else {
            log.info("여기는 addGoodsInCart 동작이여");
            cartService.addGoodsInCart(cartVO);
            log.info("addSuccess");
            return "addSuccess";
        }
    }


    @Override
    @RequestMapping(value = "/myCartList.do", method = RequestMethod.GET)
    public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        log.info("CartControllerImpl myCartMain viewName : " + viewName);
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session = request.getSession();
        memberVO = (MemberVO) session.getAttribute("memberInfo");
        log.info("CartControllerImpl myCartMain memberVO : " + memberVO.getMemberId() + " " + memberVO.getMemberPw() + " " + memberVO.getMemberName());
        String memberId = memberVO.getMemberId();

        // goodsVO 객체 생성 및 필드 초기화
        GoodsVO goodsVO = new GoodsVO();
        String goodsId = String.valueOf(goodsVO.getGoodsId());
        log.info("CartControllerImpl myCartMain goodsId : " + goodsId);
        int goodsPrice = goodsVO.getGoodsPrice();
        log.info("CartControllerImpl myCartMain goodsPrice : " + goodsPrice);
        int goodsDeliveryPrice = goodsVO.getGoodsDeliveryPrice();
        log.info("CartControllerImpl myCartMain goodsDeliveryPrice : " + goodsDeliveryPrice);
        int goodsSalesPrice = goodsVO.getGoodsSalesPrice();
        log.info("CartControllerImpl myCartMain goodsSalesPrice : " + goodsSalesPrice);

        log.info("CartControllerImpl myCartMain memberId : " + memberId);
        cartVO.setMemberId(memberId);
        log.info("CartControllerImpl myCartMain cartVO.setMemberId(memberId) : " + cartVO.getMemberId());
        log.info("여기까지는 동작합니다1.");

        // 필요한 값들을 cartVO에 설정
        cartVO.setGoodsPrice(goodsPrice);
        cartVO.setGoodsDeliveryPrice(goodsDeliveryPrice);
        cartVO.setGoodsSalesPrice(goodsSalesPrice);

        Map<String, List> cartMap = cartService.myCartList(cartVO);
        log.info("여기까지는 동작합니다2.");
        log.info("CartControllerImpl myCartMain cartMap : " + cartMap);
        session.setAttribute("cartMap", cartMap);
        return mav;
    }


    @Override
    @RequestMapping(value = "/removeCartGoods.do", method = RequestMethod.POST)
    public ModelAndView removeCartGoods(@RequestParam("cartId") int cart_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        log.info("CartControllerImpl removeCartGoods cartId : " + cart_id);
        cartService.removeCartGoods(cart_id);
        log.info("카트에서 " + cart_id + " 삭제됨");
        mav.setViewName("redirect:/cart/myCartList.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/modifyCartQty.do", method = RequestMethod.POST)
    public @ResponseBody String modifyCartQty(@RequestParam("goodsId") int goods_id, @RequestParam("cartGoodsQty") int cart_goods_qty, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        memberVO = (MemberVO) session.getAttribute("memberInfo");
        String member_id = memberVO.getMemberId();
        cartVO.setGoodsId(goods_id);
        cartVO.setMemberId(member_id);
        cartVO.setCartGoodsQty(cart_goods_qty);
        boolean result = cartService.modifyCartQty(cartVO);

        if (result) {
            return "modifySuccess";
        } else {
            return "modifyFailed";
        }
    }
}
