package com.androidstudy.daraja.constants;

public enum TransactionType {

    CustomerPayBillOnline {
        @Override
        public String toString() {
            return Transtype.TRANSACTION_TYPE_CUSTOMER_PAYBILL_ONLINE;
        }
    },

    CustomerBuyGoodsOnline{
        @Override
        public String toString() {
            return Transtype.TRANSACTION_TYPE_CUSTOMER_BUY_GOODS;
        }
    }
}
