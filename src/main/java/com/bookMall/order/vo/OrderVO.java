package com.bookMall.order.vo;

import org.springframework.stereotype.Component;

@Component("orderVO")
public class OrderVO {
    private int orderSeqNum;
    private int orderId;
    private String memberId;
    private int goodsId;
    private String ordererName;
    private String goodsTitle;
    private int orderGoodsQty;
    private int goodsSalesPrice;
    private String goodsFileName;
    private String receiverName;
    private String receiverHp1;
    private String receiverHp2;
    private String receiverHp3;
    private String receiverTel1;
    private String receiverTel2;
    private String receiverTel3;
    private String deliveryAddress;
    private String deliveryMethod;
    private String deliveryMessage;
    private String giftWrapping;
    private String payMethod;
    private String cardComName;
    private String cardPayMonth;
    private String payOrdererHpNum;
    private String deliveryState;
    private String payOrderTime;
    private String ordererHp;


    private int totalGoodsPrice;
    private String finalTotalPrice;
    private int goodsQty;
    private int cartGoodsQty;


    private int goodsPrice;
    private int goodsPoint;
    private int goodsDeliveryPrice;

    public int getOrderSeqNum() {
        return orderSeqNum;
    }

    public void setOrderSeqNum(int orderSeqNum) {
        this.orderSeqNum = orderSeqNum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrdererName() {
        return ordererName;
    }

    public void setOrdererName(String ordererName) {
        this.ordererName = ordererName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public int getOrderGoodsQty() {
        return orderGoodsQty;
    }

    public void setOrderGoodsQty(int orderGoodsQty) {
        this.orderGoodsQty = orderGoodsQty;
    }

    public int getGoodsSalesPrice() {
        return goodsSalesPrice;
    }

    public void setGoodsSalesPrice(int goodsSalesPrice) {
        this.goodsSalesPrice = goodsSalesPrice;
    }

    public String getGoodsFileName() {
        return goodsFileName;
    }

    public void setGoodsFileName(String goodsFileName) {
        this.goodsFileName = goodsFileName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverHp1() {
        return receiverHp1;
    }

    public void setReceiverHp1(String receiverHp1) {
        this.receiverHp1 = receiverHp1;
    }

    public String getReceiverHp2() {
        return receiverHp2;
    }

    public void setReceiverHp2(String receiverHp2) {
        this.receiverHp2 = receiverHp2;
    }

    public String getReceiverHp3() {
        return receiverHp3;
    }

    public void setReceiverHp3(String receiverHp3) {
        this.receiverHp3 = receiverHp3;
    }

    public String getReceiverTel1() {
        return receiverTel1;
    }

    public void setReceiverTel1(String receiverTel1) {
        this.receiverTel1 = receiverTel1;
    }

    public String getReceiverTel2() {
        return receiverTel2;
    }

    public void setReceiverTel2(String receiverTel2) {
        this.receiverTel2 = receiverTel2;
    }

    public String getReceiverTel3() {
        return receiverTel3;
    }

    public void setReceiverTel3(String receiverTel3) {
        this.receiverTel3 = receiverTel3;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDeliveryMessage() {
        return deliveryMessage;
    }

    public void setDeliveryMessage(String deliveryMessage) {
        this.deliveryMessage = deliveryMessage;
    }

    public String getGiftWrapping() {
        return giftWrapping;
    }

    public void setGiftWrapping(String giftWrapping) {
        this.giftWrapping = giftWrapping;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getCardComName() {
        return cardComName;
    }

    public void setCardComName(String cardComName) {
        this.cardComName = cardComName;
    }

    public String getCardPayMonth() {
        return cardPayMonth;
    }

    public void setCardPayMonth(String cardPayMonth) {
        this.cardPayMonth = cardPayMonth;
    }

    public String getPayOrdererHpNum() {
        return payOrdererHpNum;
    }

    public void setPayOrdererHpNum(String payOrdererHpNum) {
        this.payOrdererHpNum = payOrdererHpNum;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getPayOrderTime() {
        return payOrderTime;
    }

    public void setPayOrderTime(String payOrderTime) {
        this.payOrderTime = payOrderTime;
    }

    public String getOrdererHp() {
        return ordererHp;
    }

    public void setOrdererHp(String ordererHp) {
        this.ordererHp = ordererHp;
    }

    public int getTotalGoodsPrice() {
        return totalGoodsPrice;
    }

    public void setTotalGoodsPrice(int totalGoodsPrice) {
        this.totalGoodsPrice = totalGoodsPrice;
    }

    public String getFinalTotalPrice() {
        return finalTotalPrice;
    }

    public void setFinalTotalPrice(String finalTotalPrice) {
        this.finalTotalPrice = finalTotalPrice;
    }

    public int getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(int goodsQty) {
        this.goodsQty = goodsQty;
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

    public int getGoodsPoint() {
        return goodsPoint;
    }

    public void setGoodsPoint(int goodsPoint) {
        this.goodsPoint = goodsPoint;
    }

    public int getGoodsDeliveryPrice() {
        return goodsDeliveryPrice;
    }

    public void setGoodsDeliveryPrice(int goodsDeliveryPrice) {
        this.goodsDeliveryPrice = goodsDeliveryPrice;
    }
}
