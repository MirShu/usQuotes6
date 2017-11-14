package com.quantpower.usquotes.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/1.
 */

public class MarketViewRollModel implements Serializable {
    private static final long serialVersionUID = -6849740916741934057L;

    /**
     * " "market_name": "美元白银",
     * "market_price": "16.68",
     * "market_float": "0.27%"
     */
    private String market_name;
    private String market_price;
    private String market_float;

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getMarket_float() {
        return market_float;
    }

    public void setMarket_float(String market_float) {
        this.market_float = market_float;
    }


    public static MarketViewRollModel parse(String result) {
        MarketViewRollModel marketViewRollModel = new MarketViewRollModel();
        try {
            marketViewRollModel = JSON.parseObject(result, MarketViewRollModel.class);
        } catch (Exception e) {
            String error = e.getMessage();

        }
        return marketViewRollModel;
    }

}
