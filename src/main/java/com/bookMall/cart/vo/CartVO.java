package com.bookMall.cart.vo;

import org.springframework.stereotype.Component;

@Component(value = "cartVO")
public class CartVO {
    private int cartId;
    private int goodsId;

    private String memberId;
    private String creDate;
    private int cartGoodsQty;

    private int goodsPrice;
    private int goodsDeliveryPrice;
    private int goodsSalesPrice;
    private int goodsPoint;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate;
    }

    public int getCartGoodsQty() {
        return cartGoodsQty;
    }

    public void setCartGoodsQty(int cartGoodsQty) {
        this.cartGoodsQty = cartGoodsQty;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsDeliveryPrice() {
        return goodsDeliveryPrice;
    }

    public void setGoodsDeliveryPrice(int goodsDeliveryPrice) {
        this.goodsDeliveryPrice = goodsDeliveryPrice;
    }

    public int getGoodsSalesPrice() {
        return goodsSalesPrice;
    }

    public void setGoodsSalesPrice(int goodsSalesPrice) {
        this.goodsSalesPrice = goodsSalesPrice;
    }

    public int getGoodsPoint() {
        return goodsPoint;
    }

    public void setGoodsPoint(int goodsPoint) {
        this.goodsPoint = goodsPoint;
    }
}
